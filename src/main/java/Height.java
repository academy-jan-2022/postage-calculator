class Height extends Measurement {

    public static final int TIER_ONE_LIMIT = 229;
    public static final int TIER_TWO_LIMIT = 324;

    Height(int value) {
        super(value, TIER_ONE_LIMIT, TIER_TWO_LIMIT);
    }
}
