import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * This is a class to send request with SwingWorker multi-thread system
 */
public class SendRequestClass extends SwingWorker<String[] ,String[]> {
    private RequestClass request;
    private View view;
    private String responseMessage,responseHeader,responseTime,responseSize,codeAndResponse,responseCode,responseStatusMessage;
    public SendRequestClass(RequestClass request,View view){
        this.request=request;
        this.view=view;
    }
    @Override
    protected String[] doInBackground() throws Exception {
        String[] responseData=request.send();
        responseMessage=responseData[0];
        responseHeader=responseData[1];
        responseSize=responseData[2];
        responseTime=responseData[3];
        return responseData;
    }

    @Override
    protected void done() {
        codeAndResponse="";
        setupHeaders();
        String tmpStatus[]=codeAndResponse.split(" ");
        try {
            responseCode = tmpStatus[1];
            responseStatusMessage = tmpStatus[2].replaceAll(",", "");
        }catch (Exception ew){
            responseCode="";
            responseStatusMessage="ERROR";
            view.getRightSide().getStatusPanel().getStatusCode().setBackground(new Color(255, 90, 27));
            view.getRightSide().getStatusPanel().getStatusMessage().setBackground(new Color(255, 90, 27));
        }
        if(tmpStatus.length>3){
            responseStatusMessage+=" "+tmpStatus[3].replaceAll(",","");
            if(tmpStatus.length>4){
                responseStatusMessage+=" "+tmpStatus[4].replaceAll(",","");
            }
        }
        try {
            view.getRightSide().getStatusPanel().getSizeOfData().setText("SIZE " + String.format("%.2f", Double.parseDouble(responseSize)) + " KB");
        }catch (Exception e){
            view.getRightSide().getStatusPanel().getSizeOfData().setText("SIZE " + "--" + " KB");
        }
        view.getRightSide().getStatusPanel().getStatusMessage().setText(responseStatusMessage);
        view.getRightSide().getStatusPanel().getStatusCode().setText(responseCode);
        try {
            switch (responseCode.charAt(0)) {
                case '2':
                    view.getRightSide().getStatusPanel().getStatusCode().setBackground(new Color(190, 255, 55));
                    view.getRightSide().getStatusPanel().getStatusMessage().setBackground(new Color(190, 255, 55));
                    break;
                case '3':
                    view.getRightSide().getStatusPanel().getStatusCode().setBackground(new Color(64, 77, 255));
                    view.getRightSide().getStatusPanel().getStatusMessage().setBackground(new Color(64, 77, 255));
                    break;
                default:
                    view.getRightSide().getStatusPanel().getStatusCode().setBackground(new Color(255, 90, 27));
                    view.getRightSide().getStatusPanel().getStatusMessage().setBackground(new Color(255, 90, 27));
                    break;
            }
        }catch (Exception e){

        }
        view.getRightSide().getStatusPanel().getTimeAmount().setText("TIME " + responseTime + " ms");
        PreviewClass previewClass=new PreviewClass(view.getRightSide().getEditorPane(),view.getMiddleSide().getUrlTextField());
        previewClass.execute();
        view.getRightSide().getMessageTextArea().setText(responseMessage);
        view.getRightSide().getMessageTextArea().validate();
    }

    /**
     * This is a method to set response headers for UI
     */
    private void setupHeaders(){
        Component[] lastHeaders=view.getRightSide().getHeaderBodyResponse().getElements().getComponents();
        for(Component header:lastHeaders){
            view.getRightSide().getHeaderBodyResponse().getElements().remove(header);
        }
        view.getRightSide().getHeaderBodyResponse().getElements().validate();
        try {
            String[] headersArray = responseHeader.split("&");
            for (String header : headersArray) {
                String[] keyAndValue = header.split(":");
                HeaderResponseElement headerResponseElement = new HeaderResponseElement();
                headerResponseElement.getKey().setText(keyAndValue[0]);
                headerResponseElement.getValue().setText(keyAndValue[1].replaceAll(",", " "));
                view.getRightSide().getHeaderBodyResponse().getElements().add(headerResponseElement);
                if(keyAndValue[0].contains("null")){
                    codeAndResponse=keyAndValue[1];
                }
            }
            view.getRightSide().getHeaderBodyResponse().getElements().validate();
        }catch (Exception e){

        }
//        System.out.println(responseHeader);
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseHeader() {
        return responseHeader;
    }
}
