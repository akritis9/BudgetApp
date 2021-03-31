package persistence;

import model.Budget;
import org.json.JSONObject;

//CODE CREDIT: All code in this class is based on the JsonSerialization app
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
