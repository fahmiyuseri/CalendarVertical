package com.fahmiyuseri.customcalendarlibrary;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GridObject {
    private List<Date> dayValueInCells;
    private List<EventsObject> listEvents;
    private Calendar cal;

    public GridObject(List<Date> dayValueInCells, Calendar cal ,List<EventsObject> listEvents) {
        this.dayValueInCells = dayValueInCells;
        this.listEvents = listEvents;
        this.cal = cal;
    }

    public List<EventsObject> getListEvents() {
        return listEvents;
    }

    public void setListEvents(List<EventsObject> listEvents) {
        this.listEvents = listEvents;
    }

    public List<Date> getDayValueInCells() {
        return dayValueInCells;
    }

    public void setDayValueInCells(List<Date> dayValueInCells) {
        this.dayValueInCells = dayValueInCells;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }
}
