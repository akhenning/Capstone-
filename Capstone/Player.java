
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

class Player
{
    Point2D.Double center;
    double radius;
    Rectangle rect;
    Color color;
    double upVelocity=0;
    double xVelocity=0;
    Player inactive;
    boolean touching=false;
    public Player(Point2D.Double center, double radius, Color color)
    {
        this.center=center;
        this.radius=radius;
        this.color=color;        
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
    }
    public void calcMove(boolean space)
    {
        
        if (center.getX()<0)//||center.getX()>840)
        {
            xVelocity*=-1;
            if(center.getX()<-20)
            {   
                goTo(20, center.getY());
            }
            //if(center.getX()>860)
            //{   
            //    goTo(820, center.getY());
            //}
        }
        move(xVelocity,-1*upVelocity);
        if (upVelocity<-2||space)
        {
            upVelocity-=.25;            
        }
        else
        {                  
            upVelocity-=.75;        
        }
        
        xVelocity*=.95;
        
        //System.out.println(center);
    }
    
    public void moveX(double direction,boolean shift)
    {
        xVelocity+=direction/4;
        if (shift)
        {
            xVelocity+=direction/8;
        }
        
        if (xVelocity>35||xVelocity<-35)
        {            
            xVelocity*=9;
            xVelocity/=10;
        }
        else if ((xVelocity>25||xVelocity<-25)&&!shift)
        {
            xVelocity*=9;
            xVelocity/=10;
        }
       
    }
    
    public void jump()
    {
        if(touching)
        {
            if (xVelocity>20)
            {
                upVelocity=22+Math.abs(xVelocity%20/4);
            }
            else
            {
                upVelocity=12+Math.abs(xVelocity/2);
            }
        }
        touching=false;
    }
    
    public void whenTouchingGround(boolean touching, int groundHeight)
    {       
       
        if (touching)
        {           
            this.touching=true;
            upVelocity=0;
            goTo(center.getX(),groundHeight-radius);
            // System.out.println("Made it here  "+groundHeight);         
        }
    }
    
    void draw(Graphics2D g2)
    {        
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
        //System.out.println(center);
        
        Rectangle rect2;
        Rectangle rect3;
        if(touching)
        {
            Rectangle rect6=new Rectangle((int)(center.getX()-(radius/2)),(int)(center.getY()+(radius/2)),(int)radius*2/3,(int)radius/4);
            g2.fill(rect6);
            rect2=new Rectangle((int)(center.getX()-(radius*2/3)),(int)(center.getY()-(radius/2)),(int)radius/2,(int)radius/2);
            rect3=new Rectangle((int)(center.getX()+(radius/3)),(int)(center.getY()-(radius/2)),(int)radius/2,(int)radius/2);
        }
        else
        {
            Ellipse2D.Double rect6=new Ellipse2D.Double((int)(center.getX()-(radius*2/3)),(int)(center.getY()+(radius/4)),(int)radius,(int)radius/2);
            g2.fill(rect6);
            rect2=new Rectangle((int)(center.getX()-(radius*3/4)),(int)(center.getY()-(radius*3/5)),(int)radius*3/5,(int)radius*3/5);
            rect3=new Rectangle((int)(center.getX()+(radius/4)),(int)(center.getY()-(radius*3/5)),(int)radius*3/5,(int)radius*3/5);
        }
        g2.setColor(color);                 
        g2.fill(rect);
        g2.setColor(Color.WHITE);  
        g2.fill(rect2);
        g2.fill(rect3);
        Rectangle rect4=new Rectangle((int)(center.getX()-(radius*2/3)),(int)(center.getY()-(radius/2)),(int)radius/4,(int)radius/4);
        Rectangle rect5=new Rectangle((int)(center.getX()+(radius/3)),(int)(center.getY()-(radius/2)),(int)radius/4,(int)radius/4);
        g2.setColor(Color.BLACK);                 
        g2.fill(rect4);
        g2.fill(rect5);
        if(touching)
        {
            Rectangle rect6=new Rectangle((int)(center.getX()-(radius/2)),(int)(center.getY()+(radius/2)),(int)radius*2/3,(int)radius/4);
            g2.fill(rect6);
        }
        else
        {
            Ellipse2D.Double rect6=new Ellipse2D.Double((int)(center.getX()-(radius*2/3)),(int)(center.getY()+(radius/4)),(int)radius,(int)radius/2);
            g2.fill(rect6);
        }
        
    }
    public void setSpeed(int speed)
    {
        upVelocity=speed;
    }
    
    public void setRadius(double newR)
    {
        radius=newR;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public void move(double x, double y)
    {
        center=new Point2D.Double(center.getX()+x,center.getY()+y);
    }
    
    public void goTo(double x, double y)
    {
        center=new Point2D.Double(x,y);
    }
    
    public boolean isInside(Point2D.Double point)
    {
        return (Math.abs(center.getX()-point.getX())<radius)&&(Math.abs(center.getY()-point.getY())<radius);
    }
    
//     public boolean isOnTopOf(Shape on)
//     {
//         return (on.isInside(new Point2D.Double(center.getX()+radius,center.getY()+radius)))||(on.isInside(new Point2D.Double(center.getX()-radius,center.getY()+radius)));
//     }
    
    public boolean isOnTopOfNextFrame(Shape on)
    {
        Point2D.Double center2=new Point2D.Double(center.getX()+xVelocity,center.getY()-upVelocity);        
        return(upVelocity<0)&&((on.isInside(new Point2D.Double(center2.getX()+radius,center2.getY()+radius)))||(on.isInside(new Point2D.Double(center2.getX()-radius,center2.getY()+radius))));
        
    }
    
    public boolean isHitNextFrame(Shape on)
    {
        Point2D.Double center2=new Point2D.Double(center.getX()+xVelocity,center.getY()-upVelocity);        
        return (on.isInside(new Point2D.Double(center2.getX()+radius+1,center2.getY()+(radius*3/4))))||(on.isInside(new Point2D.Double(center2.getX()-radius-1,center2.getY()+(radius*.75))))||(on.isInside(new Point2D.Double(center2.getX()-radius-1,center2.getY()-radius)))||(on.isInside(new Point2D.Double(center2.getX()+radius+1,center2.getY()-radius)));
        
    }

    
    
    public void hitWall(double objX, double objY, double xLength,double yLength)
    {
        if(objX-xLength>center.getX())
        {
            
            goTo(objX-xLength-radius,center.getY());
            xVelocity=-.25;
        }
        else if (objX+xLength<center.getX())
        {
            
            goTo(objX+xLength+radius,center.getY());
            xVelocity=.25;
        }
        else if (objY+xLength<center.getY())
        {
            
            goTo(center.getX(),objY+yLength+radius);
            upVelocity=0;
        }   
    }
    
}

