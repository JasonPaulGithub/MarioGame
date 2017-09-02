package Screens;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MarioGame;

public class PlayScreen implements Screen {
    public MarioGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer box2dr;

    public PlayScreen(MarioGame game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MarioGame.V_WIDTH, MarioGame.V_HEIGHT, gameCam);
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("core/assets/map/level.tmx");
        renderer = new OrthoCachedTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0,0),true);
        box2dr = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixDef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fixDef.shape=shape;
            body.createFixture(fixDef);
        }

        //pipes
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fixDef.shape=shape;
            body.createFixture(fixDef);
        }

        //coins
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fixDef.shape=shape;
            body.createFixture(fixDef);
        }

        //bricks
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
            body=world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fixDef.shape=shape;
            body.createFixture(fixDef);
        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float deltaTime) {
        if (Gdx.input.isTouched()) gameCam.position.x += 100 * deltaTime;
    }

    public void update(float deltaTime) {
        handleInput(deltaTime);
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(gameCam);
        renderer.render();

        box2dr.render(world,gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
