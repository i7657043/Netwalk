package com.example.jord.i7657043;

import android.graphics.Bitmap;

class Tile {

    private final Bitmap img;
    private final Integer identifier;

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
}
