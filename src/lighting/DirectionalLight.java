package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * How much light comes from the source of light to the illuminated object.
 * @author Hanna Silverberg & Hila Buznach
 */
public class DirectionalLight extends Light implements LightSource{
    private final Vector direction; // The direction of the light

    /**
     * constructor
     * @param color determines the color of the intensity
     * @param myDirection determines the direction of the light
     */
    public DirectionalLight(Color color, Vector myDirection) {
        super(color);
        direction = myDirection.normalize();
    }

    /**
     * Get intensity function
     *
     * @param p certain point in scene
     * @return the color of the point
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * @param p certain point in scene
     * @return vector
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }

    /**
     * @param point certain pont in scene
     * @return distance
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
