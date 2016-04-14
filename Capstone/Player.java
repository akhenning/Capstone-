
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;


class Player
{
    Point2D.Double center;
    double radius;
    Rectangle rect;
    Color color;
    int upVelocity=0;
    int xVelocity=0;
    Player inactive;
    public Player(Point2D.Double center, double radius, Color color)
    {
        this.center=center;
        this.radius=radius;
        this.color=color;        
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
    }
    public void calcMove()
    {
        //if(center.getY()>600)
        //{
        //    upVelocity=0;
        //    goTo(center.getX(),500);
       // }
        if (center.getX()<0||center.getX()>840)
        {
            xVelocity*=-1;
            if(center.getX()<-20)
            {   
                goTo(20, center.getY());
            }
            if(center.getX()>860)
            {   
                goTo(820, center.getY());
            }
        }
        move(xVelocity,-1*upVelocity);
        
        upVelocity-=2;
        
        xVelocity=(int)Math.round((float)xVelocity*.9);
        
        
    }
    public void jump()
    {
        upVelocity=20;
        
    }
    public void whenTouchingGround(boolean touching)
    {
       
        if (touching)
        {
            jump();
        //    goTo(center.getX(),center.getY()-(upVelocity)-1);
        //    upVelocity=2;
             System.out.println("Made it here  "+center);         
        }
    }
    void draw(Graphics2D g2, boolean filled)
    {        
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
        //System.out.println(center);
        g2.setColor(color);
        if (filled)
        {            
            g2.fill(rect);
        }
        else
        {
            g2.draw(rect);
        }
    }
    public void setSpeed(int speed)
    {
        upVelocity=speed;
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
    public boolean isOnTopOf(Shape on)
    {
        return (on.isInside(new Point2D.Double(center.getX()+radius,center.getY()+radius)))||(on.isInside(new Point2D.Double(center.getX()-radius,center.getY()+radius)));
    }
    public boolean isOnTopOfNextFrame(Shape on)
    {
        NextFramePlayer next=new NextFramePlayer(center,radius);
        return next.isOnTopOfNext(on,center,upVelocity,xVelocity);
        
    }

    public void moveX(int direction)
    {
        xVelocity+=direction;
        if (xVelocity>25||xVelocity<-25)
        {
            xVelocity*=9;
            xVelocity/=10;
        }
          
    }
}

