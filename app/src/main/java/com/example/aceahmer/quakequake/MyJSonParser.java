package com.example.aceahmer.quakequake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MyJSonParser {
    String geoJson;
    public MyJSonParser(String geoJson) {
        this.geoJson = geoJson;
    }

    public ArrayList<DataModel> data() {
        ArrayList<DataModel> arrayList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(geoJson);
            JSONArray jsonArray = jsonObject.getJSONArray("features");
            for (int i = 0; i < jsonArray.length(); i++) {
                DecimalFormat decimalFormat = new DecimalFormat("0.0");
                arrayList.add(new DataModel(Double.parseDouble(decimalFormat.format(jsonArray.getJSONObject(i).getJSONObject("properties").getDouble("mag"))), jsonArray.getJSONObject(i).getJSONObject("properties").getString("place").toString(),jsonArray.getJSONObject(i).getJSONObject("properties").getLong("time"),jsonArray.getJSONObject(i).getJSONObject("properties").getString("url").toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

}
