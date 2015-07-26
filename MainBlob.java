import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.util.Random;

public class MainBlob extends JPanel implements ActionListener, KeyListener, MouseListener
{
    //for whole program
    Timer time = new Timer(10, this);
    Random gen = new Random();
    int count = 450;//not 0 so game can start sooner
    boolean end;

    //For Main dude
    int x = 370;
    int y = 400;
    int velx;
    int vely;
    Color col = Color.BLACK;
    int width;
    int size;
    int mouthSize = 7;

    //For blobs
    int x2;
    int y2;

    //For score bar
    int score = 0;
    int strike = 0;

    //for title page
    boolean start = true;// To display the title slide only before arrows are pressed

    ArrayList<Integer> velocityListX = new ArrayList<>();// the secret to animating multople objects at once
    ArrayList<Integer> velocityListY = new ArrayList<>();
    ArrayList<SuperBlobClass> blobList = new ArrayList<>();

    public MainBlob()
    {
        addMouseListener(this);
        addKeyListener(this);//this refers to KeyListerner Internface up top
        setFocusable(true);//implements Key Listner
        setFocusTraversalKeysEnabled(false); //won't be using shift and tab keys
        try
        {
        AudioClip.playBack();
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.fillRect(0,0,800,800);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,800,30);

        if (start)// Draws cover slide
        {
            Avatar temp0 = new Avatar(170, 250, size, mouthSize, col);
            temp0.draw((Graphics2D) g);

            BadBlob temp1 = new BadBlob(195, 400);
            temp1.draw((Graphics2D) g);

            PlusBlob temp2 = new PlusBlob(191, 500);
            temp2.draw((Graphics2D) g);

            Bomb temp3 = new Bomb(193, 600);
            temp3.draw((Graphics2D) g);

            SuperBomb temp4 = new SuperBomb(192, 695);
            temp4.draw((Graphics2D) g);

            g.setColor(Color.WHITE);
            g.drawString("Welcome to Gastro.", 100, 120);
            g.drawString("The objective of this game is to collect as many Green Pluses as possible and avoid dangerous Red Blobs.", 100, 140);
            g.drawString("Created by: Victor Chien and Suraj Setty.", 100, 160);
            g.drawString("Press any key to begin.", 100, 180);

            g.drawString("Your Avatar: This is the character you control ", 300, 280);
            g.drawString("Red Blob: Increases strikes. Three strikes you're out", 300, 405);
            g.drawString("Green Plus: Increases score and size of Avatar", 300, 509);
            g.drawString("Bomb: Destroys all Red Blobs", 300, 607);
            g.drawString("Super Bomb: Turns all RedBlobs into Green Pluses", 300, 705);
        }
        else //Actual game
        {
            g.setColor(Color.MAGENTA);//creates three buttons for mouse
            g.fillOval(665, 1, 28, 28);
            g.setColor(Color.MAGENTA);
            g.fillOval(710, 1, 28, 28);
            g.setColor(Color.MAGENTA);
            g.fillOval(755, 1, 28, 28);
            g.setColor(Color.BLACK);//text
            g.drawString("Play", 667, 21);
            g.drawString("Stop", 711, 21);
            g.drawString("End", 758, 21);

            g.setColor(Color.RED);
            g.drawString("Score: " + score, 20, 20);
            g.drawString("Strike: " + strike, 100, 20);
            g.drawString("Gastro", 360, 20);

            Avatar two = new Avatar(x, y, size, mouthSize, col);
            two.draw((Graphics2D) g);
            width = two.getWidth();// so does not go through wall later on

            for (int a = 0; a < blobList.size(); a++) {
                if (two.getBody().contains(blobList.get(a).getX(), blobList.get(a).getY()))
                {
                    if (blobList.get(a).getName().equals("good")) {
                        score++;
                        size += 5;// increases size of the avatar   
                        try
                        {
                            AudioClip.playGood();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    } else if (blobList.get(a).getName().equals("bad")) {
                        strike++;
                        try
                        {
                            AudioClip.playUpper();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                    if (!(blobList.get(a).getName().equals("bomb")) && !(blobList.get(a).getName().equals("superbomb"))) {
                        blobList.remove(a);
                        velocityListX.remove(a);
                        velocityListY.remove(a);
                    }
                    else if(blobList.get(a).getName().equals("bomb"))
                    {
                        blobList.remove(a);
                        velocityListX.remove(a);
                        velocityListY.remove(a);
                        try
                        {
                            AudioClip.playBomb();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < blobList.size(); i++) {
                            if (blobList.get(i).getName().equals("bad")) {
                                blobList.remove(i);
                                velocityListX.remove(i);
                                velocityListY.remove(i);
                                i -= 1; //Makes sure the one right after the one you just deleted is not skipped over
                            }
                        }
                    }
                    else if (blobList.get(a).getName().equals("superbomb"))
                    {
                        try
                        {
                            AudioClip.playFryingPan();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                        blobList.remove(a);
                        velocityListX.remove(a);
                        velocityListY.remove(a);

                        int length = blobList.size();
                        for (int i = 0; i < length; i++) {
                            if (blobList.get(i).getName().equals("bad")) {
                                blobList.add(new PlusBlob(blobList.get(i).getX(), blobList.get(i).getY()));
                                velocityListX.add(velocityListX.get(i));
                                velocityListY.add(velocityListY.get(i));

                                blobList.remove(i);
                                velocityListX.remove(i);
                                velocityListY.remove(i);
                                i -= 1; //Makes sure the one right after the one you just deleted is not skipped over
                            }
                        } 
                    }
                }
                else {
                    blobList.get(a).draw((Graphics2D) g);// this is the part where you draw blobs
                }
            }
        }
        if (strike >= 3)
        {
            end = true;
            time.stop();
            velocityListX.clear();
            velocityListY.clear();
            blobList.clear();

            try {// from Stack Overflow
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            g.setColor(Color.PINK);
            g.fillRect(315, 380, 200, 100);
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 375, 410);
            g.drawString("To play again click Play.", 340, 430);
            g.drawString("Final Score: " + score, 370, 450);

        }
        else
        {
            time.start();
        }
    }

    public void actionPerformed(ActionEvent e)// if user presses arrow keys, will add velx to x = move forward etc.
    {
        if (!start) {
            if (x < -1) {
                velx = 0;
                x = 0;
            } else if (x > 800 - width) {
                velx = 0;
                x = 800 - width;
            }
            if (y < 30) {
                vely = 0;
                y = 30;
            } else if (y > 800 - width) {
                vely = 0;
                y = 800 - width;
            }
            x = x + velx;
            y = y + vely;

            //Blob interactivity with environment
            for (int b = 0; b < blobList.size(); b++) {
                if (blobList.get(b).getName().equals("good")) {
                    if (blobList.get(b).getX() < 0) {
                        velocityListX.set(b, gen.nextInt(2) + 1);
                        blobList.set(b, new PlusBlob(0, blobList.get(b).getY()));
                    } else if (blobList.get(b).getX() > 800 - 18) {
                        velocityListX.set(b, -1 * (gen.nextInt(2) + 1));
                        blobList.set(b, new PlusBlob(800 - 18, blobList.get(b).getY()));
                    }
                    if (blobList.get(b).getY() < 30) {
                        velocityListY.set(b, gen.nextInt(2) + 1);
                        blobList.set(b, new PlusBlob(blobList.get(b).getX(), 30));
                    } else if (blobList.get(b).getY() > 800 - 18) {
                        velocityListY.set(b, -1 * (gen.nextInt(2) + 1));
                        blobList.set(b, new PlusBlob(blobList.get(b).getX(), 800 - 18));
                    }
                    blobList.set(b, new PlusBlob(blobList.get(b).getX() + velocityListX.get(b), blobList.get(b).getY() + velocityListY.get(b)));
                } else if (blobList.get(b).getName().equals("bad")) {
                    if (blobList.get(b).getX() < 0) {
                        velocityListX.set(b, gen.nextInt(3) + 1);
                        blobList.set(b, new BadBlob(0, blobList.get(b).getY()));
                    } else if (blobList.get(b).getX() > 800 - 10) {
                        velocityListX.set(b, -1 * (gen.nextInt(3) + 1));
                        blobList.set(b, new BadBlob(800 - 10, blobList.get(b).getY()));
                    }
                    if (blobList.get(b).getY() < 30) {
                        velocityListY.set(b, gen.nextInt(3) + 1);
                        blobList.set(b, new BadBlob(blobList.get(b).getX(), 30));
                    } else if (blobList.get(b).getY() > 800 - 10) {
                        velocityListY.set(b, -1 * (gen.nextInt(3) + 1));
                        blobList.set(b, new BadBlob(blobList.get(b).getX(), 800 - 10));
                    }
                    blobList.set(b, new BadBlob(blobList.get(b).getX() + velocityListX.get(b), blobList.get(b).getY() + velocityListY.get(b)));
                }
                else if (blobList.get(b).getName().equals("bomb")) //makes sure bomb does not get immediately removed by the code in the lower segment of this statement by setting it one higher
                {
                    if (blobList.get(b).getX() < 0) {
                        velocityListX.set(b, gen.nextInt(4) + 1);
                        blobList.set(b, new Bomb(0, blobList.get(b).getY()));
                    } else if (blobList.get(b).getX() > 800 - 10) {
                        velocityListX.set(b, -1 * (gen.nextInt(4) + 1));
                        blobList.set(b, new Bomb(800 - 10, blobList.get(b).getY()));
                    }
                    if (blobList.get(b).getY() < 30) {
                        velocityListY.set(b, gen.nextInt(4) + 1);
                        blobList.set(b, new Bomb(blobList.get(b).getX(), 30));
                    } else if (blobList.get(b).getY() > 800 - 10) {
                        velocityListY.set(b, -1 * (gen.nextInt(4) + 1));
                        blobList.set(b, new Bomb(blobList.get(b).getX(), 800 - 10));
                    }
                    blobList.set(b, new Bomb(blobList.get(b).getX() + velocityListX.get(b), blobList.get(b).getY() + velocityListY.get(b)));
                    if (blobList.get(b).getX() < 0 || blobList.get(b).getX() > 800 - 10 || blobList.get(b).getY() < 30 || blobList.get(b).getY() > 800 - 10) //removes the bomb after it reaches the edge
                    {
                        blobList.remove(b);
                        velocityListX.remove(b);
                        velocityListY.remove(b);
                    }
                }
                else if (blobList.get(b).getName().equals("superbomb")) //makes sure bomb does not get immediately removed by the code in the lower segment of this statement by setting it one higher
                {
                    if (blobList.get(b).getX() < 0) {
                        velocityListX.set(b, gen.nextInt(4) + 1);
                        blobList.set(b, new SuperBomb(0, blobList.get(b).getY()));
                    } else if (blobList.get(b).getX() > 800 - 10) {
                        velocityListX.set(b, -1 * (gen.nextInt(4) + 1));
                        blobList.set(b, new SuperBomb(800 - 10, blobList.get(b).getY()));
                    }
                    if (blobList.get(b).getY() < 30) {
                        velocityListY.set(b, gen.nextInt(4) + 1);
                        blobList.set(b, new SuperBomb(blobList.get(b).getX(), 30));
                    } else if (blobList.get(b).getY() > 800 - 10) {
                        velocityListY.set(b, -1 * (gen.nextInt(4) + 1));
                        blobList.set(b, new SuperBomb(blobList.get(b).getX(), 800 - 10));
                    }
                    blobList.set(b, new SuperBomb(blobList.get(b).getX() + velocityListX.get(b), blobList.get(b).getY() + velocityListY.get(b)));
                    if (blobList.get(b).getX() < 0 || blobList.get(b).getX() > 800 - 10 || blobList.get(b).getY() < 30 || blobList.get(b).getY() > 800 - 10) //removes the bomb after it reaches the edge
                    {
                        blobList.remove(b);
                        velocityListX.remove(b);
                        velocityListY.remove(b);
                    }
                }
            }

            int randnum = gen.nextInt(4);// Generates object at random corner
            if (randnum == 0)// top left
            {
                x2 = 1;
                y2 = 31;
            } else if (randnum == 1)// bottom left
            {
                x2 = 1;
                y2 = 799;
            } else if (randnum == 2)// bottom right
            {
                x2 = 799;
                y2 = 799;
            } else // top right
            {
                x2 = 799;
                y2 = 31;
            }

            if (count != 0 && count % 3000 == 0)// ADDS a blob every 30s
            {
                velocityListX.add((gen.nextInt(3) + 1));
                velocityListY.add(gen.nextInt(3) + 1);
                blobList.add(new Bomb(x2, y2));// don't need to worry about the thickness of PlusBlob when placing randomly becuase will automatically set it to border above

            }
            if (count != 0 && count % 5000 == 0)// ADDS a blob every 50s
            {
                velocityListX.add((gen.nextInt(3) + 1));
                velocityListY.add(gen.nextInt(3) + 1);
                blobList.add(new SuperBomb(x2, y2));// don't need to worry about the thickness of PlusBlob when placing randomly becuase will automatically set it to border above

            }
            if (count != 0 && count % (500 + gen.nextInt(2) * 100) == 0)// ADDS a blob every 6s
            {
                velocityListX.add((gen.nextInt(3) + 1));
                velocityListY.add(gen.nextInt(3) + 1);
                blobList.add(new BadBlob(x2, y2));
            }
            if (count != 0 && count % 700 == 0)// ADDS a blob every 7s
            {
                velocityListX.add((gen.nextInt(2) + 1));
                velocityListY.add(gen.nextInt(2) + 1);
                blobList.add(new PlusBlob(x2, y2));// don't need to worry about the thickness of PlusBlob becuase will automatically set it to border above

            }
            count++;
            repaint();
        }
    }

    public void keyPressed(KeyEvent e)//implements method from KeyListener Interface
    {
        int c = e.getKeyCode();//gets code for the button you pressed so know what the heck is pressed
        if (c == KeyEvent.VK_LEFT)//left key
        {
            velx = -5;
            vely = 0;// no y displacement
        }
        if (c == KeyEvent.VK_UP)
        {
            velx = 0;// no x displacement;
            vely = -5;
        }
        if (c == KeyEvent.VK_RIGHT)
        {
            velx = 5;
            vely = 0;
        }
        if (c == KeyEvent.VK_DOWN)
        {
            velx = 0;
            vely = 5;
        }
        col = Color.GREEN;
        mouthSize = 14;//opens mouth when moving
        start = false;
    }

    public void keyReleased(KeyEvent e)//stops the avatar when arrow key not pressed. Return it to normal Face.
    {
        velx = 0;
        vely = 0;
        mouthSize = 7;
        col = Color.BLACK;
    }

    public void mouseClicked(MouseEvent e)//adds functionality to three buttons on upper right corner
    {
        if (e.getX() >= 755 && e.getX() <= 783 && e.getY() >= 1 && e.getY() <= 29)//button ranges
        {
            strike = 3;//ends the entire program
        }
        else if (e.getX() >= 710 && e.getX() <= 738 && e.getY() >= 1 && e.getY() <= 29)
        {
            time.stop();//pauses the program
        }
        else if (e.getX() >= 655 && e.getX() <= 683 && e.getY() >= 1 && e.getY() <= 29)
        {
            time.start();//starts the program back up
            if (end) {//starts the entire game again
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                strike = 0;
                score = 0;
                x = 370;
                y = 400;
                size = 0;
            }
        }
    }

    public void keyTyped(KeyEvent e){} //Randomw methods that need to be written to satisfy interfaces

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}
}