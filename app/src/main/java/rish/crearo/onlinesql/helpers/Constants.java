package rish.crearo.onlinesql.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rish on 22/9/15.
 */
public class Constants {

    public static String BASE_URL = "http://192.168.150.1:8080/";
    public static String BASE_URL_GROUPS = "http://192.168.150.1:8080/groups";
    public static String BASE_URL_POSTS = "http://192.168.150.1:8080/posts";
    public static String BASE_URL_POST = "http://192.168.150.1:8080/post";

    public static final String TAG_GROUPS = "groups";
    public static final String TAG_DBNAME = "posts";
    public static final String TAG_ID = "id";
    public static final String TAG_TITLE = "p_title";
    public static final String TAG_CONTENT = "p_content";
    public static final String TAG_DATE_POSTED = "p_dateposted";
    public static final String TAG_DATE_EVENT = "p_dateevent";
    public static final String TAG_PRIORITY = "p_priority";
    public static final String TAG_ATTENDANCE = "p_attendance";
    public static final String TAG_LOCATION = "p_location";
    public static final String TAG_POSTED_BY = "p_posted_by";
    public static final String TAG_POSTED_FOR = "p_posted_for";
    public static final String USER_PREF_KEY = "user_pref_key";
    public static final String GROUP_PREF_KEY = "group_pref_key";
    public static final String ID_PREF_KEY = "id_pref_key";
    public static final String MAIN_PREF_KEY = "id_pref_key";

    public static String getMonthFromNumber(int month) {
        switch (month) {
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "Aug";
            case 8:
                return "Sept";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
            default:
                return "You have a sad phone man";
        }
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("hh:mm:ss").format(new Date());
    }

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
    }


}
