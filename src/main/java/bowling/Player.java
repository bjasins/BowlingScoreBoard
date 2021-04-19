package bowling;

/**
 * A POJO meant to represent an individual player.
 */
public class Player {

    private final String name;
    private final FrameRow frames;

    Player(String name) {
        this.name = name;
        frames = new FrameRow();
    }

    /**
     * @return the total score of the player.
     */
    public int getScore() {
        return frames.getTotalScore();
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
    protected FrameRow getPlayerFrames() {
        return frames;
    }
}
