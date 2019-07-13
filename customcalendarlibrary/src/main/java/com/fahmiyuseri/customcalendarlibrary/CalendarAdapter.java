package com.fahmiyuseri.customcalendarlibrary;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;



public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder> implements CalendarGridAdapter.AdapterInterface {
    private Calendar Departure = null;
    private Calendar Return = null;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    CalendarGridAdapter adapter = null;
    List<EventsObject> allEvents = null;
    private  List<CalendarModel> basicValueModelList;
    private  Context activity;
    private Context context;
    private Fragment fragment;
   AdapterInterface adapterInterface;
    public interface AdapterInterface {
         void daySelect(Calendar Depart,Calendar Return);
    }
    @Override
    public void daySelect(Calendar calendar) {
        if(Departure==null){
            Departure = calendar;
//            gridAdapter.setSelected(Departure,Return);
            notifyDataSetChanged();
            adapterInterface.daySelect(Departure,Return);

        }else{
       //  if(Return==null) {
             if (Departure != calendar) {
                 Return = calendar;

                 //gridAdapter.setSelected(Departure, Return);
                 if(Return.getTime().after(Departure.getTime())) {
                     notifyDataSetChanged();
                     adapterInterface.daySelect(Departure, Return);
                 } if( Return.getTime().equals(Departure.getTime())) {
                     notifyDataSetChanged();
                     adapterInterface.daySelect(Departure, Return);
                 }


             }
       //  }

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView calendarGridView;
        public TextView display_current_date;

        public MyViewHolder(View view) {
            super(view);
            calendarGridView = (RecyclerView) view.findViewById(R.id.rvCalendarGrid);
            display_current_date = (TextView) view.findViewById(R.id.display_current_date);
        }
    }

    public CalendarAdapter(Context activity, List<CalendarModel> basicValueModelList,AdapterInterface adapterInterface) {
        this.activity = activity;
        this.basicValueModelList = basicValueModelList;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fahmi_calendar_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CalendarModel basicValueModel = basicValueModelList.get(position);

         adapter = new CalendarGridAdapter(activity, basicValueModel.getDayValueInCells(), basicValueModel.getCalendar(),Departure,Return,allEvents, this);
        int numberOfColumns = 7;
        holder.calendarGridView.setLayoutManager(new GridLayoutManager(activity, numberOfColumns));
         holder.calendarGridView.setAdapter(adapter);
        holder.display_current_date.setText(formatter.format(basicValueModel.getCalendar().getTime()));
        //Log.d("sada", String.valueOf(basicValueModel.getGridAdapter().getCount()));



    }

    @Override
    public int getItemCount() {
        return basicValueModelList.size();
    }
    public void clear(){
        Departure = null;
        Return = null;
        notifyDataSetChanged();

    }

    public List<EventsObject> getAllEvents() {
        return allEvents;
    }

    public void setAllEvents(List<EventsObject> allEvents) {
        this.allEvents = allEvents;
    }
}
