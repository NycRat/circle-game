import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import ky.Asset;
import ky.AudioPlayer;
import ky.KYscreen;
import ky.Text;
import ky.Vector2D;

public class Main extends KYscreen implements MouseInputListener {
    public static void main (String[] args) {
        new Main();
    }

    AudioPlayer backgroundMusic;
    AudioPlayer clickSound;
    int frames=0;
    long lastSec = System.currentTimeMillis();
    Text fpsText;
    static int width = 1920;
    static int height = 1080;
    Asset cursor;

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

        clickSound = new AudioPlayer("assets/click.wav");
        cursor = new Asset("assets/cursor.png", new Vector2D(0, 0), 3);
        cursor.setVisible(true);
        add(cursor);

        addMouseListener(this);
        addMouseMotionListener(this);

        setDebugMode(false);
    }

    @Override
    public void update() {

        if (System.currentTimeMillis() >= lastSec+1000) {
            fpsText.setText("FPS: " + frames);
            frames=0;
            lastSec = System.currentTimeMillis();
        }




        frames++;
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
    public void mouseMoved(MouseEvent e) {
        cursor.setPos(e.getX()-12, e.getY()-12);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        cursor.setPos(e.getX()-12, e.getY()-12);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickSound.setTime(0,0,35);
        clickSound.play();
        
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