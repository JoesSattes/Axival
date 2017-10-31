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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.main.axival.card.CardAction;
import com.main.axival.card.CardPlay;
import com.main.axival.card.MapPlay.MapScreen;
import com.main.axival.card.RandomCard;
import com.main.axival.card.UIplay;


public class ScreenPlay implements Screen, InputProcessor{
    private Stage stage;
    private Image cardImg;
    private TextureAtlas textureAtlas;
    private TextureRegion textureRegion;
    private Image cardImg2;
    private TextureRegion textureBg;

    private ParticleEffect prototype;
    private ParticleEffectPool pool;
    private Array<ParticleEffectPool.PooledEffect> effect;

    public RandomCard randomCard;

    public Image cardHandR;
    public HorizontalGroup cardHandL;

    public boolean solveUp, solveDown, solveLeft, solveRight;

    private int countCard=0;
    public Image[] cardDeck;
    private int maxCard=10, currentCard=0;

    public int cardInHand=0;
    public float cardCountPosY1;

    public Texture texture;
    public float rendexX, renderY;

    private CardAction cardAction;

    private UIplay uIplay;

    private MapScreen mapScreen;

    private final CardPlay cardPlay;


    public ScreenPlay(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        this.cardCountPosY1 = 0;
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        texture = new Texture("badlogic.jpg");
        rendexX = 100;
        renderY = 100;

        textureBg = new TextureRegion(cardPlay.assetManager.get("bg1.jpg", Texture.class));
        textureBg.setRegion(0,0, CardPlay.V_WIDTH,CardPlay.V_HEIGHT);

        Pixmap pm = new Pixmap(Gdx.files.internal("cursorImage2.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth()/2, pm.getHeight()/2));
        pm.dispose();

        prototype = new ParticleEffect();
        prototype = cardPlay.assetManager.get("effect01.party");
        prototype.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        prototype.start();

        randomCard = new RandomCard(cardPlay);
        //cardHandL = randomCard.cardHand(2,'l');
        //stage.addActor(cardHandL);

        this.cardDeck = randomCard.allCardDeck(maxCard);
        this.cardAction = new CardAction(this);
        this.uIplay = new UIplay(this.cardPlay);
        this.mapScreen = new MapScreen(this.cardPlay);
    }

    @Override
    public void show() {
        System.out.println("screen");
    }

    /*
    public void cardHandActionFirst(){
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/1.7f, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        cardCountPosY1 = cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getY();
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click 2: "+0);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getX(),
                        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 2");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getX(),
                        CardPlay.V_HEIGHT-cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 2");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getX(),
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getY(),
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getWidth()*.05*.2,
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(0))].getHeight()*.05*.2);
        cardInHand = 1;
    }
    public void cardHandActionSecond(){
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/1.7f, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1-1))].addAction(Actions.sequence(Actions.moveTo(1280/1.7f-50, -50, 1f)));
        cardCountPosY1 = cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getY();
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click 3: "+1);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getX(),
                        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 3");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getX(),
                        CardPlay.V_HEIGHT-cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 3");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getX(),
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getY(),
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getWidth()*.05*.2,
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(1))].getHeight()*.05*.2);
        cardInHand = 2;
    }
    public void cardHandActionThirst(){
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/1.7f+100, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2-1))].addAction(Actions.sequence(Actions.moveTo(1280/1.7f, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2-2))].addAction(Actions.sequence(Actions.moveTo(1280/1.7f-100, -50, 1f)));
        cardCountPosY1 = cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getY();
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click 4: "+2);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getX(),
                        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 4");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getX(),
                        CardPlay.V_HEIGHT-cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 4");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getX(),
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getY(),
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getWidth()*.05*.2,
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(2))].getHeight()*.05*.2);
        cardInHand = 3;
    }
    public void cardHandActionFourth() {
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280 / 1.7f + 150, -50, 2f),
                        Actions.scaleTo(.17f, .17f, 2f))));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3 - 1))].addAction(Actions.sequence(Actions.moveTo(1280 / 1.7f + 50, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3 - 2))].addAction(Actions.sequence(Actions.moveTo(1280 / 1.7f - 50, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3 - 3))].addAction(Actions.sequence(Actions.moveTo(1280 / 1.7f - 150, -50, 1f)));
        cardCountPosY1 = cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].getY();
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click 5: " + 3);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].getX(),
                        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].getY() + 100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 5");
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(3))].getX(),
                        CardPlay.V_HEIGHT - cardCountPosY1 - 50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 5");
            }
        });
        cardInHand = 4;
    }
    public void cardHandActionFifth(){
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/1.7f+200, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4-1))].addAction(Actions.sequence(Actions.moveTo(1280/1.7f+100, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4-2))].addAction(Actions.sequence(Actions.moveTo(1280/1.7f, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4-3))].addAction(Actions.sequence(Actions.moveTo(1280/1.7f-100, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4-4))].addAction(Actions.sequence(Actions.moveTo(1280/1.7f-200, -50, 1f)));
        cardCountPosY1 = cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getY();
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click 6: "+4);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getX(),
                        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 6");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getX(),
                        CardPlay.V_HEIGHT-cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 6");
            }
        });
        cardInHand = 5;
    }

    public void cardHandActionFull() {
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(5))].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280 / 1.7f + 200, -50, 2f),
                        Actions.scaleTo(.17f, .17f, 2f))));
        //cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(5-1))].addAction(Actions.sequence(Actions.fadeOut(2f), Actions.scaleTo(.6f,.6f), Actions.removeActor()));
        cardHandActionDelIndex(5-1);
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(5-2))].addAction(Actions.sequence(Actions.moveTo(1280 / 1.7f + 100, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(5-3))].addAction(Actions.sequence(Actions.moveTo(1280 / 1.7f, -50, 1f)));
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(5-4))].addAction(Actions.sequence(Actions.moveTo(1280 / 1.7f - 100, -50, 1f)));
        randomCard.removeCardInHandIndex(Integer.parseInt(randomCard.getCountCardInHand().get(5-1)));
        cardCountPosY1 = cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getY();
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click out : "+4);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getX(),
                        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover out");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(4))].getX(),
                        CardPlay.V_HEIGHT-cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover out");
            }
        });
        cardInHand = 4;
    }

    public void cardHandActionDel(int indexCard){
        if(indexCard==0){
            if(randomCard.sizeCountCardInHand()==1){
                cardHandActionDelIndex(indexCard);
            }
        }
        else if(indexCard==1){

        }
        else if(indexCard==2){

        }
        else if(indexCard==3){

        }
        else if(indexCard==4){

        }
    }

    public void cardHandActionDelIndex(int indexCard){
        cardDeck[Integer.parseInt(randomCard.getCountCardInHand().get(indexCard))].addAction(Actions.sequence(Actions.fadeOut(2f), Actions.scaleTo(.6f,.6f), Actions.removeActor()));
    }

    public void cardHandActionDelFirstInOne(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelFirstInTwo(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelFirstInThree(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelFirstInFour(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelFirstInFive(int indexCard){
        cardHandActionDelIndex(indexCard);

    }

    public void cardHandActionDelSecondInTwo(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelSecondInThree(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelSecondInFour(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelSecondInFive(int indexCard){
        cardHandActionDelIndex(indexCard);

    }

    public void cardHandActionDelThirthInThree(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelThirthInFour(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelThirthInFive(int indexCard){
        cardHandActionDelIndex(indexCard);

    }

    public void cardHandActionDelForthInFour(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelForthInFive(int indexCard){
        cardHandActionDelIndex(indexCard);

    }

    public void cardHandActionDelFifthInFive(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    */

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
        cardPlay.batch.begin();
        //cardPlay.batch.draw(textureBg,0,0);
        //cardPlay.batch.draw(texture, rendexX, renderY);
        uIplay.runningDraw();
        mapScreen.runner();
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
        if (keycode==Input.Keys.S){
            currentCard++;
            if (currentCard<maxCard-1){
                setCardHandR(currentCard);
                randomCard.setCardInHandIndex(currentCard);
            }
        }
        if (keycode==Input.Keys.D){
            cardHandAction();
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Gdx.app.log("Mouse", "Down");
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        Gdx.app.log("Mouse", "Up");
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        Gdx.app.log("Mouse", "Dragged");

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        //Gdx.app.log("Mouse Position", screenX+", "+screenY);
        //rendexX = screenX - texture.getWidth();
        //renderY = Gdx.graphics.getHeight() - screenY - texture.getHeight();
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        //Gdx.app.log("Mouse", "Scroll :"+amount);
        return false;
    }
}
