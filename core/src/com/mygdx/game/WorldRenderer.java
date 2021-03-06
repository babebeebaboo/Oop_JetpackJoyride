package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

@SuppressWarnings("unchecked")
class WorldRenderer {
    private final World world;
    private boolean isRunning = false;

    private final BitmapFont font;
    private final Texture flyImg;
    private final Texture diedImg;
    private final Texture bgImg;

    /* Animation
       CREDIT: http://www.pixnbgames.com/blog/libgdx/frame-by-frame-animations-in-libgdx/
    */

    private float elapsed_time = 0f;

    private final Animation runningAnimation;

    private final Animation runningonRoofAnimation;

    /* END Animation */

    public WorldRenderer(JetpackJoyrideGame jetpackjoyrideGame, World world) {
        this.world = world;

        //IMAGE
        flyImg = new Texture("flyer.png");
        bgImg = new Texture("BG.png");
        diedImg = new Texture("died.png");

        font = new BitmapFont();//FONT

        /* Running Animation */
        TextureAtlas runningCharset = new TextureAtlas(Gdx.files.internal("running.atlas"));
        Array<TextureAtlas.AtlasRegion> runningFrames = runningCharset.findRegions("running");
        float FRAME_DURATION = .05f * 2;
        runningAnimation = new Animation(FRAME_DURATION, runningFrames, Animation.PlayMode.LOOP);
        /* END Running Animation */

        /* Running Roof Animation */
        TextureAtlas runningonRoofCharset = new TextureAtlas(Gdx.files.internal("runningonRoof.atlas"));
        Array<TextureAtlas.AtlasRegion> runningonRoofFrames = runningonRoofCharset.findRegions("running");
        runningonRoofAnimation = new Animation(FRAME_DURATION, runningonRoofFrames, Animation.PlayMode.LOOP);
        /* END Running Roof Animation */
    }

    public void render() {
        Gdx.gl.glClearColor(60, 98, 163, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.update();
        SpriteBatch batch = JetpackJoyrideGame.batch;
        //ShapeRenderer shapeRenderer = jetpackjoyrideGame.shapeRenderer;

        Vector2 pos = world.getFlyer().getPosition();
        batch.begin();
        //shapeRenderer.begin(ShapeType.Line);
        //shapeRenderer.rect(world.getFlyer().getRectangle().x, world.getFlyer().getRectangle().y, world.getFlyer().getRectangle().width, world.getFlyer().getRectangle().height);
        batch.draw(bgImg, 0, 0);
        //GOLD
        for (Gold g : world.gold) {
            Vector2 gPos = g.getPosition();
            batch.draw(g.getTexture(), gPos.x - Gold.RADIUS, gPos.y - Gold.RADIUS);
            //shapeRenderer.circle(g.getCircle().x, g.getCircle().y, g.getCircle().radius);
        }
        //BLOCK
        for (Block b : world.block) {
            Vector2 bPos = b.getPosition();
            for (int i = 0; i < b.getLength(); i++) {
                if (bPos.y > 500) {
                    batch.draw(b.getTexture(), bPos.x, bPos.y - 40 * i);
                    //shapeRenderer.rect(b.getRectangle().x, b.getRectangle().y, b.getRectangle().width, b.getRectangle().height);
                } else {
                    batch.draw(b.getTexture(), bPos.x, bPos.y + 40 * i);
                    //shapeRenderer.rect(b.getRectangle().x, b.getRectangle().y, b.getRectangle().width, b.getRectangle().height);
                }
            }
        }
        //FLYER
        if (!world.isGameOver()) {
            if (pos.y >= JetpackJoyrideGame.HEIGHT - Flyer.HEIGHT) {
                elapsed_time += Gdx.graphics.getDeltaTime();
                TextureRegion runningonRoofFrame = (TextureRegion) runningonRoofAnimation.getKeyFrame(elapsed_time);
                batch.draw(runningonRoofFrame, pos.x + 20, pos.y - 20);
            } else if (pos.y > 0) {
                batch.draw(flyImg, pos.x, pos.y);
            } else {
                elapsed_time += Gdx.graphics.getDeltaTime();
                TextureRegion runningFrame = (TextureRegion) runningAnimation.getKeyFrame(elapsed_time);
                batch.draw(runningFrame, pos.x + 20, pos.y);
            }
        } else {
            batch.draw(diedImg, pos.x, pos.y);
        }
        //PRINT
        if (!world.isGameOver()) {
            font.getData().setScale(1.5f);
            font.draw(batch, "Gold: " + world.getScore(), JetpackJoyrideGame.WIDTH - 160, JetpackJoyrideGame.HEIGHT - 80);
            font.draw(batch, "High Score: " + World.getPrefs().getInteger("highscore"), JetpackJoyrideGame.WIDTH - 160, JetpackJoyrideGame.HEIGHT - 103);
        }
        //PRINT GAME OVER
        if (world.isGameOver()) {
            isRunning = true;
            if (world.getScore() > World.getPrefs().getInteger("highscore")) {
                World.getPrefs().putInteger("highscore", world.getScore());
            }
            font.getData().setScale(2);
            font.draw(batch, "You collected", JetpackJoyrideGame.WIDTH / 2 - 120, JetpackJoyrideGame.HEIGHT / 2 + 30);
            font.draw(batch, "" + world.getScore() + " Golds!", JetpackJoyrideGame.WIDTH / 2 - 90, JetpackJoyrideGame.HEIGHT / 2);
            font.draw(batch, "High Score: " + World.getPrefs().getInteger("highscore"), JetpackJoyrideGame.WIDTH / 2 - 120, JetpackJoyrideGame.HEIGHT / 2 - 30);
            font.draw(batch, "Press ESC to exit", JetpackJoyrideGame.WIDTH / 2 - 150, JetpackJoyrideGame.HEIGHT / 2 - 60);
        }

        //Welcome Screen
        if (!isRunning) {
            font.getData().setScale(2);
            font.draw(batch, "Press 'SPACE BAR' to fly", JetpackJoyrideGame.WIDTH / 2 - 180, JetpackJoyrideGame.HEIGHT / 2);
            if (world.isSpacePress()) {
                isRunning = true;
            }
        }
        batch.end();
        //shapeRenderer.end();
    }
}
