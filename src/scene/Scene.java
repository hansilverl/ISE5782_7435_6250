package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene or holding all the objects involved
 * using Builder Pattern
 * @author Hannah Silverberg & Hila Buznach
 */
public class Scene {

    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<>();


    /**
     * constructing a scene
     * @param nm name of scene
     */
    public Scene(String nm) {
        name = nm;
        background = new Color(java.awt.Color.black);
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    /**
     * constructor with scene builder
     * @param builder to build scene
     */
    public Scene(SceneBuilder builder) {
        name = builder.name;
        background = builder.background;
        ambientLight = builder.ambientLight;
        geometries = builder.geometries;
    }

    /**
     * setting ambient light
     * @param myAmbientLight to define ambient light field
     * @return scene
     */
    public Scene setAmbientLight(AmbientLight myAmbientLight) {
        ambientLight = myAmbientLight;
        return this;

    }

    /**
     * setting background
     * @param myBackground to define background field
     * @return scene
     */
    public Scene setBackground(Color myBackground) {
        background = myBackground;
        return this;
    }

    /**
     * setting geometries
     * @param myGeometries to define geometries field
     * @return scene
     */
    public Scene setGeometries(Geometries myGeometries) {
        geometries = myGeometries;
        return this;
    }

    /**
     * setting lights
     * @param myLights to define lights field
     * @return scene
     */
    public Scene setLights(List<LightSource> myLights) {
        lights = myLights;
        return this;
    }

    public static class SceneBuilder {

        private final String name;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        /**
         * class to build the scene
         * @param name of scene
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        //chaining methods

        /**
         * setting background
         * @param background to define background field
         * @return scene
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        /**
         * setting ambient light
         * @param ambientLight to define ambient light field
         * @return scene
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         *
         * setting geometries
         * @param geometries to define geometries field
         * @return scene
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * build the scene
         * @return scene
         */
        // build
        public Scene build() {
            Scene scene = new Scene(this);
            //....
            return scene;
        }
    }
}
