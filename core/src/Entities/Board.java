package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board extends Sprite {

    public HashMap<String, Rectangle> board = new HashMap<>();
    public HashMap<String, Rectangle> board_valid = new HashMap<>();
    private final String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    public ArrayList<Piece> pieces = new ArrayList<>();
    private final String[] colors = {"black", "white"};
    private static final Texture damier = new Texture("damier.png");

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
                "g1_black",
                "g3_black",
                "g5_black",
                "g7_black",
                "g9_black",
                "h2_black",
                "h4_black",
                "h6_black",
                "h8_black",
                "h10_black",
                "i1_black",
                "i3_black",
                "i5_black",
                "i7_black",
                "i9_black",
                "j2_black",
                "j4_black",
                "j6_black",
                "j8_black",
                "j10_black"
        };

        for(String str : startDispositionPiecesWhite)
        {
            pieces.add(new Piece((int) board.get(str).getX(), (int) this.board.get(str).getY(), "white"));
        }

    }

    public void start_disposition_black()
    {

        String[] startDispositionPiecesBlack = {
                "a1_black",
                "a3_black",
                "a5_black",
                "a7_black",
                "a9_black",
                "b2_black",
                "b4_black",
                "b6_black",
                "b8_black",
                "b10_black",
                "c1_black",
                "c3_black",
                "c5_black",
                "c7_black",
                "c9_black",
                "d2_black",
                "d4_black",
                "d6_black",
                "d8_black",
                "d10_black"
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
