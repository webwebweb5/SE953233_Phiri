package se233.chapter5_2;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import se233.chapter5_2.controller.GameLoop;
import se233.chapter5_2.model.Food;
import se233.chapter5_2.model.Snake;
import se233.chapter5_2.model.SpecialFood;
import se233.chapter5_2.view.Platform;

public class Launcher extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(Platform.WIDTH / 2, Platform.HEIGHT / 2));
        Food food = new Food();
        SpecialFood specialFood = new SpecialFood();
        GameLoop gameLoop = new GameLoop(platform, snake, food, specialFood);
//        gameLoop.setSp(new SpecialFood());
        Scene scene = new Scene(platform, Platform.WIDTH * Platform.TILE_SIZE, Platform.HEIGHT * Platform.TILE_SIZE);
        scene.setOnKeyPressed(e -> platform.setKey(e.getCode()));
        scene.setOnKeyReleased(e -> platform.setKey(null));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        (new Thread(gameLoop)).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
