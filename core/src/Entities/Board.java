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
