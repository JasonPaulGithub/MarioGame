package Tools;

import Sprites.Brick;
import Sprites.Coin;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MarioGame;

public class B2WorldCreator {
    BodyDef bodyDef = new BodyDef();
    PolygonShape shape = new PolygonShape();
    FixtureDef fixDef = new FixtureDef();
    Body body;

    public B2WorldCreator(World world, TiledMap map) {
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(
                    (rect.getX() + rect.getWidth() / 2) / MarioGame.PPM,
                    (rect.getY() + rect.getHeight() / 2) / MarioGame.PPM
            );
            body = world.createBody(bodyDef);
            shape.setAsBox(
                    (rect.getWidth() / 2) / MarioGame.PPM,
                    (rect.getHeight() / 2) / MarioGame.PPM
            );
            fixDef.shape = shape;
            body.createFixture(fixDef);
        }

        //pipes
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(
                    (rect.getX() + rect.getWidth() / 2) / MarioGame.PPM,
                    (rect.getY() + rect.getHeight() / 2) / MarioGame.PPM
            );
            body = world.createBody(bodyDef);
            shape.setAsBox(
                    (rect.getWidth() / 2) / MarioGame.PPM,
                    (rect.getHeight() / 2) / MarioGame.PPM
            );
            fixDef.shape = shape;
            body.createFixture(fixDef);
        }

        //coins
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {

            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(world,map,rect);
        }

        //bricks
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(world,map,rect);
        }

    }

}
