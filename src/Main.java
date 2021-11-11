import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.event.MouseInputListener;

import ky.Asset;
import ky.KYscreen;
import ky.Text;
import ky.Vector2D;

public class Main extends KYscreen implements MouseInputListener {

    static int width = 0;
    static int height = 0;

    public static void main (String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDevice = gEnvironment.getDefaultScreenDevice();
        width = gDevice.getDisplayMode().getWidth();
        height = gDevice.getDisplayMode().getHeight();
        new Main();
    }

    int frames=0;
    long lastSec = System.currentTimeMillis();
    Text fpsText;
    Text scoreText;
    Asset background;
    AudioAsset musicAsset;
    AudioAsset sfxAsset;
    int score=0;

    final int maxBad=5;
    final int maxGood=10;
    static int cS=120;
    int curBad=0;
    int curGood=0;
    Random randX;
    Random randY;
    Random cSRand;

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

        musicAsset = new AudioAsset(
            "assets/Chopin - Nocturne op9 No2.wav",
            new String[]{
                "assets/music-1.png",
                "assets/music0.png",
            },
            new Vector2D(width-75/2, 75/2)
        );
        musicAsset.setVisible(true);
        musicAsset.getAudioPlayer().setLoop(true);
        musicAsset.getAudioPlayer().setTime(0,2,0);
        musicAsset.getAudioPlayer().play();
        add(musicAsset);

        sfxAsset = new AudioAsset(
            "assets/click.wav",
            new String[] {
                "assets/sound-1.png",
                "assets/sound0.png",
                "assets/sound1.png",
                "assets/sound2.png",
            },
            new Vector2D(width-musicAsset.getWidth()-75/2, 75/2)
        );
        sfxAsset.setVisible(true);
        add(sfxAsset);

        fpsText = new Text("FPS: ?", new Font ("Arial", Font.PLAIN, 30), Color.black, new Vector2D(100, 15), 200, 30, 2);
        fpsText.setVisible(true);
        add(fpsText);

        scoreText = new Text("Score: 0", new Font ("Arial", Font.PLAIN, 30), Color.black, new Vector2D(100, 45), 200, 30, 2);
        scoreText.setVisible(true);
        add(scoreText);

        cursor = new Asset("assets/cursor.png", new Vector2D(0,0), 3);
        cursor.setVisible(true);
        add(cursor);

        background = new Asset("assets/background.png", new Vector2D(width/2,height/2), 0);
        background.setVisible(true);
        add(background);

        addMouseListener(this);
        addMouseMotionListener(this);

        randX = new Random();
        randY = new Random();
        cSRand = new Random();

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
            cS = cSRand.nextInt(40)+80;
            circles.add(new Circle(new Vector2D((randX.nextDouble(width-cS)+cS/2), (randY.nextDouble(height-cS)+cS/2)), cS,cS, "good"));
            add(circles.get(circles.size()-1));
            circles.get(circles.size()-1).setVisible(true);
            curGood++;
        }
        for (int c=curBad; c < maxBad; c++) {
            cS = cSRand.nextInt(40)+80;
            circles.add(new Circle(new Vector2D((randX.nextDouble(width-cS)+cS/2), (randY.nextDouble(height-cS)+cS/2)), cS,cS, "bad"));
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
                sfxAsset.getAudioPlayer().setTime(0, 0, 35);
                sfxAsset.getAudioPlayer().play();
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
                sfxAsset.increaseVolume();
                break;
            case KeyEvent.VK_DOWN:
                sfxAsset.decreaseVolume();
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