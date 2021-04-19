package pojos;

import bowling.FrameRowUtil;

/**
 * A POJO meant to represent an individual player.
 */
public class Player {

    private final String name;
    private final FrameRow frames;

    public Player(String name) {
        this.name = name;
        frames = new FrameRow();
    }

    /**
     * @return the total score of the player.
     */
    public int getScore() {
        FrameRowUtil frameRowUtil = new FrameRowUtil();
        return frameRowUtil.getTotalScore(frames);
    }

    /**
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the {@link FrameRow} of the player.
     */
    public FrameRow getPlayerFrames() {
        return frames;
    }
}
