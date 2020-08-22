import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * This is a class to download images with SwingWorker multithread system
 */
public class DownloadThread extends SwingWorker<String, String> {
    private File file;
    private URL url;

    public DownloadThread(File file, URL url) {
        this.file = file;
        this.url = url;
    }

    @Override
    protected String doInBackground() throws Exception {
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(file);
        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
        return "";
    }
}
