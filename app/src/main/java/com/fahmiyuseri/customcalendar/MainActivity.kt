package com.fahmiyuseri.customcalendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.fahmiyuseri.customcalendarlibrary.CalendarCustomView
import com.fahmiyuseri.customcalendarlibrary.EventsObject
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var cal = Calendar.getInstance(Locale.ENGLISH)
        var events = ArrayList<EventsObject>()
        events.add(EventsObject("84",cal.time))
        custom_calendar.setEvents(events)

        button2.setOnClickListener {
custom_calendar.clearSelectedDate()
        }
        custom_calendar.setCalendarSelectedListener { Depart, Return ->

            if(Return!=null)
            Log.d("sada","Depart :"+Depart.time.toString() +" untill "+ Return.time.toString())
            if(Depart!=null)
            Log.d("sada","Depart :"+Depart.time.toString())
        }

    }
}
