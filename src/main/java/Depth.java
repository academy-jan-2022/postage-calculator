public class Depth extends Measurement {

    public static final int TIER_ONE_LIMIT = 25;
    public static final int TIER_TWO_LIMIT = 100;

    public Depth(int value) {
        super(value, TIER_ONE_LIMIT, TIER_TWO_LIMIT);
    }
}
