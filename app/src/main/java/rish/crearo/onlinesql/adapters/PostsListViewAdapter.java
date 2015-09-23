package rish.crearo.onlinesql.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import rish.crearo.onlinesql.R;
import rish.crearo.onlinesql.dbhelpers.Posts;
import rish.crearo.onlinesql.helpers.Constants;
import rish.crearo.onlinesql.helpers.TheFont;

public class PostsListViewAdapter extends ArrayAdapter<Posts> {

    public PostsListViewAdapter(Context context, ArrayList<Posts> notistructs) {
        super(context, R.layout.element_post, notistructs);
    }

    private static class ViewHolder {
        TextView h_title, h_content, h_date, h_location, h_priority, h_from;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Posts notistruct = getItem(position);
        ViewHolder viewHolder;
        Typeface type = TheFont.getPrimaryTypeface(getContext());
        Typeface typeBold = TheFont.getPrimaryTypefaceBold(getContext());

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.element_post, null);
            viewHolder.h_title = (TextView) convertView.findViewById(R.id.listelement_title);
            viewHolder.h_content = (TextView) convertView.findViewById(R.id.listelement_content);
            viewHolder.h_date = (TextView) convertView.findViewById(R.id.listelement_date);
            viewHolder.h_location = (TextView) convertView.findViewById(R.id.listelement_location);
            viewHolder.h_priority = (TextView) convertView.findViewById(R.id.listelement_priority);
            viewHolder.h_from = (TextView) convertView.findViewById(R.id.listelement_from);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (viewHolder != null) {
            viewHolder.h_title.setTypeface(typeBold);
            viewHolder.h_content.setTypeface(type);
            viewHolder.h_priority.setTypeface(type);
            viewHolder.h_location.setTypeface(type);
            viewHolder.h_date.setTypeface(type);
            viewHolder.h_from.setTypeface(type);
            viewHolder.h_content.setText(notistruct.ns_content);
            viewHolder.h_title.setText(notistruct.ns_title);
            viewHolder.h_priority.setText(notistruct.ns_prioritylevel);
            viewHolder.h_location.setText(notistruct.ns_location);
            viewHolder.h_from.setText(notistruct.ns_from);
            viewHolder.h_date.setText(notistruct.ns_duedate);

            try {
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date event_date = format.parse(notistruct.ns_duedate.split(" ")[1]);
                int diffInDays = (int) ((event_date.getTime() - Constants.getCurrentDate().getTime()) / (1000 * 60 * 60 * 24));
                String time = notistruct.ns_duedate.split(" ")[0];
                time = time.substring(0, time.length() - 3);
                if (diffInDays == -3) {
                    viewHolder.h_date.setText("3 days ago");
                } else if (diffInDays == -2) {
                    viewHolder.h_date.setText("2 days ago");
                } else if (diffInDays == -1) {
                    viewHolder.h_date.setText("Yesterday");
                } else if (diffInDays == 0) {
                    viewHolder.h_date.setText("Today at " + time);
                } else if (diffInDays == 1) {
                    viewHolder.h_date.setText("Tomorrow at " + time);
                } else if (diffInDays == 2) {
                    viewHolder.h_date.setText("In 2 days at " + time);
                } else if (diffInDays == 3) {
                    viewHolder.h_date.setText("In 3 days at " + time);
                }

            } catch (Exception e) {
                viewHolder.h_date.setText(notistruct.ns_duedate);
            }

            switch (notistruct.ns_prioritylevel) {
                case "1":
                    viewHolder.h_priority.setBackgroundResource(R.color.priority_0);
                    break;
                case "2":
                    viewHolder.h_priority.setBackgroundResource(R.color.priority_1);
                    break;
                case "3":
                    viewHolder.h_priority.setBackgroundResource(R.color.priority_2);
                    break;
                default:
                    viewHolder.h_priority.setBackgroundResource(R.color.priority_0);
            }
        }
        return convertView;
    }
}