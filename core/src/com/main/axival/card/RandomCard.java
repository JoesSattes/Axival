package com.main.axival.card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomCard{
    private int randResult;
    private CardPlay cardPlay;
    private TextureAtlas cardAll;
    private HorizontalGroup hand;
    private Image[] cardPack;
    private Stage stage;
    private int[] idenCardAll;

    private TextureRegionDrawable textureRegionDrawable;
    private Image image;

    private ArrayList<String> countCardInHand;
    public RandomCard(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.cardAll = cardPlay.assetManager.get("cardani/spritesheet/cardAni.atlas", TextureAtlas.class);
        this.countCardInHand = new ArrayList<String>();
    }
    public int generateRandom(int first, int last){
        Random rand = new Random();
        randResult = rand.nextInt(last-first+1)+first;
        System.out.print(randResult);
        return randResult;
    }
    public Image getCard(int randResult)
    {
        textureRegionDrawable = new TextureRegionDrawable(cardAll.findRegion(String.format("%03d", randResult)));
        image = new Image(textureRegionDrawable);
        //image.getImage().setFillParent(true);
        return image;
    }
    public HorizontalGroup cardHand(int num, char pos){
        cardPack = new Image[num];
        hand = new HorizontalGroup();
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        int width = Gdx.graphics.getWidth()/10;
        int height = width * 2;
        hand.space(10f);
        hand.center();
        for (int i = 0; i < cardPack.length; i++) {
            cardPack[i] = getCard(generateRandom(2,7));//max = 7
            hand.addActor(cardPack[i]);
            cardPack[i].setPosition(300+100*i,150);
            cardPack[i].setName(String.format("%d", i));
        }
        hand.setScale(.2f);
        if(pos=='r')
            hand.setPosition(640+100+60, 50);
        else if(pos=='l')
            hand.setPosition(140+60, 50);
        return hand;
    }

    public Image[] allCardDeck(int maxCard){
        Image[] cardDeck = new Image[maxCard];
        idenCardAll = new int[maxCard];
        for(int i=0;i<maxCard;i++){
            randResult = generateRandom(1,7);
            cardDeck[i] = getCard(randResult);
            cardDeck[i].setScale(.05f);
            cardDeck[i].setPosition(640-cardDeck[i].getWidth()/10, 700);
            idenCardAll[i] = randResult;
        }
        return cardDeck;
    }

    public void setCardInHandIndex(int indexCard){
        countCardInHand.add(indexCard+"");
        System.out.println("Set:"+getCountCardInHand());
        System.out.println("Set(status):"+ Arrays.toString(getIdenCardAll()));
    }

    public void removeCardInHandIndex(int valueCard){
        countCardInHand.remove(valueCard);
        System.out.println("Remove:"+getCountCardInHand());
        System.out.println("Remove(status):"+Arrays.toString(getIdenCardAll()));
    }

    public ArrayList<String> getCountCardInHand() {
        return countCardInHand;
    }

    public int[] getIdenCardAll(){
        return idenCardAll;
    }

    public int sizeCountCardInHand(){
        return countCardInHand.size();
    }
}
