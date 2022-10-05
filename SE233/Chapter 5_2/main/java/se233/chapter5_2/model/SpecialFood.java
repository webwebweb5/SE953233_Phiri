package se233.chapter5_2.model;

import javafx.geometry.Point2D;
import se233.chapter5_2.view.Platform;

import java.util.Random;

public class SpecialFood {
    private Point2D position;
    private Random rn;

    public SpecialFood(Point2D position) {
        this.rn = new Random();
        this.position = position;
    }

    public SpecialFood() {
        this.rn = new Random();
        respawn();
    }

    public void respawn() {
        Point2D prev_position = this.position;
        do {
            this.position = new Point2D(rn.nextInt(Platform.WIDTH), rn.nextInt(
                    Platform.HEIGHT));
        } while (prev_position == this.position);
    }

    public Point2D getPosition() {
        return position;
    }
}
