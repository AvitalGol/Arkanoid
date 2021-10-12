package levels;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import movment.Sprite;
import movment.Velocity;
import shapes.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level 2 - WideEasy.
 */
public class WideEasy implements LevelInformation {

    private static final int NUM_OF_BLOCKS = 15;
    private static final int NUM_OF_BALLS = 10;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity velocity = new Velocity(0, 5);
        for (int i = 0; i < NUM_OF_BALLS; i++) {
            velocities.add(velocity);
        }
        return velocities;
    }

    @Override
    public List<Point> initialBallsStartPoint() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            points.add(new Point(190 + i * 40, 300 - i * 10));
        }
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            points.add(new Point(490 + i * 40, 260 + i * 10));
        }
        return points;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 660;
    }

    @Override
    public Point paddleStartPoint() {
        return new Point(70, 560);
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.white);
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(Color.black);
                d.drawText(550, 15, "Level Name: Wide Easy", 15);
                d.setColor(new Color(247, 220, 111));
                for (int i = 0; i < 100; i++) {
                    d.drawLine(200, 120, 0 + i * 10, 230);
                }
                d.setColor(new Color(244, 208, 63));
                d.fillCircle(200, 120, 50);
                d.setColor(new Color(247, 220, 111));
                d.fillCircle(200, 120, 40);
                d.setColor(new Color(249, 231, 159));
                d.fillCircle(200, 120, 30);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < NUM_OF_BLOCKS; i++) {
            Color color = new Color(153, 180 + i * 5, 255);
            Point startPoint = new Point(25 + i * 50, 230);
            blocks.add(new Block(new Rectangle(startPoint, 50, 20), color));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
