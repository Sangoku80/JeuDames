package Main;

import Controller.InputProcessor;
import Entities.Board;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Board board;
	private ShapeRenderer shapeRenderer;

	@Override
	public void create () {

		// instances
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		board = new Board();

		// inputs
		InputProcessor inputProcessor = new InputProcessor(board);
		Gdx.input.setInputProcessor(inputProcessor);

		// startGame
		board.startGame();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		// view:

		// Commencer le rendu des sprites
		batch.getProjectionMatrix().setToOrtho2D(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), -Gdx.graphics.getHeight());
		batch.begin();

		// dessin du plateau
		board.draw(batch);

		// dessin des pièces
		board.drawAllPieces(batch);

		// terminer le rendu des sprites
		batch.end();


		// Commencez le rendu des formes (carré)
		shapeRenderer.getProjectionMatrix().setToOrtho2D(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), -Gdx.graphics.getHeight());
		shapeRenderer.begin(ShapeType.Line);

		// dessin du boardCollide
		shapeRenderer.setColor(1, 0,0,0);

		for(int i = 0; i < 10; i++)
		{
			for(int j = 0)
			shapeRenderer.rect(385f + i * 72.5f, 58, 72.4f, 72.4f);
		}

		// Terminez le rendu des formes
		shapeRenderer.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
	}



}
