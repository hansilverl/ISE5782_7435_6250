package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Models omni-directional point source (such as a bulb)
 *
 * @author Hanna Silverberg & Hila Buznach
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * constructor
     *
     * @param color      determines the color of the intensity
     * @param myPosition determines the position of the point
     */
    public PointLight(Color color, Point myPosition) {
        super(color);
        position = myPosition;
    }

    /**
     * Get intensity function
     *
     * @param p certain point in scene
     * @return the color of the point
     */
    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        Color iL = getIntensity().scale((1 / (kC + kL * d + kQ * d * d)));
        return iL;
    }

    /**
     * @param p certain point in scene
     * @return vector
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();

    }

    /**
     * get function
     *
     * @param point certain pont in scene
     * @return
     */
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    /**
     * set function
     *
     * @param myPosition determines the position of the point
     * @return object
     */
    public PointLight setPosition(Point myPosition) {
        position = myPosition;
        return this;
    }

    /**
     * set function
     *
     * @param myKC determines the kc
     * @return object
     */
    public PointLight setkC(double myKC) {
        kC = myKC;
        return this;
    }

    /**
     * set function
     *
     * @param myKL determines the kl
     * @return object
     */
    public PointLight setKl(double myKL) {
        kL = myKL;
        return this;
    }

    /**
     * set function
     *
     * @param myKQ determines the kq
     * @return object
     */
    public PointLight setKq(double myKQ) {
        kQ = myKQ;
        return this;
    }
}
