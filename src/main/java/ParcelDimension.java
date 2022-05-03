import java.util.stream.Stream;

record ParcelDimension(int weight, int height, int width, int depth) {

    public Tier getTier() {
        return Stream.of(getWidthTier(), getDepthTier(), getWeightTier(), getHeightTier())
                .reduce(Tier.ONE, Tier::comparePriority);

    }

    private Tier getWeightTier() {
        if (weight > 500) {
            return Tier.THREE;
        }

        if (this.weight > 60) {
            return Tier.TWO;
        }

        return Tier.ONE;
    }

    private Tier getHeightTier() {
        if (height > 324) {
            return Tier.THREE;
        }

        if (height > 229) {
            return Tier.TWO;
        }

        return Tier.ONE;
    }


    private Tier getWidthTier() {
        if (width > 229) return Tier.THREE;
        if (width > 162) return Tier.TWO;
        return Tier.ONE;

    }


    private Tier getDepthTier() {
        return (this.depth > 50) ? Tier.TWO : Tier.ONE;
    }
}
