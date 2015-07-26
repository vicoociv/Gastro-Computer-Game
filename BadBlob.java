import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class BadBlob extends SuperBlobClass
{
    private int xLeft;
    private int yTop;
    
    public BadBlob(int x, int y)
    {
        xLeft = x;
        yTop = y;
        name = "bad";
    }
    
    public void draw(Graphics2D g2)
    {
        Ellipse2D.Double body = new Ellipse2D.Double(xLeft, (yTop), 10 , 10);
        
        g2.setColor(Color.RED);
        g2.fill(body);
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