package Controller;

import Model.Activity;
import Model.ActivityList;
import View.Main;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;

public class Controller {


    private ActivityList ActList = new ActivityList();

    public void addActivity(String title, String point, String date, String week){
        ActList.addActivity(title, point, date, week);
    }

    public void removeActivity(String title, String date){
        ActList.removeActivity(title, date);
    }

    public String getAllActivities(){
        return ActList.getAllActivities();
    }

    public int getPointScore(){
        return ActList.getPointScore();
    }

    public void saveList(){
        ActList.saveList();
    }

    public  void loadList(){
        ActList.loadList();
    }

    public ArrayList<Activity> getAllActivitiesTitles() {
        //loadList();
        return ActList.getAllActivitiesTitles();
    }

    public ArrayList<Activity> getAllActivitiesTitlesUnique() {
        //loadList();
        return  ActList.getAllActivitiesTitlesUnique();
    }

    public void breakList() {
        ActList.breakList();
    }
}
