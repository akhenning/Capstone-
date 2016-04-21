import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;

public abstract class Shape
{
    Point2D.Double center;
    double radius;
    Color color;
    final double STATIC_X;
    public Shape(Point2D.Double center, double radius, Color color)
    {
        this.center=center;
        STATIC_X=center.getX();
        this.radius=radius;
        this.color=color;
    }
    
    public Point2D.Double getCenter()
    {
        return center;
    }
    
    double getXL()
    {
        return radius;
    }
    double getYL()
    {
        return radius;
    }
    double getStaticX()
    {
        return STATIC_X;
    }
    
    double getHeight()
    {
        return center.getY();
    }
    
    void move(double x, double y)
    {
        center=new Point2D.Double(center.getX()+x,center.getY()+y);
    }
    
    void goTo(double x, double y)
    {
        center=new Point2D.Double(x,y);
    }
    
    void goToX(double x)
    {
        center=new Point2D.Double(x,center.getY());
    }
    
    void setRadius(double r)
    {
        radius=r;
    }
    
    public void hitFromBottom()
    {}
    
    abstract boolean isInside(Point2D.Double point);
    //abstract boolean isOnBorder(Point2D.Double point);
    abstract void draw(Graphics2D g2); 
}
