record ParcelDimension(int weight, int height, int width, int depth) {

    public Tier getTier() {
        if (isTierTwo())
            return Tier.TWO;

        return Tier.ONE;

    }

    private boolean isTierTwo() {
        return isTierTwoPriceBasedOnWeight() ||
                isTierTwoPriceBasedOnHeight() ||
                isTierTwoPriceBasedOnWidth() ||
                isTierTwoPriceBasedOnDepth();
    }

    private boolean isTierTwoPriceBasedOnDepth() {
        return this.depth > 50;
    }

    private boolean isTierTwoPriceBasedOnWidth() {
        return this.width > 162 && this.width <= 229;
    }

    private boolean isTierTwoPriceBasedOnHeight() {
        return !(this.height > 324) && this.height > 229;
    }

    private boolean isTierTwoPriceBasedOnWeight() {
        return this.weight > 60;
    }
}
