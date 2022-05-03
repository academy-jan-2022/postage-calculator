import java.util.stream.Stream;

record ParcelDimension(int weight, int height, int width, int depth) {

    public Tier getTier() {
        return Stream.of(getWidthTier(), getDepthTier(), getWeightTier(), getHeightTier())
                .reduce(Tier.ONE, Tier::comparePriority);

    }

    private Tier getWeightTier() {
        return this.weight > 60 ? Tier.TWO : Tier.ONE;
    }

    private Tier getHeightTier() {
        return (this.height <= 324 && this.height > 229) ? Tier.TWO : Tier.ONE;
    }


    private Tier getWidthTier() {
        return (this.width > 162 && this.width <= 229) ? Tier.TWO : Tier.ONE;
    }


    private Tier getDepthTier() {
        return (this.depth > 50) ? Tier.TWO : Tier.ONE;
    }
}
