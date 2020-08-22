import javax.swing.*;
import java.util.ArrayList;

/**
 * This class is the Model part of Project
 * This class includes list of requests and their data
 */
public class Model {
    private SavedRequests savedRequests;
    private ArrayList<RequestClass> requestsList;

    public Model(){
        savedRequests=new SavedRequests();
        requestsList=savedRequests.getRequests();
    }


    public ArrayList<RequestClass> getRequestsList() {
        return requestsList;
    }
}
