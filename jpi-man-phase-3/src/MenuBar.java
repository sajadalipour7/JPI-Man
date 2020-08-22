import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * This is a class for Menu bar on top left of project
 */
public class MenuBar {
    //Menubar
    private JMenuBar menuBar = new JMenuBar();
    //application menu
    private JMenu applicationMenu;
    //menu items in application menu
    private JMenuItem saveRequest, options, exit,saveOutput;
    //view menu
    private JMenu viewMenu;
    //menu items in view menu
    private JMenuItem toggleFullScreen, toggleSidebar;
    //help menu
    private JMenu helpMenu;
    //menu items in help menu
    private JMenuItem about, helpItem;
    //options window
    private OptionFrame optionFrame;

    public MenuBar(JFrame mainFrame, ImageIcon imageIcon) {
        /// Application Menu
        applicationMenu = new JMenu("Application");
        menuBar.add(applicationMenu);
        applicationMenu.setMnemonic('A');
        //Options
        options = new JMenuItem("Options");
        applicationMenu.add(options);
        optionFrame = new OptionFrame();
        options.setMnemonic('O');
        options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        //Save Request
        saveRequest = new JMenuItem("Save Request");
        saveRequest.setMnemonic('S');
        saveRequest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        applicationMenu.add(saveRequest);
        //Save Output
        saveOutput = new JMenuItem("Save Response Output");
        saveOutput.setMnemonic('R');
        saveOutput.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        applicationMenu.add(saveOutput);
        //adding a thin line
        applicationMenu.add(new JPopupMenu.Separator());
        //Quit
        exit = new JMenuItem("Exit");
        exit.setMnemonic('E');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        applicationMenu.add(exit);
        /// View Menu
        viewMenu = new JMenu("View");
        menuBar.add(viewMenu);
        viewMenu.setMnemonic('V');
        //Toggle Full Screen
        toggleFullScreen = new JMenuItem("Toggle Full Screen");
        viewMenu.add(toggleFullScreen);
        toggleFullScreen.setMnemonic('F');
        toggleFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
        //Toggle Sidebar
        toggleSidebar = new JMenuItem("Toggle Sidebar");
        viewMenu.add(toggleSidebar);
        toggleSidebar.setMnemonic('T');
        toggleSidebar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, KeyEvent.CTRL_DOWN_MASK));
        /// Help Menu
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        helpMenu.setMnemonic('H');
        //About
        about = new JMenuItem("About");
        helpMenu.add(about);
        about.setMnemonic('A');
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_DOWN_MASK));
        //Help Item
        helpItem = new JMenuItem("Help");
        helpMenu.add(helpItem);
        helpItem.setMnemonic('H');
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        //Set menuBar to mainFrame
        mainFrame.setJMenuBar(menuBar);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenu getApplicationMenu() {
        return applicationMenu;
    }

    public JMenu getHelpMenu() {
        return helpMenu;
    }

    public JMenu getViewMenu() {
        return viewMenu;
    }

    public JMenuItem getAbout() {
        return about;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public JMenuItem getHelpItem() {
        return helpItem;
    }

    public JMenuItem getOptions() {
        return options;
    }

    public JMenuItem getToggleFullScreen() {
        return toggleFullScreen;
    }

    public JMenuItem getToggleSidebar() {
        return toggleSidebar;
    }

    public OptionFrame getOptionFrame() {
        return optionFrame;
    }

    public JMenuItem getSaveRequest() {
        return saveRequest;
    }

    public JMenuItem getSaveOutput() {
        return saveOutput;
    }
}
