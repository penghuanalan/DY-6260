package com.dayuan.dy_6260chartscanner;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomView extends View {
	List<Integer> list;
	public float XPoint = 45; // 原点X坐标点
	public float YPoint = 330; // 原点Y坐标点
	public float XLength = 373; // X轴的长度
	public float YLength = 314; // Y轴坐标的长度
    public float valueC;
    public float valueT;
    float gap;

	public float startX = 0;
	public float startY = 0;
	float maxValue;
	float minValue;
	int CIndex;
	int TIndex;
	private float value;
	private float Y2;
	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);//设置线条的宽度，单位是px
		paint.setAntiAlias(true);// 设置抗锯齿
		paint.setColor(Color.BLUE);
		int index=(int) (maxValue/500);
		 Y2=(float) (6*500*(YLength/1.1)/maxValue);
		//for (int i = 0; i < list.size()-40; i++) {
		 //绘制曲线图
			for (int i = 0; i < list.size(); i++) {
			gap =(XLength) / (list.size()-1);
				if (i < list.size()-1) {
				startX = gap * i + XPoint;
				startY = (float) (YPoint-list.get(i) *(YLength) /maxValue); 
				float stopX = (i + 1) * gap + XPoint;
				float nextValue = list.get(i +1);
				float stopY = (float) (nextValue *(YLength) /(maxValue));
				stopY = YPoint-stopY;
				canvas.drawLine(startX, Y2+startY, stopX, Y2+stopY, paint);
			}
		}
			//这里是绘制坐标值
			for (int i =5; i <1.1*index; i++) {
				value=(float)(i+1)*500;
				paint.setStrokeWidth(1);
				float Y1=(float) (value*(YLength)/maxValue);
				
				canvas.drawText(value+"", XPoint-45, YPoint- Y1+Y2, paint);
			}
			
		canvas.drawLine(XPoint, YPoint - YLength, XPoint, YPoint, paint);
		canvas.drawLine(XPoint, YPoint, XPoint + XLength, YPoint, paint);
		//canvas.drawCircle(cx, cy, 4, paint);
		
	}

	public void setData(List<Integer> data) {
		this.list = data;
			for (int i =0; i < list.size(); i++) {
			float value = list.get(i);
			if (value > maxValue) {
				maxValue = value;
				Log.d("maxValue", "" + maxValue);
			}
		}
	//Value();
	}

	public void removeData() {
		list.clear();
	}

	
}
