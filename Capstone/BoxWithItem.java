import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


class BoxWithItem extends Shape
{
    Rectangle rect;
    Color color;
    boolean hit=false;
    Powerup contains;
    public BoxWithItem(Point2D.Double center, double radius, Color color, Powerup contains)
    {
        super(center,radius,color);
        this.color=color;
        this.contains=contains;
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
    }
    void draw(Graphics2D g2)
    {
        
        if (hit)
        {
            rect=new Rectangle((int)(getCenter().getX()-radius),(int)(getCenter().getY()-radius),(int)radius*2,(int)radius*2);
            Line2D.Double l1=new Line2D.Double(getCenter().getX()-(radius-3),(getCenter().getY()-(radius-3)), getCenter().getX()+(radius-3),(getCenter().getY()+(radius-3)));
            Line2D.Double l2=new Line2D.Double(getCenter().getX()+(radius-3),(getCenter().getY()-(radius-3)), getCenter().getX()-(radius-3),(getCenter().getY()+(radius-3)));
            
            g2.setColor(color);                
            g2.fill(rect);
            g2.setColor(Color.BLACK);g2.draw(l1);
            g2.draw(l1);
            g2.draw(l2);
        }
        else
        {
            rect=new Rectangle((int)(getCenter().getX()-radius+2),(int)(getCenter().getY()-radius+2),(int)radius*2-4,(int)radius*2-4);
            g2.setColor(color);                
            g2.fill(rect);
            g2.setColor(Color.BLACK);
            g2.draw(rect);
        }
    }
    
    boolean isInside(Point2D.Double point)
    {
        return (Math.abs(getCenter().getX()-point.getX())<radius)&&(Math.abs(getCenter().getY()-point.getY())<radius);
    }
    
    public void hitFromBottom()
    {
        if(hit==false)
        {
            hit=true;
            contains.appear(this);
        }
    }
}

