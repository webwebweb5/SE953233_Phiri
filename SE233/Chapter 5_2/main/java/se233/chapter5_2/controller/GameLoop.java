package se233.chapter5_2.controller;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Popup;
import se233.chapter5_2.Launcher;
import se233.chapter5_2.model.Direction;
import se233.chapter5_2.model.Food;
import se233.chapter5_2.model.Snake;
import se233.chapter5_2.model.SpecialFood;
import se233.chapter5_2.view.Platform;
import se233.chapter5_2.view.Score;

import java.util.ArrayList;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    private float interval = 1000.0f / 10;
    private boolean running;
    private int score;

    public GameLoop(Platform platform, Snake snake, Food food, SpecialFood specialFood) {
        this.snake = snake;
        this.platform = platform;
        this.food = food;
        this.specialFood = specialFood;
        running = true;
    }

    private void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();
        if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN)
            snake.setCurrentDirection(Direction.UP);
        else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP)
            snake.setCurrentDirection(Direction.DOWN);
        else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT)
            snake.setCurrentDirection(Direction.LEFT);
        else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT)
            snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
    }

    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
            this.score++;
//            javafx.application.Platform.runLater(() -> {
////                platform.setScore(this.score);
//            });
        } else if (snake.isCollidingWith(specialFood)) {
            snake.specialGrow();
            specialFood.respawn();
            this.score += 5;
//            javafx.application.Platform.runLater(() -> {
////                platform.setScore(this.score);
//            });
        }
        if (snake.isDead()) {
            running = false;
        }
    }

    private void redraw() {
        platform.render(snake, food, specialFood);
    }

    @Override
    public void run() {
        while (running) {
            update();
            checkCollision();
            updateScore(platform.getScoreList());
            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        javafx.application.Platform.runLater(() -> {
            Label text = new Label();
            text.setText("Game Over");
            text.setStyle("-fx-font-size: 26px;");
            Popup popup = new Popup();
            popup.getContent().add(text);
            popup.show(Launcher.primaryStage);
        });
    }

    public Snake getSnake() {
        return snake;
    }

    public Platform getPlatform() {
        return platform;
    }

    private void updateScore(ArrayList<Score> scoreList) {
        javafx.application.Platform.runLater(() -> {
            for (int i = 0; i < scoreList.size(); i++) {
                scoreList.get(i).setPoint(score);
            }
        });
    }
}
