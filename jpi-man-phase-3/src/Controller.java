import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class is the Controller part of Project
 * This class includes main handling and core of Project
 */
public class Controller {
    private Model model;
    private View view;
    //Toggle sidebar flag
    private boolean isToggled = false;
    //Full Screen flag
    private boolean isFullScreened = false;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.visible();
        setupLoadRequests();
        setupFunctionalityForMenuBar();
        setupSendButton();
        setupUrlTextField();
        setupNewRequestButton();
        setupCopyToClipBoardButton();
        setupFilterSearch();
        setupMethodsComboBox();
        setupExitOperation();
        setupOptionMenu();
        loadOptionMenuSaves();
    }

    /**
     * This is a method to load saved requests
     */
    public void setupLoadRequests() {
        for (RequestClass requestClass : model.getRequestsList()) {
            RequestPanel requestPanel = new RequestPanel(requestClass.getRequestName(), view.getLeftSide().getAllTheRequestsPanel());
            requestPanel.setRequestClass(requestClass);
            requestPanel.getKindOFRequestLabel().setText(requestClass.getMethod()+"  ");
            view.getLeftSide().getAllTheRequestsPanel().add(requestPanel);
        }
    }

    /**
     * This is a method to setup option window functionality
     */
    public void setupOptionMenu() {
        view.getMenuBar().getOptionFrame().getPurpleTheme().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //Change Theme To Purple Mode
                    changeTheme("PURPLE");
                } else {
                    //Change Theme To Chocolate Mode
                    changeTheme("CHOCOLATE");
                }
            }
        });
    }

    /**
     * This is a method to load options window saves
     */
    private void loadOptionMenuSaves() {
        if (Files.exists(Paths.get("./saves/OptionSave.txt"))) {
            //System.out.println("hello");
            try (FileReader reader = new FileReader("./saves/OptionSave.txt")) {
                if ((char) reader.read() == 'y') {
                    makeItFullScreen();
                    isFullScreened = true;
                } else {
                    //makeItNormalScreen();
                    isFullScreened = false;
                }
                if ((char) reader.read() == 'y') {
                    view.getMenuBar().getOptionFrame().getIsFollowRedirect().setSelected(true);
                } else {
                    view.getMenuBar().getOptionFrame().getIsFollowRedirect().setSelected(false);
                }
                if ((char) reader.read() == 'y') {
                    view.getMenuBar().getOptionFrame().getIsClosedIntoSystemTray().setSelected(true);
                } else {
                    view.getMenuBar().getOptionFrame().getIsClosedIntoSystemTray().setSelected(false);
                }
                if ((char) reader.read() == 'p') {
                    view.getMenuBar().getOptionFrame().getPurpleTheme().setSelected(true);
                } else {
                    view.getMenuBar().getOptionFrame().getChocolateTheme().setSelected(true);
                }
            } catch (IOException e) {

            }
        } else {
            //if there is not that file
            File file = new File("./saves/OptionSave.txt");
            try (FileWriter writer = new FileWriter("./saves/OptionSave.txt")) {
                writer.write('n');
                writer.write('n');
                writer.write('n');
                writer.write('p');
            } catch (IOException e) {

            }
        }
    }

    /**
     * This is a method to change theme between purple and chocolate
     */
    private void changeTheme(String mode) {
        if (mode.equals("PURPLE")) {
            view.getLeftSide().getPanel().setBackground(ColorsForThemes.purpleBack);
            view.getMiddleSide().getPanel().setBackground(ColorsForThemes.purpleBack);
            view.getRightSide().getPanel().setBackground(ColorsForThemes.purpleBack);
            view.getLeftSide().getAllTheRequestsPanel().setBackground(ColorsForThemes.purpleFore);
            view.getMiddleSide().getRequestTabs().getTabs().setBackground(ColorsForThemes.purpleFore);
            view.getRightSide().getHeaderBodyResponse().setBackground(ColorsForThemes.purpleFore);
            view.getRightSide().getStatusPanel().setBackground(ColorsForThemes.purpleBack);
            view.getRightSide().getPreviewPanel().setBackground(ColorsForThemes.purpleFore);
            view.getRightSide().getMessageTextArea().setBackground(ColorsForThemes.purpleFore);
            view.getMiddleSide().getRequestTabs().getFormDataPanel().setBackground(ColorsForThemes.purpleFore);
            view.getMiddleSide().getRequestTabs().getHeaderPanel().setBackground(ColorsForThemes.purpleFore);
            view.getMiddleSide().getRequestTabs().getButtonPanel().setBackground(ColorsForThemes.purpleFore);
            view.getMiddleSide().getRequestTabs().getNewFormDataButtonPanel().setBackground(ColorsForThemes.purpleFore);
            HeaderElementPanel.colorOfPanel = ColorsForThemes.purpleFore;
            FormDataElementPanel.colorOfPanel = ColorsForThemes.purpleFore;
            HeaderResponseElement.colorOfPanel = ColorsForThemes.purpleFore;
            RequestPanel.colorOfPanel = ColorsForThemes.purpleFore;
            RequestPanel.mouseFocusedColor = ColorsForThemes.colorFocusedMouseReqPanelPurple;
            RequestPanel.selectedPanelColor = ColorsForThemes.selectedPanelPurple;
            RequestPanel.defaultColor = ColorsForThemes.purpleFore;
        } else {
            view.getLeftSide().getPanel().setBackground(ColorsForThemes.BrownBack);
            view.getMiddleSide().getPanel().setBackground(ColorsForThemes.BrownBack);
            view.getRightSide().getPanel().setBackground(ColorsForThemes.BrownBack);
            view.getLeftSide().getAllTheRequestsPanel().setBackground(ColorsForThemes.BrownFore);
            view.getMiddleSide().getRequestTabs().getTabs().setBackground(ColorsForThemes.BrownFore);
            view.getRightSide().getHeaderBodyResponse().setBackground(ColorsForThemes.BrownFore);
            view.getRightSide().getStatusPanel().setBackground(ColorsForThemes.BrownBack);
            view.getRightSide().getPreviewPanel().setBackground(ColorsForThemes.BrownFore);
            view.getRightSide().getMessageTextArea().setBackground(ColorsForThemes.BrownFore);
            view.getMiddleSide().getRequestTabs().getFormDataPanel().setBackground(ColorsForThemes.BrownFore);
            view.getMiddleSide().getRequestTabs().getHeaderPanel().setBackground(ColorsForThemes.BrownFore);
            view.getMiddleSide().getRequestTabs().getButtonPanel().setBackground(ColorsForThemes.BrownFore);
            view.getMiddleSide().getRequestTabs().getNewFormDataButtonPanel().setBackground(ColorsForThemes.BrownFore);
            HeaderElementPanel.colorOfPanel = ColorsForThemes.BrownFore;
            FormDataElementPanel.colorOfPanel = ColorsForThemes.BrownFore;
            HeaderResponseElement.colorOfPanel = ColorsForThemes.BrownFore;
            RequestPanel.colorOfPanel = ColorsForThemes.BrownFore;
            RequestPanel.mouseFocusedColor = ColorsForThemes.colorFocusedMouseReqPanelBrown;
            RequestPanel.selectedPanelColor = ColorsForThemes.selectedPanelBrown;
            RequestPanel.defaultColor = ColorsForThemes.BrownFore;
        }
        Component[] reqPanels = view.getLeftSide().getAllTheRequestsPanel().getComponents();
        for (Component component : reqPanels) {
            component.setBackground(RequestPanel.colorOfPanel);
            if (((RequestPanel) component).getIsSelected()) {
                component.setBackground(RequestPanel.selectedPanelColor);
            }
        }
        Component[] headerPanels = view.getMiddleSide().getRequestTabs().getHeaderPanel().getComponents();
        for (Component component : headerPanels) {
            component.setBackground(HeaderElementPanel.colorOfPanel);
        }
        Component[] formDataPanels = view.getMiddleSide().getRequestTabs().getFormDataPanel().getComponents();
        for (Component component : formDataPanels) {
            component.setBackground(FormDataElementPanel.colorOfPanel);
        }
        Component[] headerResponsePanels = view.getRightSide().getHeaderBodyResponse().getElements().getComponents();
        for (Component component : headerResponsePanels) {
            component.setBackground(HeaderResponseElement.colorOfPanel);
            ((HeaderResponseElement) component).getValue().setBackground(HeaderElementPanel.colorOfPanel);
            ((HeaderResponseElement) component).getKey().setBackground(HeaderElementPanel.colorOfPanel);
        }
        view.getMainFrame().validate();
        saveOptionsSaves();
    }

    /**
     * This is a method to setup exit functionality
     */
    public void setupExitOperation() {
        view.getMainFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //if system tray is off
                if (!view.getMenuBar().getOptionFrame().getIsClosedIntoSystemTray().isSelected()) {
                    int result = JOptionPane.showConfirmDialog(view.getMainFrame(), "Are you sure you want to exit the application?", "Exit Application", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        //view.getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        System.exit(0);
                    }
                } else {
                    view.getMainFrame().setVisible(false);
                }
            }
        });
    }

    /**
     * This is a method to setup kind of requests functionality
     */
    public void setupMethodsComboBox() {
        view.getMiddleSide().getMethodsComboBox().addActionListener(new ComboBoxActionListener());
    }

    /**
     * This is a method to setup MenuBar functionality
     */
    public void setupFunctionalityForMenuBar() {
        view.getMenuBar().getSaveOutput().addActionListener(new SaveOutputActionListener());
        view.getMenuBar().getSaveRequest().addActionListener(new SaveRequestActionListener());
        view.getMenuBar().getExit().addActionListener(new ExitActionListener());
        view.getMenuBar().getAbout().addActionListener(new AboutActionListener());
        view.getMenuBar().getToggleSidebar().addActionListener(new ToggleSidebarActionListener());
        view.getMenuBar().getToggleFullScreen().addActionListener(new FullScreenActionListener());
        view.getMenuBar().getHelpItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpString="Help guide for JPI Man \n\n"+
                        "At first you need to create a request by pressing + button at the left side\n"+
                        "then click on the request to be selected\n"+
                        "then you should enter url and select your request method\n"+
                        "after that you can also add form data and header to your body of the request\n"+
                        "in the middle of the program\n"+
                        "then you should click send button\n"+
                        "you can see your response message and preview and response headers on the right side\n"+
                        "Also you can set follow redirect on or off in options menu\n"+
                        "and you can change theme of program in options menu\n"+
                        "and also you can exit to system tray if you turn the option on in options menu\n\n\n"+
                        "We wish you best experience :)";
                JOptionPane.showMessageDialog(view.getMainFrame(), helpString,"Help",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        view.getMenuBar().getOptions().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getMenuBar().getOptionFrame().setVisible(true);
            }
        });
        view.getMenuBar().getOptionFrame().getIsClosedIntoSystemTray().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    saveOptionsSaves();
                } else {
                    saveOptionsSaves();
                }
            }
        });
        view.getMenuBar().getOptionFrame().getIsFollowRedirect().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    saveOptionsSaves();
                } else {
                    saveOptionsSaves();
                }
            }
        });
    }

    /**
     * This is a method to save Options window changes
     */
    private void saveOptionsSaves() {
        try (FileWriter writer = new FileWriter("./saves/OptionSave.txt")) {
            if (isFullScreened) {
                writer.write('y');
            } else {
                writer.write('n');
            }
            if (view.getMenuBar().getOptionFrame().getIsFollowRedirect().isSelected()) {
                writer.write('y');
            } else {
                writer.write('n');
            }
            if (view.getMenuBar().getOptionFrame().getIsClosedIntoSystemTray().isSelected()) {
                writer.write('y');
            } else {
                writer.write('n');
            }
            if (view.getMenuBar().getOptionFrame().getPurpleTheme().isSelected()) {
                writer.write('p');
            } else {
                writer.write('c');
            }
        } catch (IOException exception) {

        }
    }

    /**
     * This is a method to setup send button functionality
     */
    public void setupSendButton() {
        view.getMiddleSide().getSendButton().addMouseListener(new SendMouseListener(view.getMiddleSide().getSendButton()));
        view.getMiddleSide().getSendButton().addActionListener(new ClickActionListener(view.getMiddleSide().getUrlTextField(), view.getRightSide().getEditorPane()));
    }

    /**
     * This is a method to setup url text field functionality
     */
    public void setupUrlTextField() {
        Color defaultColor = view.getMiddleSide().getUrlTextField().getBackground();
        view.getMiddleSide().getUrlTextField().addFocusListener(new UrlTextFieldFocusListener(view.getMiddleSide().getUrlTextField(), defaultColor));
        view.getMiddleSide().getUrlTextField().addActionListener(new ClickActionListener(view.getMiddleSide().getUrlTextField(), view.getRightSide().getEditorPane()));
    }

    /**
     * This is a method to setup option window functionality
     */
    public void setupFilterSearch() {
        view.getLeftSide().getFilterSearch().getDocument().addDocumentListener(new SearchFocusListener());
    }

    /**
     * This is a method to setup New Request button functionality
     */
    public void setupNewRequestButton() {
        JButton button = view.getLeftSide().getNewRequestButton();
        button.addActionListener(new newRequestActionListener());
        button.addMouseListener(new NewRequestMouseListener(button));
    }

    /**
     * This is a method to setup copy to clipboard functionality
     */
    public void setupCopyToClipBoardButton() {
        view.getRightSide().getHeaderBodyResponse().getCopyToClipBoard().addActionListener(new CopyClipboardActionListener());
    }

    /**
     * Action Listener for Copy To Clipboard
     */
    private class CopyClipboardActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Component[] components = view.getRightSide().getHeaderBodyResponse().getElements().getComponents();
            String headerResponseToText = "";
            for (Component component : components) {
                headerResponseToText += ((HeaderResponseElement) component).getKey().getText() + ": " + ((HeaderResponseElement) component).getValue().getText() + "\n";
            }
            StringSelection stringSelection = new StringSelection(headerResponseToText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }

    /**
     * Mouse Adaptor for New Request Button
     */
    private class NewRequestMouseListener extends MouseAdapter {
        private JButton button;

        public NewRequestMouseListener(JButton button) {
            this.button = button;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(Color.green);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(new Color(255, 198, 50));
        }

    }

    /**
     * Action Listener for Exit
     */
    private class ExitActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!view.getMenuBar().getOptionFrame().getIsClosedIntoSystemTray().isSelected()) {
                int result = JOptionPane.showConfirmDialog(view.getMainFrame(), "Are you sure you want to exit the application?", "Exit Application", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    //view.getMainFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
                }
            } else {
                view.getMainFrame().setVisible(false);
            }
        }
    }

    /**
     * Action Listener for Save Request menubar clicked
     */
    private class SaveRequestActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (RequestPanel.selectedPanel != null) {
                RequestClass requestClass = RequestPanel.selectedPanel.getRequestClass();
                ///Updating request data
                //update url
                try {
                    requestClass.setUrl(new URL(view.getMiddleSide().getUrlTextField().getText()));
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                requestClass.setMethod(String.valueOf(view.getMiddleSide().getMethodsComboBox().getSelectedItem()));
                updateRequestBody(requestClass);
                //Save
                requestClass.save();
                JOptionPane.showMessageDialog(view.getMainFrame(),"Successfully saved saved in './requests' !","",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view.getMainFrame(), "Please select a request first!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This is a method to update UI inputs to request class
     * @param requestClass
     */
    public void updateRequestBody(RequestClass requestClass) {
        //update header body
        String headerString = "";
        Component[] headerData = view.getMiddleSide().getRequestTabs().getHeaderPanel().getComponents();
        for (Component header : headerData) {
            if (header.getClass().getName().equals("HeaderElementPanel")) {
                if(((HeaderElementPanel) header).getIsActive().isSelected()) {
                    headerString += ((HeaderElementPanel) header).getKey().getText() + ":" + ((HeaderElementPanel) header).getValue().getText() + ";";
                }
            }
        }
        if (headerString.isEmpty()) {
            requestClass.setHasHeaderBody(false);
            requestClass.setHeaderBody(null);
        } else {
            headerString = headerString.substring(0, headerString.length() - 1);
            headerString = "\"" + headerString + "\"";
//            System.out.println(headerString);
            requestClass.setHasHeaderBody(true);
            requestClass.setHeaderBody(headerString);
        }
        //update form body
        String formString = "";
        Component[] formData = view.getMiddleSide().getRequestTabs().getFormDataPanel().getComponents();
        for (Component form : formData) {
            if (form.getClass().getName().equals("FormDataElementPanel")) {
                if(((FormDataElementPanel) form).getIsActive().isSelected()) {
                    formString += ((FormDataElementPanel) form).getKey().getText() + "=" + ((FormDataElementPanel) form).getValue().getText() + "&";
                }
            }
        }
        if (formString.isEmpty()) {
            requestClass.setHasFormData(false);
            requestClass.setFormData(null);
        } else {
            formString = formString.substring(0, formString.length() - 1);
            formString = "\"" + formString + "\"";
//            System.out.println(formString);
            requestClass.setHasFormData(true);
            requestClass.setFormData(formString);
        }
    }

    /**
     * Action Listener for Combobox
     */
    private class ComboBoxActionListener implements ActionListener {
        private JComboBox comboBox = view.getMiddleSide().getMethodsComboBox();

        @Override
        public void actionPerformed(ActionEvent e) {
            if (RequestPanel.isAnyOneSelected) {
                String kind = "GET ";
                switch (view.getMiddleSide().getMethodsComboBox().getSelectedItem().toString()) {
                    case "GET":
                        kind = "GET      ";
                        break;
                    case "POST":
                        kind = "POST     ";
                        break;
                    case "DELETE":
                        kind = "DELETE   ";
                        break;
                    case "PUT":
                        kind = "PUT      ";
                        break;
                    case "PATCH":
                        kind = "PATCH    ";
                        break;
                }
                RequestPanel.selectedPanel.getKindOFRequestLabel().setText(kind);

            } else {
                JOptionPane.showMessageDialog(view.getMainFrame(), "You should select or create a request at first!", "ERROR", JOptionPane.ERROR_MESSAGE);
                comboBox.setSelectedItem("GET");
            }
        }
    }

    /**
     * ActionListener for save output menubar click
     */
    private class SaveOutputActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(RequestPanel.selectedPanel!=null){
                    RequestPanel.selectedPanel.getRequestClass().saveOutput();
                    JOptionPane.showMessageDialog(view.getMainFrame(),"Successfully saved in './Responses_Output' !","",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(view.getMainFrame(),"Please select a request first!","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception ee){
                ee.printStackTrace();
                JOptionPane.showMessageDialog(view.getMainFrame(),"ERROR!","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Action Listener for send click
     */
    private class ClickActionListener implements ActionListener {
        private JEditorPane editorPane;
        private JTextField textField;

        public ClickActionListener(JTextField textField, JEditorPane editorPane) {
            this.textField = textField;
            this.editorPane = editorPane;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (RequestPanel.selectedPanel != null) {
                RequestClass requestClass=RequestPanel.selectedPanel.getRequestClass();
                String url=view.getMiddleSide().getUrlTextField().getText();
                if(!url.contains("http")){
                    url="https://"+url;
                }
                try{
                    requestClass.setUrl(new URL(url));
                }catch (Exception eeee){
                    JOptionPane.showMessageDialog(view.getMainFrame(),"Invalid URL!","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                requestClass.setMethod(String.valueOf(view.getMiddleSide().getMethodsComboBox().getSelectedItem()));
                updateRequestBody(requestClass);
                if(!view.getMenuBar().getOptionFrame().getIsFollowRedirect().isSelected()){
                    requestClass.setFollowRedirect(false);
                }
                SendRequestClass task = new SendRequestClass(requestClass,view);
                task.execute();
            } else {
                JOptionPane.showMessageDialog(view.getMainFrame(), "Please select a request first!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }


    /**
     * Action Listener for About
     */
    private class AboutActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Image scaleImage = view.getImageIcon().getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            ImageIcon scaleImageIcon = new ImageIcon(scaleImage);
            String aboutString = "Written by MohammadSajad Alipour\n" +
                    "Email : sajadalipour@aut.ac.ir\n" +
                    "Student ID : 9831043\n";
            JOptionPane.showMessageDialog(view.getMainFrame(), aboutString, "JPI Man", JOptionPane.INFORMATION_MESSAGE, scaleImageIcon);
        }
    }

    /**
     * Action Listener for Toggle Sidebar
     */
    private class ToggleSidebarActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isToggled) {
                view.getLeftSide().getPanel().setVisible(true);
                isToggled = false;
            } else {
                view.getLeftSide().getPanel().setVisible(false);
                isToggled = true;
            }
        }
    }

    /**
     * This is a method to change project to full screen mode
     */
    private void makeItFullScreen() {
        view.getMainFrame().dispose();
        view.getMainFrame().setUndecorated(true);
        view.getMainFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.getMiddleSide().getRequestTabs().getTabs().setPreferredSize(new Dimension(250, 720));
        view.getMiddleSide().getRequestTabs().getScrollPaneFormData().setPreferredSize(new Dimension(500, 650));
        view.getLeftSide().getScrollPane().setPreferredSize(new Dimension(10, 630));
        view.getMainFrame().setVisible(true);
    }

    /**
     * This is a method to change project to normal screen mode
     */
    private void makeItNormalScreen() {
        view.getMainFrame().dispose();
        view.getMainFrame().setExtendedState(0);
        view.getMainFrame().setUndecorated(false);
        view.getMainFrame().setVisible(true);
        view.getMainFrame().setSize(1330, 515);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        view.getMainFrame().setLocation(dim.width / 2 - view.getMainFrame().getSize().width / 2, dim.height / 2 - view.getMainFrame().getSize().height / 2);
        view.getMiddleSide().getRequestTabs().getTabs().setPreferredSize(new Dimension(250, 380));
        view.getMiddleSide().getRequestTabs().getScrollPaneFormData().setPreferredSize(new Dimension(400, 340));
        view.getLeftSide().getScrollPane().setPreferredSize(new Dimension(10, 290));
    }

    /**
     * ActionListener for FullScreen
     */
    private class FullScreenActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isFullScreened) {
                makeItNormalScreen();
                isFullScreened = false;
                saveOptionsSaves();
            } else {
                makeItFullScreen();
                isFullScreened = true;
                saveOptionsSaves();
            }

        }
    }

    /**
     * MouseAdaptor for Send Mouse Hover effect
     */
    private class SendMouseListener extends MouseAdapter {
        private JButton button;

        public SendMouseListener(JButton button) {
            this.button = button;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(Color.CYAN);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(Color.green);
        }

    }

    /**
     * DocumentListener for Search text field in left side
     */
    private class SearchFocusListener implements DocumentListener {
        private JPanel allTheRequests;
        private JTextField textField;

        public SearchFocusListener() {
            allTheRequests = view.getLeftSide().getAllTheRequestsPanel();
            textField = view.getLeftSide().getFilterSearch();
        }


        public void insertUpdate(DocumentEvent e) {
            Component[] components = view.getLeftSide().getAllTheRequestsPanel().getComponents();
            for (Component component : components) {
                RequestPanel requestPanel = (RequestPanel) component;
                if (!requestPanel.getRequestNameLabel().getText().toLowerCase().contains(textField.getText().toLowerCase())) {
                    requestPanel.setVisible(false);
                    allTheRequests.validate();
                    //System.out.println("True");
                }
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            Component[] components = view.getLeftSide().getAllTheRequestsPanel().getComponents();
            if (textField.getText().isEmpty()) {
                for (Component component : components) {
                    RequestPanel requestPanel = (RequestPanel) component;
                    requestPanel.setVisible(true);
                    allTheRequests.validate();
                }
            } else {
                for (Component component : components) {
                    RequestPanel requestPanel = (RequestPanel) component;
                    if (!requestPanel.getRequestNameLabel().getText().toLowerCase().contains(textField.getText().toLowerCase())) {
                        requestPanel.setVisible(false);
                        allTheRequests.validate();
                        //System.out.println("True");
                    } else {
                        if (!requestPanel.isVisible()) {
                            requestPanel.setVisible(true);
                            allTheRequests.validate();
                        }
                    }
                }
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    /**
     * Focus Listener for Url text field hover effect
     */
    private class UrlTextFieldFocusListener implements FocusListener {
        private JTextField urlTextField;
        private Color defaultBackgroundColor;

        public UrlTextFieldFocusListener(JTextField urlTextField, Color defaultBackgroundColor) {
            this.urlTextField = urlTextField;
            this.defaultBackgroundColor = defaultBackgroundColor;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (urlTextField.getText().equals("https://api.example.com/users")) {
                urlTextField.setText("");
                urlTextField.setForeground(Color.BLACK);
            }
            urlTextField.setBackground(new Color(255, 232, 166));
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (urlTextField.getText().isEmpty()) {
                urlTextField.setForeground(Color.GRAY);
                urlTextField.setText("https://api.example.com/users");
            }
            urlTextField.setBackground(defaultBackgroundColor);
        }
    }

    /**
     * ActionListener for new Request button
     */
    private class newRequestActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newRequestName = JOptionPane.showInputDialog(view.getMainFrame(), "Please enter a name for your request", "My Request");
            if (newRequestName == null) {
                //Do nothing
            } else if (newRequestName.isEmpty()) {
                JOptionPane.showMessageDialog(view.getMainFrame(), "Please enter a name to create a request!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                RequestPanel newReqPanel = new RequestPanel(newRequestName, view.getLeftSide().getAllTheRequestsPanel());
                RequestClass newReqClass = new RequestClass();
                newReqClass.setRequestName(newRequestName);
                newReqPanel.setRequestClass(newReqClass);
                model.getRequestsList().add(newReqClass);
                view.getLeftSide().getAllTheRequestsPanel().add(newReqPanel);
                String kind = "GET";
                kind=String.valueOf(view.getMiddleSide().getMethodsComboBox().getSelectedItem());
                newReqPanel.getKindOFRequestLabel().setText(kind+"  ");
//                clearFields();
                //System.out.println(requestsList);
                view.getMainFrame().validate();
            }
        }
    }

    /**
     * This is a method to clear fields
     */
    public void clearFields() {
        Component[] headerComponents = view.getMiddleSide().getRequestTabs().getHeaderPanel().getComponents();
        for (Component component : headerComponents) {
            if (component.getClass().getName().equals("HeaderElementPanel")) {
                view.getMiddleSide().getRequestTabs().getHeaderPanel().remove(component);
                view.getMiddleSide().getRequestTabs().getHeaderPanel().validate();
            }
        }
        Component[] formDataComponents = view.getMiddleSide().getRequestTabs().getFormDataPanel().getComponents();
        for (Component component : formDataComponents) {
            if (component.getClass().getName().equals("FormDataElementPanel")) {
                view.getMiddleSide().getRequestTabs().getFormDataPanel().remove(component);
                view.getMiddleSide().getRequestTabs().getFormDataPanel().validate();
            }
        }
    }

    public View getView() {
        return view;
    }
}
