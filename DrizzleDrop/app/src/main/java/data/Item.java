package data;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by David on 4/11/2016.
 */
public class Item implements JSONPopulator {
    private Condition condition;
    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
