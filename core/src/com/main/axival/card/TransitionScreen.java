package com.main.axival.card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.main.axival.card.screen.ScreenPlay;

public class TransitionScreen implements Screen{
    private CardPlay cardPlay;
    private Screen first;
    private Stage stage;
    private Texture texture;
    public TransitionScreen(CardPlay cardPlay){
        this.cardPlay = cardPlay;
        //this.first = screen;
    }

    @Override
    public void show() {
        final Image image= new Image(new TextureRegion(getTexture()));
        image.setSize(cardPlay.transitionStage.getWidth(),cardPlay.transitionStage.getHeight());
        image.setOrigin(cardPlay.transitionStage.getWidth()/2,cardPlay.transitionStage.getHeight()/2);
        image.setColor(Color.BLACK);

        cardPlay.transitionStage.addActor(image);
        image.addAction(Actions.sequence(Actions.color(Color.BLACK,1), Actions.run(new Runnable() {
            @Override
            public void run() {
                cardPlay.setScreen(new Menu(cardPlay));
                System.out.println("in fade");
            }
        })));
        /*
        cardPlay.transitionStage.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                image.addAction(Actions.sequence(Actions.color(Color.BLACK,2),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        ((CardPlay) Gdx.app.getApplicationListener()).setScreen(new Menu(cardPlay));
                        System.out.println("in fade");
                    }
                })));

                super.clicked(event, x, y);
            }
        });*/

        image.addAction(Actions.sequence(Actions.color(Color.WHITE,1), Actions.fadeOut(1f)));
    }

    public static Texture getTexture(){

        Pixmap pixmap;
        try {
            pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        }catch (GdxRuntimeException e)
        {
            pixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        }
        pixmap.setColor(Color.WHITE);
        pixmap.drawRectangle(0,0,1,1);

        return new Texture(pixmap);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 1, 1, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //cardPlay.transitionStage.getRoot().setColor(1, 1, 1, 0);
        //cardPlay.transitionStage.getRoot().addAction(Actions.sequence(Actions.fadeIn(3f)));
        update(delta);
        cardPlay.transitionStage.draw();
    }

    public void update(float delta){
        cardPlay.transitionStage.act(delta);
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
