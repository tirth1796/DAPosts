package rish.crearo.onlinesql.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rish.crearo.onlinesql.R;

public class FragmentSubPosts extends Fragment {


    public FragmentSubPosts() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_subscribed_posts,
                container, false);

        return rootView;
    }

}