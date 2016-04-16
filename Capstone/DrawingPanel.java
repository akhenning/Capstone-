import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.JColorChooser;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class DrawingPanel extends JPanel
{
    ArrayList<Shape> everything;
    Player player=new Player(new Point2D.Double(20,20),30,Color.GREEN);
    Color current;    
    boolean isShift=false;
    boolean isLeft=false;
    boolean isRight=false;
    boolean isJumping=false;
        double lol=.5;
        
    public DrawingPanel()
    {        
        everything=new ArrayList<Shape>();
        setBackground(Color.WHITE);       
        current=new Color(0,0,0);
        addMouseListener(new ClickListener());
        //addMouseMotionListener(new MovementListener());
        setFocusable(true);
        addKeyListener(new KeysListener());
        everything.add(new RectObj(new Point2D.Double(400,625),400,25,Color.BLACK));
        everything.add(new SquareObj(new Point2D.Double(400,250),50,Color.BLACK));
        everything.add(new SquareObj(new Point2D.Double(500,350),50,Color.RED));
    }
    
    public Color getColor()
    {
        return current;
    }
    
    public Dimension getPreferredSize()
    {
        Dimension d=new Dimension(350,300);
        return d;
    }
   
    //public void addSquare()
    //{
    //    shapes.add(new Square(new Point2D.Double(100,100),15,current));
    //    repaint();
    //}
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        for (Shape shape:everything)
        {            
            shape.draw(g2,true);
        }
        // System.out.println(current);
    }
    public void nextFrame()
    {
        
        for (Shape shape:everything)
        {            
            if(player.isHitNextFrame(shape))
            {
                player.hitWall(shape.getCenter().getX(),shape.getCenter().getY(),shape.getX(),shape.getY());
              
            }
            boolean b=player.isOnTopOfNextFrame(shape);
            int top=(int)(shape.getCenter().getY()-shape.getY());
            player.whenTouchingGround(b,top);
        }
        player.calcMove(isJumping);
        if (isLeft)
        {
            player.moveX(-1,isShift);
            lol();
        }
        if (isRight)
        {
            player.moveX(1,isShift);
            lol();
        }
        
        repaint();
        requestFocusInWindow();
    }
    public void lol()
    {
        if(player.getRadius()>40)
            {
                lol=-.5;                
            }
            else if (player.getRadius()<30)
            {
                lol=.5;
            }
            player.setRadius(player.getRadius()+lol);
    }
    public class ClickListener implements MouseListener
    {
        
        public void mouseClicked(MouseEvent e)
        {}
        public void mouseEntered(MouseEvent e)
        {}
        public void mouseExited(MouseEvent e)
        {}
        public void mousePressed(MouseEvent e)
        {
            
            //repaint();
            //requestFocusInWindow();
        }
        public void mouseReleased(MouseEvent e)
        {
            //canDrag=false;
            //nowResize=false;
            //requestFocusInWindow();
        }
    }
    public class KeysListener implements KeyListener
    {
        
        public void keyPressed(KeyEvent e)
        {              
            
            if (e.getKeyCode()==KeyEvent.VK_SPACE)
            {
                player.jump();
                isJumping=true;
            }
            else if (e.getKeyCode()==16)
            {
                isShift=true;
            }
            else if (e.getKeyCode()==KeyEvent.VK_A)
            {
                 isLeft=true;
            }
            else if (e.getKeyCode()==KeyEvent.VK_D)
            {
                 isRight=true;
            }            
            repaint();
            requestFocusInWindow();           
        }
        public void keyReleased(KeyEvent e)
        {
            if (e.getKeyCode()==16)
            {
                isShift=false;
            }
            else if (e.getKeyCode()==KeyEvent.VK_A)
            {
                 isLeft=false;
            }
            else if (e.getKeyCode()==KeyEvent.VK_D)
            {
                 isRight=false;
            }          
            else if (e.getKeyCode()==KeyEvent.VK_SPACE)
            {
                isJumping=false;
            }
        }
        public void keyTyped(KeyEvent e)
        {}
    }
}
