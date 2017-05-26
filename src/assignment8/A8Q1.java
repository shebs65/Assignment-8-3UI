package assignment8;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author shebm3906
 */
public class A8Q1 extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // GAME VARIABLES WOULD GO HERE
    Color peach = new Color(255, 218, 185);
    Color SeaGreen = new Color(154, 255, 154);
    Color Brown = new Color(116, 76, 41);
    int eyebrowLeftY = 150;
    int eyebrowRightY = 150;
    int eyebrowDirection = 1;
    boolean reached = false;

    // GAME VARIABLES END HERE   
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        // head 
        g.setColor(peach);

        g.fillOval(200, 50, 400, 500);

        g.fillOval(570, 250, 50, 125);
        g.fillOval(180, 250, 50, 125);

        // outer eyes
        g.setColor(Color.WHITE);

        g.fillOval(290, 200, 75, 50);
        g.fillOval(440, 200, 75, 50);

        // iris
        g.setColor(SeaGreen);

        g.fillOval(310, 203, 35, 45);
        g.fillOval(460, 203, 35, 45);

        // pupils and mouth
        g.setColor(Color.BLACK);

        g.fillOval(318, 215, 20, 20);
        g.fillOval(467, 215, 20, 20);

        g.fillOval(340, 400, 125, 50);

        // tongue
        g.setColor(Color.RED);

        g.fillOval(378, 425, 50, 25);

        // eyebrows and hair
        g.setColor(Brown);

        g.fillOval(275, eyebrowLeftY, 100, 20);
        g.fillOval(425, eyebrowRightY, 100, 20);

        g.fillArc(240, 50, 320, 180, 0, 180);


        // GAME DRAWING ENDS HERE
    }

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {
        // Any of your pre setup before the loop starts should go here
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime = 0;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 


            if (eyebrowLeftY <= 150 && reached == false) {
                eyebrowDirection = 1;


                if (eyebrowLeftY == 150) {
                    reached = true;
                }
            }

            if (eyebrowLeftY >= 180 && reached == true) {
                eyebrowDirection = -1;

                if (eyebrowLeftY == 180) {
                    reached = false;
                }
                if (eyebrowRightY <= 150 && reached == false) {
                    eyebrowDirection = 1;


                    if (eyebrowRightY == 150) {
                        reached = true;
                    }
                }

                if (eyebrowRightY >= 180 && reached == true) {
                    eyebrowDirection = -1;

                    if (eyebrowRightY == 180) {
                        reached = false;
                    }
                }
            }
            eyebrowLeftY = eyebrowLeftY + eyebrowDirection * 1;
            eyebrowRightY = eyebrowRightY + eyebrowDirection * 1;

            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        A8Q1 game = new A8Q1();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);

        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        game.addMouseListener(new Mouse());

        // starts the game loop
        game.run();
    }

    // Used to implement any of the Mouse Actions
    private static class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down

        @Override
        public void mousePressed(MouseEvent e) {
        }

        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e) {
        }

        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

    // Used to implements any of the Keyboard Actions
    private static class Keyboard extends KeyAdapter {
        // if a key has been pressed down

        @Override
        public void keyPressed(KeyEvent e) {
        }

        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
