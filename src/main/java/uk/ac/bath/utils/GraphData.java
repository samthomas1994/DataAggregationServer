package uk.ac.bath.utils;

/**
 * Created by Sam on 03/03/2016.
 */
public class GraphData {

    private String x;
    private int y;

    public GraphData() {

    }

    public GraphData(String x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("GraphData[x=%s, y='%d']", x, y);
    }
}
