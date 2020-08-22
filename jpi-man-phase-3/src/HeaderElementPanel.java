import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is a class for Header element in middle side
 * Also this class extends from JPanel
 */
public class HeaderElementPanel extends JPanel {
    //parent panel
    private JPanel parentPanel;
    private JTextField key;
    private JTextField value;
    private JCheckBox isActive;
    private JButton removeButton;
    public static Color colorOfPanel = ColorsForThemes.purpleFore;

    public HeaderElementPanel(JPanel parentPanel) {
        this.parentPanel = parentPanel;
        key = new JTextField("New Header", 17);
        key.setForeground(Color.GRAY);
        value = new JTextField("New Value", 17);
        value.setForeground(Color.GRAY);
        isActive = new JCheckBox("", true);
        //add icon to remove button
        ImageIcon trashIcon = new ImageIcon("./trash.png");
        Image scaleImage = trashIcon.getImage().getScaledInstance(15, 20, Image.SCALE_SMOOTH);
        ImageIcon scaleImageIcon = new ImageIcon(scaleImage);
        removeButton = new JButton(scaleImageIcon);
        removeButton.setBackground(new Color(255, 84, 52));
        //hover effect for remove button
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                removeButton.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeButton.setBackground(new Color(255, 84, 52));
            }
        });
        removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(key);
        this.add(value);
        this.add(isActive);
        this.add(removeButton);
        HeaderElementPanel thisPanel = this;
        //ActionListener remove button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                thisPanel.validate();
                parentPanel.remove(thisPanel);
                parentPanel.validate();

            }
        });
        Color defaultBackgroundColor = key.getBackground();
        //FocusListener for key for hover effect
        key.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (key.getText().equals("New Header")) {
                    key.setText("");
                    key.setForeground(Color.BLACK);
                }
                //key.setBackground(new Color(147, 215, 218));
                key.setBackground(new Color(201, 255, 170));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (key.getText().isEmpty()) {
                    key.setForeground(Color.GRAY);
                    key.setText("New Header");
                }
                key.setBackground(defaultBackgroundColor);
            }
        });
        //FocusListener for value for hover effect
        value.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (value.getText().equals("New Value")) {
                    value.setText("");
                    value.setForeground(Color.BLACK);
                }
                //value.setBackground(new Color(147, 215, 218));
                value.setBackground(new Color(201, 255, 170));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (value.getText().isEmpty()) {
                    value.setForeground(Color.GRAY);
                    value.setText("New Value");
                }
                value.setBackground(defaultBackgroundColor);
            }
        });
        this.setBackground(colorOfPanel);
        this.setMaximumSize(new Dimension(410, 35));
        this.setMinimumSize(new Dimension(200, 35));
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JCheckBox getIsActive() {
        return isActive;
    }

    public JPanel getParentPanel() {
        return parentPanel;
    }

    public JTextField getKey() {
        return key;
    }

    public JTextField getValue() {
        return value;
    }

}
