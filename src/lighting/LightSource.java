package lighting;

import primitives.Color;
import primitives.*;
//TODO: JAVADOC
public interface LightSource {
    public Color getIntensity(Point p);

    public Vector getL(Point p);

    public double getDistance(Point point);


}