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

/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
*/

}
