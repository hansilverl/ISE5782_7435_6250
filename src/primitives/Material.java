package primitives;


import static primitives.Util.isZero;

/**
 * Material class to represent different materials
 *
 * @author Hannah Silverberg & Hila Buznach
 */
public class Material {
    Double3 kD = new Double3(0.0);  //Diffuse factor (spreading of the light)
    Double3 kS = new Double3(0.0);  //percentage of energy that goes to direct reflection
    int nShininess = 0; //the object’s shininess, Max light that is in the return direction by the value of n

    Double3 kT = new Double3(0.0); //Transparency coefficient

    Double3 kR = new Double3(0.0); //Reflection, Perfect mirror has a kr = 1 and matt surface has a kr = 0

    public double glossiness = 0;   //Amount of glossiness


    /**
     * setter function that get double variable
     *
     * @param myKD to set {@link Material#kD}
     * @return object (applying builder pattern)
     */
    public Material setKd(double myKD) {
        kD = new Double3(myKD);
        return this;
    }

    /**
     * setter function that get Double3 variable
     *
     * @param myKD to set {@link Material#kD}
     * @return object (applying builder pattern)
     */
    public Material setKd(Double3 myKD) {
        kD = myKD;
        return this;
    }

    /**
     * setter function that get double variable
     *
     * @param myKS to set {@link Material#kS}
     * @return object (applying builder pattern)
     */
    public Material setKs(double myKS) {
        kS = new Double3(myKS);
        return this;
    }

    /**
     * setter function that get Double3 variable
     *
     * @param myKS to set {@link Material#kS}
     * @return object (applying builder pattern)
     */
    public Material setKs(Double3 myKS) {
        kS = myKS;
        return this;
    }

    /**
     * setter function that get int variable
     *
     * @param nS to set {@link Material#nShininess}
     * @return object (applying builder pattern)
     */
    public Material setShininess(int nS) {
        nShininess = nS;
        return this;
    }

    /**
     * using builder pattern for setter
     *
     * @param kT to initialize
     * @return object
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter function that get double variable
     *
     * @param kT to initialize
     * @return object
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * using builder pattern for setter
     *
     * @param kR to initialize
     * @return object
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * setter function that get double variable
     *
     * @param kR to initialize
     * @return object
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * @return {@link Material#kD}
     */
    public Double3 getKd() {
        return kD;
    }

    /**
     * @return @return {@link Material#kS}
     */
    public Double3 getKs() {
        return kS;
    }

    /**
     * @return {@link Material#nShininess}
     */
    public int getShininess() {
        return nShininess;
    }

    /**
     * @return {@link Material#kT}
     */
    public Double3 getKt() {
        return kT;
    }

    /**
     * @return {@link Material#kR}
     */
    public Double3 getKr() {
        return kR;
    }


}
