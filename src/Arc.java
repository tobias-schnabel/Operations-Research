public class Arc {

    private int origin;
    private int destination;
    private int weight;

    public Arc(int origin, int destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public int getOrigin() {
        return origin;
    }

    public int getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from " + origin +
                ", to " + destination +
                " with weight of" + weight +
                '}';
    }
}
