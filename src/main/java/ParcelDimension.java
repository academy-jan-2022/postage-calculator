import java.util.stream.Stream;

record ParcelDimension(Measurement weight, Measurement height, Width width, Depth depth) {

    public Tier getTier() {
        return Stream.of(width.getTier(), depth.getTier(), weight.getTier(), height.getTier())
                .reduce(Tier.ONE, Tier::comparePriority);

    }

    public int getWeight(){
        return weight.getValue();
    }
}
