import javax.swing.*;
import java.awt.*;

/**
 * This is a class for status of response
 * Also this class extends from JPanel
 */
public class StatusPanel extends JPanel {
    //Label for status code
    private JLabel statusCode;
    //Label for status Message
    private JLabel statusMessage;
    //Label for status time spent
    private JLabel timeAmount;
    //Label for status size of response
    private JLabel sizeOfData;

    public StatusPanel() {
        //Creating Labels and details
        statusCode = new JLabel("200");
        statusMessage = new JLabel("OK");
        statusMessage.setOpaque(true);
        statusCode.setOpaque(true);
        statusCode.setBackground(new Color(190, 255, 55));
        statusMessage.setBackground(new Color(190, 255, 55));
        statusCode.setToolTipText("Status Code");
        statusCode.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        statusMessage.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        statusMessage.setToolTipText("Status Message");
        statusCode.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        statusMessage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        timeAmount = new JLabel("TIME -- ms");
        sizeOfData = new JLabel("SIZE -- KB");
        timeAmount.setOpaque(true);
        sizeOfData.setOpaque(true);
        timeAmount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        sizeOfData.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        timeAmount.setToolTipText("Time amount spent");
        sizeOfData.setToolTipText("Size of Data");
        timeAmount.setBackground(new Color(255, 191, 32));
        sizeOfData.setBackground(new Color(75, 228, 255));
        timeAmount.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        sizeOfData.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(statusCode);
        this.add(statusMessage);
        this.add(Box.createRigidArea(new Dimension(40, 0)));
        this.add(timeAmount);
        this.add(Box.createRigidArea(new Dimension(40, 0)));
        this.add(sizeOfData);
        //this.setPreferredSize(new Dimension(200,40));
    }

    public JLabel getSizeOfData() {
        return sizeOfData;
    }

    public JLabel getStatusCode() {
        return statusCode;
    }

    public JLabel getStatusMessage() {
        return statusMessage;
    }

    public JLabel getTimeAmount() {
        return timeAmount;
    }
}
