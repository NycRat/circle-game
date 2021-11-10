import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class Cursor extends Asset {

    private static int cursorSize=40;

    public Cursor() {
        super("assets/cursor.png", new Vector2D(-cursorSize/4, -cursorSize/4), 3);
        setVisible(true);
        // cursorAsset = new Asset
        // cursorAsset.setVisible(true);
        
        // setVisible(true);
        // add(cursorAsset);

    }

    public void update (double deltaTime, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(KeyEvent.VK_L)) {
        }
    }

    public boolean inCircle (double x, double y, int size) {
        return Math.sqrt(Math.pow(getX() - x,2) + Math.pow(getY() - y, 2)) <= size/2;
    }

    
}
