package com.main.axival.card;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.main.axival.card.screen.ScreenPlay;


public class UIplay implements Screen{
    private Texture overlayLBottom, overlayRButtom, leftPlayer1, leftPlayer2, rightPlayer1, rightPlayer2;
    private Texture nextPhase, overlaybigbottom, overlaybigtop, mana_right, mana_left, sword, shoe, shield, skill, turn_line;
    private Texture Heart_colour, Mana_colour;
    private Texture heart_left;
    private Texture skill1, skill2;
    private SpriteBatch batch;

    private ScreenPlay screenPlay;
    private CardPlay cardPlay;

    private Stage stage;
    private Image nextPhaseImg, leftPlayerImg1, leftPlayerImg2, rightPlayerImg1, rightPlayerImg2, skillImg1, skillImg2;

    public UIplay(CardPlay cardPlay, final ScreenPlay screenPlay){
        this.cardPlay = cardPlay;
        this.screenPlay = screenPlay;
        overlayLBottom = new Texture("UI_Assets/Axival_UI_Assets/Overlay Bottom Left@1x.png");
        overlayRButtom = new Texture("UI_Assets/Axival_UI_Assets/Overlay Bottom Right@1x.png");
        leftPlayer1 = new Texture("UI_Assets/Axival_UI_Assets/Left Player 1@1x.png");
        leftPlayer2 = new Texture("UI_Assets/Axival_UI_Assets/Left Player 2@1x.png");
        rightPlayer1 = new Texture("UI_Assets/Axival_UI_Assets/Right Player 1@1x.png");
        rightPlayer2 = new Texture("UI_Assets/Axival_UI_Assets/Right Player 2@1x.png");
        nextPhase = new Texture("UI_Assets/Axival_UI_Assets/Next Phase Button@1x.png");
        overlaybigbottom = new Texture("UI_Assets/Axival_UI_Assets/Overlay Big Bottom@1x.png");
        overlaybigtop = new Texture("UI_Assets/Axival_UI_Assets/Overlay Big Top@1x.png");
        mana_right = new Texture("UI_Assets/Axival_UI_Assets/Mana Icon Full Right Bottom@1x.png");
        mana_left = new Texture("UI_Assets/Axival_UI_Assets/Mana Left Bottom@1x.png");
        sword = new Texture("UI_Assets/Axival_UI_Assets/Sword Right Bottom@1x.png");
        shield = new Texture("UI_Assets/Axival_UI_Assets/Shield Right Bottom@1x.png");
        shoe = new Texture("UI_Assets/Axival_UI_Assets/Shoe Right Bottom@1x.png");
        //skill = new Texture("UI_Assets/Axival_UI_Assets/Skill@1x.png");
        heart_left = new Texture("UI_Assets/Axival_UI_Assets/Heart Left Buttom@1x.png");
        turn_line = new Texture("UI_Assets/Axival_UI_Assets/Turn Line@1x.png");
        Heart_colour = new Texture("UI_Assets/Axival_UI_Assets/Heart Mini Playerbar@1x.png");
        Mana_colour = new Texture("UI_Assets/Axival_UI_Assets/Mana Mini Playerbar@1x.png");
        skill1 = new Texture("UI_Assets/Axival_UI_Assets/Skill@1x.png");
        skill2 = new Texture("UI_Assets/Axival_UI_Assets/Skill@1x.png");

        nextPhaseImg = new Image(nextPhase);
        nextPhaseImg.setPosition(550, 630);
        nextPhaseImg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                screenPlay.editStatusPhase(3, 0, 1);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                nextPhaseImg.addAction(Actions.sequence(Actions.parallel(Actions.scaleTo(1.1f,1.1f,.5f),
                        Actions.moveTo(540, 630, .5f))));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                nextPhaseImg.addAction(Actions.sequence(Actions.parallel(Actions.scaleTo(1f,1f,.5f),
                        Actions.moveTo(550, 630, .5f))));
            }
        });

        leftPlayerImg1 = new Image(leftPlayer1);
        leftPlayerImg2 = new Image(leftPlayer2);
        rightPlayerImg1 = new Image(rightPlayer1);
        rightPlayerImg2 = new Image(rightPlayer2);

        leftPlayerImg1.setPosition(200,656);
        leftPlayerImg2.setPosition(346, 656);
        rightPlayerImg1.setPosition(778, 656);
        rightPlayerImg2.setPosition(924,656);

        skillImg1 = new Image(skill1);
        skillImg2 = new Image(skill2);

        skillImg1.setPosition(860,10);
        skillImg1.setSize(62,55);
        skillImg2.setPosition(860, 65);
        skillImg2.setSize(62,55);

        screenPlay.stage.addActor(nextPhaseImg);
        screenPlay.stage.addActor(leftPlayerImg1);
        screenPlay.stage.addActor(leftPlayerImg2);
        screenPlay.stage.addActor(rightPlayerImg1);
        screenPlay.stage.addActor(rightPlayerImg2);
        screenPlay.stage.addActor(skillImg1);
        screenPlay.stage.addActor(skillImg2);
    }

    public void render(){
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    }

    public void runningDraw(){
        cardPlay.batch.draw(overlayLBottom, 0, 0, 235, 125);
        cardPlay.batch.draw(overlayRButtom,1045,0, 235, 125);
        //cardPlay.batch.draw(leftPlayer1,200,656, 166, 64);
        //cardPlay.batch.draw(leftPlayer2,346,656, 166, 64);
        //cardPlay.batch.draw(rightPlayer1,778,656, 166, 64);
        //cardPlay.batch.draw(rightPlayer2,924,656, 166, 64);
        //cardPlay.batch.draw(nextPhase,550,630, 197, 33);
        cardPlay.batch.draw(overlaybigbottom,0,0, 1280, 250);
        cardPlay.batch.draw(overlaybigtop,0,600, 1280, 250);
        cardPlay.batch.draw(mana_right,1100,15, 14, 14);
        cardPlay.batch.draw(mana_right,1100,50, 14, 14);
        cardPlay.batch.draw(mana_right,1100,85, 14, 14);
        cardPlay.batch.draw(sword,1240,15, 26, 26);
        cardPlay.batch.draw(shield,1240,50, 20, 24);
        cardPlay.batch.draw(shoe,1240,85, 26, 24);
        cardPlay.batch.draw(mana_left,10,25, 25, 25);
        cardPlay.batch.draw(heart_left,10,70, 25, 25);
        cardPlay.batch.draw(Heart_colour,230,645, 13, 13);
        cardPlay.batch.draw(Mana_colour,300,645, 13, 13);
        cardPlay.batch.draw(Heart_colour,370,645, 13, 13);
        cardPlay.batch.draw(Mana_colour,440,645, 13, 13);
        cardPlay.batch.draw(Heart_colour,818,645, 13, 13);
        cardPlay.batch.draw(Mana_colour,888,645, 13, 13);
        cardPlay.batch.draw(Heart_colour,958,645, 13, 13);
        cardPlay.batch.draw(Mana_colour,1028,645, 13, 13);
        //cardPlay.batch.draw(skill1, 860,10, 62, 55);
        //cardPlay.batch.draw(skill2, 860,65, 62,55);
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

