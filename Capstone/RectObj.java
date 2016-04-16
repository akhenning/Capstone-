import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


class RectObj extends Shape
{
    Rectangle rect;
    Color color;
    double x;
    double y;
    public RectObj(Point2D.Double center, double x, double y, Color color)
    {
        super(center,x,color);
        this.color=color;
        this.x=x;
        this.y=y;
        rect=new Rectangle((int)(center.getX()-x),(int)(center.getY()-y),(int)x*2,(int)y*2);
    }
    void draw(Graphics2D g2, boolean filled)
    {
        rect=new Rectangle((int)(center.getX()-x),(int)(center.getY()-y),(int)x*2,(int)y*2);
        g2.setColor(color);
        if (filled)
        {            
            g2.fill(rect);
        }
        else
        {
            g2.draw(rect);
        }
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(getCenter().getX()-point.getX())<x)&&(Math.abs(getCenter().getY()-point.getY())<y);
    }

    boolean isOnBorder(Point2D.Double point)
    {        
        return (Math.abs(Math.abs(getCenter().getX()-point.getX())-radius)<=radius/4)||(Math.abs(Math.abs(getCenter().getY()-point.getY())-radius)<=radius/4);
    }
    
    double getX()
    {
        return x;
    }
    double getY()
    {
        return y;
    }
}

