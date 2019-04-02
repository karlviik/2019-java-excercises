package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final double COST_MULTIPLIER = 1.3;
    private static final double CLICKER_TIME_MULTIPLIER = 0.9;
    private int clickerIntervaMillisecondsa = 10000;
    private static final int BAKERY_INITIAL_CPS = 10;
    private static final int BAKERY_INITIAL_COST = 100;
    private HashMap<Integer, Integer>  components = new HashMap<>();

    private Label scoreLabel;
    private ArrayList<Label> labelList;
    private HashMap<Label, Long> labelTimes;
    private Scene scene;
    private Group group;
    private long score = 0;
    private static Random rand = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception{
        components.put(0, 1);
        components.put(1, 0);
        components.put(2, 0);

        labelList = new ArrayList<>();
        // set up scorelabel
        scoreLabel = new Label();
        labelTimes = new HashMap<>();
        scoreLabel.setScaleX(5);
        scoreLabel.setScaleY(5);
        scoreLabel.setText(Float.toString(0));
        scoreLabel.setTranslateX(WIDTH / 2.0);
        scoreLabel.setTranslateY(HEIGHT / 2.0);

        GridPane gridpane = new GridPane();
        Image image = new Image("File:cookie.png");
        ImageView pic = new ImageView();
        pic.setFitWidth(500);
        pic.setFitHeight(500);
        pic.setImage(image);
        gridpane.getChildren().add(pic);


        group = new Group();
        group.getChildren().add(gridpane);
        group.getChildren().add(scoreLabel);

        primaryStage.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                long time = System.currentTimeMillis();
                String character = keyEvent.getCharacter().toUpperCase();
                ArrayList<Label> removeList = new ArrayList<>();
                for (Label label : labelList) {
                    if (label.getText().equals(character)) {
                        removeList.add(label);
                        long points = labelTimes.get(label) + 100 - time;
                        if (points < -200) {
                            points = -200;
                        }
                        score += points;
                    }
                }
                if (removeList.size() == 0) {
                    score -= 10;
                }
                scoreLabel.setText(Long.toString(score / 100));

                for (Label label : removeList) {
                    labelList.remove(label);
                    labelTimes.remove(label);

                    KeyValue xScale = new KeyValue(label.scaleXProperty(), 200);
                    KeyValue yScale = new KeyValue(label.scaleYProperty(), 200);
                    KeyFrame keyFrame = new KeyFrame(Duration.millis(250), xScale, yScale);
                    Timeline timeline = new Timeline();
                    timeline.getKeyFrames().add(keyFrame);
                    timeline.play();
                    timeline.setOnFinished(event -> group.getChildren().remove(label));

                    generateLabel();
                    if (Math.random() < 0.15) {
                        generateLabel();
                        if (Math.random() < 0.15) {
                            generateLabel();
                        }
                    }
                }
            }
        });
        generateLabel();
        generateLabel();
        generateLabel();


        AnchorPane root = new AnchorPane();
        root.getChildren().add(group);
        scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("TypeGame");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }

    private void generateLabel() {
        if (labelList.size() > 15) {
            return;
        }
        javafx.scene.control.Label label = new Label();
        label.setScaleX(5);
        label.setScaleY(5);
        System.out.println(scoreLabel.getBoundsInParent());
        System.out.println(scoreLabel.getBoundsInLocal());


        label.setLayoutX(10);
        label.setLayoutY(20);
        label.setText("ah");
        label.setTextFill(Color.color(Math.random() * 0.9, Math.random() * 0.9, Math.random() * 0.9));
        labelList.add(label);
        labelTimes.put(label, System.currentTimeMillis());
        group.getChildren().add(label);
    }
}
