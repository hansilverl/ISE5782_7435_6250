package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * How much light comes from the source of light to the illuminated object.
 * @author Hanna Silverberg & Hila Buznach
 */
public class DirectionalLight extends Light implements LightSource{
    private Vector direction; // The direction of the light

    /**
     * constructor
     * @param color determines the color of the intensity
     * @param myDirection determines the direction of the light
     */
    protected DirectionalLight(Color color, Vector myDirection) {
        super(color);
        direction = myDirection;
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
        return null;
    }

    @Override
    public double getDistance(Point point) {
        return 0;
    }
}
