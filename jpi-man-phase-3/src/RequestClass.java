import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a class for Request properties and details
 */
public class RequestClass {
    private boolean showHeaderResponse = true;
    private boolean outputSave = false;
    private boolean hasHeaderBody = false;
    private boolean hasFormData = false;
    private boolean isFollowRedirect = true;
    private boolean isItAFile = false;
    private String requestName;
    private HttpURLConnection connection;
    private URL url;
    private String outputNameSave;
    private String headerBody;
    private String formData;
    private String method = "GET";
    private String responseAnswerMessage, responseAnswerHeader, responseAnswerTime, responseAnswerSize, responseAnswerStatusCode, responseAnswerStatusMessage;

    public RequestClass() {

    }

    /**
     * This is the send method to send the request
     *
     * @throws IOException
     */
    public String[] send() throws IOException {
        String[] requestProperties = new String[10];
        //Opening the connection
        connection = (HttpURLConnection) url.openConnection();
        //Sending requests by method
        switch (method) {
            case "GET":
                try {
                    sendByGet();
                } catch (IOException e) {

                }
                break;
            case "POST":
                sendByPost();
                break;
            case "PUT":
                sendByPut();
                break;
            case "DELETE":
                sendByDelete();
                break;
            default:
                System.out.println("Invalid Method!");
                break;
        }
        requestProperties[0] = responseAnswerMessage;
        requestProperties[1] = responseAnswerHeader;
        requestProperties[2] = responseAnswerSize;
        requestProperties[3] = responseAnswerTime;
        requestProperties[4] = responseAnswerStatusMessage;
        requestProperties[5] = responseAnswerStatusCode;
        return requestProperties;

    }

    /**
     * This is a method to save response output
     */
    public void saveOutput() {
        try {
            if (connection.getHeaderFields().get("Content-Type").get(0).contains("image")) {
//                System.out.println("It's an image!");
                outputNameSave = JOptionPane.showInputDialog("Please enter a name with format to save image");
                //                String format = connection.getHeaderFields().get("Content-Type").get(0);
                //                String[] splitter = format.split("/");
                //                format = splitter[1];
                File file = new File("./Responses_Output/" + outputNameSave);
//                    downloadImage(file);
                DownloadThread downloadThread = new DownloadThread(file, url);
                downloadThread.execute();
//                    System.out.println("Image succesfully saved into './Responses_Output' !");
                isItAFile = true;
            }
        } catch (Exception e) {

        }
        if (!isItAFile) {
            outputNameSave = JOptionPane.showInputDialog("Please enter a name with format to save output");
            try (FileWriter fileWriter = new FileWriter("./Responses_Output/" + outputNameSave)) {
                fileWriter.write(responseAnswerMessage);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * This is a method to save the request details
     */
    public void save() {
        try (FileWriter fileWriter = new FileWriter("./requests/" + requestName + ".txt")) {
            fileWriter.write(url.toString() + "\n");
            fileWriter.write(method + "\n");
            if (hasHeaderBody) {
                fileWriter.write(headerBody.replaceAll("\"", "") + "\n");
            } else {
                fileWriter.write("0\n");
            }
            if (hasFormData) {
                fileWriter.write(formData.replaceAll("\"", "") + "\n");
            } else {
                fileWriter.write("0\n");
            }


        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     * Send request with GET method
     *
     * @throws IOException
     */
    public void sendByGet() throws IOException {
        Date start = new Date();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        //FollowRedirect
        if (!isFollowRedirect) {
            connection.setInstanceFollowRedirects(false);
        }
        ///Setting Headers
        if (hasHeaderBody) {
            doHeaderStuff();
        }
        //Header Response
        if (showHeaderResponse) {
            showHeaderResponseMessages();
        }


        //Message Response
        showMessageBodyResponse(start);
        try {
            if (connection.getHeaderFields().get("Content-Type").get(0).contains("image")) {
                isItAFile = true;
            } else {
                isItAFile = false;
            }
        } catch (Exception e) {

        }
    }


    /**
     * Send request with POST method
     *
     * @throws IOException
     */
    public void sendByPost() throws IOException {
        Date start = new Date();
        ///Setting formdata
        HashMap<String, String> formBodyData = new HashMap<>();
        formData = formData.replaceAll("\"", "");
        String[] formDataSplitByAmpersand = formData.split("&");
        for (String keyAndValue : formDataSplitByAmpersand) {
            String[] tmp = keyAndValue.split("=");
            formBodyData.put(tmp[0], tmp[1]);
        }
        connection.setRequestMethod("POST");
        ///Setting Headers
        if (hasHeaderBody) {
            doHeaderStuff();
        }
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setDoOutput(true);
        String boundary = System.currentTimeMillis() + "";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        BufferedOutputStream req = new BufferedOutputStream(connection.getOutputStream());
        //Posting form data
        writeInOutputStreamForServer(formBodyData, boundary, req);
        ///Getting And Showing Response
        //Header response
        if (showHeaderResponse) {
            showHeaderResponseMessages();
        }
        //MessageBody response
        showMessageBodyResponse(start);


    }

    /**
     * This is a method to set header body
     */
    private void doHeaderStuff() {
        headerBody = headerBody.replaceAll("\"", "");
        String[] headerSplitBySemicolon = headerBody.split(";");
        for (String keyAndValue : headerSplitBySemicolon) {
            String[] tmp = keyAndValue.split(":");
            connection.setRequestProperty(tmp[0], tmp[1]);
        }
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
    }

    /**
     * This is a method to write form data into outputStream for sending to server
     *
     * @param body
     * @param boundary
     * @param bufferedOutputStream
     * @throws IOException
     */
    private void writeInOutputStreamForServer(HashMap<String, String> body, String boundary, BufferedOutputStream bufferedOutputStream) throws IOException {
        for (String key : body.keySet()) {
            bufferedOutputStream.write(("--" + boundary + "\r\n").getBytes());
            if (key.contains("file")) {
                bufferedOutputStream.write(("Content-Disposition: form-data; filename=\"" + (new File(body.get(key))).getName() + "\"\r\nContent-Type: Auto\r\n\r\n").getBytes());
                try {
                    BufferedInputStream tempBufferedInputStream = new BufferedInputStream(new FileInputStream(new File(body.get(key))));
                    byte[] filesBytes = tempBufferedInputStream.readAllBytes();
                    bufferedOutputStream.write(filesBytes);
                    bufferedOutputStream.write("\r\n".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                bufferedOutputStream.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n").getBytes());
                bufferedOutputStream.write((body.get(key) + "\r\n").getBytes());
            }
        }
        bufferedOutputStream.write(("--" + boundary + "--\r\n").getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

    }

    /**
     * Send request with PUT method
     *
     * @throws IOException
     */
    public void sendByPut() throws IOException {
        Date start = new Date();
        ///Setting formdata
        HashMap<String, String> formBodyData = new HashMap<>();
        formData = formData.replaceAll("\"", "");
        String[] formDataSplitByAmpersand = formData.split("&");
        for (String keyAndValue : formDataSplitByAmpersand) {
            String[] tmp = keyAndValue.split("=");
            formBodyData.put(tmp[0], tmp[1]);
        }
        connection.setRequestMethod("PUT");
        ///Setting Headers
        if (hasHeaderBody) {
            doHeaderStuff();
        }
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setDoOutput(true);
        String boundary = System.currentTimeMillis() + "";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        BufferedOutputStream req = new BufferedOutputStream(connection.getOutputStream());
        //Posting form data
        writeInOutputStreamForServer(formBodyData, boundary, req);
        //Header response
        if (showHeaderResponse) {
            showHeaderResponseMessages();
        }
        //MessageBody response
        showMessageBodyResponse(start);
    }

    /**
     * Send request with DELETE
     *
     * @throws IOException
     */
    public void sendByDelete() throws IOException {
        Date start = new Date();
        connection.setRequestMethod("DELETE");
        ///Setting Headers
        if (hasHeaderBody) {
            doHeaderStuff();
        }
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setDoOutput(true);
        //Header response
        if (showHeaderResponse) {
            showHeaderResponseMessages();
        }
        //MessageBody response
        showMessageBodyResponse(start);

    }

    /**
     * This is a method to show header of response
     */
    private void showHeaderResponseMessages() {
        String tmp = "";
//        for (Map.Entry<String, List<String>> entries : connection.getHeaderFields().entrySet()) {
//            String values = "";
//            for (String value : entries.getValue()) {
//                values += value + ",";
//            }
//            System.out.println(entries.getKey() + " : " + values);
//        }
        for (Map.Entry<String, List<String>> entries : connection.getHeaderFields().entrySet()) {
            String values = "";
            for (String value : entries.getValue()) {
                values += value + ",";
            }
//            System.out.println(entries.getKey() + " : " + values);
            tmp += entries.getKey() + ":" + values + "&";
        }
        responseAnswerHeader = tmp;
        //        System.out.println("\n");
    }

    /**
     * This is a method to show message of response and time and size of the response
     *
     * @param start
     * @throws IOException
     */
    private void showMessageBodyResponse(Date start) throws IOException {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            byte[] bytes = bufferedInputStream.readAllBytes();
            String responseMessage = new String(bytes);
            responseAnswerStatusCode = String.valueOf(connection.getResponseCode());
            responseAnswerStatusMessage = connection.getResponseMessage();
            Date finished = new Date();
            long responseTime = finished.getTime() - start.getTime();
//            System.out.println(responseMessage);
            responseAnswerMessage = responseMessage;

            responseAnswerSize = String.valueOf(bytes.length / 1000.0);
            responseAnswerTime = String.valueOf(responseTime);
//            System.out.println("\n\nSize : " + bytes.length);
//            System.out.println("Time : " + responseTime);
            bufferedInputStream.close();
        } catch (Exception e) {

        }


    }

    /**
     * This is a method to load the request data to UI
     */
    public void load() {
        View.viewInstance.getMiddleSide().getMethodsComboBox().setSelectedItem(method);
        if (url != null) {
            View.viewInstance.getMiddleSide().getUrlTextField().setForeground(Color.BLACK);
            View.viewInstance.getMiddleSide().getUrlTextField().setText(url.toString());
        } else {
            View.viewInstance.getMiddleSide().getUrlTextField().setForeground(Color.GRAY);
            View.viewInstance.getMiddleSide().getUrlTextField().setText("https://api.example.com/users");
        }
        RequestTabs tabs = View.viewInstance.getMiddleSide().getRequestTabs();
        Component[] headerComponents = tabs.getHeaderPanel().getComponents();
        for (Component component : headerComponents) {
            if (component.getClass().getName().equals("HeaderElementPanel")) {
                tabs.getHeaderPanel().remove(component);
                tabs.getHeaderPanel().validate();
            }
        }
        Component[] formDataComponents = tabs.getFormDataPanel().getComponents();
        for (Component component : formDataComponents) {
            if (component.getClass().getName().equals("FormDataElementPanel")) {
                tabs.getFormDataPanel().remove(component);
                tabs.getFormDataPanel().validate();
            }
        }
        if (hasHeaderBody) {
            String copyOfHeaderBody = headerBody;
            copyOfHeaderBody = copyOfHeaderBody.replaceAll("\"", "");
            String[] headers = copyOfHeaderBody.split(";");
            for (String keyAndValue : headers) {
                String[] tmp = keyAndValue.split(":");
//                System.out.println(tmp[0] + " , " + tmp[1]);
                HeaderElementPanel headerElementPanel = new HeaderElementPanel(tabs.getHeaderPanel());
                headerElementPanel.getKey().setForeground(Color.BLACK);
                headerElementPanel.getValue().setForeground(Color.BLACK);
                headerElementPanel.getKey().setText(tmp[0]);
                headerElementPanel.getValue().setText(tmp[1]);
                tabs.getHeaderPanel().add(headerElementPanel);
                tabs.getHeaderPanel().validate();
            }
        }
        if (hasFormData) {
            String copyOfFormBody = formData;
            copyOfFormBody = copyOfFormBody.replaceAll("\"", "");
            String[] bodies = copyOfFormBody.split("&");
            for (String keyAndValue : bodies) {
                String[] tmp = keyAndValue.split("=");
//                System.out.println(tmp[0] + " , " + tmp[1]);
                FormDataElementPanel formDataElementPanel = new FormDataElementPanel(tabs.getFormDataPanel());
                formDataElementPanel.getKey().setForeground(Color.BLACK);
                formDataElementPanel.getValue().setForeground(Color.BLACK);
                formDataElementPanel.getKey().setText(tmp[0]);
                formDataElementPanel.getValue().setText(tmp[1]);
                tabs.getFormDataPanel().add(formDataElementPanel);
                tabs.getFormDataPanel().validate();
            }
        }


    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setShowHeaderResponse(boolean showHeaderResponse) {
        this.showHeaderResponse = showHeaderResponse;
    }

    public String getMethod() {
        return method;
    }

    public void setOutputNameSave(String outputNameSave) {
        this.outputNameSave = outputNameSave;
    }

    public void setOutputSave(boolean outputSave) {
        this.outputSave = outputSave;
    }


    public void setHasHeaderBody(boolean hasHeaderBody) {
        this.hasHeaderBody = hasHeaderBody;
    }

    public void setHeaderBody(String headerBody) {
        this.headerBody = headerBody;
    }

    public void setFollowRedirect(boolean followRedirect) {
        isFollowRedirect = followRedirect;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public void setHasFormData(boolean hasFormData) {
        this.hasFormData = hasFormData;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public String getHeaderBody() {
        return headerBody;
    }

    public String getOutputNameSave() {
        return outputNameSave;
    }

    public String getFormData() {
        return formData;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getResponseAnswerMessage() {
        return responseAnswerMessage;
    }

    public boolean isHasFormData() {
        return hasFormData;
    }

    public boolean isHasHeaderBody() {
        return hasHeaderBody;
    }

    public boolean isOutputSave() {
        return outputSave;
    }

    public boolean isItAFile() {
        return isItAFile;
    }

    public boolean isShowHeaderResponse() {
        return showHeaderResponse;
    }

    @Override
    public String toString() {
        return "Request{" +
                "URL=" + url.toString() +
                "\n showHeaderResponse=" + showHeaderResponse +
                "\n outputSave=" + outputSave +
                "\n hasHeaderBody=" + hasHeaderBody +
                "\n hasFormData=" + hasFormData +
                "\n isFollowRedirect=" + isFollowRedirect +
                "\n outputNameSave='" + outputNameSave + '\'' +
                "\n headerBody='" + headerBody + '\'' +
                "\n formData='" + formData + '\'' +
                "\n method='" + method + '\'' +
                '}';
    }


}
