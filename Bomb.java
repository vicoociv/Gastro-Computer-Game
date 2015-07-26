import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;

public class Bomb extends SuperBlobClass
{
    private int xLeft;
    private int yTop;
    
    public Bomb(int x, int y)
    {
        xLeft = x;
        yTop = y;
        name = "bomb";
    }
    
    public void draw(Graphics2D g2)
    {
        Ellipse2D.Double body = new Ellipse2D.Double(xLeft, (yTop), 15 , 15);
        Ellipse2D.Double top = new Ellipse2D.Double(xLeft + 2, (yTop) + 2, 11 , 11);
        g2.setColor(Color.WHITE);
        g2.fill(body);
        g2.setColor(Color.BLUE);
        g2.fill(top);
    }
    
    public int getX()
    {
        return xLeft;
    }
    
    public int getY()
    {
        return yTop;
    }
}