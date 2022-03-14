package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * common interface for all graphic 3D objects
 * that interface with a specific Ray {@link primitives.Ray}
 */
public interface Intersectable {

    List<Point> findIntersection(Ray ray);
    /**
     * find all intersection points {@link Point}
     * that intersect the Shape from a specific Ray {@link Ray}
     * @param ray Ray pointing towards the *******************************
     ************************************missing explanation
     */
}
