package MP1;

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
                //Main rectangle right
                new Polygon(new Point(700, -800, 0), new Point(700, 800, 0))//
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
}
