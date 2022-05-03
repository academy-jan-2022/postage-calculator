import java.util.stream.Stream;

record ParcelDimension(int weight, int height, int width, int depth) {

    public Tier getTier() {
        return Stream.of(new WeightTier(), new HeightTier(), new WidthTier(), new DepthTier())
                .map(PriceTier::getTier)
                .reduce(Tier.ONE, Tier::getHigherPriority);
    }

    private interface PriceTier {
        Tier getTier();
    }

    private class WeightTier implements PriceTier {
        @Override
        public Tier getTier() {
            if (weight > 500) return Tier.THREE;
            if (weight > 60) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private class HeightTier implements PriceTier {
        @Override
        public Tier getTier() {
            if (height > 324) return Tier.THREE;
            if (height > 229) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private class WidthTier implements PriceTier {
        @Override
        public Tier getTier() {
            if (width > 229) return Tier.THREE;
            if (width > 162) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private class DepthTier implements PriceTier {
        @Override
        public Tier getTier() {
            if (depth > 100) return Tier.THREE;
            if (depth > 25) return Tier.TWO;
            return Tier.ONE;
        }
    }

}
