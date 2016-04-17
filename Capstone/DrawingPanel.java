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
    ArrayList<Shape> groundBlocks;
    ArrayList<Enemy> enemies;
    Player player=new Player(new Point2D.Double(100,100),30,Color.GREEN);
    Color current;    
    boolean isShift=false;
    boolean isLeft=false;
    boolean isRight=false;
    boolean isJumping=false;
        double lol=.5;
        
    public DrawingPanel()
    {        
        groundBlocks=new ArrayList<Shape>();
        enemies=new ArrayList<Enemy>();
        setBackground(Color.WHITE);       
        current=new Color(0,0,0);
        addMouseListener(new ClickListener());
        //addMouseMotionListener(new MovementListener());
        setFocusable(true);
        addKeyListener(new KeysListener());
        groundBlocks.add(new RectObj(new Point2D.Double(400,625),400,25,Color.BLACK));
        groundBlocks.add(new SquareObj(new Point2D.Double(400,250),50,Color.BLACK));
        groundBlocks.add(new SquareObj(new Point2D.Double(500,350),50,Color.RED));
        groundBlocks.add(new SquareObj(new Point2D.Double(1100,625),50,Color.BLUE));
        groundBlocks.add(new SquareObj(new Point2D.Double(1500,500),50,Color.RED));
        groundBlocks.add(new RectObj(new Point2D.Double(1900,700),300,50,Color.YELLOW));
        groundBlocks.add(new RectObj(new Point2D.Double(1900,300),150,50,Color.BLACK));
        //System.out.println(groundBlocks.get(2));
        enemies.add(new MatedEnemy(Color.RED,groundBlocks.get(6),50,player));
        enemies.add(new MatedEnemy(Color.RED,groundBlocks.get(3),50,player));
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
        for (Shape shape:groundBlocks)
        {            
            double position=shape.getStaticX()-player.getScrollX();
            if (position+shape.getXL()>-20&&position-shape.getXL()<1220)
            {
                shape.goToX(position);
                shape.draw(g2);
            }
            else
            {
                shape.goToX(-999);
            }
        }
        
        for (Enemy enemy:enemies)
        {            
            if(enemy.isAlive())
            {
                if(enemy.getMate().getCenter().getX()!=-999)
                {
                    enemy.calcXY();
                    enemy.draw(g2);
                
                }
                else
                {
                    enemy.goToX(-999);
                }
            }
        }
        // System.out.println(current);
    }
    public void nextFrame()
    {
        
        for (Shape shape:groundBlocks)
        {            
            if(player.isHitNextFrame(shape))
            {
                player.hitWall(shape.getCenter().getX(),shape.getCenter().getY(),shape.getXL(),shape.getYL());
              
            }
            boolean b=player.isOnTopOfNextFrame(shape);
            int top=(int)(shape.getCenter().getY()-shape.getYL());
            player.whenTouchingGround(b,top);
        }
        
        for (Enemy enemy:enemies)
        {            
            
            if(enemy.isAlive())
            {
                boolean b=player.isOnTopOfNextFrame(enemy);        
                if (b)
                {
                    player.bounce();
                    System.out.println("Jumped on");
                    enemy.getHit();
                }
                else if(player.isHitNextFrame(enemy))
                {
                    player.takeDamage();
                    System.out.println("Hurt");
                    
                }
            }            
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
            //repaint();
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
