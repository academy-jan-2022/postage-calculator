abstract class Measurement {
    private final int value;
    private final int tierOneLimit;
    private final int tierTwoLimit;

    Measurement(int value, int tierOneLimit, int tierTwoLimit) {
        this.value = value;
        this.tierOneLimit = tierOneLimit;
        this.tierTwoLimit = tierTwoLimit;
    }

    protected Tier getTier() {
        if (value > tierTwoLimit) {
            return Tier.THREE;
        }

        if (value > tierOneLimit) {
            return Tier.TWO;
        }

        return Tier.ONE;
    }

    protected int getValue() {
        return value;
    }
}
