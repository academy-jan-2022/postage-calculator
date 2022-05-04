import java.util.stream.Stream;

class PricingTierCalculator {
    private final Stream<PriceTier> pricingTiers;

    public PricingTierCalculator(ParcelDimension parcelDimension) {
        pricingTiers = Stream.of(
                new WeightTierCalculator(parcelDimension.weight()),
                new HeightTierCalculator(parcelDimension.height()),
                new WidthTierCalculator(parcelDimension.width()),
                new DepthTierCalculator(parcelDimension.depth()));
    }

    public Tier checkParcelPricingTier() {
        return pricingTiers.map(PriceTier::getTier).reduce(Tier.ONE, Tier::getHigherPriority);
    }

    private interface PriceTier {
        Tier getTier();
    }

    private record WeightTierCalculator(int weight) implements PriceTier {
        @Override
        public Tier getTier() {
            if (weight > 500) return Tier.THREE;
            if (weight > 60) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private record HeightTierCalculator(int height) implements PriceTier {
        @Override
        public Tier getTier() {
            if (height > 324) return Tier.THREE;
            if (height > 229) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private record WidthTierCalculator(int width) implements PriceTier {
        @Override
        public Tier getTier() {
            if (width > 229) return Tier.THREE;
            if (width > 162) return Tier.TWO;
            return Tier.ONE;
        }
    }

    private record DepthTierCalculator(int depth) implements PriceTier {
        @Override
        public Tier getTier() {
            if (depth > 100) return Tier.THREE;
            if (depth > 25) return Tier.TWO;
            return Tier.ONE;
        }
    }
}
