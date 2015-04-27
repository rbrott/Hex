package com.hex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.hex.model.GameModel;

public class ScoreBarView extends View {
	
	public GameModel gameModel = null;

	public ScoreBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ScoreBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ScoreBarView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		gameModel = ((GameActivity) getContext()).getGameModel();
		int width = this.getWidth(), height = this.getHeight();
		Paint paint = new Paint();
		paint.setColor(0xFFFF8300);
		paint.setStyle(Style.FILL_AND_STROKE);
		RectF rect = new RectF(0F, 0F, (float) width, (float) height);
		canvas.drawRect(rect, paint);
		paint.setColor(Color.WHITE);
		paint.setTextSize(80F);
		canvas.drawText("SCORE: " + Integer.toString(gameModel.getScore()), 25, 105, paint);
		super.onDraw(canvas);
	}

}
