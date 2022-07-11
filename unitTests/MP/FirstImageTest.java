package MP;

import geometries.Cuboid;
import geometries.Plane;
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

        Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0,1, 0))
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

        /*Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //front lens
        Camera camera1 = new Camera(new Point(-1400, 20, 800), new Vector(0.5, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //side lens
        Camera camera1 = new Camera(new Point(0, 0, -2500), new Vector(0, 0, 1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000); //back lens*/
        Camera camera1 = new Camera(new Point(-1300, -70, 900), new Vector(0.68, 0, -1), new Vector(0, 1, 0))
                .setVPSize(2500, 2500).setVPDistance(1000);



        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.01)));
        scene.geometries.add(

                //floor
                new Plane(new Point(-760,-1066,22),new Point(-671,-1066,-1322),new Point(760,-1066,-300))//
                        .setEmission(new Color(86, 86, 86)).setMaterial(new Material().setKr(0.3)),

                //Main rectangle - front - behind bottom - behind top - front frame - behind frame
                new Cuboid(new Point(-760, 600, 0),1520,1600, 300, new Color(237, 242, 252),0,0.03),
                new Cuboid(new Point(-660, 0, -300),1320,1000, 1500, new Color(237, 242, 252)),
                new Cuboid(new Point(-600, 600, -300),1200,1000, 1400, new Color(237, 242, 252)),
                new Cuboid(new Point(-771, -970, 22),1542,96, 344, new Color(220, 226, 238),0,0.07),
                new Cuboid(new Point(-671, -970, -278),1342,96, 1544, new Color(220, 226, 238),0,0.07),

                //small door
                new Cuboid(new Point(-235, -290,1),470,710,5,new Color(123,123,123)),
                //frame door
                //-up
                new Cuboid(new Point(-235, -292,5),464,80,5,new Color(163,163,163)),
                new Cuboid(new Point(-235, -291,10),466,60,10,new Color(176,176,176)),
                new Cuboid(new Point(-235, -290,15),468,40,15,new Color(189,189,189)),
                new Cuboid(new Point(-235, -289,20),470,20,20,new Color(202,202,202)),
                //-left
                new Cuboid(new Point(-235, -292,5),80,710,5,new Color(163,163,163)),
                new Cuboid(new Point(-235, -291,10),60,710,10,new Color(176,176,176)),
                new Cuboid(new Point(-235, -290,15),40,710,15,new Color(189,189,189)),
                new Cuboid(new Point(-235, -289,20),20,710,20,new Color(202,202,202)),
                //right
                new Cuboid(new Point(235, -292,5),-80,710,5,new Color(163,163,163)),
                new Cuboid(new Point(235, -291,10),-60,710,10,new Color(176,176,176)),
                new Cuboid(new Point(235, -290,15),-40,710,15,new Color(189,189,189)),
                new Cuboid(new Point(235, -289,20),-20,710,20,new Color(202,202,202)),

                //5 above the door
                new Cuboid(new Point(-248, -220,10),496,20,10,new Color(184, 118, 90)),
                new Cuboid(new Point(-256, -180,20),512,20,20,new Color(184, 118, 90)),
                new Cuboid(new Point(-264, -140,30),528,20,30,new Color(184, 118, 90)),
                new Cuboid(new Point(-272, -100,40),544,20,40,new Color(184, 118, 90)),
                new Cuboid(new Point(-280, -60,50),560,20,50,new Color(184, 118, 90)),

                //Bottom top (cover) - front - behind
                new Cuboid(new Point(-800,725,25), 1600, 125,380, new Color(WHITE)),
                new Cuboid(new Point(-635,725,-275), 1270, 125,1480, new Color(WHITE)),
                //Middle top (cover) - front - behind
                new Cuboid(new Point(-760, 830, 0), 1520, 105,300, new Color(253,213,74)),
                new Cuboid(new Point(-600, 830, -300), 1200, 105,1400, new Color(253,213,74)),
                //top top (cover) - front - behind
                new Cuboid(new Point(-800,955,25),1600, 125, 380, new Color(WHITE)),
                new Cuboid(new Point(-635,955,-275),1270, 125, 1480, new Color(WHITE)),

                //Column (left left)
                new Cuboid(new Point(-700,470, 15), 130, 1450, 20, new Color(252, 226, 217),0,0.06),
                //Column (left right)
                new Cuboid(new Point(-460, 470, 15), 130, 1450, 20,new Color(252, 226, 217),0,0.06),
                //Column (right right)
                new Cuboid(new Point(570, 470, 15), 130, 1450, 20, new Color(252, 226, 217),0,0.06),
                //Column (right left)
                new Cuboid(new Point(330, 470, 15), 130, 1450, 20, new Color(252, 226, 217),0,0.06),

                //Decoration column (left left) - front - left - right
                new Polygon(new Point(-715, 600, 40), new Point(-700, 470, 15), new Point(-570, 470, 15), new Point(-555, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-715, 600, -40), new Point(-700, 470, -15), new Point(-700, 470, 15), new Point(-715, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-555, 600, 40), new Point(-570, 470, 15), new Point(-570, 470, -15), new Point(-555, 600, -40))//
                        .setEmission(new Color(253,213,74)),
                //Decoration column (left right) - front - left - right
                new Polygon(new Point(-475, 600, 40), new Point(-460, 470, 15), new Point(-330, 470, 15), new Point(-315, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-475, 600, -40), new Point(-460, 470, -15), new Point(-460, 470, 15), new Point(-475, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-315, 600, 40), new Point(-330, 470, 15), new Point(-330, 470, -15), new Point(-315, 600, -40))//
                        .setEmission(new Color(253,213,74)),
                //Decoration column (right right) - front - left - right
                new Polygon(new Point(555, 600, 40), new Point(570, 470, 15), new Point(700, 470, 15), new Point(715, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(555, 600, -40), new Point(570, 470, -15), new Point(570, 470, 15), new Point(555, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(715, 600, 40), new Point(700, 470, 15), new Point(700, 470, -15), new Point(715, 600, -40))//
                        .setEmission(new Color(253,213,74)),
                //Decoration Column (right left) - front - left - right
                new Polygon(new Point(315, 600, 40), new Point(330, 470, 15), new Point(460, 470, 15), new Point(475, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(315, 600, -40), new Point(330, 470, -15), new Point(330, 470, 15), new Point(315, 600, 40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(475, 600, 40), new Point(460, 470, 15), new Point(460, 470, -15), new Point(475, 600, -40))//
                        .setEmission(new Color(253,213,74)),

                //left-down
                new Cuboid(new Point(-668, 0, -1800),16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-668, 0, -1450),16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-668, 0, -1100),16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-668, 0, -750),16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-668, 0, -400),16,1000, -100, new Color(228, 234, 247)),
                //Decoration left-down
                new Cuboid(new Point(-670, 1, -1800),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-670, 1, -1450),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-670, 1, -1100),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-670, 1, -750),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-670, 1, -400),16,100, -104, new Color(253,213,74)),
                //right-down
                new Cuboid(new Point(668, 0, -1800),-16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(668, 0, -1450),-16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(668, 0, -1100),-16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(668, 0, -750),-16,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(668, 0, -400),-16,1000, -100, new Color(228, 234, 247)),
                //Decoration right-down
                new Cuboid(new Point(670, 1, -1800),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(670, 1, -1450),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(670, 1, -1100),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(670, 1, -750),16,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(670, 1, -400),16,100, -104, new Color(253,213,74)),
                //left-up
                new Cuboid(new Point(-607, 600, -1700),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-607, 600, -1400),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-607, 600, -1100),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-607, 600, -800),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(-607, 600, -500),14,1000, -100, new Color(228, 234, 247)),
                //Decoration left-up
                new Cuboid(new Point(-609, 601, -1700),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -1400),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -1100),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -800),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -500),14,100, -104, new Color(253,213,74)),
                //right up
                new Cuboid(new Point(607, 600, -1700),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(607, 600, -1400),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(607, 600, -1100),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(607, 600, -800),14,1000, -100, new Color(228, 234, 247)),
                new Cuboid(new Point(607, 600, -500),14,1000, -100, new Color(228, 234, 247)),
                //Decoration right-up
                new Cuboid(new Point(-609, 601, -1700),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -1400),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -1100),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -800),14,100, -104, new Color(253,213,74)),
                new Cuboid(new Point(-609, 601, -500),14,100, -104, new Color(253,213,74)),

                //stairs
                new Cuboid(new Point(-235, -965,0),470,8,-40,new Color(117, 61, 61)),
                new Cuboid(new Point(-248, -973,0),496,8,-50,new Color(145, 100, 100)),
                new Cuboid(new Point(-258, -981,0),516,8,-60,new Color(172, 139, 139)),

                new Cuboid(new Point(-273, -989,0),546,8,-80,new Color(197, 138, 67)),
                new Cuboid(new Point(-283, -997,0),566,8,-90,new Color(209, 161, 105)),
                new Cuboid(new Point(-293, -1005,0),586,8,-100,new Color(220, 185, 142)),

                new Cuboid(new Point(-308, -1013,0),616,8,-120,new Color(214, 194, 56)),
                new Cuboid(new Point(-318, -1021,0),636,8,-130,new Color(222, 206, 96)),
                new Cuboid(new Point(-328, -1029,0),656,8,-140,new Color(230, 218, 136)),

                new Cuboid(new Point(-343, -1037,0),686,8,-160,new Color(158, 201, 56)),
                new Cuboid(new Point(-353, -1045,0),706,8,-170,new Color(177, 212, 96)),
                new Cuboid(new Point(-363, -1053,0),726,8,-180,new Color(197, 223, 136)),

                //Kale Orev - tringles
                //-front
                new Polygon(new Point(-799,955,25), new Point(-769,1035,25),new Point(-739,955,25))//
                        .setEmission(new Color(253,213,74)),
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
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(719,955,25), new Point(749,1035,25),new Point(779,955,25))//
                        .setEmission(new Color(253,213,74)),
                //-left front
                new Polygon(new Point(-800,955,-37), new Point(-800,1035,-7),new Point(-800,955,23))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-800,955,-100), new Point(-800,1035,-70),new Point(-800,955,-40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-800,955,-163), new Point(-800,1035,-133),new Point(-800,955,-103))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-800,955,-226), new Point(-800,1035,-196),new Point(-800,955,-166))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-800,955,-289), new Point(-800,1035,-259),new Point(-800,955,-229))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-800,955,-352), new Point(-800,1035,-322),new Point(-800,955,-292))//
                        .setEmission(new Color(253,213,74)),
                //-right front
                new Polygon(new Point(800,955,-37), new Point(800,1035,-7),new Point(800,955,23))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(800,955,-100), new Point(800,1035,-70),new Point(800,955,-40))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(800,955,-163), new Point(800,1035,-133),new Point(800,955,-103))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(800,955,-226), new Point(800,1035,-196),new Point(800,955,-166))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(800,955,-289), new Point(800,1035,-259),new Point(800,955,-229))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(800,955,-352), new Point(800,1035,-322),new Point(800,955,-292))//
                        .setEmission(new Color(253,213,74)),
                //-behind left front
                new Polygon(new Point(-800,955,-355), new Point(-770,1035,-355),new Point(-740,955,-355))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-740,955,-355), new Point(-710,1035,-355),new Point(-680,955,-355))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-680,955,-355), new Point(-650,1035,-355),new Point(-635,955,-355))//
                        .setEmission(new Color(253,213,74)),
                //-behind right front
                new Polygon(new Point(800,955,-355), new Point(770,1035,-355),new Point(740,955,-355))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(740,955,-355), new Point(710,1035,-355),new Point(680,955,-355))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(680,955,-355), new Point(650,1035,-355),new Point(635,955,-355))//
                        .setEmission(new Color(253,213,74)),
                //-left back
                new Polygon(new Point(-635,955,-415), new Point(-635,1035,-385),new Point(-635,955,-355))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-475), new Point(-635,1035,-445),new Point(-635,955,-415))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-535), new Point(-635,1035,-505),new Point(-635,955,-475))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-595), new Point(-635,1035,-565),new Point(-635,955,-535))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-655), new Point(-635,1035,-625),new Point(-635,955,-595))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-715), new Point(-635,1035,-685),new Point(-635,955,-655))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-775), new Point(-635,1035,-745),new Point(-635,955,-715))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-835), new Point(-635,1035,-805),new Point(-635,955,-775))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-895), new Point(-635,1035,-865),new Point(-635,955,-835))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-955), new Point(-635,1035,-925),new Point(-635,955,-895))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1015), new Point(-635,1035,-985),new Point(-635,955,-955))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1075), new Point(-635,1035,-1045),new Point(-635,955,-1015))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1135), new Point(-635,1035,-1105),new Point(-635,955,-1075))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1195), new Point(-635,1035,-1165),new Point(-635,955,-1135))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1255), new Point(-635,1035,-1225),new Point(-635,955,-1195))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1315), new Point(-635,1035,-1285),new Point(-635,955,-1255))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1375), new Point(-635,1035,-1345),new Point(-635,955,-1315))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1435), new Point(-635,1035,-1405),new Point(-635,955,-1375))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1495), new Point(-635,1035,-1465),new Point(-635,955,-1435))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1555), new Point(-635,1035,-1525),new Point(-635,955,-1495))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1615), new Point(-635,1035,-1585),new Point(-635,955,-1555))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1675), new Point(-635,1035,-1645),new Point(-635,955,-1615))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1735), new Point(-635,1035,-1705),new Point(-635,955,-1675))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-635,955,-1755), new Point(-635,1035,-1755),new Point(-635,955,-1735))//
                        .setEmission(new Color(253,213,74)),
                //-right back
                new Polygon(new Point(635,955,-415), new Point(635,1035,-385),new Point(635,955,-355))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-475), new Point(635,1035,-445),new Point(635,955,-415))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-535), new Point(635,1035,-505),new Point(635,955,-475))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-595), new Point(635,1035,-565),new Point(635,955,-535))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-655), new Point(635,1035,-625),new Point(635,955,-595))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-715), new Point(635,1035,-685),new Point(635,955,-655))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-775), new Point(635,1035,-745),new Point(635,955,-715))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-835), new Point(635,1035,-805),new Point(635,955,-775))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-895), new Point(635,1035,-865),new Point(635,955,-835))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-955), new Point(635,1035,-925),new Point(635,955,-895))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1015), new Point(635,1035,-985),new Point(635,955,-955))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1075), new Point(635,1035,-1045),new Point(635,955,-1015))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1135), new Point(635,1035,-1105),new Point(635,955,-1075))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1195), new Point(635,1035,-1165),new Point(635,955,-1135))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1255), new Point(635,1035,-1225),new Point(635,955,-1195))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1315), new Point(635,1035,-1285),new Point(635,955,-1255))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1375), new Point(635,1035,-1345),new Point(635,955,-1315))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1435), new Point(635,1035,-1405),new Point(635,955,-1375))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1495), new Point(635,1035,-1465),new Point(635,955,-1435))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1555), new Point(635,1035,-1525),new Point(635,955,-1495))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1615), new Point(635,1035,-1585),new Point(635,955,-1555))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1675), new Point(635,1035,-1645),new Point(635,955,-1615))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1735), new Point(635,1035,-1705),new Point(635,955,-1675))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1755), new Point(635,1035,-1755),new Point(635,955,-1735))//
                        .setEmission(new Color(253,213,74)),
                //-behind back
                new Polygon(new Point(-575,955,-1755), new Point(-605,1035,-1755),new Point(-635,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-514,955,-1755), new Point(-544,1035,-1755),new Point(-574,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-454,955,-1755), new Point(-484,1035,-1755),new Point(-514,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-393,955,-1755), new Point(-423,1035,-1755),new Point(-453,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-333,955,-1755), new Point(-363,1035,-1755),new Point(-393,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-272,955,-1755), new Point(-302,1035,-1755),new Point(-332,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-212,955,-1755), new Point(-242,1035,-1755),new Point(-272,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-151,955,-1755), new Point(-181,1035,-1755),new Point(-211,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-91,955,-1755), new Point(-121,1035,-1755),new Point(-151,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(-30,955,-1755), new Point(-60,1035,-1755),new Point(-90,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(30,955,-1755), new Point(0,1035,-1755),new Point(-30,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(91,955,-1755), new Point(61,1035,-1755),new Point(31,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(151,955,-1755), new Point(121,1035,-1755),new Point(91,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(212,955,-1755), new Point(182,1035,-1755),new Point(152,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(272,955,-1755), new Point(242,1035,-1755),new Point(212,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(333,955,-1755), new Point(303,1035,-1755),new Point(273,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(393,955,-1755), new Point(363,1035,-1755),new Point(333,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(454,955,-1755), new Point(424,1035,-1755),new Point(394,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(514,955,-1755), new Point(484,1035,-1755),new Point(454,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(575,955,-1755), new Point(545,1035,-1755),new Point(515,955,-1755))//
                        .setEmission(new Color(253,213,74)),
                new Polygon(new Point(635,955,-1755), new Point(605,1035,-1755),new Point(575,955,-1755))//
                        .setEmission(new Color(253,213,74)));


        scene.background = new Color(169, 207, 217);

        camera1.setRayTracer(new RayTracerBasic(scene)).setImageWriter(new ImageWriter("temple3d", 2010, 2010))
                .renderImage()
                .writeToImage();
    }
}
