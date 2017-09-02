package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MarioGame;

/**
 * Created by admin on 02.09.2017.
 */
public class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds)
    {
        this.world= world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(
                (bounds.getX() + bounds.getWidth() / 2) / MarioGame.PPM,
                (bounds.getY() + bounds.getHeight() / 2) / MarioGame.PPM
        );
        body = world.createBody(bodyDef);
        shape.setAsBox(
                (bounds.getWidth() / 2) / MarioGame.PPM,
                (bounds.getHeight() / 2) / MarioGame.PPM
        );
        fixDef.shape = shape;
        body.createFixture(fixDef);
    }

}
