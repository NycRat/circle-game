import ky.Vector2D;
import ky.Asset;

public class Circle extends Asset {

    public Circle(Vector2D position, int width, int height, String name) {
        super(new String[]{"assets/circle.png", "assets/circleB.png"}, position, width, height, 1, name);
        if (name.equals("bad")) {
            setImageIndex(1);
        }
    }

    public boolean inCircle (Vector2D position) {
        return Math.sqrt(Math.pow(position.getX() - getX(),2) + Math.pow(position.getY() - getY(), 2)) <= getHeight()/2;
    }

    // circleAsset = new Asset("assets/circle.png", new Vector2D(0, 0), 2);
        // circleAsset.setVisible(true);
        // add(circleAsset);
    
}
