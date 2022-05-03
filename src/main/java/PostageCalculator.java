import java.math.BigDecimal;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    public static final int TIER_ONE_PRICE = 120;

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        ParcelDimension parcelDimension = new ParcelDimension(weight, height, width, depth);
        BigDecimal amount = getAmountByTier(parcelDimension);

        return new Money(amount, Currency.GBP);
    }

    private BigDecimal getAmountByTier(ParcelDimension parcelDimension) {
        if (shouldApplyTierTwoPriceFor(parcelDimension)) {
            return BigDecimal.valueOf(parcelDimension.weight).multiply(TIMES_4);
        }

        return BigDecimal.valueOf(TIER_ONE_PRICE);
    }

    private boolean shouldApplyTierTwoPriceFor(ParcelDimension parcelDimension) {
        return isTierTwoPriceBasedOnWeight(parcelDimension) ||
                isTierTwoPriceBasedOnHeight(parcelDimension) ||
                isTierTwoPriceBasedOnWidth(parcelDimension) ||
                isTierTwoPriceBasedOnDepth(parcelDimension);
    }

    private boolean isTierTwoPriceBasedOnDepth(ParcelDimension parcelDimension) {
        return parcelDimension.depth > 50;
    }

    private boolean isTierTwoPriceBasedOnWidth(ParcelDimension parcelDimension) {
        return parcelDimension.width > 162 && parcelDimension.width <= 229;
    }

    private boolean isTierTwoPriceBasedOnHeight(ParcelDimension parcelDimension) {
        return !(parcelDimension.height > 324) && parcelDimension.height > 229;
    }

    private boolean isTierTwoPriceBasedOnWeight(ParcelDimension parcelDimension) {
        return parcelDimension.weight > 60;
    }


    private record ParcelDimension(int weight, int height, int width, int depth) {
    }
}

