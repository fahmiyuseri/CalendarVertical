package com.fahmiyuseri.customcalendarlibrary;

import android.widget.GridView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarModel {
    private GridView calendarGrid;
    private List<GridObject>  gridObjects;
    private GridObject  gridObject;
    private List<Date> dayValueInCells;
    private Calendar calendar;
    private String sDate;

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public GridView getCalendarGrid() {
        return calendarGrid;
    }

    public void setCalendarGrid(GridView calendarGrid) {
        this.calendarGrid = calendarGrid;
    }

    public List<GridObject> getGridObjects() {
        return gridObjects;
    }

    public void setGridObjects(List<GridObject> gridObjects) {
        this.gridObjects = gridObjects;
    }

    public CalendarModel(GridView calendarGrid, List<GridObject> gridObjects, String sDate) {
        this.calendarGrid = calendarGrid;
        this.gridObjects = gridObjects;
        this.sDate = sDate;
    }

    public CalendarModel(GridView calendarGrid, GridObject gridObject, String sDate) {
        this.calendarGrid = calendarGrid;
        this.gridObject = gridObject;
        this.sDate = sDate;
    }

    public CalendarModel(List<Date> dayValueInCells, Calendar calendar, String sDate) {
        this.dayValueInCells = dayValueInCells;
        this.calendar = calendar;
        this.sDate = sDate;
    }

    public GridObject getGridObject() {
        return gridObject;
    }

    public void setGridObject(GridObject gridObject) {
        this.gridObject = gridObject;
    }

    public List<Date> getDayValueInCells() {
        return dayValueInCells;
    }

    public void setDayValueInCells(List<Date> dayValueInCells) {
        this.dayValueInCells = dayValueInCells;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
