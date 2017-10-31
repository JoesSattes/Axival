package com.testgdx.game.backup;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.testgdx.game.backup.screen.ScreenPlay;

import java.util.Arrays;

public class CardAction {
    public ScreenPlay screenPlay;
    int[] cardHandIndex = new int[6];
    public CardAction(ScreenPlay screenPlay){
        this.screenPlay = screenPlay;
    }
    public void cardHandActionFirst(final int currentCard){
        screenPlay.cardDeck[currentCard].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/2.2f, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[0] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                //cardHandActionDel(0);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 1");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 1");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getX(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getY(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getWidth()*.05*.2,
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].getHeight()*.05*.2);
        screenPlay.cardInHand = 1;
    }
    public void cardHandActionSecond(final int currentCard){
        screenPlay.cardDeck[currentCard].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/2.2f+50, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        screenPlay.cardDeck[currentCard-1].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-50, -50, 1f)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[1] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 2");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 2");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getX(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getY(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getWidth()*.05*.2,
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].getHeight()*.05*.2);
        screenPlay.cardInHand = 2;
    }
    public void cardHandActionThirst(final int currentCard){
        screenPlay.cardDeck[currentCard].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/2.2f+100, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        screenPlay.cardDeck[currentCard-1].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -50, 1f)));
        screenPlay.cardDeck[currentCard-2].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-100, -50, 1f)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[2] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 3");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 3");
            }
        });
        System.out.printf("PicX: %f, PicY: %f, Size: %fx%f\n", screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getX(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getY(),
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getWidth()*.05*.2,
                screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].getHeight()*.05*.2);
        screenPlay.cardInHand = 3;
    }
    public void cardHandActionFourth(final int currentCard) {
        screenPlay.cardDeck[currentCard].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280 / 2.2f + 150, -50, 2f),
                        Actions.scaleTo(.17f, .17f, 2f))));
        screenPlay.cardDeck[currentCard-1].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f + 50, -50, 1f)));
        screenPlay.cardDeck[currentCard-2].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f - 50, -50, 1f)));
        screenPlay.cardDeck[currentCard-3].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f - 150, -50, 1f)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[3] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(3));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener() {
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY() + 100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 4");
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT - screenPlay.cardCountPosY1 - 50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 4");
            }
        });
        screenPlay.cardInHand = 4;
    }
    public void cardHandActionFifth(final int currentCard){
        screenPlay.cardDeck[currentCard].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280/2.2f+200, -50, 2f),
                        Actions.scaleTo(.17f,.17f,2f))));
        screenPlay.cardDeck[currentCard-1].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+100, -50, 1f)));
        screenPlay.cardDeck[currentCard-2].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -50, 1f)));
        screenPlay.cardDeck[currentCard-3].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-100, -50, 1f)));
        screenPlay.cardDeck[currentCard-4].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-200, -50, 1f)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[currentCard].getY();
        //cardHandIndex[4] = Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(4));
        //System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click : "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover 5");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover 5");
            }
        });
        screenPlay.cardInHand = 5;
    }

    public void cardHandActionFull(final int currentCard) {
        screenPlay.cardDeck[currentCard].addAction(Actions.sequence(Actions.alpha(.7f),
                Actions.parallel(Actions.fadeIn(3f, Interpolation.pow2),
                        Actions.moveTo(1280 / 2.2f + 200, -50, 2f),
                        Actions.scaleTo(.17f, .17f, 2f))));
        screenPlay.cardDeck[currentCard-2].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f + 100, -50, 1f)));
        screenPlay.cardDeck[currentCard-3].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f, -50, 1f)));
        screenPlay.cardDeck[currentCard-4].addAction(Actions.sequence(Actions.moveTo(1280 / 2.2f - 100, -50, 1f)));
        screenPlay.cardCountPosY1 = screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(5))].getY();
        screenPlay.cardDeck[currentCard].addListener(new ClickListener(){
            private int currentCardListener = currentCard;
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Click Full: "+currentCardListener+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
                cardHandActionDel(screenPlay.randomCard.getCountCardInHand().indexOf(currentCardListener+""));
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        screenPlay.cardDeck[currentCardListener].getY()+100, .5f),
                        Actions.scaleTo(.2f, .2f, .5f))));
                System.out.println("Hover out");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor){
                screenPlay.cardDeck[currentCardListener].addAction(Actions.sequence(Actions.parallel(Actions.moveTo(screenPlay.cardDeck[currentCardListener].getX(),
                        CardPlay.V_HEIGHT-screenPlay.cardCountPosY1-50, .5f),
                        Actions.scaleTo(.17f, .17f, .5f))));
                System.out.println("exit Hover out");
            }
        });
        //cardHandActionDelIndex(currentCard-1);
        //screenPlay.randomCard.removeCardInHandIndex(Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(5-1)));
        System.out.println("Click FullPO: "+currentCard+", findIncount: "+screenPlay.randomCard.getCountCardInHand().indexOf((currentCard-1)+""));
        cardHandActionDelIndex(screenPlay.randomCard.getCountCardInHand().indexOf((currentCard-1)+""));
        System.out.println("CardIndex: "+ Arrays.toString(cardHandIndex));
        screenPlay.cardInHand = 4;
    }

    public void cardHandActionDelIndex(int indexCard){
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(indexCard))].addAction(Actions.sequence(Actions.fadeOut(2f), Actions.scaleTo(.6f,.6f), Actions.removeActor()));
        screenPlay.randomCard.removeCardInHandIndex(indexCard);
        //System.out.println("Remove: "+screenPlay.randomCard.getCountCardInHand());
    }

    public void cardHandActionDelFirstInOne(int indexCard){
        cardHandActionDelIndex(indexCard);

    }
    public void cardHandActionDelFirstInTwo(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -50, 1f)));

    }
    public void cardHandActionDelFirstInThree(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-50, -50, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+50, -50, 1f)));
    }
    public void cardHandActionDelFirstInFour(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-100, -50, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f, -50, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+100, -50, 1f)));
    }
    public void cardHandActionDelFirstInFive(int indexCard){
        cardHandActionDelIndex(indexCard);
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(0))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-150, -50, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(1))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f-50, -50, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(2))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+50, -50, 1f)));
        screenPlay.cardDeck[Integer.parseInt(screenPlay.randomCard.getCountCardInHand().get(3))].addAction(Actions.sequence(Actions.moveTo(1280/2.2f+150, -50, 1f)));
    }


    public void cardHandActionDel(int indexCard){
        if(screenPlay.randomCard.sizeCountCardInHand()==1){
            cardHandActionDelFirstInOne(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==2){
            cardHandActionDelFirstInTwo(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==3){
            cardHandActionDelFirstInThree(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==4){
            cardHandActionDelFirstInFour(indexCard);
        }
        else if(screenPlay.randomCard.sizeCountCardInHand()==5){
            cardHandActionDelFirstInFive(indexCard);
        }
    }
}
