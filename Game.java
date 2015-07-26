import javax.swing.JFrame;

public class Game
{
    public static void main(String[]args)
    {
        MainBlob mov = new MainBlob();
        JFrame frame = new JFrame();
        
        frame.add(mov);
        
        frame.setSize(800, 800);
        frame.setTitle("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}