import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class SuperBomb extends SuperBlobClass
{
    private int xLeft;
    private int yTop;

    public SuperBomb(int x, int y)
    {
        xLeft = x;
        yTop = y;
        name = "superbomb";
    }

    public void draw(Graphics2D g2)
    {
        Ellipse2D.Double body1 = new Ellipse2D.Double(xLeft, yTop, 20, 20 );
        Ellipse2D.Double body2 = new Ellipse2D.Double(xLeft + 2, yTop + 2, 16, 16);
        Ellipse2D.Double body3 = new Ellipse2D.Double(xLeft + 4, yTop + 4, 12, 12);
        Ellipse2D.Double body4 = new Ellipse2D.Double(xLeft + 6, yTop + 6, 8, 8);
        Ellipse2D.Double body5 = new Ellipse2D.Double(xLeft + 8, yTop + 8, 4, 4);

        g2.setColor(Color.RED);
        g2.fill(body1);
        g2.setColor(Color.YELLOW);
        g2.fill(body2);
        g2.setColor(Color.GREEN);
        g2.fill(body3);
        g2.setColor(Color.CYAN);
        g2.fill(body4);
        g2.setColor(Color.RED);
        g2.fill(body5);

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