public record ParcelDimension(int weight, int height, int width, int depth) {
    public int multiplyAll() {
        return weight * height * width * depth;
    }
}
