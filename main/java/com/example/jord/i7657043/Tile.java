package com.example.jord.i7657043;

import android.graphics.Bitmap;

/**
 * Created by Jord on 03/02/2017.
 */

class Tile {

    private Bitmap img;
    private Integer identifier;

    public Tile(Bitmap img, int identifier)
    {
        this.identifier = identifier;
        this.img = img;
    }

    public Integer getId()
    {
        return this.identifier;
    }

    public Bitmap getImg()
    {
        return this.img;
    }

    public void setId(int id)
    {
        this.identifier = id;
    }

    public void setImg(Bitmap img)
    {
        this.img = img;
    }
}
