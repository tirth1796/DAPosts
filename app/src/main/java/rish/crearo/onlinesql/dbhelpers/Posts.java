package rish.crearo.onlinesql.dbhelpers;

import android.app.ProgressDialog;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;
import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rish.crearo.onlinesql.helpers.Constants;

/**
 * Created by rish on 3/5/15.
 */
public class Posts extends SugarRecord<Posts> {

    public Posts() {
    }

    public int ns_id;
    public String ns_title;
    public String ns_content;
    public String ns_prioritylevel;
    public String ns_from;
    public String ns_for; // csv as there will be many
    public String ns_postdate;
    public String ns_duedate;
    public String ns_location;
    public String ns_attendancecount;


    public Posts(int ns_id, String ns_title, String ns_content, String ns_prioritylevel, String ns_from, String ns_for, String ns_postdate, String ns_duedate, String ns_location, String ns_attendancecount) {
        this.ns_id = ns_id;
        this.ns_title = ns_title;
        this.ns_content = ns_content;
        this.ns_prioritylevel = ns_prioritylevel;
        this.ns_from = ns_from;
        this.ns_for = ns_for;
        this.ns_postdate = ns_postdate;
        this.ns_duedate = ns_duedate;
        this.ns_location = ns_location;
        this.ns_attendancecount = ns_attendancecount;
    }

    public Posts(String ns_title, String ns_content, String ns_prioritylevel, String ns_from, String ns_for, String ns_postdate, String ns_duedate, String ns_location, String ns_attendancecount) {
        this.ns_title = ns_title;
        this.ns_content = ns_content;
        this.ns_prioritylevel = ns_prioritylevel;
        this.ns_from = ns_from;
        this.ns_for = ns_for;
        this.ns_postdate = ns_postdate;
        this.ns_duedate = ns_duedate;
        this.ns_location = ns_location;
        this.ns_attendancecount = ns_attendancecount;
    }

    public static void refreshPosts(final VolleyCallback callback) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Constants.BASE_URL_POSTS + "/" + getMostRecentPostID(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Printing the response => ");

                Log.d("response", response.toString());
                try {
                    JSONArray postsArray = response.getJSONArray(Constants.TAG_DBNAME);

                    for (int i = 0; i < postsArray.length(); i++) {

                        JSONObject jobj = postsArray.getJSONObject(i);

                        int id = 0 + jobj.getInt(Constants.TAG_ID);
                        String title = "" + jobj.getString(Constants.TAG_TITLE);
                        String content = "" + jobj.getString(Constants.TAG_CONTENT);
                        String posteddate = "" + jobj.getString(Constants.TAG_DATE_POSTED);
                        String priority = "" + jobj.getString(Constants.TAG_PRIORITY);
                        String attendance = "" + jobj.getString(Constants.TAG_ATTENDANCE);
                        String eventdate = "" + jobj.getString(Constants.TAG_DATE_EVENT);
                        String location = "" + jobj.getString(Constants.TAG_LOCATION);
                        String postedby = "" + jobj.getString(Constants.TAG_POSTED_BY);
                        String postedfor = "" + jobj.getString(Constants.TAG_POSTED_FOR);
                        System.out.println("data - " + title + " ()" + content + " ()" + " ()" + priority + " ()" + attendance + " ()" + eventdate + " ()" + location + " ()");

                        Posts newPost = new Posts(id, title, content, priority, postedby, postedfor, posteddate, eventdate, location, attendance);
                        newPost.save();
                        System.out.println("saved newPost with title " + title);
                    }
                    callback.onSuccess_Posts();
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFailure_Posts();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error message man " + error.getMessage());
                VolleyLog.d("error man", "Error: " + error.getMessage());
                callback.onFailure_Posts();
            }
        });
        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    public static void broadcastPost(Posts post, final VolleyCallback callback) {
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("p_title", post.ns_title);
        params.put("p_content", "" + post.ns_content);
        params.put("p_dateposted", "" + post.ns_postdate);
        params.put("p_dateevent", "" + post.ns_duedate);
        params.put("p_priority", "" + post.ns_prioritylevel);
        params.put("p_attendance", post.ns_attendancecount);
        params.put("p_location", post.ns_location);
        params.put("p_posted_by", post.ns_from);
        params.put("p_posted_for", post.ns_for);
        JsonObjectRequest req = new JsonObjectRequest(Constants.BASE_URL_POST, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("RESPONSE = " + response);
                        callback.onSuccess_Posts();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                callback.onFailure_Posts();
            }
        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<String, String>();
//                String creds = String.format("%s:%s", Constants.API_USERNAME, Constants.API_PASSWD);
//                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
//                params.put("Authorization", auth);
//                return params;
//            }
        };

        // add the request object to the queue to be executed
        AppController.getInstance().addToRequestQueue(req);
    }

    public static int getMostRecentPostID() {
        int id = 0;
        List<Posts> posts = Posts.listAll(Posts.class);
        for (Posts post : posts) {
            if (post.ns_id > id)
                id = post.ns_id;
        }
        return id;
    }

    public interface VolleyCallback {
        void onSuccess_Posts();

        void onFailure_Posts();
    }
}