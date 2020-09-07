package Model;

import Mathmathics.Addition;

import java.io.*;
import java.util.ArrayList;

public class ActivityList{

    private ArrayList<Activity> activeList;

    public ActivityList(){
        activeList = new ArrayList<Activity>();
    }

    /**
     * Gets only the titles if they aren't repeated
     * @return returns a list of titles with no Duplicates
     */
    public ArrayList<Activity> getAllActivitiesTitlesUnique() {
        ArrayList<Activity> res = new ArrayList<Activity>();

        for(Activity a : activeList){
            boolean eq = false;
            for(Activity b : res){
                if(a.getTitle().equals(b.getTitle())){
                    eq = true;
                    break;
                }
            }

            if(!eq){
                res.add(a);
            }
        }
        return res;
    }


    /**
     * Add an activity to the Main Arraylist
     */
    public void addActivity(String title1, String point1, String date1, String week1){
        activeList.add(new Activity(title1, Integer.parseInt(point1), date1, Integer.parseInt(week1)));
    }

//    public Activity getActivity(String title){
//        return activeList.get(findActivityIndex(title));
//    }

    /**
     * Remove an Activity from the ArrayList
     * @param title This is passed to findActivityIndex
     * @param date This is passed to findActivityIndex+
     */
    public void removeActivity(String title, String date){
        activeList.remove(findActivityIndex(title, date));
    }

    /**
     * This is used to find the index of an activity in the list
     * @param title of the activity and it will match this with an activity in the arraylist
     * @param date of the activity this is to make sure it deletes the selects the correct Activity if the Activity repeats
     * @return return the's correct index
     */
    public int findActivityIndex(String title, String date){
        int res = activeList.size() - 1;

        for(int i = 0; i < activeList.size(); i++){
            if (title.toLowerCase().equals(activeList.get(i).getTitle().toLowerCase()) && date.toLowerCase().equals(activeList.get(i).getDate())) {
                //System.out.print(title.toLowerCase() + " " + activeList.get(i).getTitle().toLowerCase());
                res = i;
                break;
            }
        }
        System.out.println("Index: " + res);
        return res;
    }

    /**
     * Return's a list of acvitities
     * @return An arraylist of all activities
     */
    public String getAllActivities(){
        String res = "";



        for(Activity a: activeList){
            res += a.toString() + "\n";
        }

        return res;
    }

    /**
     * Gets all the activity titles for the drop down menus
     *
     * @return  The titles
     */
    public ArrayList<Activity> getAllActivitiesTitles(){
        ArrayList<Activity> res = new ArrayList<>();

        for(Activity a: activeList){
            res.add(a);
        }

        return res;
    }

    /**
     * Adds up all points to get the total.
     * @return Point Score
     */
    public int getPointScore(){
        int res = 0;
        for(Activity a: activeList){
            res = Addition.addTo(res, a.getPoint());
            //res += a.getPoint();
        }

        return res;
    }

    /**
     * Serializes all objects to a file.
     */
    public void saveList(){
        try (
                FileOutputStream fileOut = new FileOutputStream(new File("C:\\Users\\Eoghan Spillane\\Desktop\\CIT_175214\\Year_2\\Semester_2\\OOP\\Project\\src\\Model\\objects.txt"));
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        )
        {
            objectOut.writeObject(activeList);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Unserializes the objects and copies them to the arraylist.
     */
    public void loadList(){
        try (
                FileInputStream fileIn = new FileInputStream("C:\\Users\\Eoghan Spillane\\Desktop\\CIT_175214\\Year_2\\Semester_2\\OOP\\Project\\src\\Model\\objects.txt");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ){
            activeList = (ArrayList<Activity>) objectIn.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds all objects currently in the list into the list again. This is used to break the program.
     */
    public void breakList() {
        activeList.addAll(activeList);
    }
}
