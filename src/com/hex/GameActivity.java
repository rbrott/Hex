package com.hex;

import com.hex.model.GameModel;
import com.hex.model.Point;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;

public class GameActivity extends Activity {
	
	private GameModel gameModel = new GameModel();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}
	
	public GameModel getGameModel() { return this.gameModel; }
	public void setGameModel(GameModel val) { this.gameModel = val; }
}
