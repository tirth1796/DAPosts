package rish.crearo.onlinesql.dbhelpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rish.crearo.onlinesql.helpers.Constants;

public class UserPrefs {

    public String id;
    public String groups;

    public static void setID(Context context, String id) {
        SharedPreferences prefid = context.getSharedPreferences(Constants.USER_PREF_KEY, Context.MODE_PRIVATE);
        prefid.edit().putString(Constants.ID_PREF_KEY, id).commit();
    }

    public static void setGroups(Context context, ArrayList<String> groups) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.USER_PREF_KEY, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(groups);

        prefs.edit().putString(Constants.GROUP_PREF_KEY, json).commit();
    }

    public static String getID(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.USER_PREF_KEY, Context.MODE_PRIVATE);
        return prefs.getString(Constants.ID_PREF_KEY, null);
    }

    public static ArrayList<String> getGroupsPrefs(Context context) {
        ArrayList<String> queue = new ArrayList<>();
        SharedPreferences prefs = context.getSharedPreferences(Constants.USER_PREF_KEY, Context.MODE_PRIVATE);
        String jsonFavorites = prefs.getString(Constants.GROUP_PREF_KEY, null);
        try {
            JSONArray jsonArray = new JSONArray(jsonFavorites);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                queue.add(jsonObject.getString("groups"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return queue;
    }


    public static void clearActionPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.USER_PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();

        ArrayList<String> empty = new ArrayList<>();
        Gson gson = new Gson();
        String json = gson.toJson(empty);

        edit.putString(Constants.USER_PREF_KEY, json);
        edit.commit();
    }
}