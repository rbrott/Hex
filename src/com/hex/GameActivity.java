package com.hex;

import android.app.Activity;
import android.os.Bundle;

import com.hex.model.GameModel;

public class GameActivity extends Activity {

	private GameModel gameModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		gameModel = new GameModel(4);
	}

	public GameModel getGameModel() { return this.gameModel; }
	public void setGameModel(GameModel val) { this.gameModel = val; }
}
