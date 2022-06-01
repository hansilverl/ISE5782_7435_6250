package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import primitives.Material;
/**
 * Geometry class to represent shapes
 * @author Hila & Hannah
 */
public abstract class Geometry extends Intersectable {

    protected Color emission =new Color(java.awt.Color.black);
    private Material material = new Material();

    /**
     * Setter for material field
     * @param material of {@link Geometry}
     * @return object (as in builder pattern)
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * @return {@link Geometry#material}
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @return {@link Geometry#emission}
     */
    public Color getEmission() {
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
     * @param point to normalize
     * @return normal;
     */
    public abstract Vector getNormal(Point point);
}


