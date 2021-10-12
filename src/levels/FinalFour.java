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
 * level 4- FinalFour.
 */
public class FinalFour implements LevelInformation {
    private static final int NUM_OF_BLOCKS = 105;
    private static final int NUM_OF_BALLS = 3;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-3, -3));
        velocities.add(new Velocity(3, -3));
        velocities.add(new Velocity(0, -3));
        return velocities;
    }

    @Override
    public List<Point> initialBallsStartPoint() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(300, 500));
        points.add(new Point(500, 500));
        points.add(new Point(400, 400));
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(52, 152, 219));
                d.fillRectangle(0, 20, 800, 600);
                d.setColor(Color.black);
                d.drawText(550, 15, "Level Name: Final Four", 15);
                d.setColor(new Color(204, 209, 209));
                for (int i = 0; i < 10; i++) {
                    d.drawLine(155 + i * 10, 350, 105 + i * 10, 800);
                }
                for (int i = 0; i < 10; i++) {
                    d.drawLine(605 + i * 10, 450, 555 + i * 10, 800);
                }
                d.fillCircle(155, 350, 20);
                d.fillCircle(605, 450, 20);
                d.setColor(new Color(189, 195, 199));
                d.fillCircle(170, 370, 25);
                d.fillCircle(620, 470, 25);
                d.setColor(new Color(178, 186, 187));
                d.fillCircle(190, 340, 30);
                d.fillCircle(640, 440, 30);
                d.setColor(new Color(149, 165, 166));
                d.fillCircle(220, 350, 33);
                d.fillCircle(670, 450, 33);
                d.fillCircle(200, 370, 25);
                d.fillCircle(650, 470, 25);


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
        blockColors.add(new Color(215, 189, 226));
        return blockColors;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        List<Color> blockColors = new ArrayList<>();
        // gets array of colors for the blocks
        blockColors = createColors(blockColors);
        for (int i = 0; i < 7; i++) {
            for (int j = 15; j > 0; j--) {
                Point upperLeft = new Point(777.5 - (j * 50), i * 25 + 100);
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
