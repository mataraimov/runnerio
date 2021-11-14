__Runner.io__  <br />
<br />
<img align="center"  width="550px" src="https://github.com/itsabdiev/runnerio/blob/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20(1).png" />  <br />
    <br />
   
   
<img align="center" alt="Java " width="550px" src="https://github.com/itsabdiev/runnerio/blob/main/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%20(2).png" />  <br />
    <br />    
    
    
---
### Tools:
[<img align="left" alt="Java " width="36px" src="https://img.icons8.com/ios/452/domain.png" />][website]
[<img align="left" alt="Java " width="36px" src="https://pbs.twimg.com/profile_images/1206618215767584769/zl48EuhC.jpg" />][Intellij]
[<img align="left" alt="Java " width="36px" src="https://icon-library.com/images/java-icon-png/java-icon-png-2.jpg" />][Java] <br /> 

<br />


   <details>
  <summary>:video_game: How to Run</summary>
  
 
<!--START_SECTION:activity-->
1. :bookmark_tabs: I want you inform you that this project is built in Java SDK 17 and must be runned the same(Java Development Kit 17) 
2. :cloud: Download this project fully and extract to Idea Projects and find Main.java file
3. :framed_picture: Before running,please change some pathes to images and files
4. :arrow_forward: Run
5. :sunglasses: Enjoy
6. But also you can dowload .jar file from here and also run it
<!--END_SECTION:activity-->

</details>

---

* Game.java
```java
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
```  
* Main.java
```java
package com.example.runnerio;

public class Main {
    public static void main(String[] args) {
        Game.main(args);
    }
}
```  
* Runner.java
```java
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

```
* Monster.java
```java
package com.example.runnerio;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import java.util.Random;

public class Monster extends Runner{
    Monster(int initialPosX, int initialPosY, Image image) {
           super(initialPosX, initialPosY, image);

    }
    void drawMonster(Group root){
        imageView = new ImageView(this.image);
        imageView.setX(this.initialPosX);
        imageView.setY(this.initialPosY);
        imageView.setScaleX(0.6);
        imageView.setScaleY(0.6);
        imageView.setRotationAxis(Rotate.Y_AXIS);
        imageView.setRotate(180);
        root.getChildren().add(imageView);
    }

    @Override
    void running(int speed){
        int currentX = (int)imageView.getX();
        currentX-=speed;
        imageView.setX(currentX);
    }

    void checkPos() {
        if (imageView.getX() < 0) {
            Random random = new Random();
            int p = random.nextInt(650, 750);
            imageView.setX(p);
        }
    }


}
```

[website]: https://itsabdiev.github.io/Everest/
[Intellij]: https://www.jetbrains.com/
[Java]: https://www.java.com/ru/
[SceneBuilder]: https://gluonhq.com/products/scene-builder/
[youtube]: https://www.youtube.com/watch?v=aSMC_n-VCz4
