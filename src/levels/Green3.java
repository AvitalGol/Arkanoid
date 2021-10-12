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
 * level 3- Green3.
 */
public class Green3 implements LevelInformation {
    private static final int NUM_OF_BLOCKS = 40;
    private static final int NUM_OF_BALLS = 2;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-3, -3));
        velocities.add(new Velocity(3, -3));
        return velocities;
    }

    @Override
    public List<Point> initialBallsStartPoint() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(300, 500));
        points.add(new Point(500, 500));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(171, 235, 198));
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(Color.black);
                d.drawText(550, 15, "Level Name: Green 3", 15);
                d.setColor(new Color(23, 32, 42));
                d.fillRectangle(100, 400, 100, 200);
                d.setColor(new Color(39, 55, 70));
                d.fillRectangle(133, 350, 33, 50);
                d.setColor(new Color(87, 101, 115));
                d.fillRectangle(145, 200, 10, 150);
                d.setColor(new Color(255, 255, 153));
                d.fillCircle(150, 200, 12);
                d.setColor(new Color(225, 153, 153));
                d.fillCircle(150, 200, 8);
                d.setColor(Color.white);
                d.fillCircle(150, 200, 3);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        d.fillRectangle(110 + j * 14, 410 + i * 50, 10, 40);
                    }
                }

            }

            @Override
            public void timePassed() {

            }
        };
    }

    /**
     * creates list of colors.
     *
     * @param blockColors - the list.
     * @return - the updated list.
     */
    public List<Color> createColors(List<Color> blockColors) {
        blockColors.add(new Color(224, 224, 224));
        blockColors.add(new Color(225, 153, 153));
        blockColors.add(new Color(255, 255, 153));
        blockColors.add(new Color(153, 204, 255));
        blockColors.add(new Color(255, 204, 229));
        blockColors.add(new Color(204, 255, 229));
        return blockColors;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        List<Color> blockColors = new ArrayList<>();
        // gets array of colors for the blocks
        blockColors = createColors(blockColors);
        for (int i = 0; i < 5; i++) {
            for (int j = 10 - i; j > 0; j--) {
                Point upperLeft = new Point(780 - (j * 50), i * 25 + 100);
                blocks.add(new Block(new Rectangle(upperLeft, 50, 25), blockColors.get(i)));
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
