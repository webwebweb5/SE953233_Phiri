package se233.chapter5_2.model;

import javafx.geometry.Point2D;
import se233.chapter5_2.view.Platform;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private Direction direction;
    private Point2D head;
    private Point2D prev_tail;
    private ArrayList<Point2D> body;

    public Snake(Point2D position) {
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
    }

    public void update() {
        head = head.add(direction.current);
        prev_tail = body.remove(body.size() - 1);
        body.add(0, head);
    }

    public void setCurrentDirection(Direction direction) {
        this.direction =
                direction;
    }

    public Direction getCurrentDirection() {
        return this.direction;
    }

    public Point2D getHead() {
        return head;
    }

    public boolean isCollidingWith(Food food) {
        return head.equals(food.getPosition());
    }

    public boolean isCollidingWith(SpecialFood food) {
        return head.equals(food.getPosition());
    }

    public void grow() {
        body.add(prev_tail);
    }

    public void specialGrow() {
        for (int i = 0; i < 5; i++) {
            body.add(prev_tail);
        }
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }

    public boolean isDead() {
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() >
                Platform.WIDTH || head.getY() > Platform.HEIGHT;
        boolean isHitBody = body.lastIndexOf(head) > 0;
        return isOutOfBound || isHitBody;
    }

}

