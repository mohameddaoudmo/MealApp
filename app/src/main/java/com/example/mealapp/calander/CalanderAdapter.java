package com.example.mealapp.calander;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.mealapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
public class CalanderAdapter extends BaseAdapter  {
    private Context mContext;
    private LayoutInflater mInflater;
    private Calendar mCalendar;
    private int mCurrentDayOfMonth;
    private int mDaysInMonth;
    private int mFirstDayOfMonth;
    OnclickListnerday onclickListnerday;

    public CalanderAdapter(Context context, OnclickListnerday onclickListnerday) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mCalendar = Calendar.getInstance();
        mCurrentDayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
        mDaysInMonth = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        this.onclickListnerday = onclickListnerday;
        mFirstDayOfMonth = mCalendar.get(Calendar.DAY_OF_WEEK); // Format to display day of the week
    }
    @Override
    public int getCount() {
        return mDaysInMonth + mFirstDayOfMonth - 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);

        if (position < mFirstDayOfMonth - 1 || position >= mDaysInMonth + mFirstDayOfMonth - 1) {
            textView.setText("");
            textView.setBackgroundResource(0);
            textView.setEnabled(false);
        } else {
            int dayOfMonth = position - mFirstDayOfMonth + 2;
            textView.setText(String.valueOf(dayOfMonth));
            textView.setEnabled(true);
            if (dayOfMonth == mCurrentDayOfMonth) {
                textView.setBackgroundResource(R.drawable.shapcalander);
            } else {
                textView.setBackgroundResource(0);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedDayOfMonth = Integer.parseInt(textView.getText().toString());
                    System.out.println(clickedDayOfMonth);
                    onclickListnerday.onDayClick(clickedDayOfMonth);


                }
            });
        }

        return view;
    }




}