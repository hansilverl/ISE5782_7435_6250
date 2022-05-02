package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


public abstract interface Geometry extends Intersectable {

 /*   //TODO: Constructor!
    Color emission =new Color(java.awt.Color.black);
     static void setEmission(Color col)
    {
        emission=col;
    }*/
    /**
     * Getter for normal vector
     *
     * @param point
     * @return
     */
    public abstract Vector getNormal(Point point);
}


