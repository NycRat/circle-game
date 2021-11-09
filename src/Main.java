import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import ky.AudioPlayer;
import ky.KYscreen;
import ky.Text;
import ky.Vector2D;

public class Main extends KYscreen {
    public static void main (String[] args) {
        new Main();
    }

    AudioPlayer backgroundMusic;
    int frames=0;
    long lastSec = System.currentTimeMillis();
    Text fpsText;
    static int width = 1920;
    static int height = 1080;

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

        setDebugMode(true);
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

}