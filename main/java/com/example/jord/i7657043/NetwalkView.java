package com.example.jord.i7657043;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.jord.i7657043.GameScreen.n;

public class NetwalkView extends View {

    private final GestureDetector gestDetect;
    private TextView turnsLabel;
    private float prevLeft, prevTop;
    private float eachCellLength, eachCellHeight;

    //Hashmap could be used to show the correct image instead of Tile class
    //HashMap<Integer, Bitmap> tileMap = new HashMap<>();

    private final ArrayList<Tile> TILES = new ArrayList<Tile>();

    public NetwalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
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

            if(colTouched<=n.getColumns()&&rowTouched<=n.getRows())
            {
                n.rotateRight(colTouched, rowTouched);
                n.turnIncrement();
                if (n.checkWin())
                {
                    Toast.makeText(getContext(), "You win in " + n.getTurn() + " turns!!!", Toast.LENGTH_LONG).show();
                    won=true;
                    updateTurnsLabel();
                    n.resetTurns();
                }
                else
                {
                    updateTurnsLabel();
                }
                invalidate();
            }
        }
    }
    boolean won = false;
    private void updateTurnsLabel()
    {
        if (n.getTurn()>0)
        {
            turnsLabel = (TextView) ((Activity) getContext()).findViewById(R.id.turnsLbl);
            if (won)
            {
                String temp = "Winner ! " + Integer.toString(n.getTurn()) + " turns !";
                turnsLabel.setText(temp);
                won=false;
            }
            else
            {
                String temp = "Turns: " + Integer.toString(n.getTurn());
                turnsLabel.setText(temp);
            }

        }
        else
        {
            turnsLabel = (TextView) ((Activity)getContext()).findViewById(R.id.turnsLbl);
            turnsLabel.setText(getResources().getString(R.string.turns_label));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
        //Update turn label every time View is clicked
        if (n.getTurn()>0) {
            updateTurnsLabel();
        }

    }

    private void drawGrid(Canvas canvas)
    {
        eachCellLength = getWidth()/n.getColumns();
        eachCellHeight = getHeight()/n.getRows();
        prevLeft = 0;
        prevTop = 0;

        for (int i =0; i<n.getRows(); i++)
        {
            for (int j=0; j<n.getColumns(); j++)
            {
                Integer val = n.getGridElem(j,i);

                for (Tile t : TILES)
                {
                    if (val.compareTo(t.getId())==0)
                    {
                        canvas.drawBitmap(t.getImg(), null, new Rect((int)prevLeft,(int)prevTop,(int)(prevLeft+eachCellLength),(int)(prevTop+eachCellLength)), null);
                        break;
                    }
                }

                //For use with hashmap array alternative
                /*
                int val = n.getGridElem(i,j);
                Bitmap img1 = Bitmap.createBitmap(tileMap.get(val));
                canvas.drawBitmap(img1, null, new Rect((int)prevLeft,(int)prevTop,(int)(prevLeft+eachCellLength),(int)(prevTop+eachCellLength)), null);
                */
                prevLeft += eachCellLength;
            }
            prevTop += eachCellLength;
            prevLeft=0;
        }
    }


    //For use with hashmap array
   /* private void init() {
        Bitmap p3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p3);
        tileMap.put(3,p3);

        Bitmap p5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p5);
        tileMap.put(5,p5);

        Bitmap p6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p6);
        tileMap.put(6,p6);

        Bitmap p7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p7);
        tileMap.put(7,p7);

        Bitmap p9 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p9);
        tileMap.put(9,p9);

        Bitmap p10 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p10);
        tileMap.put(10,p10);

        Bitmap p11 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p11);
        tileMap.put(11,p11);

        Bitmap p12 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p12);
        tileMap.put(12,p12);

        Bitmap p13 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p13);
        tileMap.put(13,p13);

        Bitmap p14 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p14);
        tileMap.put(14,p14);

        Bitmap n17 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n17);
        tileMap.put(17,n17);

        Bitmap n18 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n18);
        tileMap.put(18,n18);

        Bitmap n20 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n20);
        tileMap.put(20,n20

        Bitmap n24 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n24);
        tileMap.put(24,n24);

        Bitmap s88 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s88);
        tileMap.put(88,s88);

        Bitmap s84 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s84);
        tileMap.put(84s84);

        Bitmap s92 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s92);
        tileMap.put(92,s92);

        Bitmap s82 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s82);
        tileMap.put(82s82);

        Bitmap s90 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s90);
        tileMap.put(90,s90);

        Bitmap s86 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s86);
        tileMap.put(86,s86);

        Bitmap s94 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s94);
        tileMap.put(94,s94);

        Bitmap s81 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s81);
        tileMap.put(81,s81);

        Bitmap s89 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s89);
        tileMap.put(89,s89);

        Bitmap s85 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s85);
        tileMap.put(85,s85);

        Bitmap s93 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s93);
        tileMap.put(93,s93);

        Bitmap s83 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s83);
        tileMap.put(83,s83);

        Bitmap s91 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s91);
        tileMap.put(91,s91);

        Bitmap s87 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s87);
        tileMap.put(87,s87);
    }*/

    private void init() {
        Bitmap p12 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p12);
        Tile tile = new Tile(p12,12);
        TILES.add(tile);

        Bitmap p10 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p10);
        tile = new Tile(p10,10);
        TILES.add(tile);

        Bitmap p6 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p6);
        tile = new Tile(p6,6);
        TILES.add(tile);

        Bitmap p14 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p14);
        tile = new Tile(p14,14);
        TILES.add(tile);

        Bitmap p9 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p9);
        tile = new Tile(p9,9);
        TILES.add(tile);

        Bitmap p5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p5);
        tile = new Tile(p5,5);
        TILES.add(tile);

        Bitmap p13 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p13);
        tile = new Tile(p13,13);
        TILES.add(tile);

        Bitmap p3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p3);
        tile = new Tile(p3,3);
        TILES.add(tile);

        Bitmap p11 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p11);
        tile = new Tile(p11,11);
        TILES.add(tile);

        Bitmap p7 = BitmapFactory.decodeResource(this.getResources(), R.drawable.p7);
        tile = new Tile(p7,7);
        TILES.add(tile);

        Bitmap n40 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n40);
        tile = new Tile(n40,40);
        TILES.add(tile);

        Bitmap n36 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n34);
        tile = new Tile(n36,36);
        TILES.add(tile);

        Bitmap n34 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n20);
        tile = new Tile(n34,34);
        TILES.add(tile);

        Bitmap n33 = BitmapFactory.decodeResource(this.getResources(), R.drawable.n33);
        tile = new Tile(n33,33);
        TILES.add(tile);

        Bitmap s88 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s88);
        tile = new Tile(s88,88);
        TILES.add(tile);

        Bitmap s84 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s84);
        tile = new Tile(s84,84);
        TILES.add(tile);

        Bitmap s92 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s92);
        tile = new Tile(s92,92);
        TILES.add(tile);

        Bitmap s82 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s82);
        tile = new Tile(s82,82);
        TILES.add(tile);

        Bitmap s90 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s90);
        tile = new Tile(s90,90);
        TILES.add(tile);

        Bitmap s86 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s86);
        tile = new Tile(s86,86);
        TILES.add(tile);

        Bitmap s94 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s94);
        tile = new Tile(s94,94);
        TILES.add(tile);

        Bitmap s81 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s81);
        tile = new Tile(s81,81);
        TILES.add(tile);

        Bitmap s89 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s89);
        tile = new Tile(s89,89);
        TILES.add(tile);

        Bitmap s85 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s85);
        tile = new Tile(s85,85);
        TILES.add(tile);

        Bitmap s93 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s93);
        tile = new Tile(s93,93);
        TILES.add(tile);

        Bitmap s83 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s83);
        tile = new Tile(s83,83);
        TILES.add(tile);

        Bitmap s91 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s91);
        tile = new Tile(s91,91);
        TILES.add(tile);

        Bitmap s87 = BitmapFactory.decodeResource(this.getResources(), R.drawable.s87);
        tile = new Tile(s87,87);
        TILES.add(tile);
    }
}
