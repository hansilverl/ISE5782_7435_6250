package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Models point light source with direction (such as a luxo lamp)
 * @author Hannah Silverberg & Hila Buznach
 */
public class SpotLight extends PointLight{
    private Vector direction; // The direction of the light

    /**
     * constructor
     * @param color determines the color of the intensity
     * @param myPosition determines the position of the point
     * @param myKC determines the kc
     * @param myKL determines the kl
     * @param myKQ determines the kq
     * @param myDirection determines the direction of the light
     */
    protected SpotLight(Color color, Point myPosition, double myKC, double myKL, double myKQ, Vector myDirection) {
        super(color, myPosition, myKC, myKL, myKQ);
        direction = myDirection;
    }
}
