package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


/**
 * Geometry class to represent shapes
 * @author Hila & Hannah
 */
public abstract class Geometry extends Intersectable {

    protected static Color emission =new Color(java.awt.Color.black);

    /**
     * @return {@link Geometry#emission}
     */
    public static Color getEmission() {
        return emission;
    }

    /**
     * @param myEmission to set {@link Geometry#emission}
     * @return @return {@link Geometry}
     */
    public Geometry setEmission(Color myEmission) {
        emission = myEmission;
        return this;
    }

    /**
     * Getter for normal vector
     *
     * @param point
     * @return
     */
    public abstract Vector getNormal(Point point);
}


