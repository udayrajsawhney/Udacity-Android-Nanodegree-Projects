package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getName();

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        final String KEY_NAME = "name";
        final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String KEY_DESCRIPTION = "description";
        final String KEY_IMAGE = "image";
        final String KEY_MAINNAME = "mainName";
        final String KEY_INGRRDIENTS = "ingredients";
        final String KEY_ALSO_KNOW_AS= "alsoKnownAs";


        JSONObject sandwichObject = new JSONObject(json);
        //get the name JSONObject
        JSONObject name = sandwichObject.getJSONObject(KEY_NAME);

        //get origin
        String origin = sandwichObject.getString(KEY_PLACE_OF_ORIGIN);
        //get description
        String description = sandwichObject.getString(KEY_DESCRIPTION);
        //get image
        String image = sandwichObject.getString(KEY_IMAGE);
        //get nameString
        String nameString = name.getString(KEY_MAINNAME);

        //i will create method getArrayObject since our data structure is in a List of type string to get the ingredients and alsoknowas.
        List<String> ingredients = getArrayObject(sandwichObject.getJSONArray(KEY_INGRRDIENTS));

        List<String> asKnowAsName = getArrayObject(name.getJSONArray(KEY_ALSO_KNOW_AS));

        // return the Sandwich class which takes six parameters.
        return new Sandwich(nameString, asKnowAsName, origin, description, image, ingredients);
    }
    private static List<String> getArrayObject(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        try {
            int n = jsonArray.length();
            for (int i = 0; i < n; i++) {
                list.add(jsonArray.getString(i));
            }

        } catch (JSONException e ){

            Log.e(TAG,"Error parsing",e);

        }
        return list;
    }
}
