package com.main.axival.card.fade;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.main.axival.card.CardPlay;
import com.main.axival.card.Menu;

public class FadeScence {
    private CardPlay cardPlay;
    private Image toneWhite, toneBlack;
    public FadeScence(CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.toneBlack = new Image(new Texture("tone/white.jpg"));
        this.toneWhite = new Image(new Texture("tone/white.jpg"));
    }
    public void screenfadeIn(Image image){
        image.setPosition(0,0);
        image.setColor(1, 1, 1, 0);
        image.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                cardPlay.setScreen(new Menu(cardPlay));
                System.out.println("in fade");
            }
        }), Actions.removeActor()));
        cardPlay.fadeScreenStage.addActor(image);
    }

    public void screenfadeOut(Image image){
        image.setPosition(0,0);
        image.setColor(1,1,1,1);
        image.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.removeActor()));
        cardPlay.fadeScreenStage.addActor(image);
    }
}

