package com.fahmiyuseri.customcalendarlibrary;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class CalendarCustomView extends LinearLayout{

    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    private GridView calendarGridView;
    private Button addEventButton;
    private RecyclerView rvListDate;
    private static final int MAX_CALENDAR_COLUMN = 35;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private Context context;
    List<CalendarModel> listCalendar = new ArrayList<>();
    private CalendarAdapter calendarAdapter;
    CalendarAdapter.AdapterInterface adapterInterface;

    public CalendarCustomView(Context context) {
        super(context);
    }
    public CalendarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeUILayout();
       // setUpCalendarAdapter(1);
        //setPreviousButtonClickEvent();
        //setNextButtonClickEvent();
       // setGridCellClickEvents();
        Log.d(TAG, "I need to call this method");
    }
    public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initializeUILayout(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fahmi_custom_layout, this);
       /* previousButton = (ImageView)view.findViewById(R.id.previous_month);
        nextButton = (ImageView)view.findViewById(R.id.next_month);
        currentDate = (TextView)view.findViewById(R.id.display_current_date);
        addEventButton = (Button)view.findViewById(R.id.add_calendar_event);
        calendarGridView = (GridView)view.findViewById(R.id.calendar_grid);*/
        rvListDate = (RecyclerView) view.findViewById(R.id.rvListDate);
        LinearLayoutManager mLayoutManagerBestDeals = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvListDate.setLayoutManager(mLayoutManagerBestDeals);
         calendarAdapter = new CalendarAdapter(context, listCalendar, new CalendarAdapter.AdapterInterface() {
             @Override
             public void daySelect(Calendar Depart, Calendar Return) {
                 adapterInterface.daySelect(Depart,Return);
             }
         });
        rvListDate.setAdapter(calendarAdapter);
        //cal.add(Calendar.MONTH, 2);
        for(int i=0;i<20;i++){
            setUpCalendarAdapter(i);
        }

    }
    private void setPreviousButtonClickEvent(){
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //cal.add(Calendar.MONTH, -1);
              //  setUpCalendarAdapter();
            }
        });
    }
    private void setNextButtonClickEvent(){
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
          //      cal.add(Calendar.MONTH, 1);
                //setUpCalendarAdapter();
            }
        });
    }
    private void setGridCellClickEvents(){
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Clicked " + position, Toast.LENGTH_LONG).show();
            }
        });
    }
    private void setUpCalendarAdapter(int i){
        List<Date> dayValueInCells = new ArrayList<Date>();
        List<GridObject> gridObjectList = new ArrayList<GridObject>();
        GridObject gridObject = null;
     //   mQuery = new DatabaseQuery(context);
      //  List<EventObjects> mEvents = mQuery.getAllFutureEvents();
       // cal.add(Calendar.MONTH, 2);
         Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.add(Calendar.MONTH,i);
        Calendar mCal = (Calendar)cal.clone();
        GridView calendarGrid = new GridView(context);
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d(TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
//        currentDate.setText(sDate);
        gridObject = new GridObject(dayValueInCells, cal,null);
        //mAdapter = new GridAdapter(context, dayValueInCells, cal, null);

        listCalendar.add(new CalendarModel(dayValueInCells,cal,null));
        calendarAdapter.notifyDataSetChanged();


//try





    }
    public void clearSelectedDate(){
        calendarAdapter.clear();
    }
    public void setEvents(List<EventsObject> allEvents){
        calendarAdapter.setAllEvents(allEvents);
    }
    public void setCalendarSelectedListener(CalendarAdapter.AdapterInterface calendarSelectedListener){
    adapterInterface = calendarSelectedListener;
    }
}