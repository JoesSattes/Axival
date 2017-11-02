package com.main.axival.card;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.axival.card.screen.ScreenPlay;

public class UIplay implements Screen{
    private Texture overlayLBottom, overlayRButtom, leftPlayer1, leftPlayer2, rightPlayer1, rightPlayer2;
    private Texture nextPhase, overlaybigbottom, overlaybigtop, mana_right, mana_left, sword, shoe, shield, skill, turn_line;
    private Texture Heart_colour, Mana_colour;
    private Texture heart_left;
    private SpriteBatch batch;

    private ScreenPlay screenPlay;
    private CardPlay cardPlay;

    public UIplay(CardPlay cardPlay){
        this.cardPlay = cardPlay;
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
        skill = new Texture("UI_Assets/Axival_UI_Assets/Skill@1x.png");
        heart_left = new Texture("UI_Assets/Axival_UI_Assets/Heart Left Buttom@1x.png");
        turn_line = new Texture("UI_Assets/Axival_UI_Assets/Turn Line@1x.png");
        Heart_colour = new Texture("UI_Assets/Axival_UI_Assets/Heart Mini Playerbar@1x.png");
        Mana_colour = new Texture("UI_Assets/Axival_UI_Assets/Mana Mini Playerbar@1x.png");
    }

    public void render(){
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cardPlay.batch.draw(overlayLBottom, 0, 0, 235, 125);
        cardPlay.batch.draw(overlayRButtom,1045,0, 235, 125);
        cardPlay.batch.draw(leftPlayer1,200,656, 166, 64);
        cardPlay.batch.draw(leftPlayer2,346,656, 166, 64);
        cardPlay.batch.draw(rightPlayer1,778,656, 166, 64);
        cardPlay.batch.draw(rightPlayer2,924,656, 166, 64);
        cardPlay.batch.draw(nextPhase,550,630, 197, 33);
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
    }

    public void runningDraw(){
        cardPlay.batch.draw(overlayLBottom, 0, 0, 235, 125);
        cardPlay.batch.draw(overlayRButtom,1045,0, 235, 125);
        cardPlay.batch.draw(leftPlayer1,200,656, 166, 64);
        cardPlay.batch.draw(leftPlayer2,346,656, 166, 64);
        cardPlay.batch.draw(rightPlayer1,778,656, 166, 64);
        cardPlay.batch.draw(rightPlayer2,924,656, 166, 64);
        cardPlay.batch.draw(nextPhase,550,630, 197, 33);
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

