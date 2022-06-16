package geometries;

import primitives.*;


public class Cuboid extends Geometries{

    public Cuboid(Point p1, double width, double height, double depth)
    {
      this(p1,width,height,depth,new Color(java.awt.Color.BLACK));
    }
    /**
     * @param p1 top left point
     * @param width of cuboid
     * @param height of cuboid
     * @param depth of cuboid
     */
    public Cuboid(Point p1, double width, double height, double depth,Color emission)
    {
        Point p2 = new Point(p1.getX(),p1.getY()-height,p1.getZ());
        Point p3 = new Point(p1.getX()+width,p2.getY(), p1.getZ());
        Point p4 = new Point(p1.getX()+width,p1.getY(),p1.getZ());
        this.add(new Polygon(p1,p2,p3,p4).setEmission(emission));
        Point p5 = new Point(p3.getX(), p3.getY(), p3.getZ()-depth);
        Point p6 = new Point(p4.getX(),p4.getY(), p4.getZ()-depth);
        this.add(new Polygon(p4,p3,p5,p6).setEmission(emission));
        Point p7 = new Point(p5.getX()-width,p5.getY(), p5.getZ());
        Point p8 = new Point(p6.getX()-width,p6.getY(),p6.getZ());
        this.add(new Polygon(p6,p5,p7,p8).setEmission(emission));
        this.add(new Polygon(p8,p7,p2,p1).setEmission(emission));
        this.add(new Polygon(p1,p4,p6,p8).setEmission(emission));
        this.add(new Polygon(p2,p3,p5,p7).setEmission(emission));
    }

}
