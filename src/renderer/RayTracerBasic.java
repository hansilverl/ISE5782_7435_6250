package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * Class to scan the ray and calculate the colour
 * Class extending {@link RayTracerBase}
 *
 * @author Hannah Silverberg & Hila buznach
 */
public class RayTracerBasic  extends  RayTracerBase{

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * tracing ray
     * @param ray to trace
     * @return pixel color
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersection(ray);
        if (intersections == null)
            return scene.background;
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint,ray);
    }

    /**
     * calculating color
     * @param closestPoint to VP
     * @param ray traced to calculate
     * @return intensity color
     */
    private Color calcColor(Point closestPoint, Ray ray) {
        return scene.ambientLight.getIntensity();
    }
}
