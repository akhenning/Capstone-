
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;

class Powerup extends Entity
{
    Color color;
    double staticX;
    double staticY;
    int type;
    double grow=0;
    public Powerup(Color color, double staticX, double staticY,int type)
    {
        super(color,50);
        this.staticX=staticX;
        this.color=color;    
        this.type=type;
        setAlive(false);
        setY(staticY); 
    }
    public void calcXY()    
    {
        setY(staticY+grow);
        setX(staticX-Player.scrollX);
        
    }
    public void draw(Graphics2D g2)
    {   
        if(isAlive())
        {
            if(type==1)
            {
                if (grow>-100)
                {
                    grow-=.5;
                }
                Line2D.Double l1=new Line2D.Double(getX(),(getY()-(radius-3)),getX(),(getY()+(radius-3)));
                Ellipse2D.Double ell2=new Ellipse2D.Double(getX()-25,getY()-25,50,25);
                Ellipse2D.Double ell=new Ellipse2D.Double(getX()-50,getY()-50,100,50);
                
                g2.setColor(color);  
                g2.draw(l1);            
                g2.setColor(Color.RED); 
                g2.fill(ell);
                g2.setColor(Color.ORANGE); 
                g2.fill(ell2);
            }
            else if (type==2)
            {
                if (grow>-100)
                {
                    grow-=.5;
                }
                Line2D.Double l1=new Line2D.Double(getX()-radius+2,getY()-radius+2,getX()-radius/2,(getY()));
                Line2D.Double l2=new Line2D.Double(getX()-radius+2,getY()-radius+2,getX(),(getY()-radius/2));
                Rectangle rect2=new Rectangle((int)(getX()-radius+3),(int)(getY()-radius/8),(int)radius-6,(int)(radius-6));
                Rectangle rect3=new Rectangle((int)(getX()-radius/8),(int)(getY()-radius+3),(int)(radius-6),(int)(radius-6));
                g2.setColor(Color.GREEN);
                g2.draw(l1);
                g2.draw(l2);
                g2.setColor(Color.RED); 
                g2.fill(rect2);
                g2.fill(rect3);                
            }
            else if (type==3)
            {
                if (grow>-100)
                {
                    grow-=.5;
                }                
                Rectangle rect1=new Rectangle((int)(getX()-radius+5),(int)(getY()-radius+5),(int)(radius*.85),(int)(radius*.85));
                Rectangle rect3=new Rectangle((int)(getX()-radius*.666+5),(int)(getY()-radius*.666+5),(int)(radius*.85),(int)(radius*.85));
                Rectangle rect2=new Rectangle((int)(getX()-radius*.333+5),(int)(getY()-radius*.333+5),(int)(radius*.85),(int)(radius*.85));
                Rectangle rect4=new Rectangle((int)(getX()+5),(int)(getY()+5),(int)(radius*.85),(int)(radius*.85));
                g2.setColor(Color.RED); 
                g2.draw(rect2);
                g2.draw(rect3);     
                g2.draw(rect1);
                g2.draw(rect4); 
            }
        }
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(getX()-point.getX())<getRadius())&&(Math.abs(getY()-point.getY())<getRadius());
    }                 
    
    public void getHit(Player player, boolean x)
    {        
        player.getPowerUp(type);
        setAlive(false);
    }
    
    public int interactionType()
    {
        return 2;
    }
    
    public void appear(Shape block)
    {
        setAlive(true);
        staticY=block.getHeight();
        staticX=block.getStaticX();
    }
}