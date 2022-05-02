package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic  extends  RayTracer{

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersection(ray);
        if( intersections == null)
           return Color.BLACK;

        Point closestPoint = ray.findClosestPoint(intersections);
        Color color = calcColor(closestPoint,ray);
        return color;
    }

    private Color calcColor(Point closestPoint, Ray ray) {
        return scene.ambientLight.getIntensity();
    }
}
