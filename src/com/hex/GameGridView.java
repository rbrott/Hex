package com.hex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.hex.model.GameModel;
import com.hex.model.Hex;
import com.hex.model.HexData;
import com.hex.model.Point;

import java.util.Map.Entry;

public class GameGridView extends View {
	
	public GameModel gameModel = null;
	
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
		float modifiedWidth = canvas.getWidth() / (gameModel.getDepth() * 2 - 1);
		float size = 0.8F * modifiedWidth;
		float padding = 0.05F * modifiedWidth;
		canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
		Paint paint = new Paint();
		paint.setStrokeWidth(10F);
		HexData value;
		Point key;
		Path path;
		for(Entry<String, HexData> entry : gameModel.getGrid().entrySet()) {
			value = entry.getValue();
			key = Point.fromString(entry.getKey());
			path = Hex.getPath(key, size, padding);
			
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(value.color);
			canvas.drawPath(path, paint);
		}
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		gameModel.fireMotionEvent(event);
//		this.invalidate();
//		return super.onTouchEvent(event);
//	}

}
