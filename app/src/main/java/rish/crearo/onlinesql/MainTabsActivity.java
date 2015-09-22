package rish.crearo.onlinesql;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import rish.crearo.onlinesql.fragments.FragmentBroadcast;
import rish.crearo.onlinesql.fragments.FragmentSubPosts;
import rish.crearo.onlinesql.fragments.FragmentUser;
import rish.crearo.onlinesql.fragments.FragmentAllPosts;


public class MainTabsActivity extends ActionBarActivity {

    String actionbarcolor = "#FFC107";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(actionbarcolor));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Notifications");

        ViewPager pager = (ViewPager) findViewById(R.id.tabs_pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(0);

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_tabs);
        tabs.setViewPager(pager);
        tabs.setViewPager(pager);
        tabs.setIndicatorHeight(8);
        tabs.setIndicatorColor(Color.WHITE);
        tabs.setTextColor(Color.WHITE);
        tabs.setDividerPadding(10);
        tabs.setDividerColor(Color.parseColor("#FFC107"));
        tabs.setBackgroundColor(Color.parseColor("#FFC107"));
        tabs.setScrollOffset(50);
        tabs.setShouldExpand(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Latest", "All", "Settings", "Post"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new FragmentSubPosts();
                case 1:
                    return new FragmentAllPosts();
                case 2:
                    return new FragmentUser();
                case 3:
                    return new FragmentBroadcast();
                default:
                    return new FragmentSubPosts();
            }
        }
    }
}