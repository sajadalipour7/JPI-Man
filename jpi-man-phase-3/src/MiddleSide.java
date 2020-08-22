import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is a class for middle side of Project
 * This class includes url text field and body and headers tabs and etc.
 */
public class MiddleSide {
    //Main Panel of MiddleSide
    private JPanel panel;
    //Button for sending request
    private JButton sendButton;
    //TextField for putting url there
    private JTextField urlTextField;
    //ComboBox for selecting kind of request
    private JComboBox methodsComboBox;
    //A class for tabs objects and etc
    private RequestTabs requestTabs;

    public MiddleSide(JFrame mainFrame, ImageIcon imageIcon) {
        //Creating Main Panel
        panel = new JPanel(new BorderLayout());
        //adding main panel to main frame
        mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
        //Setting panel's background
        //panel.setBackground(new Color(73, 138, 166));
        panel.setBackground(new Color(66, 67, 158));
        //setting border for panel
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.black)));
        //panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        //Combo box for methods of request
        //Creating combobox for kind of requests
        String[] methodNames = {"GET", "POST", "PUT", "DELETE"};
        methodsComboBox = new JComboBox(methodNames);
        panel.add(methodsComboBox, BorderLayout.LINE_START);
        //TextField for url of request
        urlTextField = new JTextField("https://api.example.com/users", 30);
        urlTextField.setForeground(Color.GRAY);
        urlTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        urlTextField.setToolTipText("Enter your URL here");
        panel.add(urlTextField, BorderLayout.CENTER);
        //Creating Send button
        Image scaleImage = imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon scaleImageIcon = new ImageIcon(scaleImage);
        sendButton = new JButton("Send", scaleImageIcon);
        sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendButton.setFocusPainted(false);
        sendButton.setBorderPainted(false);
        sendButton.setBackground(Color.green);
        //sendButton.setOpaque(true);
        panel.add(sendButton, BorderLayout.LINE_END);
        //Creating Request Tabs
        requestTabs = new RequestTabs(panel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JComboBox getMethodsComboBox() {
        return methodsComboBox;
    }

    public JTextField getUrlTextField() {
        return urlTextField;
    }

    public RequestTabs getRequestTabs() {
        return requestTabs;
    }
}
