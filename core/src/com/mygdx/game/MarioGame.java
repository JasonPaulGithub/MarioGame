package com.mygdx.game;

import Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MarioGame extends Game
{
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render ()
	{
		super.render();
	}

	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;

	public static final float PPM = 100;//Pixels per meter
}
