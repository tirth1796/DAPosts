package rish.crearo.onlinesql.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import rish.crearo.onlinesql.R;
import rish.crearo.onlinesql.dbhelpers.Posts;
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
        System.out.println("no - " + notistruct.ns_title);
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
            viewHolder.h_date.setText(notistruct.ns_duedate);
            viewHolder.h_from.setText(notistruct.ns_from);
            switch (notistruct.ns_prioritylevel) {
                case "1":
                    viewHolder.h_priority.setBackgroundColor(Color.parseColor("#CDDC39"));
                    break;
                case "2":
                    viewHolder.h_priority.setBackgroundColor(Color.parseColor("#FFC107"));
                    break;
                case "3":
                    viewHolder.h_priority.setBackgroundColor(Color.parseColor("#FF5722"));
                    break;
                default:
                    viewHolder.h_priority.setBackgroundColor(Color.parseColor("#CDDC39"));
            }
        }
        return convertView;
    }
}


//public class CustomListViewAdapter extends BaseAdapter {
//
//    LayoutInflater inflater = null;
//    Context context;
//
//    ArrayList<NotiStruct> notistruct_list = new ArrayList<>();
//
//    public CustomListViewAdapter(Context context, ArrayList<NotiStruct> notistruct_list) {
//        this.context = context;
//        this.notistruct_list = notistruct_list;
//        inflater = (LayoutInflater) context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    public void updateResults(ArrayList<NotiStruct> results) {
////        notistruct_list = results;
////        //Triggers the list update
////        this.notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return notistruct_list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return notistruct_list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    public class Holder {
//        TextView h_title, h_content, h_date, h_location, h_priority;
//
//        public Holder(View view) {
//            h_title = (TextView) view.findViewById(R.id.listelement_title);
//            h_content = (TextView) view.findViewById(R.id.listelement_content);
//            h_date = (TextView) view.findViewById(R.id.listelement_date);
//            h_location = (TextView) view.findViewById(R.id.listelement_location);
//            h_priority = (TextView) view.findViewById(R.id.listelement_priority);
//            view.setTag(this);
//        }
//    }
//
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        View rowView = convertView;
//        Holder holder = null;
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.element_post, null);
//            holder = new Holder(convertView);
//        }
////        Holder holder = (Holder) convertView.getTag();
//
//        rowView = inflater.inflate(R.layout.element_post, null);
//
//        holder.h_content.setText(notistruct_list.get(position).ns_content);
//        holder.h_title.setText(notistruct_list.get(position).ns_title);
//        holder.h_priority.setText(notistruct_list.get(position).ns_prioritylevel);
//        holder.h_location.setText(notistruct_list.get(position).ns_location);
//        holder.h_date.setText(notistruct_list.get(position).ns_duedate);
//
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "You Clicked " + notistruct_list.get(position).ns_title, Toast.LENGTH_LONG).show();
//            }
//        });
//        return rowView;
//    }
//
//}