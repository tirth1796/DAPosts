package rish.crearo.onlinesql.pagerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rish.crearo.onlinesql.R;

public class FragPagerOne extends Fragment {


    public FragPagerOne() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frag_pager_one,
                container, false);

        return rootView;
    }

}