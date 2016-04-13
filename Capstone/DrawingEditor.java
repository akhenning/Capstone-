
 import java.awt.Rectangle;
 import javax.swing.JFrame;


/**
 * Write a description of class DrawingEditorComponent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DrawingEditor extends JFrame
{
    /** description of instance variable x (add comment for each instance variable) */
    private DrawingPanel canvas;
    //private ControlPanel controls;

    public DrawingEditor()
    {
        canvas=new DrawingPanel();
        //controls=new ControlPanel(canvas);
        add(canvas);       
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main()
    {
        DrawingEditor start=new DrawingEditor();
    }
}
