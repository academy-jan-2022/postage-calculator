import java.math.BigDecimal;

public class CurrencyConverter {
    public static final BigDecimal CONVERSION_FEE = BigDecimal.valueOf(20);
    public static final BigDecimal EUR_CONVERSION_RATE = BigDecimal.valueOf(1.19);
    public static final BigDecimal USD_CONVERSION_RATE = BigDecimal.valueOf(1.25);

    private CurrencyConverter() {
    }

    public static BigDecimal convertTo(Currency currency, BigDecimal amount) {
        return switch (currency) {
            case EUR -> amount.multiply(EUR_CONVERSION_RATE).add(CONVERSION_FEE);
            case USD -> amount.multiply(USD_CONVERSION_RATE).add(CONVERSION_FEE);
            case GBP -> amount;
        };
    }
}
