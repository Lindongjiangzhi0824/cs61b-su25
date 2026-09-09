import java.nio.channels.Pipe;

/** A class that represents a path via pursuit curves. */
public class Path {
    public Point p;
    public Point p_next;

    public Path(double x, double y) {
        this.p = new Point(x, y);
        this.p_next = new Point(0, 0);
    }
    // TODO
    public double getCurrX() {
        return p.getX();
    }

    public double getCurrY() {
        return p.getY();
    }

    public double getNextX() {
        return p_next.getX();
    }

    public double getNextY() {
        return p_next.getY();
    }

    public Point getCurrentPoint() {
        return this.p;
    }

    public void setCurrentPoint(Point point) {
        this.p.setX(point.getX());
        this.p.setY(point.getY());
    }

    public void iterate(double dx, double dy) {
        this.p = new Point(this.p_next.getX(), this.p_next.getY());

        this.p_next.setX(p_next.getX() + dx);
        this.p_next.setY(p_next.getY() + dy);
    }

}
