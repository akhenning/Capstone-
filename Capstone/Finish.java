import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

class Finish extends Entity
{
    Color color;
    double staticX;
    public Finish(double staticX)
    {
        super(Color.BLACK,100);
        this.staticX=staticX;
        this.color=color;     
        setY(450); 
    
    }
    public void calcXY()    
    {
        setX(staticX-Player.scrollX);        
    }
    public void draw(Graphics2D g2)
    {
       
        Rectangle rect=new Rectangle((int)(getX()-40),0,(int)(getX()+40),1000);
        g2.setColor(Color.BLACK);                  
        g2.fill(rect);
        
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(getX()-point.getX())<40);
    }                 
    
    public void getHit(Player player)
    {        
        player.takeDamage(1);
        
    }
    
    public int interactionType()
    {
        return 1;
    }
}