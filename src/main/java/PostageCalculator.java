import java.math.BigDecimal;

import static java.math.RoundingMode.UNNECESSARY;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    private static final BigDecimal TIMES_6 = BigDecimal.valueOf(6);
    public static final int TIER_ONE_PRICE = 120;

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        var parcelDimension = new ParcelDimension(weight, height, width, depth);

        var pricingTierCalculator = new PricingTierCalculator(parcelDimension);
        Tier tier = pricingTierCalculator.checkParcelPricingTier();

        BigDecimal amount = getAmountBy(tier, parcelDimension);

        return new Money(amount, Currency.GBP);
    }

    private BigDecimal getAmountBy(Tier tier, ParcelDimension parcelDimension) {
        if (Tier.TWO == tier) {
            return BigDecimal.valueOf(parcelDimension.weight()).multiply(TIMES_4);
        }

        if (Tier.THREE == tier) {
            return weightTimesSix(parcelDimension).max(sumOfAllDimension(parcelDimension));
        }

        return BigDecimal.valueOf(TIER_ONE_PRICE);
    }

    private BigDecimal sumOfAllDimension(ParcelDimension parcelDimension) {
        return BigDecimal.valueOf(parcelDimension.multiplyAll())
                .divide(BigDecimal.valueOf(1000), UNNECESSARY)
                .multiply(TIMES_6);
    }

    private BigDecimal weightTimesSix(ParcelDimension parcelDimension) {
        return BigDecimal.valueOf(parcelDimension.weight()).multiply(TIMES_6);
    }

}

