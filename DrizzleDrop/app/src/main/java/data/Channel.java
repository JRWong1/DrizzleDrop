package data;

import org.json.JSONObject;

/**
 * Created by David on 4/11/2016.
 */
public class Channel implements JSONPopulator {
    private Item item;
    private Units units;


    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));
    }
}
