package rish.crearo.onlinesql.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rish.crearo.onlinesql.R;

/**
 * Created by rish on 14/9/15.
 */
public class DatePickerDialog_ extends Dialog {
    Context context;
    DatePicker datePicker;
    TextView btn_okay, btn_cancel;
    getPickedDate listener;

    public DatePickerDialog_(Context context, getPickedDate listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_date_picker);

        this.setCancelable(true);

        datePicker = (DatePicker) findViewById(R.id.dialog_datePicker);
        btn_okay = (TextView) findViewById(R.id.dialogdate_okay);
        btn_cancel = (TextView) findViewById(R.id.dialogdate_cancel);

        btn_okay.setTypeface(TheFont.getPrimaryTypeface(getContext()));
        btn_cancel.setTypeface(TheFont.getPrimaryTypeface(getContext()));

        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar calendar = Calendar.getInstance();
                    String str1 = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
                    Date todaysdate = formatter.parse(str1);
                    String str2 = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();
                    Date selecteddate = formatter.parse(str2);

                    if (selecteddate.after(todaysdate) || selecteddate.equals(todaysdate)) {
                        listener.getPickedDate(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());
                        dismiss();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "Event has to be after today!", Snackbar.LENGTH_LONG)
                                .setActionTextColor(Color.RED)
                                .show();
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface getPickedDate {
        void getPickedDate(int DAY, int MONTH, int YEAR);
    }
}