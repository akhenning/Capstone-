import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


abstract class Entity extends Shape
{
    boolean alive=true;
    Color color;
    double radius;
    double x;
    double y;
    public Entity(Color color, double r)
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
        // System.out.println(x+"   "+y);
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
    
    public Shape getMate()
    {
        return null;
    }
    
    public boolean isAlive()
    {
        return alive;
    }  
    
    public void setAlive(boolean alive)
    {
        this.alive=alive;
    }  
    
    public void getHit(Player player)
    {
        alive=false;
        goToX(-999);
    }
    
    public int interactionType()
    {
        return 0;//o is bounce, 1 is act as though tangible, 2 is go through
    }
}

