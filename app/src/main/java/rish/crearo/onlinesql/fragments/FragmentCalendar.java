package rish.crearo.onlinesql.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import rish.crearo.onlinesql.R;
import rish.crearo.onlinesql.adapters.PostsListViewAdapter;
import rish.crearo.onlinesql.dbhelpers.Posts;

/**
 * Created by rish on 24/9/15.
 */
public class FragmentCalendar extends Fragment {

    private ListView listview;
    private ArrayAdapter<Posts> adapter;
    private ArrayList<Posts> postslist;

    public FragmentCalendar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        listview = (ListView) rootView.findViewById(R.id.calendar_listview);

        postslist = (ArrayList<Posts>) Posts.findWithQuery(Posts.class, "Select * from Posts order by ? DESC;", "ns_duedate");
        adapter = new PostsListViewAdapter(getActivity().getApplicationContext(), postslist);
        adapter.notifyDataSetChanged();

        return rootView;
    }
}
