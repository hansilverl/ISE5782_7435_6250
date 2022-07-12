package lighting;

import primitives.Color;
import primitives.*;
/**
 * The class to represent Light Source
 * @author Hannah Silverberg & Hila Buznach
 */
public interface LightSource {
    /**
     * Get intensity function
     * @param p certain point in scene
     * @return the color of the point
     */
    Color getIntensity(Point p);

    /**
     * Get function
     * @param p certain point in scene
     * @return the value of the direction of the light
     */
    Vector getL(Point p);

    /**
     * Get distance function
     * @param point certain pont in scene
     * @return the distance
     */
    double getDistance(Point point);


}