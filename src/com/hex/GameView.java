package com.hex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hex.model.GameModel;
import com.hex.model.HexData;
import com.hex.model.HexUtil;
import com.hex.model.Point;

import java.util.HashMap;
import java.util.Map.Entry;

public class GameView extends View {

	private float size = 0, padding = 0;
	public GameModel gameModel = null;
	
	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GameView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (gameModel == null) gameModel = ((GameActivity) getContext()).getGameModel();

		if (size == 0) {
			float modifiedWidth = canvas.getWidth() / (gameModel.getDepth() * 2 - 1);
			this.size = 0.8F * modifiedWidth;
			this.padding = 0.05F * modifiedWidth;
		}

		Paint paint = new Paint();

		paint.setColor(Color.DKGRAY);
		paint.setTextSize(80F);
		canvas.drawText("SCORE:\t" + Integer.toString(gameModel.getScore()), 25, 105, paint);

		canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
		paint.setStrokeWidth(10F);
		HexData value;
		Point key;
		Path path;
		for(Entry<String, HexData> entry : gameModel.getGrid().entrySet()) {
			value = entry.getValue();
			key = Point.fromString(entry.getKey());
			path = HexUtil.getPath(key, size, padding);
			
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(value.color);
			canvas.drawPath(path, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		Point mouse = new Point((int) motionEvent.getX(), (int) motionEvent.getY()), key, temp;
		for (HashMap.Entry<String, HexData> entry : gameModel.getGrid().entrySet()) {
			key = Point.fromString(entry.getKey());
			temp = HexUtil.getDevicePoint(key, size, padding);
			temp.x += this.getWidth() / 2;
			temp.y += this.getHeight() / 2;
			if (HexUtil.distance(mouse, temp) <= size / (float) Math.sqrt(3)) {
				gameModel.fireMotionEvent(key);
			}
		}
		this.invalidate();
		return super.onTouchEvent(motionEvent);
	}

}
