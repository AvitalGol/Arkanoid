package game;

import biuoop.DrawSurface;
import movment.SpriteCollection;
import java.awt.Color;
/**
 * CountdownAnimation Class  - counts down before the level starts.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Boolean stop;
    private long timeForNum;
    private long startNumTime;
    private Boolean firstNum;

    /**
     * constructor.
     * @param numOfSeconds - the time the numbers should be shown.
     * @param countFrom - the number to count from
     * @param gameScreen - the sprites that should be on the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.firstNum = true;
        this.timeForNum = (long) (numOfSeconds * 1000 / countFrom);
    }

    /**
     * what need to appear in each "frame".
     * @param d - the surface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        //draw all sprites
        this.gameScreen.drawAllOn(d);
        // counts until 0
        if (this.countFrom <= 0) {
            this.stop = true;
            return;
        }
        // initialize the time that the first number was shown.
        if (this.firstNum) {
            this.startNumTime = System.currentTimeMillis();
            this.firstNum = false;
        }
        // checks if the time for the number ended, if yes - it will show the
        // next number and change the start time accordingly.
        if (startNumTime + timeForNum <= System.currentTimeMillis()) {
            countFrom--;
            startNumTime = System.currentTimeMillis();
        }
        // draws the number on the surface
        d.setColor(Color.GRAY);
        d.drawText(390, 290, String.valueOf(countFrom), 40);
    }

    /**
     * returns true when the animation should stop.
     * @return - true/false
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
