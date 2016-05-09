import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Component;

class FlyingEnemy extends Entity
{
    Rectangle rect;
    Color color;
    double staticX;
    double staticY;
    double flightRange;
    double flightSpeed;
    double roam=1;
    double roamV=-.01;
    final double finRadius;
    Image image;
    public FlyingEnemy(Color color, double staticX, double staticY, double flightRange, double flightSpeed, double radius)
    {
        super(color,radius);
        this.staticX=staticX;
        this.staticY=staticY;
        this.color=color;     
        this.flightRange=flightRange;
        this.flightSpeed=flightSpeed;
        roamV=flightSpeed;
        finRadius=radius;
        if (this.flightSpeed>0)
        {
            image=Toolkit.getDefaultToolkit().getImage("flying.png");
        }
    }
    public void calcXY()
    {
        setRadius(finRadius+(DrawingPanel.wobble*5));
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
        if(flightSpeed>0)
        {
            rect=new Rectangle((int)(getX()-getRadius()),(int)(getY()-getRadius()),(int)getRadius()*2,(int)getRadius()*2);
            g2.setColor(color);
            g2.fill(rect);
            g2.drawImage(image,(int)(getX()-getRadius()),(int)(getY()-getRadius()),(int)getRadius()*2,(int)getRadius()*2,null);
            
        }
        else
        {
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

