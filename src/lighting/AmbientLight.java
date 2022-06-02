package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient light for all objects in the scene
 * @author Hannah Silverberg & Hila Buznach
 */
public class AmbientLight extends Light {

    /**
     * primary constructor - call to father constructor
     * @param Ia basic illumination
     * @param Ka attenuation factor
     */
    public AmbientLight(Color Ia , Double3 Ka){
        super(Ia.scale(Ka));
    }

    /**
     * @param Ia basic illumination
     * @param Ka attenuation factor
     */
    public AmbientLight(Color Ia , double Ka){
        super(Ia.scale(Ka));
    }

    /**
     * default constructor send to the father constructor Black
     */
    public AmbientLight(){
        super(Color.BLACK);
    }

}
