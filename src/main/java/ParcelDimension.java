import java.util.stream.Stream;

abstract class Measurement {
    private final int value;
    private final int limitOne;
    private final int limitTwo;

    Measurement(int value, int limitOne, int limitTwo){
        this.value = value;
        this.limitOne = limitOne;
        this.limitTwo = limitTwo;
    }
    protected Tier getTier(){
        if(value > limitTwo ){
            return Tier.THREE;
        }

        if(value > limitOne){
            return Tier.TWO;
        }

        return Tier.ONE;
    }

    protected int getValue(){
        return value;
    }
}

class Weight extends Measurement {
    Weight(int value) {
        super(value, 60, 500);
    }
}

class Height extends Measurement {
    Height(int value) {
        super(value, 229, 324);
    }
}

record ParcelDimension(Measurement weight, Measurement height, Width width, int depth) {

    public Tier getTier() {
        return Stream.of(getWidthTier(), getDepthTier(), weight.getTier(), height.getTier())
                .reduce(Tier.ONE, Tier::comparePriority);

    }

    public int getWeight(){
        return weight.getValue();
    }

    private Tier getWidthTier() {
       return width.getTier();
    }


    private Tier getDepthTier() {
        if(depth > 100){
            return Tier.THREE;
        }

        if(depth > 25){
            return Tier.TWO;
        }

        return Tier.ONE;
    }
}
