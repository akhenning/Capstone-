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

    public boolean isOnTopOfNext(Shape on,Point2D.Double center,int upV, double xV)
    {
        center=new Point2D.Double(center.getX()+xV,center.getY()-upV);        
        return (on.isInside(new Point2D.Double(center.getX()+radius,center.getY()+radius)))||(on.isInside(new Point2D.Double(center.getX()-radius,center.getY()+radius)));
    }
    public boolean isHitNext(Shape on,Point2D.Double center,int upV, double xV)
    {
        center=new Point2D.Double(center.getX()+xV,center.getY()-upV);        
        return (on.isInside(new Point2D.Double(center.getX()+radius+1,center.getY()+radius-3)))||(on.isInside(new Point2D.Double(center.getX()-radius-1,center.getY()+radius-3)));
    }
    public boolean isHitCeilingNext(Shape on,Point2D.Double center,int upV, double xV)
    {
        center=new Point2D.Double(center.getX()+xV,center.getY()-upV);        
        return (on.isInside(new Point2D.Double(center.getX(),center.getY()-radius)));
    }
}
