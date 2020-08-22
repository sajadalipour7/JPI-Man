import javax.swing.*;
import java.awt.*;

/**
 * This is a class for header response element which includes key and value
 * Also this class extends from JPanel
 */
public class HeaderResponseElement extends JPanel {
    //TextField key
    private JTextField key;
    //TextField value
    private JTextField value;
    public static Color colorOfPanel = ColorsForThemes.purpleFore;

    public HeaderResponseElement() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        key = new JTextField("Key: Something", 12);
        value = new JTextField("Value: Something", 12);
        key.setFont(new Font("Arial", Font.PLAIN, 18));
        value.setFont(new Font("Arial", Font.PLAIN, 18));
        key.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        value.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        //setting text fields uneditable
        key.setEditable(false);
        value.setEditable(false);
        //setting borders null
        key.setBorder(null);
        value.setBorder(null);
        key.setBackground(colorOfPanel);
        value.setBackground(colorOfPanel);
        this.add(key);
        //this.add(Box.createRigidArea(new Dimension(0,0)));
        this.add(value);
        this.setBackground(colorOfPanel);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 4, 5, 4), BorderFactory.createLineBorder(Color.black, 3, true)));

    }

    public JTextField getValue() {
        return value;
    }

    public JTextField getKey() {
        return key;
    }
}
