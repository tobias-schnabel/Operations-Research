/* Authors:
Schnabel, Tobias
Dohmen, Raoul
Pulles, Obbe*/

public class Arc {

    private int origin;
    private int destination;
    private final int weight;
    private int flow;

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

    public void setFlow(int flow) {
        this.flow = flow;
    }


    @Override
    public String toString() {
        return "Arc " +
                "from " + origin +
                " to " + destination +
                " with flow " + flow +
                " / " + weight +
                " .";
    }
}
