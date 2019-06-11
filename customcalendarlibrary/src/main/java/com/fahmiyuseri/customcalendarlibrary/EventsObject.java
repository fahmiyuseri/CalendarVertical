package com.fahmiyuseri.customcalendarlibrary;
import java.util.Date;
public class EventsObject {
    private int id;
    private String message;
    private Date date;
    public EventsObject(String message, Date date) {
        this.message = message;
        this.date = date;
    }
    public EventsObject(int id, String message, Date date) {
        this.date = date;
        this.message = message;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public Date getDate() {
        return date;
    }
}