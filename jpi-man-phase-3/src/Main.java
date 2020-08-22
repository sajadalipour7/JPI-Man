import javax.swing.*;

/**
 * In the name of god
 * <p>
 * --------------------------
 * JPI Man
 * --------------------------
 * This Project is a http client with Swing GUI .
 * <p>
 * The Architecture of the project is MVC(Model View Controller)
 * <p>
 * <p>
 * This class is the Main class for running the project
 *
 * @author MohammadSajad Alipour
 * @version 1.0    2020
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        //Setting Nimbus LookAndFeel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            //Default Look and Feel
        }
        //Creating Model Of the Project
        Model model=new Model();
        //Creating View Of the Project
        View view = new View();
        //Creating Controller Of the Project
        Controller controller = new Controller(model,view);
    }
}
