package View;

import Controller.Controller;
import Controller.ViewController;

import Model.Activity;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;

public class Main extends Application{

    private Controller con = new Controller();
    private ViewController buttonCon = new ViewController();

    public void reload(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Carbon Impact Calculator");

        //_____________________________________________________
        // Tab 1

        //Image carbon = new Image("C:\\Users\\Eoghan Spillane\\Desktop\\CIT_175214\\Year_2\\Semester_2\\OOP\\Project\\src\\Model\\Carbon.png");
        Image carbon = new Image("file:C:\\Users\\Eoghan Spillane\\Desktop\\CIT_175214\\Year_2\\Semester_2\\OOP\\Project\\src\\Model\\Carbon.png");
        Image Earth_Chan = new Image("file:C:\\Users\\Eoghan Spillane\\Desktop\\CIT_175214\\Year_2\\Semester_2\\OOP\\Project\\src\\Model\\4x6a9550.bmp");

        ImageView carbonImg = new ImageView();
        carbonImg.setImage(Earth_Chan);
        //carbonImg.setImage(carbon);
        carbonImg.setFitWidth(500);
        carbonImg.setPreserveRatio(true);
        carbonImg.setSmooth(true);
        carbonImg.setCache(true);

        BorderPane pane = new BorderPane();
        pane.setPrefSize(400, 300);
        pane.setCenter(carbonImg);

        //carbonImg.fitWidthProperty().bind(((AnchorPane)carbonImg.getParent()).widthProperty());

        //______________________________________________________________________________
        // Upper Gui Tab 2


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label week = new Label("Week:  ");
        grid.add(week, 1,0);
        TextField weekInput = new TextField();
        grid.add(weekInput, 2, 0);

        Label date = new Label("Date:  ");
        grid.add(date, 1,1);
        DatePicker dateInput = new DatePicker();
        grid.add(dateInput, 2, 1);


        Label activity = new Label("Activity:  ");
        grid.add(activity, 1,2);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setEditable(true);
        grid.add(comboBox, 2, 2);


        Label points = new Label("Points (+10/-10):  ");
        grid.add(points, 1,3);
        TextField pointInput = new TextField();
        grid.add(pointInput, 2, 3);



        //______________________________________________________________________
        // Buttons - Tab 2
        Button add = new Button("Add");
        add.setLayoutX(10);
        add.setLayoutY(180);

        Button remove = new Button("Remove");
        remove.setLayoutX(60);
        remove.setLayoutY(180);

        Button list = new Button("Reload");
        list.setLayoutX(130);
        list.setLayoutY(180);

        Button summary = new Button("Summary");
        summary.setLayoutX(190);
        summary.setLayoutY(180);


        //______________________________________________________
        // Main Activity List - Tab 2
        TextArea textBox = new TextArea();
        textBox.setLayoutX(10);
        textBox.setLayoutY(220);

        Button load = new Button("Load");
        load.setLayoutX(10);
        load.setLayoutY(410);

        Button save = new Button("Save");
        save.setLayoutX(60);
        save.setLayoutY(410);

        Button breakProg = new Button("Break");
        breakProg.setLayoutX(130);
        breakProg.setLayoutY(410);

        Button quit = new Button("Quit");
        quit.setLayoutX(450);
        quit.setLayoutY(410);


        //_______________________________________________________
        // Gui Tab 3

        ListView<String> listView = new ListView<>();

        ArrayList<String> content = new ArrayList<>();
        for(int i = 0; i < con.getAllActivitiesTitles().size(); i++){
            content.add(con.getAllActivitiesTitles().get(i).getTitle() + "\t\t " + con.getAllActivitiesTitles().get(i).getDate() + "\t\t" + con.getAllActivitiesTitles().get(i).getPoint());
        }

        Button sortDate = new Button("Sort by date");
        sortDate.setLayoutX(100);
        sortDate.setLayoutY(70);

        Button sortActivity = new Button("Sort by Activity");
        sortActivity.setLayoutX(250);
        sortActivity.setLayoutY(70);

        listView.getItems().addAll(content);
        listView.setLayoutX(100);
        listView.setLayoutY(100);


        //_______________________________________________________
        // Button Handlers
        add.setOnAction(actionEvent ->  {
            con.addActivity(comboBox.getValue(), pointInput.getText(), dateInput.getValue().toString(), weekInput.getText());

            textBox.setText(con.getAllActivities());
            weekInput.clear();
            pointInput.clear();
            dateInput.getEditor().clear();
            comboBox.getItems().clear();
            for(int i = 0; i < con.getAllActivitiesTitlesUnique().size(); i++){
                comboBox.getItems().add(con.getAllActivitiesTitlesUnique().get(i).getTitle());
                //System.out.println(con.getAllActivitiesTitlesUnique().get(i).getTitle());
            }

        });

        // Brings up dialog box, the inputed text will then be sent to the controller
        // Method which will call the activitylist and remove it. there is then a line to reload.
        remove.setOnAction(actionEvent ->  {
            List<String> choices = new ArrayList<>();
            for(int i = 0; i < con.getAllActivitiesTitles().size(); i++){
                choices.add(con.getAllActivitiesTitles().get(i).getTitle() + "\t\t" + con.getAllActivitiesTitles().get(i).getDate());
            }

            con.loadList();
            ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
            dialog.setTitle("Remove");
            dialog.setHeaderText("Remove an Activity");
            dialog.setContentText("Activity to remove:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                try {
                    String titleRem = dialog.getResult().contains(" ") ? dialog.getResult().split("\t\t")[0] : dialog.getResult();
                    String dateRem = dialog.getResult().contains(" ") ? dialog.getResult().split("\t\t")[1] : dialog.getResult();
                    System.out.print(titleRem + dateRem);
                    con.removeActivity(titleRem, dateRem);
                    textBox.setText(con.getAllActivities());

                } catch(Exception e){
                    System.out.print("Error");
                }
        }
            System.out.print("Remove \n");
        });

        //reload
        list.setOnAction(actionEvent ->  {
            textBox.setText(con.getAllActivities());
            //for(int i = 0; i < con.getAllActivitiesTitlesUnique().size(); i++){
                //comboBox.getItems().add(con.getAllActivitiesTitlesUnique().get(i).getTitle());
                //System.out.println(con.getAllActivitiesTitlesUnique().get(i).getTitle());
            //}
        });

        //Point Score total
        summary.setOnAction(actionEvent ->  {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Point Score");
            alert.setHeaderText(null);
            alert.setContentText("You have Scored " + con.getPointScore() + " Points");
            alert.showAndWait();

            System.out.print("Summary \n");
        });

        save.setOnAction(actionEvent ->  {
            con.saveList();
            System.out.print("Save \n");
        });

        sortDate.setOnAction(actionEvent -> {
            listView.getItems().clear();
            Collections.sort(content, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return LocalDate.parse(o1.split("\t\t")[1].strip()).compareTo(LocalDate.parse(o2.split("\t\t")[1].strip()));
                }
            });
            for(int i = 0; i < content.size(); i++)
            {
                listView.getItems().add(content.get(i));
            }
        });

        sortActivity.setOnAction(actionEvent -> {
            Collections.sort(content, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {

                    return o1.split("\t\t")[0].compareTo(o2.split("\t\t")[0]);
                }
            });
            listView.getItems().clear();
            for(int i = 0; i < content.size(); i++)
            {
                listView.getItems().add(content.get(i));
            }
//            for(int i = 0; i < con.getAllActivitiesTitles().size(); i++){
//                content.add(con.getAllActivitiesTitles().get(i).getTitle() + "\t\t " + con.getAllActivitiesTitles().get(i).getDate() + "\t\t" + con.getAllActivitiesTitles().get(i).getPoint());
//            }
        });

        load.setOnAction(actionEvent ->  {
            con.loadList();
            textBox.setText(con.getAllActivities());
            comboBox.getItems().clear();
            for(int i = 0; i < con.getAllActivitiesTitlesUnique().size(); i++){
                comboBox.getItems().add(con.getAllActivitiesTitlesUnique().get(i).getTitle());
                System.out.println(con.getAllActivitiesTitlesUnique().get(i).getTitle());
            }
            System.out.print("Load \n");

            listView.getItems().clear();
            content.clear();
            for(int i = 0; i < con.getAllActivitiesTitles().size(); i++){
                content.add(con.getAllActivitiesTitles().get(i).getTitle() + "\t\t " + con.getAllActivitiesTitles().get(i).getDate() + "\t\t" + con.getAllActivitiesTitles().get(i).getPoint());
            }

            listView.getItems().addAll(content);

        });

        breakProg.setOnAction(actionEvent ->  {
            con.breakList();
            textBox.setText(con.getAllActivities());

            comboBox.getItems().clear();
            for(int i = 0; i < con.getAllActivitiesTitlesUnique().size(); i++){
                comboBox.getItems().add(con.getAllActivitiesTitlesUnique().get(i).getTitle());
                System.out.println(con.getAllActivitiesTitlesUnique().get(i).getTitle());
            }
            System.out.print("Load \n");

            listView.getItems().clear();
            content.clear();
            for(int i = 0; i < con.getAllActivitiesTitles().size(); i++){
                content.add(con.getAllActivitiesTitles().get(i).getTitle() + "\t\t " + con.getAllActivitiesTitles().get(i).getDate() + "\t\t" + con.getAllActivitiesTitles().get(i).getPoint());
            }

            listView.getItems().addAll(content);

        });

        quit.setOnAction(e -> System.exit(0));
        //buttonCon.quit();

        //_______________________________________________________
        // Scene Setup
        Group g = new Group();
        g.getChildren().addAll(grid, textBox, load, save, quit, add, breakProg, remove, list, summary);

        Group f = new Group();
        f.getChildren().addAll(pane);

        Group h = new Group();
        h.getChildren().addAll(listView, sortActivity, sortDate);

        TabPane tabPane = new TabPane();
        Tab intro = new Tab("Intro", f);
        Tab calc = new Tab("Calculator", g);
        Tab actList = new Tab("Activities List", h);

        tabPane.getTabs().add(intro);
        tabPane.getTabs().add(calc);
        tabPane.getTabs().add(actList);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        //tabPane.setBackground(circle.setFill(Color.DARKRED););
        Scene GridScene = new Scene(tabPane, 500, 500);
        primaryStage.setScene(GridScene);
        primaryStage.getIcons().add(carbon);
        primaryStage.show();
        GridScene.setFill(Color.DARKRED);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
