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
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        //scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, 0, 0),new Point(0,-450,-300), new Point(150,0,0)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-390, 0, -300),new Point(0,-450,-300), new Point(-150, 0, 0)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(150,0,0),new Point(0,-450,-300),new Point(390,0,-300)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-150, 0, 0),new Point(0,150,-100), new Point(150,0,0)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-390, 0, -300),new Point(-210,150,-100), new Point(-150, 0, 0)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(150,0,0),new Point(210,150,-100),new Point(390,0,-300)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-210,150,-100),new Point(-150, 0, 0),new Point(0,150,-100)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(0,150,-100),new Point(150,0,0),new Point(210,150,-100)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(150,0,0),new Point(0,-450,-300),new Point(390,0,-300)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-150, 0, -600),new Point(0,-450,-300), new Point(150,0,-600)).setEmission(new Color(153, 0, 0))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(-390, 0, -300),new Point(0,-450,-300), new Point(-150, 0, -600)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Triangle(new Point(150,0,-600),new Point(0,-450,-300), new Point(390, 0, -300)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6))
                /*new Polygon(new Point(-150, 150, -500),new Point(-150, 0, -600), new Point(150,0,-600), new Point(150,150,-500)).setEmission(new Color(153, 0, 0))//
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Polygon(new Point(-390, 150, -200),new Point(-390, 0, -300), new Point(-150, 0, -600), new Point(-150, 150, -500)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6)),
                new Polygon(new Point(150,150,-500),new Point(150,0,-600), new Point(390, 0, -300),new Point(390, 150, -200)).setEmission(new Color(153, 0, 0)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.6))*/);
        scene.lights.add(
                new SpotLight(new Color(WHITE), new Point(-100, -100, -500), new Vector(0, 1, 0)) //
                        .setKl(1E-10).setKq(1.5E-10));
        scene.lights.add( //
                new SpotLight(new Color(0, 100, 100), new Point(-200, -200, 300), new Vector(1, 1, -3)) //
                        .setKl(1E-10).setKq(1.5E-10));

        scene.lights.add(
                new PointLight(new Color(100, 0, 100), new Point(-100, -300, 500))
                        .setKl(1E-10).setKq(1.5E-10));

        scene.lights.add(
                new PointLight(new Color(100, 40, 100), new Point(100, 300, 500))
                        .setKl(1E-10).setKq(1.5E-10));

        scene.lights.add(
                new PointLight(new Color(100, 40, 100), new Point(100, 300, -500))
                        .setKl(1E-10).setKq(1.5E-10));

        //The back
        scene.lights.add(
                new PointLight(new Color(100, 40, 100), new Point(100, 300, 500))
                        .setKl(1E-10).setKq(1.5E-10));

        ImageWriter imageWriter = new ImageWriter("diamond", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();


    }
}
