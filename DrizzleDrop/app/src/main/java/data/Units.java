package data;

import org.json.JSONObject;

/**
 * Created by David on 4/11/2016.
 */
public class Units implements JSONPopulator {

    public String getTemp() {
        return temp;
    }

    private String temp;
    @Override
    public void populate(JSONObject data) {
        temp = data.optString("temperature");
    }
}
