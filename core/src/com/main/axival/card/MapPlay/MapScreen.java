package com.main.axival.card.MapPlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.main.axival.card.CardPlay;
import com.main.axival.card.MapPlay.Hero.State;

public class MapScreen implements Screen {
    //Vision variables
    private CardPlay game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //map variables
    private Texture map;

    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap hexes;
    private HexagonalTiledMapRenderer renderer;

    // Width and Height from Map Properties variables
    MapProperties prop;
    public int mapWidth;
    public int mapHeight;
    public int tilePixelWidth;
    public int tilePixelHeight;
    public int mapPixelWidth;
    public int mapPixelHeight;

    //font variable
    private BitmapFont font;

    //Coordinates variables
    private Vector3 screenCoordinates;
    private Vector2 des;

    //Hero variables
    private Hero player;

    MoveToAction action;

    //Board variables
    public Board board;

    public MapScreen(CardPlay game) {
        this.game = game;
        //create cam used to follow hero through cam world
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite
        gamePort = new FitViewport(CardPlay.V_WIDTH , CardPlay.V_HEIGHT, gamecam);

        //create map
        map = new Texture("map-imgs/no-grid-map.png");

        //Load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        hexes = mapLoader.load("tiled-maps/map.tmx");
        renderer = new HexagonalTiledMapRenderer(hexes);

        // Get Width and Height from Map Properties
        prop = hexes.getProperties();
        mapWidth = prop.get("width", Integer.class);
        mapHeight = prop.get("height", Integer.class);
        tilePixelWidth = prop.get("tilewidth", Integer.class);
        tilePixelHeight = prop.get("tileheight", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;

        //create board
        board = new Board(this);

        //create coordinate
        screenCoordinates = new Vector3();
        des = new Vector2(0, 0);

        //create and set font
        font = new BitmapFont();
        font.setColor(255, 255, 255, 1);

        //create hero and set spritesheet
        player = new Hero(this, 0, 0);
        player.setAtlas("hero-imgs/spritesheets/myspritesheet.atlas");
        player.setImg("hero-imgs/spritesheets/myspritesheet.png");
        player.setCoordinates(board.map[0][0].corX , board.map[0][0].corY); // x+w , y+0.75h

        //initially set our gamcam to be centered correctly at the start of map
        gamecam.position.set(mapPixelWidth / 2 + 12 , mapPixelHeight / 2 - 77, 0);
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        //controll camera
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
//            gamecam.position.x += 100 * dt;
//        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
//            gamecam.position.x -= 100 * dt;
//        if (Gdx.input.isKeyPressed(Input.Keys.UP))
//            gamecam.position.y += 100 * dt;
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
//            gamecam.position.y -= 100 * dt;

        //Right-move control
        if (Gdx.input.isKeyPressed(Input.Keys.D) && player.getWalking() == 0) {
            player.setDes(board.map[player.row][player.col+1].corX,
                    board.map[player.row][player.col+1].corY);
            player.setCurrentState(State.WALKING);
            player.setWalking(1);
        }
        else if (player.getWalking() == 1) {
            if (player.getCoordinates().x < player.getDes().x) {
                player.setFacing(State.RIGHT);
                player.setCurrentState(State.WALKING);
                player.setCoordinates(player.getCoordinates().x += (Math.sqrt(3)/2) * tilePixelHeight * dt,
                        player.getCoordinates().y);
            }
            else {
                player.setRowCol(player.row, player.col+1);
                player.setWalking(0);
                player.setCurrentState(State.STANDING);
            }
        }

        //Left-move control
        if (Gdx.input.isKeyPressed(Input.Keys.A) && player.getWalking() == 0) {
            player.setDes(board.map[player.row][Math.max(0, player.col-1)].corX,
                    board.map[player.row][Math.max(0, player.col-1)].corY);
            player.setCurrentState(State.WALKING);
            player.setWalking(2);
        }
        else if (player.getWalking() == 2) {
            if (player.getCoordinates().x > player.getDes().x) {
                player.setFacing(State.LEFT);
                player.setCurrentState(State.WALKING);
                player.setCoordinates(player.getCoordinates().x -= (Math.sqrt(3)/2) * tilePixelHeight * dt,
                        player.getCoordinates().y);
            }
            else {
                player.setRowCol(player.row, Math.max(0, player.col-1));
                player.setWalking(0);
                player.setCurrentState(State.STANDING);
            }
        }

        //Up-Right combination move
        if (Gdx.input.isKeyPressed(Input.Keys.E) && player.getWalking() == 0) {
            if (player.row%2 == 0) {
                player.setDes(board.map[Math.max(0, player.row-1)][player.col+1].corX,
                        board.map[Math.max(0, player.row-1)][player.col+1].corY);
            }
            else {
                player.setDes(board.map[Math.max(0, player.row-1)][player.col].corX,
                        board.map[Math.max(0, player.row-1)][player.col].corY);
            }
            player.setCurrentState(State.WALKING);
            player.setWalking(3);
        }
        else if (player.getWalking() == 3) {
            if (player.getCoordinates().x < player.getDes().x || player.getCoordinates().y < player.getDes().y) {
                player.setFacing(State.RIGHT);
                player.setCurrentState(State.WALKING);
                if (player.getCoordinates().x < player.getDes().x) {
                    player.setCoordinates(player.getCoordinates().x += (Math.sqrt(3)/2) * tilePixelHeight * dt,
                            player.getCoordinates().y);
                }
                if (player.getCoordinates().y < player.getDes().y) {
                    player.setCoordinates(player.getCoordinates().x,
                            player.getCoordinates().y += tilePixelHeight * dt);
                }
            }
            else if (player.getCoordinates().x >= player.getDes().x && player.getCoordinates().y >= player.getDes().y){
                if (player.row%2 == 0) {
                    player.setRowCol(Math.max(0, player.row-1), player.col+1);
                }
                else {
                    player.setRowCol(Math.max(0, player.row-1), player.col);
                }
                player.setWalking(0);
                player.setCurrentState(State.STANDING);
            }
        }

        //Down-Right combination move
        if (Gdx.input.isKeyPressed(Input.Keys.C) && player.getWalking() == 0) {
            if (player.row%2 == 0) {
                player.setDes(board.map[player.row+1][player.col+1].corX, board.map[player.row+1][player.col+1].corY);
            }
            else {
                player.setDes(board.map[player.row+1][player.col].corX, board.map[player.row+1][player.col].corY);
            }
            player.setCurrentState(State.WALKING);
            player.setWalking(4);
        }
        else if (player.getWalking() == 4) {
            if (player.getCoordinates().x < player.getDes().x || player.getCoordinates().y > player.getDes().y) {
                player.setFacing(State.RIGHT);
                player.setCurrentState(State.WALKING);
                if (player.getCoordinates().x < player.getDes().x) {
                    player.setCoordinates(player.getCoordinates().x += (Math.sqrt(3)/2) * tilePixelHeight * dt,
                            player.getCoordinates().y);
                }
                if (player.getCoordinates().y > player.getDes().y) {
                    player.setCoordinates(player.getCoordinates().x,
                            player.getCoordinates().y -= tilePixelHeight * dt);
                }
            }
            else if (player.getCoordinates().x >= player.getDes().x && player.getCoordinates().y <= player.getDes().y){
                if (player.row%2 == 0) {
                    player.setRowCol(player.row+1, player.col+1);
                }
                else {
                    player.setRowCol( player.row+1, player.col);
                }
                player.setWalking(0);
                player.setCurrentState(State.STANDING);
            }
        }

        //Up-Left combination move
        if (Gdx.input.isKeyPressed(Input.Keys.Q) && player.getWalking() == 0) {
            if (player.row%2 == 0) {
                player.setDes(board.map[player.row-1][player.col].corX, board.map[player.row-1][player.col].corY);
            }
            else {
                player.setDes(board.map[player.row-1][player.col-1].corX, board.map[player.row-1][player.col-1].corY);
            }
            player.setCurrentState(State.WALKING);
            player.setWalking(5);
        }
        else if (player.getWalking() == 5) {
            if (player.getCoordinates().x > player.getDes().x || player.getCoordinates().y < player.getDes().y) {
                player.setFacing(State.LEFT);
                player.setCurrentState(State.WALKING);
                if (player.getCoordinates().x > player.getDes().x) {
                    player.setCoordinates(player.getCoordinates().x -= (Math.sqrt(3)/2) * tilePixelHeight * dt,
                            player.getCoordinates().y);
                }
                if (player.getCoordinates().y < player.getDes().y) {
                    player.setCoordinates(player.getCoordinates().x,
                            player.getCoordinates().y += tilePixelHeight * dt);
                }
            }
            else if (player.getCoordinates().x <= player.getDes().x && player.getCoordinates().y >= player.getDes().y){
                if (player.row%2 == 0) {
                    player.setRowCol(player.row-1, player.col);
                }
                else {
                    player.setRowCol( player.row-1, player.col-1);
                }
                player.setWalking(0);
                player.setCurrentState(State.STANDING);
            }
        }

        //Down-Left combination move
        if (Gdx.input.isKeyPressed(Input.Keys.Z) && player.getWalking() == 0) {
            if (player.row%2 == 0) {
                player.setDes(board.map[player.row+1][player.col].corX, board.map[player.row+1][player.col].corY);
            }
            else {
                player.setDes(board.map[player.row+1][player.col-1].corX, board.map[player.row+1][player.col-1].corY);
            }
            player.setCurrentState(State.WALKING);
            player.setWalking(6);
        }
        else if (player.getWalking() == 6) {
            if (player.getCoordinates().x > player.getDes().x || player.getCoordinates().y > player.getDes().y) {
                player.setFacing(State.LEFT);
                player.setCurrentState(State.WALKING);
                if (player.getCoordinates().x > player.getDes().x) {
                    player.setCoordinates(player.getCoordinates().x -= (Math.sqrt(3)/2) * tilePixelHeight * dt,
                            player.getCoordinates().y);
                }
                if (player.getCoordinates().y > player.getDes().y) {
                    player.setCoordinates(player.getCoordinates().x,
                            player.getCoordinates().y -= tilePixelHeight * dt);
                }
            }
            else if (player.getCoordinates().x <= player.getDes().x && player.getCoordinates().y <= player.getDes().y) {
                if (player.row%2 == 0) {
                    player.setRowCol(player.row+1, player.col);
                }
                else {
                    player.setRowCol( player.row+1, player.col-1);
                }
                player.setWalking(0);
                player.setCurrentState(State.STANDING);
            }
        }
    }

    public void update(float dt) {
        //handle user input first
        handleInput(dt);

        //update player
        player.update(dt);

        //update our gamecam with correct coordinates after changes
        gamecam.update();

        //tell our renderer to draw only what our camera can see in our game world
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // get coordinates
        screenCoordinates.set(Gdx.input.getX(), Gdx.input.getY(), 0);

        game.batch.begin();

        //render map
        game.batch.draw(map, 0, 0, CardPlay.V_WIDTH, CardPlay.V_HEIGHT );

        //render screen coordinates
        font.draw(game.batch, "Screen Coordinates", 155, 660);
        font.draw(game.batch, (int) screenCoordinates.x + " , " + (int) screenCoordinates.y, 190, 635);

        //render hero coordinates
        font.draw(game.batch, "Hero Coordinates", 400, 660);
        font.draw(game.batch, (int) player.getCoordinates().x + " , " + (int) player.getCoordinates().y, 435, 635);

        //render destination coordinates
        font.draw(game.batch, "Destination Coordinates", 600, 660);
        font.draw(game.batch, (int) player.getDes().x + " , " + (int) player.getDes().y, 635, 635);

        //render walk coordinates
        font.draw(game.batch, "Status", 800, 660);
        font.draw(game.batch, Integer.toString(player.getWalking()), 820, 635);

        //row-col coordinates
        font.draw(game.batch, "Row-Column", 1000, 660);
        font.draw(game.batch, player.row + " , " + player.col, 1020, 635);

        //animation
//        game.batch.draw(player.action().getKeyFrame(player.getElapsedTime(), true),
//                player.getCoordinates().x, player.getCoordinates().y);

        game.batch.end();

        //render our game map
        renderer.render();

        //render hero

        game.batch.begin();
        if (player.facing.compareTo(State.RIGHT) == 0) {
            game.batch.draw(player.action().getKeyFrame(player.getElapsedTime(), true),
                    player.getCoordinates().x + (player.action().getKeyFrame(player.getElapsedTime(), true).getRegionWidth())
                    , player.getCoordinates().y,
                    -(player.action().getKeyFrame(player.getElapsedTime(), true).getRegionWidth()),
                    player.action().getKeyFrame(player.getElapsedTime(), true).getRegionHeight());
        }
        else {
            game.batch.draw(player.action().getKeyFrame(player.getElapsedTime(), true),
                    player.getCoordinates().x - 10, player.getCoordinates().y);
        }

        game.batch.end();
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
