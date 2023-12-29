package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board extends Sprite {

    public HashMap<String, Rectangle> board = new HashMap<>();
    public HashMap<String, Rectangle> board_valid = new HashMap<>();
    private final String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    public ArrayList<Piece> pieces = new ArrayList<>();
    private final String[] colors = {"white", "black"};
    private static final Texture damier = new Texture("damier.png");
    private final int columns_lines = 10;
    float dimension_x = (float) 725 / columns_lines;
    float dimension_y = (float) 725 / columns_lines;

    public Board()
    {
        // redimensionner l'image
        super(damier);
        this.setPosition(0, 0);
        this.setOrigin(0, 0);
        this.setScale(0.78f, 0.78f);
    }

    public void createBoard()
    {
        for(int j = 0; j < 10; j++)
        {
            for(int i = 0; i < 10; i++)
            {
                String color = colors[(j + i) % 2];
                Rectangle rect = new Rectangle((int) (385 + i * 72.5f), (int) (58 + j * 72.5f), (int) 72.4f, (int) 72.4f);
                board.put(letters[j] + (i + 1) + "_" + color, rect);
            }

        }

    }

    public void drawCollideBoard(ShapeRenderer shapeRenderer)
    {
        for(Map.Entry<String, Rectangle> element : board.entrySet())
        {
            shapeRenderer.rect((float) element.getValue().getX(), (float) element.getValue().getY(), (float) element.getValue().getWidth(), (float) element.getValue().getHeight());
        }
    }

    public void drawRectCollide(ShapeRenderer shapeRenderer, Rectangle rect)
    {
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
    }

    public void getBoardValid()
    {
        for(Map.Entry<String, Rectangle> element : board.entrySet())
        {
            if(element.getKey().contains("black"))
            {
                board_valid.put(element.getKey(), element.getValue());
            }
        }
    }

    public void startGame()
    {
        createBoard();
        getBoardValid();
        start_disposition_black();
        start_disposition_white();
    }

    public void drawAllPieces(SpriteBatch batch)
    {
        for(Piece piece : pieces)
        {
            piece.draw(batch);
        }
    }

    public void start_disposition_white()
    {
        String[] startDispositionPiecesWhite = {
                "a10_black", "c10_black", "e10_black", "g10_black", "i10_black",
                "b9_black", "d9_black", "f9_black", "h9_black", "j9_black",
                "a8_black", "c8_black", "e8_black", "g8_black", "i8_black",
                "b7_black", "d7_black", "f7_black", "h7_black", "j7_black"
        };

        for(String str : startDispositionPiecesWhite)
        {
            pieces.add(new Piece((int) board.get(str).getX(), (int) this.board.get(str).getY(), "white"));
        }

    }

    public void start_disposition_black()
    {
 /*       String[] startDispositionPiecesBlack = {
                "b1_black", "d1_black", "f1_black", "h1_black", "j1_black",
                "a2_black", "c2_black", "e2_black", "g2_black", "i2_black",
                "b3_black", "d3_black", "f3_black", "h3_black", "j3_black",
                "a4_black", "c4_black", "e4_black", "g4_black", "i4_black"
        };*/

        String[] startDispositionPiecesBlack = {
                "a1_white"
        };

        for(String str : startDispositionPiecesBlack)
        {
            pieces.add(new Piece((int) board.get(str).getX(), (int) this.board.get(str).getY(), "black"));
        }

    }

    public String touchBoard(int xMouse, int yMouse) {

        String str = null;

        for (Map.Entry<String, Rectangle> element : board.entrySet())
        {
            if(element.getValue().contains(xMouse, yMouse))
            {
                str = element.getKey();
            }
        }

        return str;

    }

}
