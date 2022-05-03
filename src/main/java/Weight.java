class Weight extends Measurement {

    public static final int TIER_ONE_LIMIT = 60;
    public static final int TIER_TWO_LIMIT = 500;

    Weight(int value) {
        super(value, TIER_ONE_LIMIT, TIER_TWO_LIMIT);
    }
}
