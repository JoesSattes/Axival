package com.main.axival.card.MapPlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.main.axival.card.CardPlay;
import com.main.axival.card.MapPlay.Hero.State;

public class MapScreen implements Screen {
    private CardPlay game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private Texture map;
    private TmxMapLoader mapLoader;
    private TiledMap hexes;
    private HexagonalTiledMapRenderer renderer;
    private BitmapFont font;
    private Vector3 screenCoordinates;
    private Hero player;

    public MapScreen(CardPlay game) {
        /*
        this.game = game;
        this.gamecam = new OrthographicCamera();
        this.gamePort = new FitViewport(1280.0F, 720.0F, this.gamecam);
        this.hud = new Hud(game.batch);
        this.map = new Texture("map-imgs/no-grid-map.png");
        this.mapLoader = new TmxMapLoader();
        this.hexes = this.mapLoader.load("tiled-maps/map.tmx");
        this.renderer = new HexagonalTiledMapRenderer(this.hexes);
        this.screenCoordinates = new Vector3();
        this.font = new BitmapFont();
        this.font.setColor(255.0F, 255.0F, 255.0F, 1.0F);
        MapProperties prop = this.hexes.getProperties();
        int mapWidth = (Integer) prop.get("width", Integer.class);
        int mapHeight = (Integer) prop.get("height", Integer.class);
        int tilePixelWidth = (Integer) prop.get("tilewidth", Integer.class);
        int tilePixelHeight = (Integer) prop.get("tileheight", Integer.class);
        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;
        this.player = new Hero(this);
        this.player.setAtlas("hero-imgs/spritesheets/myspritesheet.atlas");
        this.player.setImg("hero-imgs/spritesheets/myspritesheet.png");
        this.player.setCoordinates((float) (tilePixelWidth - 12), (float) (Math.abs(2 * tilePixelHeight - mapPixelHeight) + 5));
        this.gamecam.position.set((float) (mapPixelWidth / 2 + 12), (float) (mapPixelHeight / 2 - 77), 0.0F);
        */

        this.game = game;
        //create cam used to follow hero through cam world
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite
        gamePort = new FitViewport(SampleMain.V_WIDTH , SampleMain.V_HEIGHT, gamecam);

        //create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        //create map
        map = new Texture("map-imgs/no-grid-map.png");

        //Load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        hexes = mapLoader.load("tiled-maps/map.tmx");
        renderer = new HexagonalTiledMapRenderer(hexes);

        //create coordinate
        screenCoordinates = new Vector3();

        //create and set font
        font = new BitmapFont();
        font.setColor(255, 255, 255, 1);

        // Get Width and Height from Map Properties
        MapProperties prop = hexes.getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;

        //create hero and set spritesheet
        player = new Hero(this);
        player.setAtlas("hero-imgs/spritesheets/myspritesheet.atlas");
        player.setImg("hero-imgs/spritesheets/myspritesheet.png");
        player.setCoordinates(tilePixelWidth - 12, Math.abs(2*tilePixelHeight-mapPixelHeight) + 5);

        //initially set our gamcam to be centered correctly at the start of map
        gamecam.position.set(mapPixelWidth / 2 + 12, mapPixelHeight / 2 - 77, 0);
    }

    public MapScreen(SampleMain sampleMain) {
    }

    public void handleInput(float dt) {
        Hero var10000;
        Vector2 var10001;
        if (Gdx.input.isKeyPressed(22)) {
            this.player.setFacing(State.RIGHT);
            this.player.setCurrentState(State.WALKING);
            var10000 = this.player;
            var10001 = this.player.getCoordinates();
            var10000.setCoordinates(var10001.x += 75.0F * dt, this.player.getCoordinates().y);
        }

        if (Gdx.input.isKeyPressed(21)) {
            this.player.setFacing(State.LEFT);
            this.player.setCurrentState(State.WALKING);
            var10000 = this.player;
            var10001 = this.player.getCoordinates();
            var10000.setCoordinates(var10001.x -= 75.0F * dt, this.player.getCoordinates().y);
        }

        Vector2 var10002;
        float var3;
        if (Gdx.input.isKeyPressed(Input.Keys.U)) {
            this.player.setCurrentState(State.WALKING);
            var10000 = this.player;
            var3 = this.player.getCoordinates().x;
            var10002 = this.player.getCoordinates();
            var10000.setCoordinates(var3, var10002.y += 95.0F * dt);
        }

        if (Gdx.input.isKeyPressed(20)) {
            this.player.setCurrentState(State.WALKING);
            var10000 = this.player;
            var3 = this.player.getCoordinates().x;
            var10002 = this.player.getCoordinates();
            var10000.setCoordinates(var3, var10002.y -= 95.0F * dt);
        }

        if (Gdx.input.isKeyPressed(54)) {
            this.player.setCurrentState(State.ATTACKING1);
        }

        if (Gdx.input.isKeyPressed(52)) {
            this.player.setCurrentState(State.ATTACKING2);
        }

    }

    public void update(float dt) {
        this.handleInput(dt);
        this.player.update(dt);
        this.gamecam.update();
        this.renderer.setView(this.gamecam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        this.update(delta);
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.screenCoordinates.set((float)Gdx.input.getX(), (float)Gdx.input.getY(), 0.0F);
        this.game.batch.begin();
        this.game.batch.draw(this.map, 0.0F, 0.0F, 1280.0F, 720.0F);
        this.font.draw(this.game.batch, "Screen Coordinates", 155.0F, 660.0F);
        this.font.draw(this.game.batch, (int)this.screenCoordinates.x + " , " + (int)this.screenCoordinates.y, 190.0F, 635.0F);
        this.game.batch.end();
        this.renderer.render();
        this.game.batch.setProjectionMatrix(this.hud.stage.getCamera().combined);
        this.hud.stage.draw();
        this.game.batch.begin();
        if (this.player.facing.compareTo(State.RIGHT) == 0) {
            this.game.batch.draw((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true), this.player.getCoordinates().x + (float)((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true)).getRegionWidth(), this.player.getCoordinates().y, (float)(-((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true)).getRegionWidth()), (float)((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true)).getRegionHeight());
        } else {
            this.game.batch.draw((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true), this.player.getCoordinates().x, this.player.getCoordinates().y);
        }

        this.game.batch.end();
    }

    public void runner(){
        if (this.player.facing.compareTo(State.RIGHT) == 0) {
            this.game.batch.draw((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true), this.player.getCoordinates().x + (float)((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true)).getRegionWidth(), this.player.getCoordinates().y, (float)(-((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true)).getRegionWidth()), (float)((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true)).getRegionHeight());
        } else {
            this.game.batch.draw((TextureRegion)this.player.action().getKeyFrame(this.player.getElapsedTime(), true), this.player.getCoordinates().x, this.player.getCoordinates().y);
        }
    }

    public void hudRun(){
        this.hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
