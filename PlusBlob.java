import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PlusBlob extends SuperBlobClass
{
    private int xLeft;
    private int yTop;
    
    public PlusBlob(int x, int y)
    {
        xLeft = x;
        yTop = y;
        name = "good";
    }
    
    public void draw(Graphics2D g2)
    {
        Rectangle side1 = new Rectangle(xLeft,(yTop) + 6, 18, 6);
        Rectangle side2 = new Rectangle(xLeft + 6,(yTop), 6, 18);
        
        g2.setColor(Color.GREEN);
        g2.fill(side1);
        g2.fill(side2);
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