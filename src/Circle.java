import ky.Vector2D;
import ky.Asset;

public class Circle extends Asset {

    public Circle(Vector2D position, int width, int height) {
        super("assets/circle.png", position, width, height, 2);
    }

    // circleAsset = new Asset("assets/circle.png", new Vector2D(0, 0), 2);
        // circleAsset.setVisible(true);
        // add(circleAsset);

    public int size () {
        return getHeight();
    }
    
    public void delete () {
        this.setVisible(false);
    }
    
}
