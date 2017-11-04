package com.main.axival.card;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.main.axival.card.screen.ScreenPlay;

public class Menu extends Game implements Screen{
    private Stage stage;
    private Label outputLabel;
    private final CardPlay cardPlay;

    int Help_Guides = 12;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;

    private Texture textureBg;
    private Image buttonImgPlay, buttonImgTutorial, buttonImgSetting, buttonImgExit, logoMenu;

    public Menu(final CardPlay cardPlay){
        this.cardPlay = cardPlay;
        this.stage = new Stage(new StretchViewport(CardPlay.V_WIDTH, CardPlay.V_HEIGHT, cardPlay.camera));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void create () {
    }

    @Override
    public void render () {
    }

    @Override
    public void show() {
        /*
        Skin mySkin = new Skin(Gdx.files.internal("skin2/glassy-ui.json"));

        Label title = new Label("Buttons with Skins",mySkin, "black");
        title.setSize(Gdx.graphics.getWidth(),row_height);
        title.setPosition(0,Gdx.graphics.getHeight()-row_height*2);
        title.setAlignment(Align.center);
        stage.addActor(title);

        // Button
        Button button1 = new Button(mySkin);
        button1.setSize(col_width*4,row_height);
        button1.setPosition(col_width,Gdx.graphics.getHeight()-row_height*3);
        button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Button");
                return true;
            }
        });
        stage.addActor(button1);

        // Text Button
        Button button2 = new TextButton("Start Game",mySkin);
        button2.setSize(col_width*4,row_height);
        button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Start Game");
                cardPlay.setScreen(new ScreenPlay(cardPlay));
                return true;
            }
        });
        stage.addActor(button2);

        // ImageButton
        ImageButton button3 = new ImageButton(mySkin);
        button3.setSize(col_width*4,(row_height*2));
        button3.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_off.png"))));
        button3.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_on.png"))));
        button3.setPosition(col_width,Gdx.graphics.getHeight()-row_height*6);
        button3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Check Image");
                return true;
            }
        });
        stage.addActor(button3);

        //ImageTextButton
        ImageTextButton button4 = new ImageTextButton("End Game",mySkin);
        button4.setSize(col_width*4,(float)(row_height*2));
        button4.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_off.png"))));
        button4.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch_on.png"))));
        button4.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*6);
        button4.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("End Game");
                return true;
            }
        });
        stage.addActor(button4);

        outputLabel = new Label("Press a Button",mySkin,"black");
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);*/

        textureBg = new Texture("bg1.jpg");
        logoMenu = new Image(new Texture("Main-Menu/Game Logo.png"));
        logoMenu.setPosition(59, 630);
        buttonImgPlay = new Image(new Texture("Main-Menu/Play.png"));
        buttonImgPlay.setScale(.95f);
        buttonImgPlay.setPosition(40, 15);
        buttonImgSetting = new Image(new Texture("Main-Menu/Setting.png"));
        buttonImgSetting.setScale(.95f);
        buttonImgSetting.setPosition(620, 15);
        buttonImgTutorial = new Image(new Texture("Main-Menu/Tutorial.png"));
        buttonImgTutorial.setScale(.95f);
        buttonImgTutorial.setPosition(390, 15);
        buttonImgExit = new Image(new Texture("Main-Menu/Exit.png"));
        buttonImgExit.setScale(.95f);
        buttonImgExit.setPosition(880,49);

        buttonImgPlay.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                cardPlay.setScreen(new ScreenPlay(cardPlay));
                return true;
            }
        });

        stage.addActor(buttonImgPlay);
        stage.addActor(buttonImgSetting);
        stage.addActor(buttonImgTutorial);
        stage.addActor(buttonImgExit);

        stage.addActor(logoMenu);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        cardPlay.batch.begin();
        //cardPlay.batch.draw(textureBg, 0, 0);
        cardPlay.batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void hide() {

    }
}
