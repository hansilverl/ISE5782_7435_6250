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

    private static final int MAX_CALC_COLOR_LEVEL = 10; //

    private static final double MIN_CALC_COLOR_K = 0.001;

    private static final Double3 INITIAL_K = new Double3(1.0);


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
        Point point = gp.point;
        Ray lightRay = new Ray(point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(point));
        if (intersections != null) {
            double lightDistance = lightSource.getDistance(point);
            for (GeoPoint intersection : intersections) {
                if (alignZero(intersection.point.distance(point) - lightDistance) <= 0 && intersection.geometry.getMaterial().getKt().equals(new Double3(0))) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Representing transparency
     *
     * @param gp geo point
     * @param l  vector
     * @param n  vector
     * @param ls in use
     * @return false if there are intersection
     */
    private Double3 transparency(GeoPoint gp, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Point point = gp.point;
        Ray lightRay = new Ray(point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, ls.getDistance(point));
        if (intersections != null) {
            Double3 ktr = new Double3(1.0);
            double lightDistance = ls.getDistance(point);
            for (GeoPoint intersection : intersections) {
                if (alignZero(gp.point.distance(gp.point) - lightDistance) <= 0) {
                    ktr = ktr.product(gp.geometry.getMaterial().getKt());
                    if (ktr.lowerThan(MIN_CALC_COLOR_K)) return new Double3(0.0);
                }
            }
            return ktr;
        }
        return new Double3(1.0);
    }

    /**
     * tracing ray
     * Override
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
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * @param intersection to check
     * @param ray          intersected
     * @param level        of recursion
     * @param k            level
     * @return colour
     */
    public Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = intersection.geometry.getEmission()
                .add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }


    /**
     * @param intersection point
     * @param ray          intersecting
     * @param k            level
     * @return local effects colour
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
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


    /**
     * @param intersection point to check
     * @param inRay        direction of ray
     * @param level        of recursion
     * @param k            of recursion
     * @return global effects colour
     */
    private Color calcGlobalEffects(GeoPoint intersection, Ray inRay, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        Double3 kr = material.getKr();
        Double3 kkr = k.product(kr);
        Vector n = intersection.geometry.getNormal(intersection.point);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflectedRay(n, intersection.point, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        Double3 kt = material.getKt();
        Double3 kkt = k.product(kt);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefractedRay(n, intersection.point, inRay);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }

    private Ray constructRefractedRay(Vector n, Point point, Ray inRay) {
        return new Ray(point, inRay.getDir(), n);
    }

    //Constructing reflection
    private Ray constructReflectedRay(Vector n, Point point, Ray inRay) {
        Vector v = inRay.getDir();
        Vector r = null;
        try {
            r = v.subtract(n.scale(v.dotProduct(n)).scale(2)).normalize();
        } catch (Exception e) {
            return null;
        }
        return new Ray(point, r, n);
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

    /**
     * @param ray intersecting
     * @return closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        if (ray == null) return null;
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }

}
