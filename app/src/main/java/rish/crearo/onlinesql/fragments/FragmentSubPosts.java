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
import rish.crearo.onlinesql.dbhelpers.Group;
import rish.crearo.onlinesql.dbhelpers.Groups;
import rish.crearo.onlinesql.dbhelpers.Posts;
import rish.crearo.onlinesql.dbhelpers.UserPrefs;

public class FragmentSubPosts extends Fragment {

    private ListView listview;
    private ArrayAdapter<Posts> adapter;
    private ArrayList<Posts> postslist;

    public FragmentSubPosts() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_subscribed_posts, container, false);

        postslist = new ArrayList<>();
        ArrayList<Posts> allPosts = (ArrayList<Posts>) Posts.findWithQuery(Posts.class, "Select * from Posts order by ? DESC;", "ns_duedate");
        ArrayList<Group> subscribedgroups = UserPrefs.getGroupsPrefs(getActivity());

        for (Posts post : allPosts) {
            if (isWordInList(post.ns_from, subscribedgroups))
                postslist.add(post);
        }

        listview = (ListView) rootView.findViewById(R.id.subposts_listView);
        adapter = new PostsListViewAdapter(getActivity().getApplicationContext(), postslist);
        listview.setAdapter(adapter);

        postslist = (ArrayList<Posts>) Posts.listAll(Posts.class);
        adapter.notifyDataSetChanged();

        return rootView;
    }

    public boolean isWordInList(String string, ArrayList<Group> groups) {
        for (Group g : groups) {
            if (string.equals(g.name))
                return true;
        }
        return false;
    }
}