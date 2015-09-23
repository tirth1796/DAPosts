package rish.crearo.onlinesql;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;

import rish.crearo.onlinesql.dbhelpers.Groups;
import rish.crearo.onlinesql.dbhelpers.Posts;
import rish.crearo.onlinesql.dbhelpers.UserPrefs;
import rish.crearo.onlinesql.helpers.UsernameDialog;

public class MainActivity extends ActionBarActivity implements Posts.VolleyCallback, Groups.VolleyCallback, UserPrefs.UserAuthenticationListener {

    boolean groups, posts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (UserPrefs.getID(getApplicationContext()) == null) {
            new UsernameDialog(MainActivity.this, MainActivity.this).show();
            //on authentication, Groups and Posts are refreshed
        } else {
            Groups.refreshGroups(getApplicationContext(), this);
            Posts.refreshPosts(this);
        }
    }

    @Override
    public void onSuccess_Groups() {
        groups = true;
    }

    @Override
    public void onFailure_Groups() {
        groups = false;
    }

    @Override
    public void onSuccess_Posts() {
        posts = true;
        startActivity(new Intent(MainActivity.this, MainTabsActivity.class));
    }

    @Override
    public void onFailure_Posts() {
        posts = false;
        startActivity(new Intent(MainActivity.this, MainTabsActivity.class));
    }

    @Override
    public void AuthenticationResult(boolean auth) {
        //if user gets authenticated, let DA's notifications get downloaded!
        if (auth) {
            Groups.refreshGroups(getApplicationContext(), this);
            Posts.refreshPosts(this);
        }
    }
}