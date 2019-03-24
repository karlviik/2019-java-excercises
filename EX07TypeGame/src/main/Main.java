package main;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Main extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int scoreTime = 3000;
    private static final int penalty = 1000;
    private static final ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

    private Label scoreLabel;
    private ArrayList<Label> labelList;
    private HashMap<Label, Long> labelTimes;
    private Scene scene;
    private Group group;
    private long score = 0;
    private static Random rand = new Random();

    private static String getRandomLetter() {
        int x = rand.nextInt(alphabet.size());
        return alphabet.get(x);
    }

    private static Integer getRand(int high) {
        return 50 + rand.nextInt(high - 100);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        labelList = new ArrayList<>();
        // set up scorelabel
        scoreLabel = new Label();
        labelTimes = new HashMap<>();
        scoreLabel.setScaleX(5);
        scoreLabel.setScaleY(5);
        scoreLabel.setText(Float.toString(0));
        scoreLabel.setTranslateX(WIDTH / 2.0);
        scoreLabel.setTranslateY(HEIGHT / 2.0);
        group = new Group();
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
                        long points = labelTimes.get(label) + scoreTime - time;
                        if (points < -200) {
                            points = -200;
                        }
                        score += points;
                    }
                }
                if (removeList.size() == 0) {
                    score -= penalty;
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
        int x = getRand(WIDTH);
        int y = getRand(HEIGHT);
        System.out.println(scoreLabel.getBoundsInParent());
        System.out.println(scoreLabel.getBoundsInLocal());

        boolean startover = true;
        while (startover) {
            startover = false;
            if (scoreLabel.getBoundsInParent().intersects(x - 75, y - 25, x + 75, y + 25)) {
                x = getRand(WIDTH);
                y = getRand(HEIGHT);
                startover = true;
                continue;
            }
            for (Label letterLabel : labelList) {
                System.out.println(letterLabel.getBoundsInParent().toString());
                if (letterLabel.getBoundsInParent().intersects(x - 25, y - 25, x + 25, y + 25)) {
                    x = getRand(WIDTH);
                    y = getRand(HEIGHT);
                    startover = true;
                    break;
                }
            }
        }

        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setText(getRandomLetter());
        label.setTextFill(Color.color(Math.random() * 0.9, Math.random() * 0.9, Math.random() * 0.9));
        labelList.add(label);
        labelTimes.put(label, System.currentTimeMillis());
        group.getChildren().add(label);
    }
}
