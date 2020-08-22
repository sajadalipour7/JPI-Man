import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

/**
 * This is a class to show preview with SwingWorker multi-thread system
 */
public class PreviewClass extends SwingWorker<String ,String> {
    private JEditorPane editorPane;
    private JTextField textField;
    public PreviewClass(JEditorPane editorPane, JTextField textField){
        this.editorPane=editorPane;
        this.textField=textField;
    }

    @Override
    protected String doInBackground() throws Exception {
        String url=textField.getText();
        if(!url.contains("http")){
            url="https://"+url;
        }
        try {
            editorPane.setVisible(true);
            if(RequestPanel.selectedPanel.getRequestClass().isItAFile()) {
                try (FileWriter fileWriter = new FileWriter("./saves/pictureLoader.html")) {
                    String htmlBody = "<html><img src=\"" + url + "\"></img></html>";
                    fileWriter.write(htmlBody);
                } catch (Exception eeee) {
                }
                editorPane.setPage(new File("./saves/pictureLoader.html").toURI().toURL());
            }else {
                try {
                    editorPane.setPage(url);
                }catch (Exception et){

                }
            }
            editorPane.validate();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "";
    }
}
