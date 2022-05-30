package lighting;

import primitives.Color;
import primitives.*;
/**
 * The class to represent Light Source
 * @author Hannah Silverberg & Hila Buznach
 */public interface LightSource {
    /**
     * Get intensity function
     * @param p certain point in scene
     * @return the color of the point
     */
     public Color getIntensity(Point p);

    /**
     *
     * @param p certain point in scene
     * @return vector
     */
    public Vector getL(Point p);

    public double getDistance(Point point);


}