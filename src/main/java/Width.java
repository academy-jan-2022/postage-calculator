public class Width extends Measurement {

    public static final int TIER_ONE_LIMIT = 162;
    public static final int TIER_TWO_LIMIT = 229;

    public Width(int width) {
        super(width, TIER_ONE_LIMIT, TIER_TWO_LIMIT);
    }
}
