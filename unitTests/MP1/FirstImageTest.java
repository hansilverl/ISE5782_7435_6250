package MP1;

import geometries.Cuboid;
import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;
import static java.awt.Color.*;


public class FirstImageTest {
    private Scene scene = new Scene("Test scene");
//Yellow Colour:(253,213,74)
    @Test
    public void temple() {

        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.01)));
        scene.geometries.add(
                //Main rectangle front
                new Polygon(new Point(-700, 800, 0), new Point(-700, -800, 0), new Point(700, -800, 0), new Point(700, 800, 0))//
                        .setEmission(new Color(WHITE)),
                //Gray (small door)
                new Polygon(new Point(-235, -100, 2), new Point(-235, -800, 2), new Point(235, -800, 2), new Point(235, -100, 2))//
                        .setEmission(new Color(123,123,123)),
                //Bottom top (cover)
                new Polygon(new Point(-735, 935, 0),new Point(-735, 800, 0),new Point(735,800,0),new Point(735,935,0))
                        .setEmission(new Color(WHITE)),
                //Middle top (cover)
                new Polygon(new Point(-700, 1020, 0),new Point(-700, 935, 0),new Point(700,935,0),new Point(700,1020,0))
                        .setEmission(new Color(253,213,74)),
                //top top (cover)
                new Polygon(new Point(-735, 1155, 0),new Point(-735, 1020, 0),new Point(735,1020,0),new Point(735,1155,0))
                        .setEmission(new Color(WHITE)),
                //Column (left left)
                new Polygon(new Point(-670, 800, 2), new Point(-670, -800, 2), new Point(-540, -800, 2), new Point(-540, 800, 2))//
                        .setEmission(new Color(192,192,192)),
                //Column (left right)
                new Polygon(new Point(-470, 800, 2), new Point(-470, -800, 2), new Point(-340, -800, 2), new Point(-340, 800, 2))//
                        .setEmission(new Color(192,192,192)),
                //Column (right right)
                new Polygon(new Point(670, 800, 2), new Point(670, -800, 2), new Point(540, -800, 2), new Point(540, 800, 2))//
                        .setEmission(new Color(192,192,192)),
                //Column (right left)
                new Polygon(new Point(470, 800, 2), new Point(470, -800, 2), new Point(340, -800, 2), new Point(340, 800, 2))//
                        .setEmission(new Color(192,192,192)),
                //Decoration column (left left)
                new Polygon(new Point(-690, 800, 4), new Point(-675, 670, 4), new Point(-535, 670, 4), new Point(-520, 800, 4))//
                        .setEmission(new Color(253,213,74)),
                //Column (left right)
                new Polygon(new Point(-490, 800, 4), new Point(-475, 670, 4), new Point(-335, 670, 4), new Point(-320, 800, 4))//
                        .setEmission(new Color(253,213,74)),
                //Decoration column (right right)
                new Polygon(new Point(690, 800, 4), new Point(675, 670, 4), new Point(535, 670, 4), new Point(520, 800, 4))//
                        .setEmission(new Color(253,213,74)),
                //Column (right left)
                new Polygon(new Point(490, 800, 4), new Point(475, 670, 4), new Point(335, 670, 4), new Point(320, 800, 4))//
                        .setEmission(new Color(253,213,74)));


        scene.background = new Color(169, 207, 217);

        camera1.setRayTracer(new RayTracerBasic(scene)).setImageWriter(new ImageWriter("temple", 2010, 2010))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void temple3d() {

        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.01)));
        scene.geometries.add(
                //Main rectangle front
                new Cuboid(new Point(-700, 600, 0),1400,1600, 1000, new Color(249,240,198)),
                //Gray (small door)
                new Cuboid(new Point(-235, -300,10),470,700,50,new Color(123,123,123)),
                /*new Polygon(new Point(-235, -100, 2), new Point(-235, -800, 2), new Point(235, -800, 2), new Point(235, -100, 2))//
                        .setEmission(new Color(123,123,123)),*/
                //Bottom top (cover)
                new Cuboid(new Point(-735,725,25), 1450, 125,1070, new Color(WHITE)),
                //Middle top (cover)
                new Cuboid(new Point(-700, 830, 0), 1400, 105,1000, new Color(253,213,74)),
                //top top (cover)
                new Cuboid(new Point(-735,955,25),1450, 125, 1070, new Color(WHITE)),
                //Column (left left)
                new Cuboid(new Point(-660,440, 50), 130, 1440, 40, new Color(192,192,192)),
                /*new Polygon(new Point(-670, 800, 2), new Point(-670, -800, 2), new Point(-540, -800, 2), new Point(-540, 800, 2))//
                        .setEmission(new Color(192,192,192)),*/
                //Column (left right)
                new Cuboid(new Point(-460, 440, 50), 130, 1440, 40,new Color(192, 192, 192)),
                /*new Polygon(new Point(-470, 800, 2), new Point(-470, -800, 2), new Point(-340, -800, 2), new Point(-340, 800, 2))//
                        .setEmission(new Color(192,192,192)),*/
                //Column (right right)
                new Cuboid(new Point(530, 440, 50), 130, 1440, 40, new Color(192,192,192)),
                /*new Polygon(new Point(670, 800, 2), new Point(670, -800, 2), new Point(540, -800, 2), new Point(540, 800, 2))//
                        .setEmission(new Color(192,192,192)),*/
                //Column (right left)
                new Cuboid(new Point(330, 440, 50), 130, 1440, 40, new Color(192, 192, 192)),
                /*new Polygon(new Point(470, 800, 2), new Point(470, -800, 2), new Point(340, -800, 2), new Point(340, 800, 2))//
                        .setEmission(new Color(192,192,192)),*/
                //Decoration column (left left)
                new Polygon(new Point(-690, 600, 4), new Point(-675, 470, 4), new Point(-535, 470, 4), new Point(-520, 600, 4))//
                        .setEmission(new Color(253,213,74)),
                //Column (left right)
                new Polygon(new Point(-490, 600, 4), new Point(-475, 470, 4), new Point(-335, 470, 4), new Point(-320, 600, 4))//
                        .setEmission(new Color(253,213,74)),
                //Decoration column (right right)
                new Polygon(new Point(690, 600, 4), new Point(675, 470, 4), new Point(535, 470, 4), new Point(520, 600, 4))//
                        .setEmission(new Color(253,213,74)),
                //Column (right left)
                new Polygon(new Point(490, 600, 4), new Point(475, 470, 4), new Point(335, 470, 4), new Point(320, 600, 4))//
                        .setEmission(new Color(253,213,74)),
                //Kale Orev
                new Polygon(new Point(-733,955,25), new Point(-703,1035,25),new Point(-673,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-667,955,25), new Point(-637,1035,25),new Point(-607,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-601,955,25), new Point(-571,1035,25),new Point(-541,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-535,955,25), new Point(-505,1035,25),new Point(-475,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-469,955,25), new Point(-439,1035,25),new Point(-409,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-403,955,25), new Point(-373,1035,25),new Point(-343,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-337,955,25), new Point(-307,1035,25),new Point(-277,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-271,955,25), new Point(-241,1035,25),new Point(-211,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-205,955,25), new Point(-175,1035,25),new Point(-145,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-139,955,25), new Point(-109,1035,25),new Point(-79,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-73,955,25), new Point(-43,1035,25),new Point(-13,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-7,955,25), new Point(23,1035,25),new Point(53,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(59,955,25), new Point(89,1035,25),new Point(119,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(125,955,25), new Point(155,1035,25),new Point(185,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(191,955,25), new Point(221,1035,25),new Point(251,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(257,955,25), new Point(287,1035,25),new Point(317,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(323,955,25), new Point(353,1035,25),new Point(383,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(389,955,25), new Point(419,1035,25),new Point(449,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(455,955,25), new Point(485,1035,25),new Point(515,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(521,955,25), new Point(551,1035,25),new Point(581,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(587,955,25), new Point(617,1035,25),new Point(647,955,25))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(653,955,25), new Point(683,1035,25),new Point(713,955,25))//
                        .setEmission(new Color(253,213,74)));


        scene.background = new Color(169, 207, 217);

        camera1.setRayTracer(new RayTracerBasic(scene)).setImageWriter(new ImageWriter("temple3d", 2010, 2010))
                .renderImage()
                .writeToImage();
    }
}
