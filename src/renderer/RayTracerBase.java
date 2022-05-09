package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract class to trace ray.
 * @author Hannah Silverberg & Hila Buznach
 */
public abstract  class RayTracerBase {
    protected final Scene scene;

    /**
     * constructor
     * @param myScene value for scene
     */
    protected RayTracerBase(Scene myScene) {
        scene = myScene;
    }

    /**
     * find the intersection and the scene's geometries
     * @param ray to trace
     * @return color
     */
    public abstract Color traceRay(Ray ray) ;
}
