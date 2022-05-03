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
        if (isTierTwoPrice(parcelDimension)) {
            return BigDecimal.valueOf(parcelDimension.weight).multiply(TIMES_4);
        }

        return BigDecimal.valueOf(TIER_ONE_PRICE);
    }

    private boolean isTierTwoPrice(ParcelDimension parcelDimension) {
        return parcelDimension.weight > 60 || (!(parcelDimension.height > 324) && parcelDimension.height > 229) || (parcelDimension.width > 162 && parcelDimension.width <= 229) || parcelDimension.depth > 50;
    }


    private record ParcelDimension(int weight, int height, int width, int depth) {
    }
}

