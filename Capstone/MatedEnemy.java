import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;

class MatedEnemy extends Entity
{
    boolean alive=true;
    Rectangle rect;
    Color color;
    Shape matedShape;
    double x;
    double roam=1;
    double roamC;
    double rRange;
    final double finRadius;
    public MatedEnemy(Color color, Shape matedShape, double r,double speed, double rVariation)
    {
        super(color,r);
        this.matedShape=matedShape;
        this.color=color;   
        roamC=speed;//~.01
        setX(-999);
        rRange=rVariation;
        finRadius=r;
    }
    public void calcXY()
    {
        setRadius(finRadius+(DrawingPanel.wobble*rRange));
        setY(matedShape.getCenter().getY()-matedShape.getYL()-getRadius());
        setX(matedShape.getCenter().getX()+(matedShape.getXL()*roam));
        x=matedShape.getStaticX()-Player.scrollX+(matedShape.getXL()*roam);
        if (roam<-1)
        {
            roamC=Math.abs(roamC);
        }
        else if (roam>1)
        {
            roamC=-1*Math.abs(roamC);
        }
        
        roam+=roamC;
    }
    public void draw(Graphics2D g2)
    {
        //calcXY(scrollX);        
        
        rect=new Rectangle((int)(getX()-getRadius()),(int)(getY()-getRadius()),(int)getRadius()*2,(int)getRadius()*2);
        g2.setColor(color);
        g2.fill(rect);
        Rectangle rect6=new Rectangle((int)(getX()-(getRadius()/2)),(int)(getY()+(getRadius()/2)),(int)getRadius()*2/3,(int)getRadius()/4);
            
       Rectangle rect2=new Rectangle((int)(getX()-(getRadius()*2/3)),(int)(getY()-(getRadius()/4)),(int)getRadius()/2,(int)getRadius()/4);
       Rectangle rect3=new Rectangle((int)(getX()+(getRadius()/3)),(int)(getY()-(getRadius()/4)),(int)getRadius()/2,(int)getRadius()/4);
       g2.setColor(Color.BLACK);  
       g2.fill(rect2);
       g2.fill(rect3);
       //g2.setColor(Color.BLACK);
       g2.fill(rect6);
        
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(x-point.getX())<getRadius())&&(Math.abs(getY()-point.getY())<getRadius());
    }                 
    
    public Shape getMate()
    {
        return matedShape;
    }
    
}

