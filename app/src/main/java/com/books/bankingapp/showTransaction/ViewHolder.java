package com.books.bankingapp.showTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.books.bankingapp.R;
import com.books.bankingapp.database.TimeStampConverter;

import java.util.Calendar;

public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView fromName, toName, amount, date, time;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        fromName = itemView.findViewById(R.id.t_from_name);
        toName = itemView.findViewById(R.id.t_to_name);
        amount = itemView.findViewById(R.id.t_amount);
        date = itemView.findViewById(R.id.t_date);
        time = itemView.findViewById(R.id.t_time);
    }

    public void bind(String fromName, String toName, long amount, String dateTime) {
        this.fromName.setText(fromName);
        this.toName.setText(toName);
        this.amount.setText(String.valueOf(amount));

        Calendar calendar = TimeStampConverter.toCalendar(Long.parseLong(dateTime));
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "." + calendar.get(Calendar.MONTH) + 1 + "." + calendar.get(Calendar.YEAR);
        this.date.setText(date);
        String time = "";
        int minuteInt=calendar.get(Calendar.MINUTE);
        String minute;
        if(minuteInt<10){
            minute="0"+minuteInt;
        }
        else{
            minute=String.valueOf(minuteInt);
        }

        if (calendar.get(Calendar.AM_PM) == 0) {        //Forenoon
            if (calendar.get(Calendar.HOUR_OF_DAY) == 0) {
                time = "1:" + minute + " AM";
            } else {
                time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + minute + " AM";
            }
        } else {                                        //Afternoon
            if ((calendar.get(Calendar.HOUR_OF_DAY)) % 12 == 0) {
                time = "12:" + minute + " PM";
            } else {
                time = ((calendar.get(Calendar.HOUR_OF_DAY)) % 12) + ":" + minute + " PM";
            }
        }
        this.time.setText(time);
    }

    public static ViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_transaction, parent, false);
        return new ViewHolder(view);
    }
}
