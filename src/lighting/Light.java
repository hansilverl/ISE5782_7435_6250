package lighting;

import primitives.Color;

/**
 * Class to represent lighting
 * @author Hila Buznach & Hannah Silverberg
 */
public abstract class Light {
    private Color intensity;

    protected Light(Color color) {
        intensity = color;
    }

    public Color getIntensity() {
        return intensity;
    }
}