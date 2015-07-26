import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Avatar extends SuperBlobClass
{
    private int xLeft;
    private int yTop;
    private int size;
    private Color colorfx;
    private int mouthSize;
    Ellipse2D.Double body;
    
    public Avatar(int x, int y, int size, int mouthSize, Color effect)
    {
        xLeft = x;
        yTop = y;
        this.size = size;
        this.mouthSize = mouthSize;
        colorfx = effect;
        
    }
    
    public void draw(Graphics2D g2)
    {
        body = new Ellipse2D.Double(xLeft, yTop, 60 + size , 60  + size);
        Rectangle eye1 = new Rectangle(xLeft + (60 + size)/8, yTop + (60 + size)/3, 15 + (15 * size)/60 , 10 + (10 * size)/60);
        Rectangle eye2 = new Rectangle(xLeft + (60 + size)/8 + (30 + (30 * size)/60), yTop + (60 + size)/3, 15 + (15 * size)/60 , 10 + (10 * size)/60);
        Rectangle mouth = new Rectangle(xLeft + (60 + size)/8 + (6 + (6 * size)/60), yTop + 2 * (60 + size)/3, 35 + (35 * size)/60, mouthSize + (mouthSize * size)/60);
        
        g2.setColor(Color.YELLOW);
        g2.fill(body);
        g2.setColor(colorfx);
        g2.fill(eye1);
        g2.fill(eye2);
        g2.setColor(Color.RED);
        g2.fill(mouth);
    }
    
    public Ellipse2D.Double getBody()
    {
        return body;
    }
    
    public int getWidth()
    {
        return 60 + size;
    }
}