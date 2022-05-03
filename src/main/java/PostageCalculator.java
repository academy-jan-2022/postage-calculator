import java.math.BigDecimal;

public class PostageCalculator {

    public static final BigDecimal TIMES_4 = BigDecimal.valueOf(4);
    public static final int TIER_ONE_PRICE = 120;

    public Money calculate(int weight, int height, int width, int depth, Currency currency) {
        ParcelDimension parcelDimension = new ParcelDimension(weight, height, width, depth);
        Tier tier = parcelDimension.getTier();
        BigDecimal amount = getAmountBy(tier, parcelDimension);

        return new Money(amount, Currency.GBP);
    }

    private BigDecimal getAmountBy(Tier tier, ParcelDimension parcelDimension) {
        if (Tier.TWO == tier) {
            return BigDecimal.valueOf(parcelDimension.weight()).multiply(TIMES_4);
        }

        return BigDecimal.valueOf(TIER_ONE_PRICE);
    }

}

