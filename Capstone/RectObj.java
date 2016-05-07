import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Component;

class RectObj extends Shape
{
    Rectangle rect;
    Color color;
    double x;
    double y;
    Image image=Toolkit.getDefaultToolkit().getImage("FL.png");
    public RectObj(Point2D.Double center, double x, double y, Color color)
    {
        super(center,x,color);
        this.color=color;
        this.x=x;
        this.y=y;
        //if(y>900)
        //{
        //    Image img1 = 
        //}
        rect=new Rectangle((int)(center.getX()-x),(int)(center.getY()-y),(int)x*2,(int)y*2);
    }
    void draw(Graphics2D g2)
    {
        if(y>900)
        {
            g2.drawImage(image,(int)(center.getX()-x),-100,100,900,null);
           
        }
        else
        {
            rect=new Rectangle((int)(center.getX()-x),(int)(center.getY()-y),(int)x*2,(int)y*2);
            g2.setColor(color);                  
            g2.fill(rect);
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
    
    double getXL()
    {
        return x;
    }
    double getYL()
    {
        return y;
    }
}

