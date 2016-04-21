
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

class Player
{
    Point2D.Double center;
    double xradius;
    double yradius;
    Rectangle rect;
    Color color;
    static double scrollX=0;
    double upVelocity=0;
    double xVelocity=0;
    double scrollXV=0;
    Player inactive;
    boolean touching=false;
    boolean scrolling=false;
    boolean crouching=false;
    int powerUpState=0;
    boolean vulnerable=true;
    double vulnerabilityTimer=0;
    public Player(Point2D.Double center, double radius)
    {
        this.center=center;
        this.xradius=radius;
        this.yradius=yradius=25;            
        rect=new Rectangle((int)(center.getX()-radius),(int)(center.getY()-radius),(int)radius*2,(int)radius*2);
    }
    
    public void calcMove(boolean space)
    {
        
        if (center.getX()>=600&&xVelocity>0)
        {       
            scrolling=true;
            
            move(0,-1*upVelocity);
            if (upVelocity<-2||space)
            {
                upVelocity-=.25;            
            }
            else
            {                  
                upVelocity-=.75;        
            }
            
            scrollX+=xVelocity;
            
            xVelocity*=.95;
            
            if (center.getY()>900)
            {
                takeDamage(0);
            }
        }
        else
        {
            scrolling=false;
            if (center.getX()<xradius)
            {
                xVelocity=.25;
                if(center.getX()<-20)
                {   
                    goTo(20, center.getY());
                }
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
            
            if (center.getY()>900)
            {
                takeDamage(0);
            }
            //System.out.println(center);
        }
    }    
    public void moveX(double direction,boolean shift, boolean crouch)
    {        
       // System.out.println(crouching);
        
        
        if (crouch)        
        {        
            if (crouching==false)
            {
                crouching=true;
                yradius/=2;
                move(0,yradius);
            }     
            if (touching)
            {
                xVelocity*=.95;
                if (xVelocity<4)
                {
                    xVelocity=0;
                }
            }
        }
        else
        {            
            if (crouching==true)
            {
                crouching=false;
                yradius*=2;   
                move(0,-1*yradius/2);
            }
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
    }        
    public void jump()
    {
        //setRadius(40);
        if(touching&&upVelocity>-3)
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
    public void bounce(int behavior)//0 is bounce, 1 is tangible, 2 is intangible
    {
        switch (behavior) {
            case 0: 
                upVelocity=20;       
                touching=false;
                break;
            case 1:
                whenTouchingGround(true,center.getY()+yradius);
        }
        
    }    
    public void whenTouchingGround(boolean touching, double groundHeight)
    {       
       
        if (touching)
        {           
            this.touching=true;
            upVelocity=0;
            goTo(center.getX(),groundHeight-yradius);        
        }
    }
    
    void draw(Graphics2D g2)
    {                
        System.out.println(vulnerabilityTimer);
        if (vulnerabilityTimer!=0)
        {
            vulnerabilityTimer--;
            if (vulnerabilityTimer==0)
            {
                vulnerable=true;
            }
        }
        if(powerUpState==0)
        {
            color=Color.GREEN;
        }
        else if(powerUpState==1)
        {
            color=Color.RED;
        }
        
        rect=new Rectangle((int)(center.getX()-xradius),(int)(center.getY()-yradius),(int)xradius*2,(int)yradius*2);
               
        Rectangle rect2;
        Rectangle rect3;
        g2.setColor(color);                 
        g2.fill(rect);
        Rectangle rect4=new Rectangle((int)(center.getX()-(xradius*2/3)),(int)(center.getY()-(yradius/2)),(int)xradius/4,(int)yradius/4);
        Rectangle rect5=new Rectangle((int)(center.getX()+(xradius/3)),(int)(center.getY()-(yradius/2)),(int)xradius/4,(int)yradius/4);
        
        if(!vulnerable)
        {
            Rectangle rect6=new Rectangle((int)(center.getX()-(xradius/2)),(int)(center.getY()+(yradius/2)),(int)xradius*2/3,(int)yradius/4);
            Line2D.Double l2=new Line2D.Double((center.getX()-(xradius*3/4)),(center.getY()-(yradius*3/5)),center.getX()-xradius/5,center.getY());
            Line2D.Double l3=new Line2D.Double(center.getX()+xradius*4/5,(center.getY()-(yradius*3/5)),(center.getX()+(xradius/4)),center.getY());
        
            g2.setColor(Color.BLACK);
            g2.fill(rect6);
            g2.draw(l2);
            g2.draw(l3);
        }        
        else if(touching&&upVelocity>-3)
        {
            Rectangle rect6=new Rectangle((int)(center.getX()-(xradius/2)),(int)(center.getY()+(yradius/2)),(int)xradius*2/3,(int)yradius/4);
            
            rect2=new Rectangle((int)(center.getX()-(xradius*2/3)),(int)(center.getY()-(yradius/2)),(int)xradius/2,(int)yradius/2);
            rect3=new Rectangle((int)(center.getX()+(xradius/3)),(int)(center.getY()-(yradius/2)),(int)xradius/2,(int)yradius/2);
            g2.setColor(Color.WHITE);  
            g2.fill(rect2);
            g2.fill(rect3);
            g2.setColor(Color.BLACK);
            g2.fill(rect6);
            g2.fill(rect4);
            g2.fill(rect5);
        }
        else
        {
            Ellipse2D.Double rect6=new Ellipse2D.Double((int)(center.getX()-(xradius*2/3)),(int)(center.getY()+(yradius/4)),(int)xradius,(int)yradius/2);
            g2.fill(rect6);
            rect2=new Rectangle((int)(center.getX()-(xradius*3/4)),(int)(center.getY()-(yradius*3/5)),(int)xradius*3/5,(int)yradius*3/5);
            rect3=new Rectangle((int)(center.getX()+(xradius/4)),(int)(center.getY()-(yradius*3/5)),(int)xradius*3/5,(int)yradius*3/5);
            g2.setColor(Color.WHITE);  
            g2.fill(rect2);
            g2.fill(rect3);
            g2.setColor(Color.BLACK);
            g2.fill(rect6);
            g2.fill(rect4);
            g2.fill(rect5);
        }
        
        
    }
            
    public void setSpeed(int speed)
    {
        upVelocity=speed;
    }    
    public void setYRadius(double newR)
    {
        yradius=newR;        
    }    
    public void changeRadius(double delta)
    {
        yradius+=delta;
        xradius+=delta;
    }    
    public double getYRadius()
    {
        return yradius;
    }
    public double getXRadius()
    {
        return xradius;
    }
    public double getScrollX()
    {
        return scrollX;
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
        return (Math.abs(center.getX()-point.getX())<xradius)&&(Math.abs(center.getY()-point.getY())<yradius);
    }
    
    public boolean isOnTopOfNextFrame(Shape on)
    {
        Point2D.Double center2=new Point2D.Double(center.getX()+xVelocity,center.getY()-upVelocity);        
        return(upVelocity<0)&&((on.isInside(new Point2D.Double(center2.getX()+xradius,center2.getY()+yradius)))||(on.isInside(new Point2D.Double(center2.getX()-xradius,center2.getY()+yradius))));
        
    }        
    public boolean isHitNextFrame(Shape on)
    {
        Point2D.Double center2=new Point2D.Double(center.getX()+xVelocity,center.getY()-upVelocity);        
        return (on.isInside(new Point2D.Double(center2.getX()+xradius+1,center2.getY()+(yradius*3/4))))||(on.isInside(new Point2D.Double(center2.getX()-xradius-1,center2.getY()+(yradius*.75))))||(on.isInside(new Point2D.Double(center2.getX()-xradius-1,center2.getY()-yradius)))||(on.isInside(new Point2D.Double(center2.getX()+xradius+1,center2.getY()-yradius)));
        
    }        
    public void hitWall(double objX, double objY, double xLength,double yLength, Shape object)
    {
        if(objX-xLength>center.getX()&&objY+yLength>center.getY()-yradius+1)
        {            
            goTo(objX-xLength-xradius,center.getY());
            xVelocity=-.25;
            if(DrawingPanel.isJumping&&upVelocity<10)
            {
                upVelocity=7;
                xVelocity=-7;
            }
            
        }
        else if (objX+xLength<center.getX()&&objY+yLength>center.getY()-yradius+1)
        {            
            goTo(objX+xLength+xradius,center.getY());
            xVelocity=.25;
            if(DrawingPanel.isJumping&&upVelocity<10)
            {
                upVelocity=7;
                xVelocity=7;
            }
        }
        else if (objY+yLength<center.getY())
        {           
            goTo(center.getX(),objY+yLength+yradius);
            upVelocity=0;
            object.hitFromBottom();
        }   
    }
    
    public void getPowerUp(int identity)
    {
        powerUpState=identity;
        yradius=30+(10*powerUpState);
    }
    public void takeDamage(int type)
    {
        if(vulnerable)
        {            
            if(type==0||type==1)
            {
                vulnerable=false;
                vulnerabilityTimer=100;
                powerUpState--;     
                yradius=30+(10*powerUpState);
                if(powerUpState<0)
                {
                    goTo(50, 400);
                    scrollX=0;
                    powerUpState=0;
                    yradius=30+(10*powerUpState);
                }
            }
        }
    }
        
}

