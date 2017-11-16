package com.main.axival.card.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.ai.btree.decorator.Repeat;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.main.axival.card.CardAction;
import com.main.axival.card.CardPlay;
import com.main.axival.card.MapPlay.MapScreen;
import com.main.axival.card.RandomCard;
import com.main.axival.card.UIplay;

import java.util.LinkedList;
import java.util.List;


public class ScreenPlay implements Screen, InputProcessor{
    public Stage stage;

    private ParticleEffect prototype;
    private ParticleEffectPool pool;
    private Array<ParticleEffectPool.PooledEffect> effect;

    public RandomCard randomCard;


    public boolean solveUp, solveDown, solveLeft, solveRight;

    public Image[] cardDeck;
    private int maxCard=10, currentCard=0;

    public int cardInHand=0;
    public float cardCountPosY1;

    private CardAction cardAction;

    private UIplay uIplay;

    private final CardPlay cardPlay;

    private MapScreen mapScreen;


    public ScreenPlay(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        this.cardCountPosY1 = 0;
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        Pixmap pm = new Pixmap(Gdx.files.internal("cursorImage2.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth()/2, pm.getHeight()/2));
        pm.dispose();

        prototype = new ParticleEffect();
        prototype = cardPlay.assetManager.get("effect01.party");
        prototype.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        prototype.start();

        randomCard = new RandomCard(cardPlay);

        this.cardDeck = randomCard.allCardDeck(maxCard);
        this.cardAction = new CardAction(this);
        this.uIplay = new UIplay(this.cardPlay);
        this.mapScreen = new MapScreen(this.cardPlay);
    }

    @Override
    public void show() {
        System.out.println("screen");
    }

    public void setCardHandR(int indexCard){
        stage.addActor(cardDeck[indexCard]);
    }

    public void cardHandAction(){
        System.out.println("Size Count Hand : "+randomCard.sizeCountCardInHand());
        if(randomCard.sizeCountCardInHand()==1){
            cardAction.cardHandActionFirst(currentCard);
        }
        else if(randomCard.sizeCountCardInHand()==2){
            cardAction.cardHandActionSecond(currentCard);
        }
        else if(randomCard.sizeCountCardInHand()==3){
            cardAction.cardHandActionThirst(currentCard);
        }
        else if(randomCard.sizeCountCardInHand()==4){
            cardAction.cardHandActionFourth(currentCard);
        }
        else if(randomCard.sizeCountCardInHand()==5){
            cardAction.cardHandActionFifth(currentCard);
        }
        else if(randomCard.sizeCountCardInHand()==6){
            cardAction.cardHandActionFull(currentCard);
            System.out.println("Out Hand");
        }
    }


    @Override
    public void render(float delta) {
        if(solveUp) {
            /*
            cardHandR.getChildren().get(0).addAction(Actions.parallel(Actions.moveTo(200, 0, 5),
                    Actions.rotateBy(90, 5)));*/
            //cardHandAction();
        }
        if(solveLeft){
            //cardHandR.getChildren().get(0).addAction(Actions.sequence(Actions.moveTo(1200, 500)));
        }
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        cardPlay.batch.setProjectionMatrix(cardPlay.camera.combined);
        mapScreen.render(delta);
        cardPlay.batch.begin();
        uIplay.runningDraw();
        cardPlay.bitmapFont.draw(cardPlay.batch, "Screen: Playing..,", 100, 100);
        prototype.draw(cardPlay.batch);
        cardPlay.batch.end();
        stage.draw();
        if(prototype.isComplete()){
            prototype.reset();
        }
    }

    public void update(float delta)
    {
//        mapScreen.update(delta);
        stage.act(delta);
        prototype.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        mapScreen.resize(width, height);
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode== Input.Keys.UP) {
            solveUp = true;
        }
        if (keycode== Input.Keys.DOWN){
            solveDown = true;
        }
        if (keycode== Input.Keys.LEFT){
            solveLeft = true;
        }
        if (keycode== Input.Keys.RIGHT){
            solveRight = true;
        }
        if (keycode==Input.Keys.O){
            currentCard++;
            if (currentCard<maxCard){
                setCardHandR(currentCard);
                randomCard.setCardInHandIndex(currentCard);
            }
        }
        if (keycode==Input.Keys.P && currentCard<maxCard){
            cardHandAction();
        }
        if (keycode==Input.Keys.Z) {

        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode== Input.Keys.UP) {
            solveUp = false;
        }
        if (keycode== Input.Keys.DOWN){
            solveDown = false;
        }
        if (keycode== Input.Keys.LEFT){
            solveLeft = false;
        }
        if (keycode== Input.Keys.RIGHT){
            solveRight = false;
        }
        if (keycode== Input.Keys.S){

        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 rowcol = mapScreen.click.getRowCol(screenX, Math.abs(mapScreen.mapPixelHeight - screenY));
        List<Vector2> area = new LinkedList<Vector2>();
        area.addAll(mapScreen.board.getOverlay(mapScreen.player[mapScreen.idx].col,
                mapScreen.player[mapScreen.idx].row, mapScreen.player[mapScreen.idx].walk));
        if (!mapScreen.board.map[(int) rowcol.y][(int) rowcol.x].isObstacle() && mapScreen.walker.getRoute() == 0
                && area.contains(rowcol)) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mapScreen.walker.isRouting() == 0) {
                mapScreen.walker.setRouting(1);
                System.out.println("Mouse clicked!");
                float x = Gdx.input.getX();
                float y = Math.abs(mapScreen.mapPixelHeight - Gdx.input.getY());
                mapScreen.path = new LinkedList<Vector2>();
                Vector2 goal = mapScreen.click.getRowCol(x, y);

                mapScreen.player[mapScreen.idx].setSource(mapScreen.player[mapScreen.idx].col,
                        mapScreen.player[mapScreen.idx].row);

                System.out.println("Column-Row = " + goal.x + "," + goal.y);
                mapScreen.path.addAll(mapScreen.board.getPath(mapScreen.player[mapScreen.idx].getRowCol(), goal));
                mapScreen.walker.setPath(mapScreen.player[mapScreen.idx].getRowCol(), mapScreen.path);
                mapScreen.walker.routing();
            }
        }
        return false;
    }

    @Override
    public boolean touchUp ( int screenX, int screenY, int pointer, int button)
    {
        Gdx.app.log("Mouse", "Up");
        return false;
    }

    @Override
    public boolean touchDragged ( int screenX, int screenY, int pointer)
    {
        Gdx.app.log("Mouse", "Dragged");

        return false;
    }

    @Override
    public boolean mouseMoved ( int screenX, int screenY)
    {
                //Gdx.app.log("Mouse Position", screenX+", "+screenY);
                //rendexX = screenX - texture.getWidth();
                //renderY = Gdx.graphics.getHeight() - screenY - texture.getHeight();
        return false;
    }

    @Override
    public boolean scrolled ( int amount)
    {
        //Gdx.app.log("Mouse", "Scroll :"+amount);
        return false;
    }
}