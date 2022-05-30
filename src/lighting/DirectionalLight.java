package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    /**
     * constructor
     *
     * @param color determines the color of the intensity
     */
    protected DirectionalLight(Color color) {
        super(color);
    }

    /**
     * Get intensity function
     *
     * @param p certain point in scene
     * @return the color of the point
     */
    @Override
    public Color getIntensity(Point p) {
        return null;
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
