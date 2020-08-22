import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a class for left side of Project
 * This class includes request panels and search text field and etc.
 */
public class LeftSide {
    //Main Panel of LeftSide
    private JPanel panel;
    //Button for creating new Request panel
    private JButton newRequestButton;
    //TextField for search among names of request panels
    private JTextField filterSearch;
    //Panel of all request panels
    private JPanel allTheRequestsPanel;
    //ScrollPane for activating scrollbar when that is necessery
    private JScrollPane scrollPane;
    //Default color for panel
    private Color defaultColor;

    public LeftSide(JFrame mainFrame) throws IOException {
        //Creating main panel
        panel = new JPanel(new BorderLayout());
        //adding main panel to main frame
        mainFrame.getContentPane().add(panel, BorderLayout.LINE_START);
        //adding Logo of JPI Man and setting that to a label
        BufferedImage jpiPic = ImageIO.read(new File("./label.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(jpiPic));
        picLabel.setToolTipText("JPI Man");
        //setting background color
        //panel.setBackground(new Color(93, 127, 222));
        panel.setBackground(new Color(66, 67, 158));
        //Creating border for main panel
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.black)));
        //panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel.add(picLabel, BorderLayout.PAGE_START);
        //Creating filter label
        JLabel filterLabel = new JLabel(" Filter  ");
        filterLabel.setOpaque(true);
        filterLabel.setBackground(new Color(223, 245, 58));
        panel.add(filterLabel, BorderLayout.LINE_START);
        //Creating filter search text field
        filterSearch = new JTextField("");
        //Adding Hover effect for filter search
        filterSearch.addFocusListener(new HoverEffectForSearchField());
        filterSearch.setToolTipText("Search Name Of Requests here");
        defaultColor = filterSearch.getBackground();
        panel.add(filterSearch, BorderLayout.CENTER);
        //Creating newRequestButton
        newRequestButton = new JButton("+");
        newRequestButton.setFont(new Font("Arial", Font.PLAIN, 30));
        newRequestButton.setBackground(new Color(255, 198, 50));
        newRequestButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newRequestButton.setToolTipText("Create a new Request");
//        newRequestButton.setFocusPainted(false);
//        newRequestButton.setBorderPainted(false);
        //newRequestButton.setOpaque(true);
        panel.add(newRequestButton, BorderLayout.LINE_END);
        //Creating all the requests panel
        allTheRequestsPanel = new JPanel();
        allTheRequestsPanel.setLayout(new BoxLayout(allTheRequestsPanel, BoxLayout.PAGE_AXIS));
        //allTheRequestsPanel.setPreferredSize(new Dimension(10,290));
        //allTheRequestsPanel.setBackground(new Color(64, 65, 137));
        //allTheRequestsPanel.setBackground(new Color(143, 161, 255));
        allTheRequestsPanel.setBackground(ColorsForThemes.ForeGroundColor);
        //setting scroll pane for allTheRequests panel
        scrollPane = new JScrollPane(allTheRequestsPanel);
        scrollPane.setPreferredSize(new Dimension(10, 290));
        panel.add(scrollPane, BorderLayout.PAGE_END);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getNewRequestButton() {
        return newRequestButton;
    }

    public JPanel getAllTheRequestsPanel() {
        return allTheRequestsPanel;
    }

    public JTextField getFilterSearch() {
        return filterSearch;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }


    /**
     * This is a class for adding hover effect to search text field by using FocusListener Interface
     */
    private class HoverEffectForSearchField implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            filterSearch.setBackground(new Color(184, 233, 255));
        }

        @Override
        public void focusLost(FocusEvent e) {
            filterSearch.setBackground(defaultColor);
        }
    }
}
