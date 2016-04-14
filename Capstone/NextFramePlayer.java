import java.awt.geom.Point2D;

public class NextFramePlayer
{
    //Point2D.Double center;
    double radius;

    /**
     * Default constructor for objects of class NextFramePlayer
     */
    public NextFramePlayer(Point2D.Double center, double radius)
    {
        this.radius=radius;
    }

    public boolean isOnTopOfNext(Shape on,Point2D.Double center,int upV, int xV)
    {
        center=new Point2D.Double(center.getX()+xV,center.getY()-upV);        
        return (on.isInside(new Point2D.Double(center.getX()+radius,center.getY()+radius)))||(on.isInside(new Point2D.Double(center.getX()-radius,center.getY()+radius)));
    }

}
