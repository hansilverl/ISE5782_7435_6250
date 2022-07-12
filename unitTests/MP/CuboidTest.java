package MP;

import geometries.Cuboid;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import scene.Scene;
import renderer.RayTracerBasic;

import static java.awt.Color.*;

class CuboidTest {
    private final Scene scene = new Scene("Test scene");
    Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVPSize(250, 250).setVPDistance(1000);

    @Test
    public void cuber() {
        Camera camera1 = new Camera(
                new Point(-12000.0, -12000.0, 12000.0),
                new Vector(1.0, 1.0, -1.0),
                new Vector(1.0, 1.0, 2.0))
                .setVPSize(200, 200).setVPDistance(1500);
        scene.geometries.add(new Cuboid(new Point(0,0,0),50,50,50,new Color(PINK)));
        scene.setBackground(new Color(WHITE));
        scene.lights.add(new SpotLight(new Color(YELLOW), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));
        camera1.setRayTracer(new RayTracerBasic(scene)).setImageWriter(new ImageWriter("cuboid", 2010, 2010))
                .renderImage()
                .writeToImage();
    }
}