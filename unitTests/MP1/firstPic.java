/**
 *
 */
package MP1;

import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class firstPic {
    private Scene scene = new Scene("Test scene");


    @Test
    public void diamond() {
        Camera camera = new Camera(new Point(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, 0, 0),new Point(0,-450,-300), new Point(150,0,0)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-390, 0, -300),new Point(0,-450,-300), new Point(-150, 0, 0)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(150,0,0),new Point(0,-450,-300),new Point(390,0,-300)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                /*new Polygon(new Point(-150, 0, 0), new Point(150,100,-150),new Point(-150, 100, -150), new Point(150,0,0))//
                        .setEmission(new Color(153, 0, 0)).setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Polygon(new Point(-340, 100, -300), new Point(-390, 0, -300), new Point(-150, 0, 0), new Point(-150, 100, -150))//
                        .setEmission(new Color(153, 0, 0)).setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Polygon(new Point(150,100,-150), new Point(150,0,0), new Point(390, 0, -300), new Point(340, 100, -300))//
                        .setEmission(new Color(153, 0, 0)).setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),*/
                new Triangle(new Point(-150, 0, -600),new Point(0,-450,-300), new Point(150,0,-600)).setEmission(new Color(0, 153, 0))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-390, 0, -300),new Point(0,-450,-300), new Point(-150, 0, -600)).setEmission(new Color(0, 153, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(150,0,-600),new Point(0,-450,-300), new Point(390, 0, -300)).setEmission(new Color(0, 153, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)));
        scene.lights.add(
                new SpotLight(new Color(white), new Point(-100, -100, -500), new Vector(0, 1, 0)) //
                        .setKl(4E-5).setKq(2E-7));
//        scene.lights.add(new SpotLight(new Color(white), new Point(-25, -60, 500), new Vector(-1, -1, -2)).setKl(0.56).setKq(0.78));
        scene.lights.add( //
                new SpotLight(new Color(0, 100, 100), new Point(-200,-50,-350), new Vector(1, 1, -3)) //
                        .setKl(1E-10).setKq(1.5E-10));

        scene.lights.add(
                new PointLight(new Color(100, 0, 100), new Point(-100, -300, 500))
                        .setKl(1E-10).setKq(1.5E-10));

        scene.lights.add(
                new PointLight(new Color(100, 40, 100), new Point(100, 300, 500))
                        .setKl(1E-10).setKq(1.5E-10));

        scene.lights.add(
                new DirectionalLight(new Color(GREEN), new Vector(50, 50, 50))
        );
        ImageWriter imageWriter = new ImageWriter("diamond", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();


    }
}
