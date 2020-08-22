import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * This class is the View part of Project
 * This class includes main gui components
 */
public class View {
    //MainFrame of Project
    private JFrame mainFrame;
    //Logo of Project
    private ImageIcon imageIcon;
    //Menubar of Project
    private MenuBar menuBar;
    //Left side of Project
    private LeftSide leftSide;
    //Middle side of Project
    private MiddleSide middleSide;
    //Right side of Project
    private RightSide rightSide;
    //View instance
    public static View viewInstance;

    public View() {
        viewInstance = this;
        //Adding Icon of application
        imageIcon = new ImageIcon("./JPI Man Icon.png");
        ///System Tray Settings
        SystemTray tray = SystemTray.getSystemTray();
        //Popup menu for system tray
        PopupMenu popupTray = new PopupMenu();
        //MenuItem for popup menu of tray
        MenuItem openItem = new MenuItem("Show JPI Man");
        popupTray.add(openItem);
        //adding functionality to tray menu item
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mainFrame.isVisible()) {
                    mainFrame.setVisible(true);
                }
            }
        });
        TrayIcon trayIcon = new TrayIcon(imageIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH), "JPI Man", popupTray);
        //adding functionality to tray icon
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mainFrame.isVisible()) {
                    mainFrame.setVisible(true);
                }
            }
        });
        //Maybe a machine doesn't support SystemTray
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println(e);
        }
        ///Loading Page
        JWindow loading = new JWindow();
        loading.getContentPane().add(new JLabel(new ImageIcon("./Loading.jpg")));
        loading.setSize(800, 515);
        //To set place middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        loading.setLocation(dim.width / 2 - loading.getSize().width / 2, dim.height / 2 - loading.getSize().height / 2);
        loading.setVisible(true);
        //Loading Wait
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
        loading.setVisible(false);
        loading.dispose();
        loading.dispatchEvent(new WindowEvent(loading, WindowEvent.WINDOW_CLOSING));
        //Creating main frame
        mainFrame = new JFrame("JPI Man");
        //To add setting for next steps
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        //Setting Size
        mainFrame.setSize(1330, 515);
        //Setting Icon
        mainFrame.setIconImage(imageIcon.getImage());
        //Setting Location of Main Frame to the center of the screen(reference : "https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution")
        mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2, dim.height / 2 - mainFrame.getSize().height / 2);
        //Creating Menu bar
        menuBar = new MenuBar(mainFrame, imageIcon);
        //Creating leftSide
        try {
            leftSide = new LeftSide(mainFrame);
        } catch (IOException e) {
            leftSide = null;
            System.out.println("logo didn't found!");
        }
        //Creating middleSide
        middleSide = new MiddleSide(mainFrame, imageIcon);
        //Creating rightSide
        rightSide = new RightSide(mainFrame);
    }

    /**
     * This is a method to set visibility of Main Frame true to show GUI components
     */
    public void visible() {
        mainFrame.setVisible(true);
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public LeftSide getLeftSide() {
        return leftSide;
    }

    public MiddleSide getMiddleSide() {
        return middleSide;
    }

    public RightSide getRightSide() {
        return rightSide;
    }
}
