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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.main.axival.card.*;
import com.main.axival.card.MapPlay.MapScreen;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


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

    private int[] statusPhase;

    private long startTime = 0;
    private int countInLoop = 0;


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

        //set value from network
        this.statusPhase = new int[10];
        statusInput();

        //check phase
        phaseAll();
    }

    @Override
    public void show() {
        System.out.println("screen");
    }

    public void setCardHandR(int indexCard){
        stage.addActor(cardDeck[indexCard]);
    }

    public void cardHandAction(int delay){
        System.out.println("Size Count Hand : "+randomCard.sizeCountCardInHand());
        if(randomCard.sizeCountCardInHand()==1){
            cardAction.cardHandActionFirst(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==2){
            cardAction.cardHandActionSecond(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==3){
            cardAction.cardHandActionThirst(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==4){
            cardAction.cardHandActionFourth(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==5){
            cardAction.cardHandActionFifth(currentCard, delay);
        }
        else if(randomCard.sizeCountCardInHand()==6){
            cardAction.cardHandActionFull(currentCard, delay);
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
        mapScreen.update(delta);
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

//            setCardHandR(currentCard);
//            randomCard.setCardInHandIndex(currentCard);
//            cardHandAction(0);
//            Timer.schedule(new Timer.Task() {
//                @Override
//                public void run() {
//                    currentCard++;
//                    setCardHandR(currentCard);
//                    randomCard.setCardInHandIndex(currentCard);
//                    cardHandAction(0);
//                    if (currentCard==9){
//                        Timer.instance().stop();
//                    }
//                }
//            }, 3, 2);
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
        //Gdx.app.log("Mouse", "Down");
        Vector2 rowcol = mapScreen.click.getRowCol(screenX, Math.abs(mapScreen.mapPixelHeight - screenY));
        List<Vector2> area = new LinkedList<Vector2>();
        area.addAll(mapScreen.board.getArea(mapScreen.player.col, mapScreen.player.row, mapScreen.player.walk));
        if (!mapScreen.board.map[(int) rowcol.y][(int) rowcol.x].isObstacle() && mapScreen.walker.getRoute() == 0
                && area.contains(rowcol)) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && mapScreen.walker.isRouting() == 0) {
                mapScreen.walker.setRouting(1);
                System.out.println("Mouse clicked!");
                float x = Gdx.input.getX();
                float y = Math.abs(mapScreen.mapPixelHeight - Gdx.input.getY());
                mapScreen.path = new LinkedList<Vector2>();
                Vector2 goal = mapScreen.click.getRowCol(x, y);
                System.out.println("Column-Row = " + goal.x + "," + goal.y);
                mapScreen.path.addAll(mapScreen.board.getPath(mapScreen.player.getRowCol(), goal));
                mapScreen.walker.setPath(mapScreen.player.getRowCol(), mapScreen.path);
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

    //Phase control
    public void statusInput(){
        statusPhase[0] = 0;
        statusPhase[1] = 0;
        statusPhase[2] = 0;
        statusPhase[3] = 0;
        statusPhase[4] = 0;
        statusPhase[5] = 0;
        statusPhase[6] = 0;
        statusPhase[7] = 0;
        statusPhase[8] = 0;
        statusPhase[9] = 0;
    }

    public void phaseAll(){
        if (statusPhase[2]==0){
            phaseInTurn();
        }
        else{
            phaseOutTurn();
        }
    }

    public void phaseInTurn(){
        if(statusPhase[3]==0){
            drawPhase();
        }
        else if(statusPhase[3]==1 || statusPhase[3]==3){
            actionPhase();
        }
        else if(statusPhase[3]==2){
            travelPhase();
        }
        else if(statusPhase[3]==4){
            endPhase();
        }
    }

    public void phaseOutTurn(){
        waitPhase();
        if (statusPhase[6]==1){
            chainPhase();
        }
    }

    public void drawPhase(){
        cardAction.setPopupOff(true);
        if (statusPhase[0]==0){
            setCardHandR(currentCard);
            randomCard.setCardInHandIndex(currentCard);
            cardHandAction(0);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    currentCard++;
                    setCardHandR(currentCard);
                    randomCard.setCardInHandIndex(currentCard);
                    cardHandAction(0);
                    if (currentCard==9){
                        Timer.instance().stop();
                    }
                }
            }, 3, 2);
        }
        else{
            currentCard++;
            if (currentCard<maxCard){
                setCardHandR(currentCard);
                randomCard.setCardInHandIndex(currentCard);
            }
            if (currentCard<maxCard){
                cardHandAction(0);
            }
        }
    }

    public void actionPhase(){
        cardAction.setPopupOff(false);
    }

    public void travelPhase(){
        cardAction.setPopupOff(true);
    }


    public void endPhase(){
        cardAction.setPopupOff(false);
    }

    public void waitPhase(){
        //can't do anything about other player but game is show you screen real time


    }

    public void chainPhase(){
        //show you when other player do something about you

    }
}
