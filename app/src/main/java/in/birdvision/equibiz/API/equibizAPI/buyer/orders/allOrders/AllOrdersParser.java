/*
 * *
 *  * Created by Vaibhav Andhare on 13/7/20 1:14 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *
 */

package in.birdvision.equibiz.API.equibizAPI.buyer.orders.allOrders;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllOrdersParser implements JsonDeserializer<AllOrdersResponse> {
    @Override
    public AllOrdersResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        AllOrdersResponse allOrdersResponse = new AllOrdersResponse();
        try {
            final HashMap<String, List<ArrayProduct>> map = readServiceUrlMap(json.getAsJsonObject());
            if (map != null)
                allOrdersResponse.allorders = map;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return allOrdersResponse;
    }

    private HashMap<String, List<ArrayProduct>> readServiceUrlMap(JsonObject jsonObject) throws JsonSyntaxException {

        if (jsonObject == null) {
            return null;
        }
        Gson gson = new Gson();

        HashMap<String, List<ArrayProduct>> products = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {

            String key = entry.getKey();
            ArrayProduct value = gson.fromJson(entry.getValue(), ArrayProduct.class);
            products.put(key, (List<ArrayProduct>) value);
        }
        return products;
    }
}
