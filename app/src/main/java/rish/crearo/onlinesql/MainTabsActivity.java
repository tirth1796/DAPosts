package rish.crearo.onlinesql;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import rish.crearo.onlinesql.fragments.FragmentAllPosts;
import rish.crearo.onlinesql.fragments.FragmentCalendar;
import rish.crearo.onlinesql.fragments.FragmentSubPosts;


public class MainTabsActivity extends ActionBarActivity {

    String actionbarcolor = "#FFC107";
    FloatingActionButton fab;

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

        fab = (FloatingActionButton) findViewById(R.id.tabs_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainTabsActivity.this, NewBroadcast.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainTabsActivity.this, Settings.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"Latest", "All", "Calendar"};

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
                    return new FragmentCalendar();
                default:
                    return new FragmentSubPosts();
            }
        }
    }
}