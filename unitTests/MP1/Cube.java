package MP1;


import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class Cube {
    public static final double coordinate = 300.0;

    Color pink = new Color(100, 75.3, 79.6).reduce(4);
    Color skyBlue = new Color(52.94, 80.78, 92.16).reduce(4);
    Color gold = new Color(255, 215, 0).reduce(4);
    Color peach = new Color(100, 79.61, 64.31).reduce(4);
    Color brightGreen = new Color(128, 255, 0).reduce(4);
    Color purple = new Color(153, 51, 255).reduce(4);
    Color strongPink = new Color(255, 0, 127).reduce(4);
    Color brightYellow = new Color(255, 255, 51).reduce(4);
    Color blue = new Color(51, 51, 255).reduce(4);
    Color brightRed = new Color(255, 102, 102).reduce(4);
    Color silver = new Color(192, 192, 192).reduce(4);

    Material triangleMat = new Material()
            .setKd(new Double3(0.5))
            .setKs(new Double3(0.5))
            .setShininess(300);

    Material sphereMat = new Material()
            .setKd(new Double3(0.5))
            .setKs(new Double3(0.5))
            .setShininess(300);

    Intersectable
            Triangle1 = new Triangle(
            new Point(-coordinate, -coordinate, -coordinate),
            new Point(-coordinate, -coordinate, coordinate),
            new Point(-coordinate, coordinate, coordinate))
            .setEmission(pink)
            .setMaterial(triangleMat),
            Triangle2 = new Triangle(
                    new Point(coordinate, coordinate, -coordinate),
                    new Point(-coordinate, -coordinate, -coordinate),
                    new Point(-coordinate, coordinate, -coordinate))
                    .setEmission(skyBlue)
                    .setMaterial(triangleMat),
            Triangle3 = new Triangle(
                    new Point(coordinate, -coordinate, coordinate),
                    new Point(-coordinate, -coordinate, -coordinate),
                    new Point(coordinate, -coordinate, -coordinate))
                    .setEmission(gold)
                    .setMaterial(triangleMat),
            Triangle4 = new Triangle(
                    new Point(coordinate, coordinate, -coordinate),
                    new Point(coordinate, -coordinate, -coordinate),
                    new Point(-coordinate, -coordinate, -coordinate))
                    .setEmission(peach)
                    .setMaterial(triangleMat),
            Triangle5 = new Triangle(
                    new Point(-coordinate, -coordinate, -coordinate),
                    new Point(-coordinate, coordinate, coordinate),
                    new Point(-coordinate, coordinate, -coordinate))
                    .setEmission(brightGreen)
                    .setMaterial(triangleMat),
            Triangle6 = new Triangle(
                    new Point(coordinate, -coordinate, coordinate),
                    new Point(-coordinate, -coordinate, coordinate),
                    new Point(-coordinate, -coordinate, -coordinate))
                    .setEmission(purple)
                    .setMaterial(triangleMat),
            Triangle7 = new Triangle(
                    new Point(-coordinate, coordinate, coordinate),
                    new Point(-coordinate, -coordinate, coordinate),
                    new Point(coordinate, -coordinate, coordinate))
                    .setEmission(strongPink)
                    .setMaterial(triangleMat),
            Triangle8 = new Triangle(
                    new Point(coordinate, coordinate, coordinate),
                    new Point(coordinate, -coordinate, -coordinate),
                    new Point(coordinate, coordinate, -coordinate))
                    .setEmission(brightYellow)
                    .setMaterial(triangleMat),
            Triangle9 = new Triangle(
                    new Point(coordinate, -coordinate, -coordinate),
                    new Point(coordinate, coordinate, coordinate),
                    new Point(coordinate, -coordinate, coordinate))
                    .setEmission(blue)
                    .setMaterial(triangleMat),
            Triangle10 = new Triangle(
                    new Point(coordinate, coordinate, coordinate),
                    new Point(coordinate, coordinate, -coordinate),
                    new Point(-coordinate, coordinate, -coordinate))
                    .setEmission(brightRed)
                    .setMaterial(triangleMat),
            Triangle11 = new Triangle(
                    new Point(coordinate, coordinate, coordinate),
                    new Point(-coordinate, coordinate, -coordinate),
                    new Point(-coordinate, coordinate, coordinate))
                    .setEmission(silver)
                    .setMaterial(triangleMat),
            Triangle12 = new Triangle(
                    new Point(coordinate, coordinate, coordinate),
                    new Point(-coordinate, coordinate, coordinate),
                    new Point(coordinate, -coordinate, coordinate))
                    .setEmission(blue)
                    .setMaterial(triangleMat),
            Sphere1 = new Sphere(
                    new Point(-700.0, 600.0, 150.0), 350.0)
                    .setEmission(new Color(BLUE).reduce(2)) //
                    .setMaterial(sphereMat),
            Sphere2 = new Sphere(
                    new Point(-350.0, 600.0, 675.0), 275)
                    .setEmission(new Color(YELLOW).reduce(2)) //
                    .setMaterial(sphereMat),
            Sphere3 = new Sphere(
                    new Point(0.0, 550.0, 1000.0), 200)
                    .setEmission(new Color(GREEN).reduce(2)) //
                    .setMaterial(sphereMat),
            Sphere4 = new Sphere(
                    new Point(-350.0, 600.0, -675.0), 275)
                    .setEmission(new Color(PINK).reduce(2)) //
                    .setMaterial(sphereMat),
            Sphere5 = new Sphere(
                    new Point(0.0, 550.0, -1000.0), 200)
                    .setEmission(new Color(RED).reduce(2)) //
                    .setMaterial(sphereMat),
            cylinder1 = new Cylinder(50d, new Ray(new Point(0.0, 550.0, -1000.0), new Vector(0.0,0.0,1.0)), 2000d) //
                    .setEmission(new Color(BLACK).reduce(2)) //
                    .setMaterial(sphereMat),
            cylinder2 = new Cylinder(50d, new Ray(new Point(-350.0, 600.0, -675.0), new Vector(0.0,0.0,1.0)), 1350d) //
                    .setEmission(new Color(BLACK).reduce(2)) //
                    .setMaterial(sphereMat);

    Scene scene = new Scene("Test scene cube");

    public void initializeScene() {
        scene.geometries.add(Triangle1, Triangle2, Triangle3, Triangle4, Triangle5, Triangle6,
                Triangle7, Triangle8, Triangle9, Triangle10, Triangle11, Triangle12, Sphere1,
                Sphere2, Sphere3, Sphere4, Sphere5, cylinder1, cylinder2);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.01)));


        scene.lights.add(
                new PointLight(
                        new Color(255, 255, 255).reduce(1.5),
                        new Point(0.0, 0.0, 900.0))
                        .setKl(4E-6).setKq(2E-6));
        scene.lights.add(
                new PointLight(
                        new Color(255, 255, 255).reduce(1.5),
                        new Point(0.0, 900.0, 0.0))
                        .setKl(4E-6).setKq(2E-6));
        scene.lights.add(
                new PointLight(
                        new Color(255, 255, 255).reduce(1.5),
                        new Point(900.0, 0.0, 0.0))
                        .setKl(4E-6).setKq(2E-6));

        scene.lights.add(
                new DirectionalLight(
                        new Color(255, 255, 255).reduce(1.5),
                        new Vector(1.0, -1.0, -2.0)));
        scene.lights.add(
                new DirectionalLight(
                        new Color(255, 255, 255).reduce(1.5),
                        new Vector(-1.0, 1.0, -2.0)));
        scene.background = new Color(0, 25, 51);
    }

    @Test
    public void cubeCamera1() {
        initializeScene();

        Camera camera1 = new Camera(
                new Point(12000.0, -12000.0, 12000.0),
                new Vector(-1.0, 1.0, -1.0),
                new Vector(-1.0, 1.0, 2.0))
                .setVPSize(200, 200).setVPDistance(1500)
                .setRayTracer(new RayTracerBasic(scene));

        camera1.setImageWriter(new ImageWriter("cube1", 600, 600))
                //.renderImageThreaded()
                .renderImage()
                .writeToImage();

        camera1.setImageWriter(new ImageWriter("cube1AntiAliasing", 600, 600))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void cubeCamera2() {
        initializeScene();

        Camera camera2 = new Camera(
                new Point(-12000.0, -12000.0, 12000.0),
                new Vector(1.0, 1.0, -1.0),
                new Vector(1.0, 1.0, 2.0))
                .setVPSize(200, 200).setVPDistance(1500)
                .setRayTracer(new RayTracerBasic(scene));

        camera2.setImageWriter(new ImageWriter("cube2", 600, 600))
                .renderImage();
        camera2.writeToImage();

    }

    @Test
    public void cubeCamera3() {
        initializeScene();

        Camera camera3 = new Camera(
                new Point(-12000.0, 12000.0, 12000.0),
                new Vector(1.0, -1.0, -1.0),
                new Vector(1.0, -1.0, 2.0))
                .setVPSize(200, 200).setVPDistance(1500)
                .setRayTracer(new RayTracerBasic(scene));

        camera3.setImageWriter(new ImageWriter("cube3", 600, 600))
                .renderImage();
        camera3.writeToImage();

        camera3.setImageWriter(new ImageWriter("cube3AntiAliasing", 600, 600))
                .renderImage();
        camera3.writeToImage();
    }

    @Test
    public void cubeCamera4() {
        initializeScene();

        Camera camera4 = new Camera(
                new Point(12000.0, 12000.0, 12000.0),
                new Vector(-1.0, -1.0, -1.0),
                new Vector(-1.0, -1.0, 2.0))
                .setVPSize(200, 200).setVPDistance(1500)
                .setRayTracer(new RayTracerBasic(scene));

        camera4.setImageWriter(new ImageWriter("cube4", 600, 600))
                .renderImage();
        camera4.writeToImage();
    }

}
