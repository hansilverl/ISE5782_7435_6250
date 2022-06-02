package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class to scan the ray and calculate the colour
 * Class extending {@link RayTracerBase}
 *
 * @author Hannah Silverberg & Hila buznach
 */
public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;    //The factor for adjustments for the shading rays (you can reduce its value according to the orders of the shape size)

    private static final int MAX_CALC_COLOR_LEVEL = 10; //

    private static final double MIN_CALC_COLOR_K = 0.001;


    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Representing shadows
     *
     * @param gp          geo point
     * @param l           vector
     * @param n           vector
     * @param lightSource in use
     * @return false if there are intersection
     */
    public boolean unshaded(GeoPoint gp, Vector l, Vector n, LightSource lightSource) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(point));
        return intersections == null;
    }

    /**
     * tracing ray
     *
     * @param ray to trace
     * @return pixel color
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> geoIntersections = scene.geometries.findGeoIntersections(ray);
        if (geoIntersections == null)
            return scene.background;
        GeoPoint closestGeo = ray.findClosestGeoPoint(geoIntersections);
        return calcColor(closestGeo, ray);
    }

    /**
     * calculating color
     *
     * @param closestPoint to VP
     * @param ray          traced to calculate
     * @return intensity color
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(closestPoint.geometry.getEmission()).add(calcLocalEffects(closestPoint, ray));
    }

    /**
     * @param intersection intersection point
     * @param ray          intersecting ray
     * @return colour of local effects
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().getShininess();

        Double3 kd = intersection.geometry.getMaterial().getKd();
        Double3 ks = intersection.geometry.getMaterial().getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // checks if nl == nv
                if (unshaded(intersection, l, n, lightSource)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }


    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        var r = l.add(n.scale(l.dotProduct(n) * -2));
        double vr = alignZero(v.scale(-1).dotProduct(r));
        if (vr < 0)
            vr = 0;
        vr = Math.pow(vr, nShininess);
        return lightIntensity.scale(ks.scale(vr));
    }

    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double ln = alignZero(l.dotProduct(n));
        if (ln < 0)
            ln = ln * -1;
        return lightIntensity.scale(kd.scale(ln));
    }
}
