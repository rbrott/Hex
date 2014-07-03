package com.hex;

import java.util.Map.Entry;

import com.hex.model.GameModel;
import com.hex.model.Hex;
import com.hex.model.HexData;
import com.hex.model.Point;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameGridView extends View {
	
	public GameModel gameModel = null;
	private boolean done = false;
	
	public GameGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public GameGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GameGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		gameModel = ((GameActivity) getContext()).getGameModel();
		if (!done) {
			gameModel.generateGrid(new Point(getWidth() / 2, getHeight() / 2), 80, 4);
			done = true;
		}
		Paint paint = new Paint();
		paint.setTextSize(50F);
		paint.setStrokeWidth(10F);
		HexData value;
		Point key;
		Path path;
		for(Entry<String, HexData> entry : gameModel.getGrid().entrySet()) {
			value = entry.getValue();
			key = new Point(entry.getKey());
			path = Hex.getPath(key, gameModel.getSideLength());
			
//			paint.setStyle(Paint.Style.STROKE);
//			paint.setColor(Color.WHITE);
//			canvas.drawPath(path, paint);
			
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(value.color);
			canvas.drawPath(path, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gameModel.fireMotionEvent(event);
		this.invalidate();
		return super.onTouchEvent(event);
	}

}
