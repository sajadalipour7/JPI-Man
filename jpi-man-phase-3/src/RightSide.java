import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This is a class for right side of Project
 * This class includes status of response and tabs for response like message body and header response and etc.
 */
public class RightSide {
    //Main Panel of RightSide
    private JPanel panel;
    //Tabs for Responses tabs
    private JTabbedPane tabs;
    //Preview Panel
    private JPanel previewPanel;
    //Status Panel
    private StatusPanel statusPanel;
    //EditorPane for showing html elements in preview panel
    private JEditorPane editorPane;
    //TextArea for showing message body response
    private JTextArea messageTextArea;
    //Specific Panel for Header Response
    private HeaderResponse headerBodyResponse;

    public RightSide(JFrame mainFrame) {
        //Creating main panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        //Creating Tabs
        tabs = new JTabbedPane();
        //Creating Status Panel
        statusPanel = new StatusPanel();
        statusPanel.setBackground(new Color(66, 67, 158));
        //Creating preview panel
        previewPanel = new JPanel();
        previewPanel.setBackground(new Color(143, 161, 255));
        //Creating Message Text Area
        messageTextArea = new JTextArea();
        messageTextArea.setColumns(35);
        messageTextArea.setRows(20);
        messageTextArea.setText(bodyExampleMessage);
        messageTextArea.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        messageTextArea.setEditable(false);
        messageTextArea.setBackground(new Color(143, 161, 255));
        messageTextArea.setForeground(new Color(0, 0, 0));
        messageTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        //Creating headerBodyResponse panel
        headerBodyResponse = new HeaderResponse();
        //Putting panels into scrollPane to activate scrollbar if that was necessary
        JScrollPane scrollPaneForMessageBody = new JScrollPane(messageTextArea);
        JScrollPane scrollPaneForHeaderResponse = new JScrollPane(headerBodyResponse);
        JScrollPane scrollPaneForPreview = new JScrollPane(previewPanel);
        //Adding tabs to tabs Panel
        tabs.add("Preview", scrollPaneForPreview);
        tabs.add("MessageBody", scrollPaneForMessageBody);
        tabs.add("Header", scrollPaneForHeaderResponse);
        tabs.setSelectedIndex(1);
        //frame.setPreferredSize(new Dimension(200,100));
        //Adding main panel to main frame
        mainFrame.getContentPane().add(panel, BorderLayout.LINE_END);
        panel.add(statusPanel);
        panel.add(tabs);
        //setting background
        //panel.setBackground(new Color(88, 184, 171));
        panel.setBackground(new Color(66, 67, 158));
        //setting header body response background
        headerBodyResponse.setBackground(new Color(143, 161, 255));
        //Creating border for panel
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.black)));
        //panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        //Creating Editor Pane
        editorPane = new JEditorPane();
        //editorPane.setPage("https://google.com");
        editorPane.setVisible(false);
        previewPanel.add(editorPane);
        scrollPaneForPreview.setPreferredSize(new Dimension(540, 500));
    }

    public JEditorPane getEditorPane() {
        return editorPane;
    }

    public HeaderResponse getHeaderBodyResponse() {
        return headerBodyResponse;
    }

    public JPanel getPanel() {
        return panel;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public JPanel getPreviewPanel() {
        return previewPanel;
    }

    public JTextArea getMessageTextArea() {
        return messageTextArea;
    }

    //This is an example for message body until phase 2 and 3
    private String bodyExampleMessage = "";

}
