package rish.crearo.onlinesql.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import rish.crearo.onlinesql.R;
import rish.crearo.onlinesql.dbhelpers.Group;
import rish.crearo.onlinesql.dbhelpers.Groups;
import rish.crearo.onlinesql.dbhelpers.UserPrefs;

public class FragmentUser extends Fragment {

    LinearLayout linearLayout_groupsPrefs;
    EditText webmailID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user, container, false);

        linearLayout_groupsPrefs = (LinearLayout) rootView.findViewById(R.id.ll_groups_prefs);
        webmailID = (EditText) rootView.findViewById(R.id.webmail_id);

        webmailID.setText("" + UserPrefs.getID(getActivity()));
        addGroups();

        return rootView;
    }

    private void addGroups() {
//        ArrayList<String> prefGroups = UserPrefs.getGroupsPrefs(getActivity());
        for (Group group : Groups.getGroupNames(getActivity())) {
            final CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setText(group.name);
//            if (isWordInList(groupName, prefGroups))
//                checkBox.setChecked(true);
//            else
//                checkBox.setChecked(false);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ArrayList<String> queue = new ArrayList<String>();
                    for (int i = 0; i < linearLayout_groupsPrefs.getChildCount(); i++) {
                        CheckBox checkBox1 = (CheckBox) linearLayout_groupsPrefs.getChildAt(i);
                        if (checkBox1.isChecked())
                            queue.add("" + checkBox1.getText());
                    }
                    UserPrefs.setGroups(getActivity(), queue);
                }
            });
            linearLayout_groupsPrefs.addView(checkBox);
            System.out.println("Added checkbox!");
        }
    }

    public boolean isWordInList(String string, ArrayList<String> strings) {
        for (String s : strings) {
            if (string.equals(s)) {
                return true;
            }
        }
        return false;
    }
}