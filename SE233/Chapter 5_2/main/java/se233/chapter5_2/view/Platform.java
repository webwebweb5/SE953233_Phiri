package se233.chapter5_2.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se233.chapter5_2.model.Food;
import se233.chapter5_2.model.Snake;
import se233.chapter5_2.model.SpecialFood;

import java.util.ArrayList;

public class Platform extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    private Canvas canvas;
    private KeyCode key;
    private Label score;
    private ArrayList<Score> scoreList;

    public Platform() {
        this.setHeight(TILE_SIZE * HEIGHT);
        this.setWidth(TILE_SIZE * WIDTH);
        canvas = new Canvas(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT);
        scoreList = new ArrayList();
        scoreList.add(new Score(20, 20));
//        score = new Label("0");
//        score.setTranslateX(20);
//        score.setTranslateY(20);
        this.getChildren().add(canvas);
//        this.getChildren().add(score);
        this.getChildren().addAll(scoreList);
    }

    public void render(Snake snake, Food food, SpecialFood specialFood) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        gc.setFill(Color.BLUE);
        snake.getBody().forEach(p -> {
            gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE,
                    TILE_SIZE);
        });
        gc.setFill(Color.RED);
        gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY()
                * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        gc.setFill(Color.GREEN);
        gc.fillRect(specialFood.getPosition().getX() * TILE_SIZE, specialFood.getPosition().getY()
                * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public KeyCode getKey() {
        return key;
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }

//    public void setScore(int score) {
//        this.score.setText(Integer.toString(score));
//    }

    public ArrayList<Score> getScoreList() {
        return scoreList;
    }
}
