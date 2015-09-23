package rish.crearo.onlinesql.dbhelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rish.crearo.onlinesql.helpers.Constants;

/**
 * Created by rish on 22/9/15.
 */
public class Groups {

    public static ArrayList<Group> getGroupNames(Context context) {
        System.out.println("Context null = " + context);
        SharedPreferences prefs = context.getSharedPreferences(Constants.MAIN_PREF_KEY, Context.MODE_PRIVATE);
        ArrayList<Group> storedgroups = new ArrayList<>();
        String jsonFavorites = prefs.getString(Constants.GROUP_PREF_KEY, null);
        System.out.println("JSONFAVS = " + jsonFavorites);
        try {
            JSONArray jsonArray = new JSONArray(jsonFavorites);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                storedgroups.add(new Group(jsonObject.getString("name")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return storedgroups;
    }

    public static void refreshGroups(final Context context, final VolleyCallback callback) {
        final ArrayList<Group> groupnames = new ArrayList<>();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Constants.BASE_URL_GROUPS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
                try {
                    JSONArray postsArray = response.getJSONArray(Constants.TAG_GROUPS);
                    for (int i = 0; i < postsArray.length(); i++) {
                        JSONObject jsonObject = postsArray.getJSONObject(i);
                        groupnames.add(new Group(jsonObject.getString("g_name")));
                    }
                    setGroups(context, groupnames);
                    callback.onSuccess_Groups();
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailure_Groups();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error message man " + error.getMessage());
                VolleyLog.d("error man", "Error: " + error.getMessage());
                callback.onFailure_Groups();
            }
        });
        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    public static void setGroups(Context context, ArrayList<Group> groups) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.MAIN_PREF_KEY, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(groups);

        prefs.edit().putString(Constants.GROUP_PREF_KEY, json).commit();
    }

    public interface VolleyCallback {
        void onSuccess_Groups();

        void onFailure_Groups();
    }
}