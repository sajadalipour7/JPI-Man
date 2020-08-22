import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * This is a class for Request Panels in the left side
 * Also this class extends from JPanel
 */
public class RequestPanel extends JPanel {
    private JButton removeButton;
    private JLabel requestNameLabel;
    private JLabel kindOFRequestLabel;
    public static RequestPanel selectedPanel = null;
    public static boolean isAnyOneSelected = false;
    private boolean isSelected = false;
    private RequestClass requestClass;
    public static Color colorOfPanel = ColorsForThemes.purpleFore;
    public static Color mouseFocusedColor = ColorsForThemes.colorFocusedMouseReqPanelPurple;
    public static Color selectedPanelColor = ColorsForThemes.selectedPanelPurple;
    public static Color defaultColor = ColorsForThemes.purpleFore;

    public RequestPanel(String requestName, JPanel parentPanel) {
        this.setLayout(new BorderLayout());
        this.setBackground(colorOfPanel);
        //Creating Labels
        requestNameLabel = new JLabel(requestName);
        kindOFRequestLabel = new JLabel("GET  ");
        //Creating Remove Button
        removeButton = new JButton("X");
        removeButton.setFont(new Font("Arial", Font.BOLD, 20));
        //removeButton.setBackground(new Color(255, 61, 88));
        removeButton.setBackground(new Color(255, 110, 143));
        //MouseListener for hover effect for remove Button
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                removeButton.setBackground(new Color(255, 61, 88));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeButton.setBackground(new Color(255, 110, 143));
            }
        });
        this.add(kindOFRequestLabel, BorderLayout.LINE_START);
        //this.add(Box.createRigidArea(new Dimension(30,10)));
        this.add(requestNameLabel, BorderLayout.CENTER);
        this.add(removeButton, BorderLayout.LINE_END);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        RequestPanel thisPanel = this;
        //MouseListener for selecting effect panel
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isAnyOneSelected) {
                    thisPanel.setBackground(selectedPanelColor);
                    parentPanel.validate();
                    isSelected = true;
                    isAnyOneSelected = true;
                    selectedPanel = thisPanel;
                    requestClass.load();
                } else {
                    if (isSelected) {
                        thisPanel.setBackground(defaultColor);
                        parentPanel.validate();
                        isSelected = false;
                        isAnyOneSelected = false;
                        selectedPanel = null;
                    }
                }
            }
        });
        //mouse listener for panels hover effect
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!thisPanel.getBackground().equals(selectedPanelColor) && !isAnyOneSelected) {
                    //thisPanel.setBackground(new Color(238, 225, 255));
                    thisPanel.setBackground(mouseFocusedColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!thisPanel.getBackground().equals(selectedPanelColor) && !isAnyOneSelected) {
                    thisPanel.setBackground(defaultColor);
                }
            }
        });
        //ActionListener for remove button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {
                    isSelected = false;
                    isAnyOneSelected = false;
                }
                try{
                    File file=new File("./requests/"+requestName+".txt");
                    file.delete();
                }catch (Exception eee){
                }
                thisPanel.setVisible(false);
                thisPanel.validate();
                parentPanel.remove(thisPanel);
                parentPanel.validate();

            }
        });
        //this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2), BorderFactory.createLineBorder(Color.black,4)));
        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        requestNameLabel.setFont(new Font("sans-serif", Font.PLAIN, 14));
        kindOFRequestLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.setMaximumSize(new Dimension(400, 35));
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JLabel getKindOFRequestLabel() {
        return kindOFRequestLabel;
    }

    public JLabel getRequestNameLabel() {
        return requestNameLabel;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public RequestClass getRequestClass() {
        return requestClass;
    }

    public void setRequestClass(RequestClass requestClass) {
        this.requestClass = requestClass;
    }
}
