package Improvements;

import geometries.*;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Class to showcase improvements made in MP2 & 1
 */
public class improvements {

    /**
     * no improvements
     */
    @Test
    public void noImprovements() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(false), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(false), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBvhIsOn(false));

        scene.geometries.setBvhIsOn(false);
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("no improvements", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    private final Scene scene = new Scene("Test scene");

    /**
     * no runtime improvements
     * picture improvements: antialiasing
     */
    @Test
    public void RandomAntiAliasing() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(false), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(false), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBvhIsOn(true));

        scene.geometries.setBvhIsOn(false);
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("RandomAntiAliasing", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImageSuperSampling() //
                .writeToImage();
    }

    /**
     * runtime improvements: adaptive supersampling
     * picture improvements: antialiasing
     */
    @Test
    public void adaptiveSS() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(false), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(false), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBvhIsOn(false));

        scene.geometries.setBvhIsOn(false);
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("adaptiveSS", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImageAdaptiveSuperSampling() //
                .writeToImage();
    }

    /**
     * runtime improvements: adaptive supersampling, Multithreading
     * picture improvements: antialiasing
     */
    @Test
    public void adaptiveSSWithMT() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("adaptiveSSWithMT", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImageMultiThreading() //
                .writeToImage();
    }


    /**
     * runtime improvements: adaptive super sampling, BVH
     * picture improvements: antialiasing
     */
    @Test
    public void adaptiveSSWithBVH() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add(new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(true), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(true), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBvhIsOn(false));

        scene.geometries.setBvhIsOn(true);
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("adaptiveSSWithBVH", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImageAdaptiveSuperSampling() //
                .writeToImage();
    }

    /**
     * Test with no BVH Improvements (similar to no improvements test)
     */
    @Test
    public void noBVH() {
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //front lens
        /*Camera camera1 = new Camera(new Point(-1400, 20, 800), new Vector(0.5, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //side lens
        Camera camera1 = new Camera(new Point(0, 0, -2500), new Vector(0, 0, 1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //back lens
        Camera camera1 = new Camera(new Point(-1300, -70, 900), new Vector(0.68, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000);*/



        scene.setAmbientLight(new AmbientLight(new Color(BLACK), 100));
        Point p1 = new Point(-775,1060,10);
        Point p2 = new Point(-795,1060,10);
        scene.lights.add(new SpotLight(new Color(950, 150, 150), p1, p2.subtract(p1)).setkC(1000).setKl(0.0001).setKq(0.00005));
        scene.lights.add( //
                new SpotLight(new Color(YELLOW), new Point(1000, 1000, 1000), new Vector(1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5).setkC(1000));

        scene.geometries.add(

                //floor
                new Plane(new Point(-760,-1066,22),new Point(-671,-1066,-1322),new Point(760,-1066,-300))//
                        .setEmission(new Color(86, 86, 86)).setMaterial(new Material().setKr(0.2)),

                //Main rectangle - front - behind bottom - behind top - front frame - behind frame
                new Cuboid(new Point(-760, 600, 0),1520,1600, 300, new Color(237, 242, 252),0,0.03,10),
                new Cuboid(new Point(-660, 0, -300),1320,1000, 1500, new Color(237, 242, 252)),
                new Cuboid(new Point(-600, 600, -300),1200,1000, 1400, new Color(237, 242, 252)),
                new Cuboid(new Point(-771, -970, 22),1542,96, 344, new Color(220, 226, 238),0,0.07,100),
                new Cuboid(new Point(-671, -970, -278),1342,96, 1544, new Color(220, 226, 238),0,0.07),

                //small door
                new Cuboid(new Point(-235, -290,1),470,710,5,new Color(123,123,123)));
            scene.geometries.setBvhIsOn(false);

        camera1.setRayTracer(new RayTracerBasic(scene)).setImageWriter(new ImageWriter("noBVH", 2010, 2010))
                .renderImage()
                .writeToImage();
    }

    /**
     * rendering with bvh
     */
    @Test
    public void BVH() {
        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //front lens
        /*Camera camera1 = new Camera(new Point(-1400, 20, 800), new Vector(0.5, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //side lens
        Camera camera1 = new Camera(new Point(0, 0, -2500), new Vector(0, 0, 1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //back lens
        Camera camera1 = new Camera(new Point(-1300, -70, 900), new Vector(0.68, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000);*/



        scene.setAmbientLight(new AmbientLight(new Color(BLACK), 100));
        Point p1 = new Point(-775,1060,10);
        Point p2 = new Point(-795,1060,10);
        scene.lights.add(new SpotLight(new Color(950, 150, 150), p1, p2.subtract(p1)).setkC(1000).setKl(0.0001).setKq(0.00005));
        scene.lights.add( //
                new SpotLight(new Color(YELLOW), new Point(1000, 1000, 1000), new Vector(1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5).setkC(1000));

        scene.geometries.add(

                //floor
                new Plane(new Point(-760,-1066,22),new Point(-671,-1066,-1322),new Point(760,-1066,-300))//
                        .setEmission(new Color(86, 86, 86)).setMaterial(new Material().setKr(0.2)),

                //Main rectangle - front - behind bottom - behind top - front frame - behind frame
                new Cuboid(new Point(-760, 600, 0),1520,1600, 300, new Color(237, 242, 252),0,0.03,10),
                new Cuboid(new Point(-660, 0, -300),1320,1000, 1500, new Color(237, 242, 252)),
                new Cuboid(new Point(-600, 600, -300),1200,1000, 1400, new Color(237, 242, 252)),
                new Cuboid(new Point(-771, -970, 22),1542,96, 344, new Color(220, 226, 238),0,0.07,100),
                new Cuboid(new Point(-671, -970, -278),1342,96, 1544, new Color(220, 226, 238),0,0.07),

                //small door
                new Cuboid(new Point(-235, -290,1),470,710,5,new Color(123,123,123)));
        scene.geometries.setBvhIsOn(true);

        camera1.setRayTracer(new RayTracerBasic(scene)).setImageWriter(new ImageWriter("BVH", 2010, 2010))
                .renderImage()
                .writeToImage();

    }

    /**
     * runtime improvements: adaptive supersampling, BVH, MT
     * picture improvements: antialiasing
     */
    @Test
    public void adaptiveSSWithBVHAndMT() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add(new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(true), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(true), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)).setBvhIsOn(false));

        scene.geometries.setBvhIsOn(true);
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("adaptiveSSWithBVHAndMT", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImageMultiThreading_AdaptSS() //
                .writeToImage();
    }

}
