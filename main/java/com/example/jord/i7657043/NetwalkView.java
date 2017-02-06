package com.example.jord.i7657043;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.jord.i7657043.game_screen.game;

/**
 * Created by Jord on 14/12/2016.
 */

public class NetwalkView extends View {

    private GestureDetector gestDetect;
    private Paint paint1, paint2, paint3;
    private TextView turnsLabel;
    private float eachCellLength, eachCellHeight;

    private ArrayList<Tile> tileArray = new ArrayList<Tile>();

    public NetwalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        gestDetect = new GestureDetector(context,new GridGestureListener());
    }

    public NetwalkView(Context context) {
        super(context);
        init();
        gestDetect = new GestureDetector(context,new GridGestureListener());
    }

    public boolean onTouchEvent(MotionEvent event) {
        this.gestDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class GridGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onDown(MotionEvent ev)
        {
            return true;
        }
        @Override
        public void onLongPress(MotionEvent ev)
        {
            float x = ev.getX();
            float y = ev.getY();

            Integer colTouched = (int) (Math.floor(x/eachCellLength));
            Integer rowTouched = (int) (Math.floor(y/eachCellHeight));

            game.takeTurn(rowTouched,colTouched);

            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        eachCellLength = getWidth()/game.getGridColumns();
        eachCellHeight = getHeight()/game.getGridRows();

        drawGrid(canvas,tileArray,(int)eachCellLength,game.getGridColumns(),game.getGridRows());

        //Need this method to update turn label every time View is clicked
        updateTurnsLabel();

    }

    private static void drawGrid(Canvas canvas, ArrayList<Tile> tileArray, int eachCellLength, int col, int row)
    {
        int prevLeft = 0;
        int prevTop = 0;

        for (int i =0; i<col; i++)
        {
            for (int j=0; j<row; j++)
            {
                for (Tile tile : tileArray) {
                    if (game.nextTile(i,j).compareTo(tile.getId()) == 0) {
                        canvas.drawBitmap(tile.getImg(), null, new Rect(prevLeft, prevTop, (prevLeft + eachCellLength),(prevTop + eachCellLength)), null);
                        break;
                    }
                }
                //increase for next tile
                prevLeft += eachCellLength;
            }
            //increase for next tile
            prevTop += eachCellLength;

            //start next at beginning
            prevLeft=0;
        }
    }

    private void updateTurnsLabel()
    {
        if (game.getTurn()>0)
        {
            turnsLabel = (TextView) ((Activity)getContext()).findViewById(R.id.turnsLbl);
            String temp = "Turns: " + Integer.toString(game.getTurn());
            turnsLabel.setText(temp);
        }
        else
        {
            turnsLabel = (TextView) ((Activity)getContext()).findViewById(R.id.turnsLbl);
            turnsLabel.setText(R.string.turns_label);
        }
    }


    private void init()
    {
        Bitmap p3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p3);
        Tile tile = new Tile(p3,3);
        tileArray.add(tile);

        Bitmap p5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p5);
        tile = new Tile(p5,5);
        tileArray.add(tile);

        Bitmap p6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p6);
        tile = new Tile(p6,6);
        tileArray.add(tile);

        Bitmap p7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p7);
        tile = new Tile(p7,7);
        tileArray.add(tile);

        Bitmap p9 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p9);
        tile = new Tile(p9,9);
        tileArray.add(tile);

        Bitmap p10 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p10);
        tile = new Tile(p10,10);
        tileArray.add(tile);

        Bitmap p11= BitmapFactory.decodeResource(this.getResources(), R.drawable.p11);
        tile = new Tile(p11,11);
        tileArray.add(tile);

        Bitmap p12 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p12);
        tile = new Tile(p12,12);
        tileArray.add(tile);

        Bitmap p13 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p13);
        tile = new Tile(p13,13);
        tileArray.add(tile);

        Bitmap p14 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p14);
        tile = new Tile(p14,14);
        tileArray.add(tile);

        Bitmap n17 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n17);
        tile = new Tile(n17,17);
        tileArray.add(tile);

        Bitmap n18 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n18);
        tile = new Tile(n18,18);
        tileArray.add(tile);

        Bitmap n20 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n20);
        tile = new Tile(n20,20);
        tileArray.add(tile);

        Bitmap n24 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n24);
        tile = new Tile(n24,24);
        tileArray.add(tile);

        Bitmap s33 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s33);
        tile = new Tile(s33,33);
        tileArray.add(tile);

        Bitmap s34 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s34);
        tile = new Tile(s34,34);
        tileArray.add(tile);

        Bitmap s35 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s35);
        tile = new Tile(s35,35);
        tileArray.add(tile);

        Bitmap s36 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s36);
        tile = new Tile(s36,36);
        tileArray.add(tile);

        Bitmap s37 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s37);
        tile = new Tile(s37,37);
        tileArray.add(tile);

        Bitmap s38 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s38);
        tile = new Tile(s38,38);
        tileArray.add(tile);

        Bitmap s39 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s39);
        tile = new Tile(s39,39);
        tileArray.add(tile);

        Bitmap s40 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s40);
        tile = new Tile(s40,40);
        tileArray.add(tile);

        Bitmap s41 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s41);
        tile = new Tile(s41,41);
        tileArray.add(tile);

        Bitmap s42 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s42);
        tile = new Tile(s42,42);
        tileArray.add(tile);

        Bitmap s43 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s43);
        tile = new Tile(s43,43);
        tileArray.add(tile);

        Bitmap s44 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s44);
        tile = new Tile(s44,44);
        tileArray.add(tile);

        Bitmap s45 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s45);
        tile = new Tile(s45,45);
        tileArray.add(tile);

        Bitmap s46 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s46);
        tile = new Tile(s46,46);
        tileArray.add(tile);
    }
}
