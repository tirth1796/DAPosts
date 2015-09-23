package rish.crearo.onlinesql.helpers;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import rish.crearo.onlinesql.R;

public class TimePickerDialog_ extends Dialog {
    Context context;
    int INIT_HOUR = Calendar.HOUR_OF_DAY, INIT_MIN = Calendar.MINUTE;
    TimePicker timePicker;
    TextView btn_okay, btn_cancel;
    getPickedTime listener;

    public TimePickerDialog_(Context context, getPickedTime listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_timepicker);

        this.setCancelable(true);

        timePicker = (TimePicker) findViewById(R.id.dialog_timePicker);
        btn_okay = (TextView) findViewById(R.id.dialog_okay);
        btn_cancel = (TextView) findViewById(R.id.dialog_cancel);

        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(INIT_HOUR);
        timePicker.setCurrentMinute(INIT_MIN);

        btn_okay.setTypeface(TheFont.getPrimaryTypeface(getContext()));
        btn_cancel.setTypeface(TheFont.getPrimaryTypeface(getContext()));

        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getPickedTime(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                
                dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface getPickedTime {
        void getPickedTime(int HOUR, int MIN);
    }
}