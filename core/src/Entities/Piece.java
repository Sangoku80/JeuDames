package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Objects;

public class Piece extends Sprite {

    private static final Texture pion_noir = new Texture("pion_noir.png");
    private static final Texture pion_blanc = new Texture("pion_blanc.png");

    public Piece(int x, int y, String color)
    {
        super(getTexture(color));

        // redimensionner l'image
        if(this.getTexture() == pion_noir)
        {
            this.setPosition(x - 0.5f, y);
            this.setOrigin(0, 0);
            this.setScale(0.61f, 0.61f);
        }
        else
        {
            this.setPosition(x - 2, y + 2);
            this.setOrigin(0, 0);
            this.setScale(0.33f, 0.33f);
        }

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