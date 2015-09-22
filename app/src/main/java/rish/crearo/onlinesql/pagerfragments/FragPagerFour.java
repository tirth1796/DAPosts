package rish.crearo.onlinesql.pagerfragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rish.crearo.onlinesql.R;
import rish.crearo.onlinesql.dbhelpers.Posts;

public class FragPagerFour extends Fragment implements Posts.VolleyCallback {

    Button submit;
    Posts.VolleyCallback volleyCallback;
    ProgressDialog progressDialog;
    Snackbar snackbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_frag_pager_four, container, false);

        volleyCallback = this;
        progressDialog = new ProgressDialog(getActivity());

        submit = (Button) rootView.findViewById(R.id.post_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Broadcasting ...");
                progressDialog.show();
                Posts.broadcastPost(new Posts("title", "content", "1", "by", "for", "date", "eventdate", "location", "10"), volleyCallback);
            }
        });

        return rootView;
    }

    @Override
    public void onSuccess_Posts() {
        snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Broadcast Successful!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Alright", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.show();
        progressDialog.dismiss();
    }

    @Override
    public void onFailure_Posts() {
        snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Broadcast Failed! Please try again.", Snackbar.LENGTH_INDEFINITE)
                .setAction("Alright", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
        snackbar.show();
        progressDialog.dismiss();
    }
}