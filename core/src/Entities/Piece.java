package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Objects;

public class Piece extends Sprite {

    private static final Texture pion_noir = new Texture("pion_noir.png");
    private static final Texture pion_blanc = new Texture("pion_blanc.png");

    public Piece(int x, int y, String color)
    {
        super(getTexture(color));
        //this.setScale(0.8f, 0.8f);
        this.setX((float) x);
        this.setY((float) y);

    }

    public static Texture getTexture(String color)
    {
        Texture txt;

        if(Objects.equals(color, "white"))
        {
            txt = pion_blanc;
        }
        else
        {
            txt = pion_noir;
        }

        return txt;
    }
}