import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.MouseInputListener;

import ky.Asset;
import ky.AudioPlayer;
import ky.KYscreen;
import ky.Text;
import ky.Vector2D;

public class Main extends KYscreen implements MouseInputListener {
    public static void main (String[] args) {
        System.setProperty("prism.allowhidpi", "false");
        new Main();
    }

    AudioPlayer backgroundMusic;
    AudioPlayer clickSound;
    int frames=0;
    long lastSec = System.currentTimeMillis();
    Text fpsText;
    Text scoreText;
    static int width = 1920;
    static int height = 1080;
    Asset background;
    int score=0;

    final int maxCircles=10;
    int curCircles=0;
    Random randX;
    Random randY;

    Cursor cursor;
    ArrayList <Circle> circles;

    public Main() {
        super(width, height, false, 0);
    }

    @Override
    public void start() {
        setFullScreen(true);
        setCursorVisible(false);

        backgroundMusic = new AudioPlayer("assets/Chopin - Nocturne op9 No2.wav");
        // backgroundMusic = new AudioPlayer("assets/Nightmare2.m4a");
        backgroundMusic.setTime(0, 2, 0);
        backgroundMusic.setLoop(true);
        backgroundMusic.play();

        fpsText = new Text("FPS: ?", new Font ("Arial", Font.PLAIN, 20), Color.black, new Vector2D(70, 15), 140, 30, 2);
        fpsText.setVisible(true);
        add(fpsText);

        scoreText = new Text("Score: 0", new Font ("Arial", Font.PLAIN, 20), Color.black, new Vector2D(70, 45), 140, 30, 2);
        scoreText.setVisible(true);
        add(scoreText);

        clickSound = new AudioPlayer("assets/click.wav");

        cursor = new Cursor();
        add(cursor);

        background = new Asset("assets/background.png", new Vector2D(1920/2,1080/2), 0);
        background.setVisible(true);
        add(background);

        addMouseListener(this);
        addMouseMotionListener(this);

        randX = new Random();
        randY = new Random();

        circles = new ArrayList<Circle>();

        setDebugMode(false);
    }


    @Override
    public void update() {
        if (System.currentTimeMillis() >= lastSec+1000) {
            fpsText.setText("FPS: " + frames);
            frames=0;
            lastSec = System.currentTimeMillis();
        }

        if (curCircles < maxCircles) {
            circles.add(new Circle(new Vector2D((randX.nextDouble(1920-80)+80/2)/1.25, (randY.nextDouble(1080-80)+80)/1.25), 80,80));
            add(circles.get(circles.size()-1));
            circles.get(circles.size()-1).setVisible(true);
            curCircles++;
        }
        frames++;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickSound.setTime(0,0,35);
        clickSound.play();
        for (int i=circles.size()-1; i>=0; i--){
            if (cursor.inCircle(circles.get(i).getX(), circles.get(i).getY(), circles.get(i).getHeight())) {
                score++;
                scoreText.setText("Score: " + score);
                curCircles--;
                circles.get(i).delete();
                circles.remove(i);
                break;
            }
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cursor.setPos(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        cursor.setPos(e.getX(), e.getY());
    }


    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        
    }

    @Override
    public void keyTyped(int keyCode) {
        
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }



}