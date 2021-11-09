import ky.AnimationAsset;
import ky.CollisionEntity;

public class Circle extends CollisionEntity {

    private AnimationAsset circleAsset;

    public Circle(double x, double y, int collisionBoxWidth, int collisionBoxHeight, int layer, String name) {
        super(x, y, collisionBoxWidth, collisionBoxHeight, layer, name);
    }

    public void start () {
    }

    public void update () {

    }
    
}
