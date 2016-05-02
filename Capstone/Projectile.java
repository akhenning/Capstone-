import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Projectile extends Entity
{          
    Color color;
    double type;
    double staticX;
    double staticY;
    double upV;
    double movementSpeed;
    public Projectile(Player player, int powerlevel)
    {
        super(Color.RED,20);
        staticX=player.getX()+Player.scrollX;
        setY(player.getY());
        setX(staticX);
        upV=player.getUpV()+10;
        color=Color.RED;     
        type=powerlevel;

    }
    public void calcXY()
    {
        if(type==1||type==2)
        {
            setY(getY()-upV);        
            setX(staticX-Player.scrollX);
            staticX+=4;
            upV-=.2;
        }        
        if(getY()<-10)
        {
            setAlive(false);
        }
        
        
    }
    public void draw(Graphics2D g2)
    {
        //calcXY(scrollX);        
        Ellipse2D.Double ell2=new Ellipse2D.Double(getX()-12,getY()-12,24,24);
       g2.setColor(Color.RED);  
       g2.fill(ell2);       
        
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(getX()-point.getX())<getRadius())&&(Math.abs(getY()-point.getY())<getRadius());
    }                 
    
    public Shape getMate()
    {
        return null;
    }
        
    public boolean isTouching(Shape on)
    {
       return (on.getCenter().distance(new Point2D.Double(getX(),getY()))<on.getXL()*.8);//I think this is slow
       
    }    
}


