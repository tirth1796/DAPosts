package rish.crearo.onlinesql;

import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import rish.crearo.onlinesql.dbhelpers.Group;
import rish.crearo.onlinesql.dbhelpers.Groups;
import rish.crearo.onlinesql.dbhelpers.UserPrefs;


public class Settings extends ActionBarActivity {

    LinearLayout linearLayout_groupsPrefs;
    EditText webmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        linearLayout_groupsPrefs = (LinearLayout) findViewById(R.id.ll_groups_prefs);
        webmailID = (EditText) findViewById(R.id.webmail_id);

        webmailID.setText("" + UserPrefs.getID(getApplicationContext()));
        addGroups();
    }

    private void addGroups() {
        ArrayList<Group> prefGroups = UserPrefs.getGroupsPrefs(getApplicationContext());
        for (Group group : Groups.getGroupNames(getApplicationContext())) {
            final CheckBox checkBox = new CheckBox(getApplicationContext());
            checkBox.setText(group.name);
            checkBox.setTextColor(getResources().getColor(R.color.textcolor_sec));
            if (prefGroups != null)
                if (isWordInList(group.name, prefGroups))
                    checkBox.setChecked(true);
                else
                    checkBox.setChecked(false);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ArrayList<Group> queue = new ArrayList<>();
                    for (int i = 0; i < linearLayout_groupsPrefs.getChildCount(); i++) {
                        CheckBox checkBox1 = (CheckBox) linearLayout_groupsPrefs.getChildAt(i);
                        if (checkBox1.isChecked())
                            queue.add(new Group(checkBox1.getText().toString()));
                    }
                    UserPrefs.setGroups(getApplicationContext(), queue);
                }
            });
            linearLayout_groupsPrefs.addView(checkBox);
            System.out.println("Added checkbox!");
        }
    }

    public boolean isWordInList(String string, ArrayList<Group> groups) {
        for (Group g : groups) {
            if (string.equals(g.name)) {
                return true;
            }
        }
        return false;
    }
}
