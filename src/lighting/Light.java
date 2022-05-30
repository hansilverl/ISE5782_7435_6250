package lighting;

import primitives.Color;

/**
 * Class to represent lighting
 * @author Hila Buznach & Hannah Silverberg
 */
public abstract class Light {
    private Color intensity;

    /**
     * constructor
     * @param color determines the color of the intensity
     */
    protected Light(Color color) {
        intensity = color;
    }

    /**
     * get function
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}