package com.example.runnerio;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends Application {
    Runner runner;
    Monster monster;
    Timeline jumpline;
    int currentFrameRunner = 0;
    int currentFrameMonster = 0;
    int thresh = 50;
    int sceneWidth = 700;
    int sceneHeight = 230;
    ArrayList<Image> images_list_runner = new ArrayList<>();
    ArrayList<Image> images_list_monster = new ArrayList<>();
    Image [] assets = {new Image("file:///C:/Users/Администратор/IdeaProjects/runnerio/src/Images/Buttons/playInit.png",50,50,false,false),
           new Image("file:///C:/Users/Администратор/IdeaProjects/runnerio/src/Images/Buttons/playPressed.png",50,50,false,false),
            new Image("file:///C:/Users/Администратор/IdeaProjects/runnerio/src/Images/Bg/MenuBack.png",sceneWidth,sceneHeight,false,false)};
    Image bg = new Image("file:///C:/Users/Администратор/IdeaProjects/runnerio/src/Images/Bg/GameBack.png");
    ImageView bgView;
    ImageView bgView2;
    int initialX = 0;
    int initialY = sceneHeight - 100;
    int speedOfRunner = 6;
    int speedOfMonster = 8;
    int score = 0;
    int jump_vel = 15;


    @Override
    public void start(Stage stage) {
        //game
        Group gameRoot = new Group();
        Scene gameScene = new Scene(gameRoot);
        //menu
        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot);
        ImageView playButton = new ImageView(assets[0]);
        ImageView promo = new ImageView(assets[2]);
        playButton.setX(300);
        playButton.setY(80);
        Label label = new Label();
        label.setTextFill(Color.rgb(120, 128, 128));
        label.setFont(Font.loadFont("file:///C:\\Users\\Администратор\\IdeaProjects\\runnerio\\src\\Images\\Bebas-Regular.ttf", 20));
        label.setLayoutX(5);
        menuRoot.getChildren().addAll(promo, playButton);
        jumpline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            runner.imageView.setY(runner.imageView.getY() - jump_vel);
            jump_vel -= 3;
            if (jump_vel < (-15)) {
                jump_vel = 15;
            }}));
        jumpline.setCycleCount(11);
        bgView = new ImageView(bg);
        bgView2 = new ImageView(bg);
        bgView2.setX(bgView.getImage().getWidth());
        stage.setResizable(false);
        stage.setWidth(sceneWidth);
        stage.setHeight(sceneHeight);
        stage.getIcons().add(new Image("file:///C:\\Users\\Администратор\\IdeaProjects\\runnerio\\src\\Images\\Icon\\Icon.png"));
        uploadFiles("Run", images_list_runner);
        uploadFiles("Monster", images_list_monster);
        runner = new Runner(initialX, initialY, images_list_runner.get(0));
        monster = new Monster(660, 150, images_list_monster.get(3));
        gameRoot.getChildren().addAll(bgView, bgView2, label);
        runner.drawRunner(gameRoot);
        monster.drawMonster(gameRoot);
        stage.setScene(gameScene);
        stage.setTitle("Runner - Adam Mcavoy");
        stage.show();
        //Loop
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            score += 1;
            label.setText(String.format("Score: %d", score));
            currentFrameRunner = currentFrameRunner + 1;
            if (currentFrameRunner >= images_list_runner.size() - 1) {
                currentFrameRunner = 0;
            }
            runner.frameChange(images_list_runner.get(currentFrameRunner));
            currentFrameMonster = currentFrameMonster + 1;
            if (currentFrameMonster >= images_list_monster.size() - 1) {
                currentFrameMonster = 0;
            }
            monster.frameChange(images_list_monster.get(currentFrameMonster));

            bgScrolling();
            runner.running(speedOfRunner);
            monster.running(speedOfMonster);
            monster.checkPos();
            if (Math.abs(rastoyanie()) <= 40 ) {
                jump();
            }


        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        //functions
        playButton.setOnMouseEntered(mouseEvent -> playButton.setImage(assets[1]));
        playButton.setOnMouseExited(mouseEvent -> playButton.setImage(assets[0]));
        playButton.setOnMouseClicked(mouseEvent -> {
            stage.setScene(gameScene);

        });
        timeline.play();
    }

    void uploadFiles(String currentpath,ArrayList<Image>images_list){
        File file = new File(String.format("C:/Users/Администратор/IdeaProjects/runnerio/src/Images/%s",currentpath));
        images_list.clear();
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            images_list.add(new Image("file:///" + listFile.toString()));
        }
    }

    void bgScrolling(){
        if (runner.imageView.getX() > thresh ) {
            bgView.setX(bgView.getX() - 10);
            bgView2.setX(bgView2.getX() - 10);
            speedOfRunner = 0;
        }
        else {
            bgView.setX(bgView.getX() - 0);
            speedOfRunner = 6;
        }
        if (bgView.getX() <= -(bgView2.getImage().getWidth())) {
            bgView.setX(bgView2.getImage().getWidth());
        }
        if (bgView2.getX() <= -(bgView.getImage().getWidth())) {
            bgView2.setX(bgView.getImage().getWidth());
        }
    }
    void jump () {
        if (runner.imageView.getY() == initialY) {
            jumpline.play();
        }
    }
    double rastoyanie(){

        return (runner.imageView.getX() + runner.imageView.getImage().getWidth())-(monster.imageView.getX() + monster.imageView.getImage().getWidth());


    }
    public static void main(String[] args) {
        launch();
    }
}