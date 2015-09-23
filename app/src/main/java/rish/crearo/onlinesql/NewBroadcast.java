package rish.crearo.onlinesql;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rish.crearo.onlinesql.dbhelpers.Posts;
import rish.crearo.onlinesql.helpers.Constants;
import rish.crearo.onlinesql.helpers.DatePickerDialog_;
import rish.crearo.onlinesql.helpers.TimePickerDialog_;

public class NewBroadcast extends ActionBarActivity implements Posts.VolleyCallback, TimePickerDialog_.getPickedTime, DatePickerDialog_.getPickedDate {

    Button submit;
    Posts.VolleyCallback volleyCallback;
    ProgressDialog progressDialog;
    Snackbar snackbar;
    EditText title;
    EditText content;
    TextView time;
    TextView date;
    EditText location;
    String EVENT_DATE;
    String EVENT_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_broadcast);
        volleyCallback = this;
        progressDialog = new ProgressDialog(getApplicationContext());

        title = (EditText) findViewById(R.id.post_title);
        location = (EditText) findViewById(R.id.post_location);
        content = (EditText) findViewById(R.id.post_contenet);
        time = (TextView) findViewById(R.id.post_time_picker);
        date = (TextView) findViewById(R.id.post_date_picker);
        submit = (Button) findViewById(R.id.post_submit);

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Just Saying");
        spinnerArray.add("Reminder");
        spinnerArray.add("Very Important");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner _spinner = (Spinner) findViewById(R.id.post_spinner);
        _spinner.setAdapter(adapter);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog_(NewBroadcast.this, NewBroadcast.this).show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog_(NewBroadcast.this, NewBroadcast.this).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Broadcasting ...");
                progressDialog.show();
                String _title = title.getText().toString();
                String _content = content.getText().toString();
                String _location = location.getText().toString();
                String _currentdatetime = Constants.getCurrentDateTime();
                String _priority = "" + _spinner.getSelectedItemPosition();
                String _eventdate = EVENT_TIME + " " + EVENT_DATE;
                Posts.broadcastPost(new Posts(_title, _content, _priority, "by", "for", _currentdatetime, _eventdate, _location, "1"), volleyCallback);
            }
        });
    }

    @Override
    public void onSuccess_Posts() {
        snackbar = Snackbar.make(findViewById(android.R.id.content), "Broadcast Successful!", Snackbar.LENGTH_INDEFINITE)
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
        snackbar = Snackbar.make(findViewById(android.R.id.content), "Broadcast Failed! Please try again.", Snackbar.LENGTH_INDEFINITE)
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
    public void getPickedTime(int HOUR, int MIN) {
        EVENT_TIME = new DecimalFormat("00").format(HOUR) + ":" + new DecimalFormat("00").format(MIN) + ":" + "00";
        time.setText(new DecimalFormat("00").format(HOUR) + " : " + new DecimalFormat("00").format(MIN));

    }

    @Override
    public void getPickedDate(int DAY, int MONTH, int YEAR) {
        EVENT_DATE = new DecimalFormat("00").format(DAY) + "-" + new DecimalFormat("00").format(MONTH + 1) + "-" + new DecimalFormat("0000").format(YEAR);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            String str1 = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
            Date todaysdate = formatter.parse(str1);
            String str2 = DAY + "/" + MONTH + "/" + YEAR;
            Date selecteddate = formatter.parse(str2);
            int diffInDays = (int) ((selecteddate.getTime() - todaysdate.getTime()) / (1000 * 60 * 60 * 24));

            if (diffInDays == 0) {
                date.setText("Today");
            } else if (diffInDays == 1) {
                date.setText("Tomorrow");
            } else if (diffInDays == 2) {
                date.setText("Day After Tomorrow");
            }
        } catch (Exception e) {
            date.setText((DAY) + " " + Constants.getMonthFromNumber(MONTH));
        }
    }
}