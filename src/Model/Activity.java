package Model;

import java.io.Serializable;

public class Activity implements Serializable {
    private String title;
    private int Point;
    private String Date;
    private int Week;


    public Activity(String title, int point, String date, int week) {
        this.title = title;
        Point = point;
        Date = date;
        Week = week;
    }

    public String getTitle(){
        return title;
    }

    public int getPoint() {
        return Point;
    }

    public String getDate() {
        return Date;
    }

    public int getWeek() {
        return Week;
    }

    public String toString(){
        return "Title: " + title + "\t Date: " + Date + "\t Week: " + Week + "\t Points: " + Point;
    }

}
