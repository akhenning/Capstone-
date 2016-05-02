import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

class Killplane extends Entity
{
    Color color;
    double staticX;
    //double staticY;
    public Killplane(Color color, double staticX, double staticY, double radius)
    {
        super(color,radius);
        this.staticX=staticX;
        this.color=color;     
        setY(staticY); 
    
    }
    public void calcXY()    
    {
        setX(staticX-Player.scrollX);        
    }
    public void draw(Graphics2D g2)
    {
        Line2D.Double l1=new Line2D.Double(getX()-(radius-3),(getY()-(radius-3)),getX()+(radius-3),(getY()+(radius-3)));
        Line2D.Double l2=new Line2D.Double(getX()+(radius-3),(getY()-(radius-3)),getX()-(radius-3),(getY()+(radius-3)));
        Rectangle rect=new Rectangle((int)(getX()-radius),(int)(getY()-radius),(int)radius*2,(int)radius*2);
        g2.setColor(new Color(255,220,200));
        g2.fill(rect);
        g2.setColor(color);  
        g2.draw(l1);
        g2.draw(l2);
        
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(getX()-point.getX())<getRadius())&&(Math.abs(getY()-point.getY())<getRadius());
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