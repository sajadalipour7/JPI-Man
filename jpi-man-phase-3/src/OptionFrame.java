import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import javax.swing.text.IconView;
import java.awt.*;

/**
 * This is a class for Options window
 * Also this class extends from JFrame
 */
public class OptionFrame extends JFrame {
    //Panel for setting good layout
    private JPanel panel;
    //FollowRedirect checkbox
    private JCheckBox isFollowRedirect;
    //SystemTray checkbox
    private JCheckBox isClosedIntoSystemTray;
    //Radiobutton for purple theme
    private JRadioButton purpleTheme;
    //Radiobutton for chocolate theme
    private JRadioButton chocolateTheme;
    //ButtonGroup for Theme radio buttons
    private ButtonGroup themeForMainFrame;

    public OptionFrame() {
        //Creating components
        panel = new JPanel();
        isFollowRedirect = new JCheckBox("", true);
        isClosedIntoSystemTray = new JCheckBox("", false);
        purpleTheme = new JRadioButton();
        purpleTheme.setSelected(true);
        purpleTheme.setForeground(Color.red);
        chocolateTheme = new JRadioButton();
        themeForMainFrame = new ButtonGroup();
        themeForMainFrame.add(purpleTheme);
        themeForMainFrame.add(chocolateTheme);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        this.add(panel);
        JPanel followPanel = new JPanel();
        followPanel.setLayout(new BoxLayout(followPanel, BoxLayout.LINE_AXIS));
        followPanel.add(new JLabel("Follow Redirect : "));
        followPanel.add(isFollowRedirect);
        JPanel trayPanel = new JPanel();
        trayPanel.setLayout(new BoxLayout(trayPanel, BoxLayout.LINE_AXIS));
        trayPanel.add(new JLabel("Change Exit mode to System Tray : "));
        trayPanel.add(isClosedIntoSystemTray);
        JPanel themePanel = new JPanel();
        themePanel.setLayout(new BoxLayout(themePanel, BoxLayout.LINE_AXIS));
        themePanel.add(new JLabel("Change JPI Man Theme :  "));
        themePanel.add(new JLabel("Purple "));
        themePanel.add(purpleTheme);
        themePanel.add(new JLabel(" Chocolate "));
        themePanel.add(chocolateTheme);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(followPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(trayPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(themePanel);
        followPanel.setBackground(new Color(101, 186, 229));
        trayPanel.setBackground(new Color(101, 186, 229));
        themePanel.setBackground(new Color(101, 186, 229));
        this.setTitle("Options");
        //Adding Icon for options window
        ImageIcon imageIcon = new ImageIcon("./JPI Man Icon.png");
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(400, 200);
        panel.setBackground(new Color(101, 186, 229));
        //To show window center of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(false);
    }

    public JCheckBox getIsClosedIntoSystemTray() {
        return isClosedIntoSystemTray;
    }

    public JRadioButton getPurpleTheme() {
        return purpleTheme;
    }

    public JCheckBox getIsFollowRedirect() {
        return isFollowRedirect;
    }

    public JRadioButton getChocolateTheme() {
        return chocolateTheme;
    }
}
