import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This is a class for Request tabs in middle side
 */
public class RequestTabs {
    //Tabs
    private JTabbedPane tabs;
    //Scroll for scrollPaneFormData
    private JScrollPane scrollPaneFormData;
    //TextArea for JSON
    private JTextArea textAreaForJSON;
    //other Request tabs panels
    private JPanel  formDataPanel, headerPanel, newFormDataButtonPanel, buttonPanel;

    public RequestTabs(JPanel panel) {
        //Creating Tabs
        tabs = new JTabbedPane();
        //FormData
        formDataPanel = new JPanel();
        //New Button for form Data
        newFormDataButtonPanel = new JPanel();
        JButton newFormDataButton = new JButton("New");
        newFormDataButtonPanel.setLayout(new BorderLayout());
        newFormDataButtonPanel.add(newFormDataButton, BorderLayout.CENTER);
        newFormDataButtonPanel.setMaximumSize(new Dimension(120, 40));
        newFormDataButtonPanel.setMinimumSize(new Dimension(120, 40));
        newFormDataButtonPanel.setBackground(new Color(143, 161, 255));
        formDataPanel.add(newFormDataButtonPanel);
        formDataPanel.setBackground(new Color(143, 161, 255));
        formDataPanel.setLayout(new BoxLayout(formDataPanel, BoxLayout.PAGE_AXIS));
        scrollPaneFormData = new JScrollPane(formDataPanel);
        scrollPaneFormData.setPreferredSize(new Dimension(400, 340));
        //ActionListener for new button in form data panel
        newFormDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formDataPanel.add(new FormDataElementPanel(formDataPanel));
                formDataPanel.validate();
                scrollPaneFormData.validate();
                tabs.validate();
            }
        });
        //Creating panels
        headerPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(headerPanel);
        headerPanel.setBackground(new Color(143, 161, 255));
        //scrollPane.setPreferredSize(new Dimension(10, 290));
        tabs.add("From Data", scrollPaneFormData);
        tabs.add("Header", scrollPane);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.PAGE_AXIS));
        //New Header button
        JButton newHeaderButton = new JButton("New");
        newHeaderButton.setToolTipText("Create New Header");
        newHeaderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newHeaderButton.setFont(new Font("Arial", Font.BOLD, 13));
        newFormDataButton.setFont(new Font("Arial", Font.BOLD, 13));
        newFormDataButton.setToolTipText("Create New Form Data");
        newFormDataButton.setBackground(new Color(255, 191, 32));
        newHeaderButton.setBackground(new Color(255, 191, 32));
        newFormDataButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newFormDataButton.addMouseListener(new HoverEffect(newFormDataButton));
        newHeaderButton.addMouseListener(new HoverEffect(newHeaderButton));
        //ActionListener for new header button
        newHeaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                headerPanel.add(new HeaderElementPanel(headerPanel));
                headerPanel.validate();
                scrollPane.validate();

            }
        });
        ///Adding panel for new header button
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(newHeaderButton, BorderLayout.CENTER);
        buttonPanel.setMaximumSize(new Dimension(120, 40));
        buttonPanel.setMinimumSize(new Dimension(120, 40));
        buttonPanel.setBackground(new Color(143, 161, 255));
        headerPanel.add(buttonPanel);
        tabs.setPreferredSize(new Dimension(250, 380));
        panel.add(tabs, BorderLayout.PAGE_END);
    }


    public JTabbedPane getTabs() {
        return tabs;
    }

    public JScrollPane getScrollPaneFormData() {
        return scrollPaneFormData;
    }

    public JTextArea getTextAreaForJSON() {
        return textAreaForJSON;
    }


    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JPanel getNewFormDataButtonPanel() {
        return newFormDataButtonPanel;
    }

    public JPanel getFormDataPanel() {
        return formDataPanel;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    /**
     * Mouse Adapter for adding Hover effect to 'New' buttons
     */
    private class HoverEffect extends MouseAdapter {
        private JButton button;

        public HoverEffect(JButton button) {
            this.button = button;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(new Color(69, 228, 33));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(new Color(255, 191, 32));
        }
    }

}
