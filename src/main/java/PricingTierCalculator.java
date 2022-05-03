import java.util.stream.Stream;

class PricingTierCalculator {
    private final Stream<PriceTier> pricingTiers;

    public PricingTierCalculator(ParcelDimension parcelDimension) {
        pricingTiers = Stream.of(
                new WeightTier(parcelDimension.weight()),
                new HeightTier(parcelDimension.height()),
                new WidthTier(parcelDimension.width()),
                new DepthTier(parcelDimension.depth()));
    }

    public Tier checkParcelPricingTier() {
        return pricingTiers.map(PriceTier::getTier).reduce(Tier.ONE, Tier::getHigherPriority);
    }

    private interface PriceTier {
        Tier getTier();
    }

    private record WeightTier(int weight) implements PriceTier {
        @Override
        public Tier getTier() {
            if (weight > 500) return Tier.THREE;
            if (weight > 60) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private record HeightTier(int height) implements PriceTier {
        @Override
        public Tier getTier() {
            if (height > 324) return Tier.THREE;
            if (height > 229) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private record WidthTier(int width) implements PriceTier {
        @Override
        public Tier getTier() {
            if (width > 229) return Tier.THREE;
            if (width > 162) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private record DepthTier(int depth) implements PriceTier {
        @Override
        public Tier getTier() {
            if (depth > 100) return Tier.THREE;
            if (depth > 25) return Tier.TWO;
            return Tier.ONE;
        }
    }
}
