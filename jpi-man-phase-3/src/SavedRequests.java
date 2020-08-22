import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * This is a class for storing all the requests and loading them
 */
public class SavedRequests {
    private ArrayList<RequestClass> requests = new ArrayList<>();

    public SavedRequests() {
        //Loading all the requests
        File folder = new File("./requests");
        for (File file : folder.listFiles()) {
            readRequest(file);
        }
    }

    /**
     * This is a method to load a request by file name
     *
     * @param file
     */
    private void readRequest(File file) {
        RequestClass request = new RequestClass();
        request.setRequestName(file.getName().replaceAll(".txt",""));
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String url = bufferedReader.readLine();
            request.setUrl(new URL(url));
            String method = bufferedReader.readLine();
            request.setMethod(method);
            String headerBody = bufferedReader.readLine();
            if (!headerBody.equals("0")) {
                request.setHasHeaderBody(true);
                request.setHeaderBody(headerBody);
            }
            String formData = bufferedReader.readLine();
            if (!formData.equals("0")) {
                request.setHasFormData(true);
                request.setFormData(formData);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        requests.add(request);
    }

    public ArrayList<RequestClass> getRequests() {
        return requests;
    }

    /**
     * This is a method to show list of all the requests that has been saved in './requests/' in a proper way
     */
    public void showRequestsList() {
        int iterator = 1;
        for (RequestClass request : requests) {
            System.out.print(iterator + " . url: " + request.getUrl().toString() + " | method: " + request.getMethod());
            if (request.isHasHeaderBody()) {
                System.out.print(" | headers: " + request.getHeaderBody());
            }
            if (request.isHasFormData()) {
                System.out.print(" | body: " + request.getFormData());
            }
            if (request.isShowHeaderResponse()) {
                System.out.print(" | -i ");
            }
            if (request.isOutputSave()) {
                System.out.print(" | Output : " + request.getOutputNameSave());
            }
            System.out.print("\n");
            iterator++;
        }
    }
}
