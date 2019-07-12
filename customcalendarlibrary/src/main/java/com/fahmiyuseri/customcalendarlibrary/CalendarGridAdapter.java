    package com.fahmiyuseri.customcalendarlibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.*;


    public class CalendarGridAdapter extends RecyclerView.Adapter<CalendarGridAdapter.MyViewHolder> {
        private Calendar Departure = null;
        private Calendar Return = null;
        private LayoutInflater mInflater;
        private List<Date> monthlyDates;
        private Calendar currentDate;
        CalendarGridAdapter.AdapterInterface buttonListener;
        Context context;
        private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        private List<EventsObject> allEvents = new ArrayList<>();
        private List<Calendar> selected = new ArrayList<>();
        private View finalView;

        public interface AdapterInterface {
            public void daySelect(Calendar calendar);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView calendar_date_id;
        public TextView event_id;

        public MyViewHolder(View view) {
            super(view);
            calendar_date_id = (TextView) view.findViewById(R.id.calendar_date_id);
            event_id = (TextView) view.findViewById(R.id.event_id);
        }
    }

        public CalendarGridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate, Calendar Departure, Calendar Return,List<EventsObject> allEvents, CalendarGridAdapter.AdapterInterface buttonListener
        ) {
            this.context = context;
            this.monthlyDates = monthlyDates;
            this.currentDate = currentDate;
            this.Departure = Departure;
            this.Return = Return;
            this.buttonListener = buttonListener;
            this.allEvents = allEvents;
            mInflater = LayoutInflater.from(context);
        }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_cell_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Date mDate = monthlyDates.get(position);
        final Calendar dateCal = Calendar.getInstance();
        Calendar curDate = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = holder.itemView;
        view.setBackgroundColor(Color.parseColor("#FFFFFF"));

        //Add day to calendar
        TextView cellNumber = holder.calendar_date_id;
        if(displayMonth == currentMonth && displayYear == currentYear){
            cellNumber.setText(String.valueOf(dayValue));
            String sCurDate = formatter.format(dateCal.getTime());

            if(Departure!=null){
                String sDeparture = formatter.format(Departure.getTime());

                if(sDeparture.equals(sCurDate)){
                    cellNumber.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.event_id.setTextColor(Color.parseColor("#FFFFFF"));
                    view.setBackground(context.getResources().getDrawable(R.drawable.oval));

                }


            }
            if(Return!=null){
                String sReturn = formatter.format(Return.getTime());
                if(dateCal.getTime().after(Departure.getTime())&&dateCal.getTime().before(Return.getTime())){
                    view.setBackgroundColor(Color.parseColor("#FF63AD67"));
                    cellNumber.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.event_id.setTextColor(Color.parseColor("#FFFFFF"));



                }
                if(sReturn.equals(sCurDate)){
                    view.setBackground(context.getResources().getDrawable(R.drawable.oval));
                    cellNumber.setTextColor(Color.parseColor("#FFFFFF"));
                    holder.event_id.setTextColor(Color.parseColor("#FFFFFF"));




                }

            }

            //Add events to the calendar
            TextView eventIndicator = (TextView)view.findViewById(R.id.event_id);

            Calendar eventCalendar = Calendar.getInstance();

            if(allEvents!=null) {
                for (int i = 0; i < allEvents.size(); i++) {
                    eventCalendar.setTime(allEvents.get(i).getDate());
                    if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                            && displayYear == eventCalendar.get(Calendar.YEAR)) {
                        if(allEvents.get(i).getColorEvent()!=null){
                            holder.event_id.setTextColor(Color.parseColor(allEvents.get(i).getColorEvent()));

                        }

                        eventIndicator.setText(allEvents.get(i).getMessage());

                    }
                }
            }
        }else{
            cellNumber.setText("");

        }
        if(currentMonth==curDate.get(Calendar.MONTH)+1) {
            if (dateCal.getTime().getTime() < currentDate.getTime().getTime()) {
                cellNumber.setEnabled(false);

            }
        }


        finalView = view;


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonListener.daySelect(dateCal);
             /*   if(Departure==null){
                    finalView.setBackgroundColor(Color.parseColor("#FF4081"));
                    Departure = dateCal;
                }
                else if(Return==null){
                    finalView.setBackgroundColor(Color.parseColor("#FF4081"));
                    Return = dateCal;



                }else{
                    if(dateCal==Return){
                        finalView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        Return = null;

                    }
                }*/

            }
        });


    }

    @Override
    public int getItemCount() {
        return monthlyDates.size();
    }
}
