import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Projectile extends Entity
{          
    Color color;
    double type;
    double staticX;
    double special;
    double upV=0;
    double movementSpeed;
    Image img;
    public Projectile(Player player, int powerlevel,double special)
    {
        super(Color.RED,10+(powerlevel*10));
        staticX=player.getX()+Player.scrollX;
        setY(player.getY());
        setX(staticX);
        if(powerlevel<3)
        {
            upV=(player.getUpV()/2)+10;
           
            //System.out.println(img);
        }              
        if (powerlevel>2)
        {
             img=Toolkit.getDefaultToolkit().getImage("fire.png");
        }
        color=Color.RED;     
        type=powerlevel;
        movementSpeed=player.getXV()+2+(powerlevel*powerlevel);
        this.special=special;
    }
    public void calcXY()
    {
        if(type==1||type==2)
        {
            setY(getY()-upV);        
            setX(staticX-Player.scrollX);
            staticX+=movementSpeed;
            upV-=.2;
        }      
        else if (type==3)
        {
            if(special!=0)
            {
                 setY(getY()-upV);  
                 if (special>-1)
                 {upV+=.05;}
                 else
                  {upV-=.05;}
                 movementSpeed*=.99;
            }
            setX(staticX-Player.scrollX);
            staticX+=movementSpeed;            
        }        
        if(getY()>1000)
        {
            setAlive(false);
        }
        else if(getX()>1400)
        {
            setAlive(false);
        }
        else if(getY()<-400)
        {
            setAlive(false);
        }
        
        
    }
    public void draw(Graphics2D g2)
    {
        //calcXY(scrollX);        
       
       
       if(type>2)
       {
           g2.drawImage(img,(int)(getX()-(getRadius())),(int)(getY()-getRadius()),(int)(getRadius()*2),(int)(getRadius()*1.4),null);
       }
       else
       {
            Ellipse2D.Double ell2=new Ellipse2D.Double(getX()-(getRadius()/2),getY()-getRadius()/2,getRadius(),getRadius());
            g2.setColor(Color.RED);  
            g2.fill(ell2);  
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
        
    public boolean isTouching(Shape on)
    {       
       return((on.isInside(new Point2D.Double(getX()+getRadius(),getY()))))||(on.isInside(new Point2D.Double(getX(),getY()+getRadius())));
       
    }    
}


