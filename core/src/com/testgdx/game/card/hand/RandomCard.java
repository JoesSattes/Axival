package com.testgdx.game.card.hand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.main.axival.card.CardPlay;

import java.util.Random;

public class RandomCard {
    private int randResult;
    private CardPlay cardPlay;
    private TextureAtlas cardAll;
    private HorizontalGroup hand;
    private Image[] cardPack;
    private Stage stage;
    public RandomCard(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.cardAll = cardPlay.assetManager.get("cardani/spritesheet/cardAni.atlas", TextureAtlas.class);
    }
    public int generateRandom(int first, int last){
        Random rand = new Random();
        randResult = rand.nextInt(last-first+1)+first;
        System.out.print(randResult);
        return randResult;
    }
    public Image getCard(int randResult)
    {
        return new Image(cardAll.findRegion(String.format("%03d", randResult)));
    }
    public HorizontalGroup cardHand(){
        cardPack = new Image[6];
        hand = new HorizontalGroup();
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        int width = Gdx.graphics.getWidth()/10;
        int height = width * 2;
        //hand.setBounds(width/2, -height/4, stage.getHeight()-height, height);
        //hand.space(width / -3f);
        hand.space(10.0f);
        hand.center();
        hand.align(Align.bottom | Align.center);
        for (int i = 0; i < cardPack.length; i++) {
            cardPack[i] = getCard(generateRandom(2,7));//max = 7
            cardPack[i].setScale(.2f);
            System.out.println(cardPack[i]);
            hand.addActor(cardPack[i]);
            //hand.addActor(cardPack[i]);
        }
        //stage.addActor(hand);
        return hand;
    }

}
