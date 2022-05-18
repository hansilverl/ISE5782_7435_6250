package renderer;

import static org.junit.jupiter.api.Assertions.*;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Integration tests of Camera Ray construction with Ray-Sphere intersections
 */
public class IntegrationTests {

    /**
     * this function count number of points that are in intersection and compare this value with the size of the list Intersectable
     *
     * @param cam      value for camera
     * @param geo      list of points that are intersected with the geometries
     * @param expected value of number points expected
     *                 This function return the number of point of intersection between the geometries and a ray from our camera
     *                 <p>
     *                 In details,we get the position of the pixel by nX & nY and launch a ray to this pixel ,if there's no
     *                 intersection so the count will be null but if there's intersection point so we add them up to our variable
     *                 "count" and return it
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;

        List<Point> allPoints = null;

        cam.setVPSize(3, 3);
        cam.setVPDistance(1);
        int nX = 3;
        int nY = 3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersection(cam.constructRay(nX, nY, j, i));
                if (intersections != null) {
                    if (allPoints == null) {
                        allPoints = new LinkedList<>();
                    }
                    //insert in allPoints list, all the points founded
                    allPoints.addAll(intersections);
                }
                if (intersections == null)
                    count += 0;
                else
                    count += intersections.size();

            }
        }

        System.out.format("there is %d points:%n", count);
        if (allPoints != null) {
            //print each point in the list
            for (var item : allPoints) {
                System.out.println(item);
            }
        }
        System.out.println();

        assertEquals(expected, count, "Wrong amount of intersections");
    }


    @Test
    /**
     * testing for {@link IntegrationTests#cameraRaySphereIntegration()}
     * test all the case of possible intersections of ray camera with sphere
     */
    public void cameraRaySphereIntegration() {
        Camera cam1 = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small Sphere (2 points)
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, -3), 1), 2);

        // TC02: Big Sphere (18 points)
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -2.5), 2.5), 18);

        // TC03: Medium Sphere (10 points)
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -2), 2), 10);

        // TC04: Inside Sphere (9 points)
        assertCountIntersections(cam2, new Sphere(new Point(0, 0, -1), 4), 9);

        // TC05: Beyond Sphere (0 points)
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, 1), 0.5), 0);
    }


    @Test
    /**
     * testing for {@link IntegrationTests#cameraRayTriangleIntegration()}
     * test all the case of small and medium triangle's intersections with camera ray (the case of big triangle is the same of the case with the plane,so DRY...)
     */
    public void cameraRayTriangleIntegration() {

        Camera cam = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)), 1);

        // TC02: Medium triangle 2 points
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 2);
    }


    /**
     * testing for {@link IntegrationTests#cameraRayPlaneIntegration()}
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void cameraRayPlaneIntegration() {
        Camera cam = new Camera(new Point(0, 0, 0),new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6);
    }


}