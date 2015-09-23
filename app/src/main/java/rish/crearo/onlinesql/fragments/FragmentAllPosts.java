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

public class FragmentAllPosts extends Fragment {

    private ListView listview;
    private ArrayAdapter<Posts> adapter;
    private ArrayList<Posts> postslist;

    public FragmentAllPosts() {
        postslist = (ArrayList<Posts>) Posts.listAll(Posts.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_all_posts,
                container, false);

        listview = (ListView) rootView.findViewById(R.id.fragtwo_listView);
        adapter = new PostsListViewAdapter(getActivity().getApplicationContext(), postslist);
        listview.setAdapter(adapter);

        postslist = (ArrayList<Posts>) Posts.listAll(Posts.class);
        adapter.notifyDataSetChanged();

        return rootView;
    }
}