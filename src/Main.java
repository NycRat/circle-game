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
    Music musicAsset;
    int score=0;

    final int maxBad=5;
    final int maxGood=10;
    int curBad=0;
    int curGood=0;
    Random randX;
    Random randY;

    Asset cursor;
    ArrayList <Circle> circles;

    public Main() {
        super(width, height, false, 0);
    }

    @Override
    public void start() {
        setFullScreen(true);
        setCursorVisible(false);
        setAlwaysOnTop(true);

        musicAsset = new Music();
        musicAsset.setVisible(true);
        add(musicAsset);

        fpsText = new Text("FPS: ?", new Font ("Arial", Font.PLAIN, 20), Color.black, new Vector2D(70, 15), 140, 30, 2);
        fpsText.setVisible(true);
        add(fpsText);

        scoreText = new Text("Score: 0", new Font ("Arial", Font.PLAIN, 20), Color.black, new Vector2D(70, 45), 140, 30, 2);
        scoreText.setVisible(true);
        add(scoreText);

        clickSound = new AudioPlayer("assets/click.wav");

        cursor = new Asset("assets/cursor.png", new Vector2D(0,0), 3);
        cursor.setVisible(true);
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

        for (int c=curGood; c < maxGood; c++) {
            circles.add(new Circle(new Vector2D((randX.nextDouble(1920-80)+80/2)/1.25, (randY.nextDouble(1080-80)+80)/1.25), 80,80, "good"));
            add(circles.get(circles.size()-1));
            circles.get(circles.size()-1).setVisible(true);
            curGood++;
        }
        for (int c=curBad; c < maxBad; c++) {
            circles.add(new Circle(new Vector2D((randX.nextDouble(1920-80)+80/2)/1.25, (randY.nextDouble(1080-80)+80)/1.25), 80,80, "bad"));
            add(circles.get(circles.size()-1));
            circles.get(circles.size()-1).setVisible(true);
            curBad++;
        }
        frames++;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i=circles.size()-1; i>=0; i--){
            if (circles.get(i).inCircle(new Vector2D(e.getX(), e.getY()))) {
                if (circles.get(i).getName().equals("good")) {
                    score++;
                    curGood--;
                } else {
                    score--;
                    curBad--;
                }
                scoreText.setText("Score: " + score);
                circles.get(i).setVisible(false);
                circles.remove(i);
                clickSound.setTime(0,0,35);
                clickSound.play();
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
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_M:
                musicAsset.mute();
                break;
            case KeyEvent.VK_UP:
                musicAsset.increaseVolume();
                break;
            case KeyEvent.VK_DOWN:
                musicAsset.decreaseVolume();
                break;
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