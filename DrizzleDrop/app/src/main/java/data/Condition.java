package data;

import org.json.JSONObject;

/**
 * Created by David on 4/11/2016.
 */
public class Condition implements JSONPopulator {
    private int code;
    private int temp;
    private String desc;

    public int getCode() {
        return code;
    }

    public int getTemp() {
        return temp;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temp = data.optInt("temp");
        desc = data.optString("text");
    }
}
