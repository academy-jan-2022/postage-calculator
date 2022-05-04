import javax.print.DocFlavor;
import java.math.BigDecimal;

import static java.math.RoundingMode.UNNECESSARY;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    private static final BigDecimal TIMES_6 = BigDecimal.valueOf(6);
    public static final int TIER_ONE_PRICE = 120;
    public static final BigDecimal CONVERSION_FEE = BigDecimal.valueOf(20);
    public static final BigDecimal EUR_CONVERSION_RATE = BigDecimal.valueOf(1.19);
    public static final BigDecimal USD_CONVERSION_RATE = BigDecimal.valueOf(1.25);

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        var parcelDimension = new ParcelDimension(weight, height, width, depth);

        var pricingTierCalculator = new PricingTierCalculator(parcelDimension);
        Tier tier = pricingTierCalculator.checkParcelPricingTier();

        BigDecimal amount = getAmountBy(tier, parcelDimension);


        return new Money(convertTo(currency, amount), currency);
    }

    private BigDecimal convertTo(Currency currency, BigDecimal amount) {
        return switch (currency) {
            case EUR -> amount.multiply(EUR_CONVERSION_RATE).add(CONVERSION_FEE);
            case USD -> amount.multiply(USD_CONVERSION_RATE).add(CONVERSION_FEE);
            case GBP -> amount;
        };
    }

    private BigDecimal getAmountBy(Tier tier, ParcelDimension parcelDimension) {
        return switch (tier) {
            case ONE -> BigDecimal.valueOf(TIER_ONE_PRICE);
            case TWO -> BigDecimal.valueOf(parcelDimension.weight()).multiply(TIMES_4);
            case THREE -> weightTimesSix(parcelDimension).max(sumOfAllDimension(parcelDimension));
        };
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

