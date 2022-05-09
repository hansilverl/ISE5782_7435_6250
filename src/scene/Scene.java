package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * Scene or holding all the objects involved
 * using Builder Pattern
 * @author Hannah Silverberg & Hila Buznach
 */
public class Scene {

    //TODO: JAVADOC!
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

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
     * @param background to define background field
     * @return scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setting geometries
     * @param geometries to define geometries field
     * @return scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public static class SceneBuilder {

        private final String name;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        /**
         *
         * @param name
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        //chaining methods

        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        // build
        public Scene build() {
            Scene scene = new Scene(this);
            //....
            return scene;
        }
    }
}
