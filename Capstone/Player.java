
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


class Player
{
    Point2D.Double center;
    double radius;
    Rectangle rect;
    Color color;
    int upVelocity=0;
    int xVelocity=0;
    public Player(Point2D.Double center, double radius, Color color)
    {
        this.center=center;
        this.radius=radius;
        this.color=color;
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
    }
    public void calcMove()
    {
        if(center.getY()>500)
        {
            upVelocity=0;
            goTo(center.getX(),500);
        }
        
        move(xVelocity,-1*upVelocity);
        
        upVelocity-=2;
        xVelocity*=4;
        xVelocity/=5;
        
    }
    public void jump()
    {
        upVelocity=10;
        
    }
    //public void move(boolean left, 
    void draw(Graphics2D g2, boolean filled)
    {        
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
        System.out.println(center);
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
    
    public void move(double x, double y)
    {
        center=new Point2D.Double(center.getX()+x,center.getY()+y);
    }
    
    public void goTo(double x, double y)
    {
        center=new Point2D.Double(x,y);
    }
    
    public boolean isInside(Point2D.Double point)
    {
        return (Math.abs(center.getX()-point.getX())<radius)&&(Math.abs(center.getY()-point.getY())<radius);
    }

    public void moveX(int direction)
    {
        xVelocity+=direction*2;
        if (xVelocity>20||xVelocity<-20)
        {
            xVelocity*=9;
            xVelocity/=10;
        }
          
    }
}

