import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


class SquareObj extends Shape
{
    Rectangle rect;
    Color color;
    public SquareObj(Point2D.Double center, double radius, Color color)
    {
        super(center,radius,color);
        this.color=color;
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
    }
    void draw(Graphics2D g2, boolean filled)
    {
        rect=new Rectangle((int)(getCenter().getX()-radius),(int)(getCenter().getY()-radius),(int)radius*2,(int)radius*2);
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
        return (Math.abs(getCenter().getX()-point.getX())<radius)&&(Math.abs(getCenter().getY()-point.getY())<radius);
    }

    boolean isOnBorder(Point2D.Double point)
    {        
        return (Math.abs(Math.abs(getCenter().getX()-point.getX())-radius)<=radius/4)||(Math.abs(Math.abs(getCenter().getY()-point.getY())-radius)<=radius/4);
    }
}

