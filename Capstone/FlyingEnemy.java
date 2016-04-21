import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


class FlyingEnemy extends Entity
{
    boolean alive=true;
    Rectangle rect;
    Color color;
    double staticX;
    double staticY;
    double flightRange;
    double flightSpeed;
    double roam=1;
    double roamV=-.01;
    public FlyingEnemy(Color color, double staticX, double staticY, double flightRange, double flightSpeed, double radius)
    {
        super(color,radius);
        this.staticX=staticX;
        this.staticY=staticY;
        this.color=color;     
        this.flightRange=flightRange;
        this.flightSpeed=flightSpeed;
        roamV=flightSpeed;

    }
    public void calcXY()
    {
        setY(staticY+(flightRange*roam));        
        setX(staticX-Player.scrollX);
        
        if (roam<0)
        {
            roamV+=flightSpeed;
        }
        else if (roam>0)
        {
            roamV-=flightSpeed;
        }
        roam+=roamV;
    }
    public void draw(Graphics2D g2)
    {
        //calcXY(scrollX);        
        
        rect=new Rectangle((int)(getX()-getRadius()),(int)(getY()-getRadius()),(int)getRadius()*2,(int)getRadius()*2);
        g2.setColor(color);
        g2.fill(rect);
        Rectangle rect6=new Rectangle((int)(getX()-(getRadius()/2)),(int)(getY()+(getRadius()/2)),(int)getRadius()*2/3,(int)getRadius()/4);
            
       Rectangle rect2=new Rectangle((int)(getX()-(getRadius()*2/3)),(int)(getY()-(getRadius()/2)),(int)getRadius()/2,(int)getRadius()/2);
       Rectangle rect3=new Rectangle((int)(getX()+(getRadius()/3)),(int)(getY()-(getRadius()/2)),(int)getRadius()/2,(int)getRadius()/2);
       g2.setColor(Color.WHITE);  
       g2.fill(rect2);
       g2.fill(rect3);
       g2.setColor(Color.BLACK);
       g2.fill(rect6);
        
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(getX()-point.getX())<getRadius())&&(Math.abs(getY()-point.getY())<getRadius());
    }                 
    
    public Shape getMate()
    {
        return null;
    }
        
}

