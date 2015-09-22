package rish.crearo.onlinesql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import rish.crearo.onlinesql.dbhelpers.Groups;
import rish.crearo.onlinesql.dbhelpers.Posts;

public class MainActivity extends ActionBarActivity implements Posts.VolleyCallback, Groups.VolleyCallback {

    boolean groups, posts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Groups.refreshGroups(getApplicationContext(), this);
        Posts.refreshPosts(this);
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
}