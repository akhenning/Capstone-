import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


abstract class Enemy extends Shape
{
    boolean alive=true;
    Color color;
    double radius;
    double x;
    double y;
    public Enemy(Color color, double r)
    {
        super(new Point2D.Double(0,0),0,null);
        radius=r;        
        this.color=color;        
    }
    abstract public void calcXY();
    abstract public void draw(Graphics2D g2);    
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(x-point.getX())<radius)&&(Math.abs(y-point.getY())<radius);
    }   
    
    double getRadius()
    {
        return radius;
    }
    double getX()
    {
        return x;
    }
    double getY()
    {
        return y;
    }
    
    void setX(double x2)
    {
        x=x2;
    }
    void setY(double y2)
    {
        y=y2;
    }
    void setRadius(double r)
    {
        radius=r;
    }
    
    void goToX(double x)
    {
        this.x=x;
    }
    
    abstract public Shape getMate();
    
    public boolean isAlive()
    {
        return alive;
    }  
    
    public void getHit()
    {
        alive=false;
    }
}

