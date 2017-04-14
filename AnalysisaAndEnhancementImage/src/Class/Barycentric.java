package Class;

public class Barycentric {
	Barycentric(Point p1, Point p2, Point p3) {
        x3 = p3.x;
        y3 = p3.y;
        y23 = p2.y - y3;
        x32 = x3 - p2.x;
        y31 = y3 - p1.y;
        x13 = p1.x - x3;
        det = y23 * x13 - x32 * y31;
        minD = Math.min(det, 0);
        maxD = Math.max(det, 0);
    }

    public boolean contains(Point p) {
        double dx = p.x - x3;
        double dy = p.y - y3;
        double a = y23 * dx + x32 * dy;
        if (a < minD || a > maxD)
            return false;
        double b = y31 * dx + x13 * dy;
        if (b < minD || b > maxD)
            return false;
        double c = det - a - b;
        if (c < minD || c > maxD)
            return false;
        return true;
    }

    private  double x3, y3;
    private  double y23, x32, y31, x13;
    private  double det, minD, maxD;
}