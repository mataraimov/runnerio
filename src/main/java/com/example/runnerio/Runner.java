package com.example.runnerio;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Runner {
    int initialPosX;
    int initialPosY;
    Image image;
    ImageView imageView;

    Runner(int initialPosX, int initialPosY, Image image){
        this.initialPosX = initialPosX;
        this.initialPosY = initialPosY;
        this.image = image;

    }
    void drawRunner(Group tv){
        imageView = new ImageView(this.image);
        imageView.setX(this.initialPosX);
        imageView.setY(this.initialPosY);

        tv.getChildren().add(imageView);
    }
    void frameChange(Image image){
        imageView.setImage(image);
    }
    void running(int speed){
        int currentX = (int)imageView.getX();
        currentX+=speed;
        imageView.setX(currentX);
    }









}
