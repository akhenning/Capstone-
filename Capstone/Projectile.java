
// 
// public class Projectile extends Entity
// {          
//     Color color;
//     double staticX;
//     double staticY;
//     double upV;
//     double movementSpeed;
//     public Projectile(Player player, int powerlevel)
//     {
//         super(Color.RED,20);
//         staticX=player.getX();
//         staticY=player.getY();
//         color=Color.RED;     
//         
// 
//     }
//     public void calcXY()
//     {
//         setY(staticY+(flightRange*roam));        
//         setX(staticX-Player.scrollX);
//         
//         if (roam<0)
//         {
//             roamV+=flightSpeed;
//         }
//         else if (roam>0)
//         {
//             roamV-=flightSpeed;
//         }
//         roam+=roamV;
//     }
//     public void draw(Graphics2D g2)
//     {
//         //calcXY(scrollX);        
//         Ellipse2D.Double ell2=new Ellipse2D.Double(getX()-12,getY()-12,24,24);
//        g2.setColor(Color.RED);  
//        g2.fill(ell2);       
//         
//     }
//     
//     boolean isInside(Point2D.Double point)
//     {
//         return (Math.abs(getX()-point.getX())<getRadius())&&(Math.abs(getY()-point.getY())<getRadius());
//     }                 
//     
//     public Shape getMate()
//     {
//         return null;
//     }
//         
// }
// 
// 
