package rish.crearo.onlinesql.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import rish.crearo.onlinesql.R;

/**
 * Created by rish on 24/9/15.
 */
public class FragmentCalendar extends Fragment {

    ListView calender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        calender = (ListView) rootView.findViewById(R.id.calendar_listview);

        return rootView;
    }
}
