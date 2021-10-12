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
 * level-"DirectHit".
 */
public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -3));
        return velocities;
    }

    @Override
    public List<Point> initialBallsStartPoint() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(400, 500));
        return points;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public Point paddleStartPoint() {
        return new Point(350, 560);
    }

    @Override
    public String levelName() {
        String levelName = "Direct Hit";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.black);
                d.fillRectangle(0, 20, 800, 600);
                d.drawText(550,  15, "Level Name: Direct Hit", 15);
                d.setColor(Color.blue);
                d.drawCircle(400, 160, 60);
                d.drawCircle(400, 160, 90);
                d.drawCircle(400, 160, 120);
                d.drawLine(400, 20, 400, 145);
                d.drawLine(400, 175, 400, 305);
                d.drawLine(260, 160, 385, 160);
                d.drawLine(415, 160, 540, 160);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(390, 150), 20, 20), Color.red));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
