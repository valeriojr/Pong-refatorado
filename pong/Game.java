package pong;/* a pong clone created by Christopher Wolff */

import pong.gameObjects.Ball;
import pong.gameObjects.GameObject;
import pong.gameObjects.ObjectManager;
import pong.gameObjects.paddle.CPUControlledState;
import pong.gameObjects.paddle.Paddle;
import pong.gameObjects.paddle.PlayerControlledState;
import pong.gameState.GameState;
import pong.gameState.RunningState;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends JFrame implements Runnable {

	public static final int GAME_HEIGHT = 450;
	public static final int GAME_WIDTH = 450;
	public static int PLAYER_PADDLE = 0;
	public static int COMPUTER_PADDLE = 1;
	public static int BALL = 2;

	private BufferedImage buffer;
	private Random random;

	private GameState state;
	private ObjectManager objectManager;

	public static final String miss = "Pong/sounds/miss.wav";
	public static final String paddle_hit = "Pong/sounds/paddle_hit.wav";
	public static final String wallHit = "Pong/sounds/wall_hit.wav";

	public static void main(String[] args){
		new Game().startGameThread();
	}

	public Game(){
		initCanvas();
		buffer = new BufferedImage(GAME_HEIGHT, GAME_WIDTH, BufferedImage.TYPE_INT_RGB);
		objectManager = ObjectManager.getInstance();
	}

	public void initCanvas(){
	    Canvas canvas = new Canvas();
		canvas.setFocusable(true);

		setLayout(new GridLayout());
		setTitle("Rolo pong");
		setVisible(true);
		setSize(GAME_HEIGHT, GAME_WIDTH);
		setVisible(true);
		addKeyListener(Input.getInstance());
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(canvas);

		requestFocus();
	}

	public void startGameThread(){
		Thread gameThread = new Thread(this);
		try{
			gameThread.join();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		gameThread.start();
	}

	public void playSound(String sound){
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(sound));
           		AudioFormat format = inputStream.getFormat();
            		DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip)AudioSystem.getLine(info);
            		clip.open(inputStream);
            		clip.start();
		//if the audio clips compete for resources
		}catch(LineUnavailableException ex){
			System.out.println("handled strange audio exception");
		}catch(Exception ex){
			System.out.println("general clip problem");
			//ex.printStackTrace();
		}
	}

	@Override
	public void run(){
		random = new Random();

		Paddle player1 = new Paddle.Builder(PLAYER_PADDLE)
				.setSpeed(1)
				.setX(25)
				.setY(GAME_HEIGHT/2)
				.getPaddle();

		Paddle player2 = new Paddle.Builder(COMPUTER_PADDLE)
				.setX(GAME_WIDTH - 25)
				.setY(GAME_HEIGHT/2)
				.getPaddle();

		double angle = random.nextInt(120) + 120 * (Math.PI / 180.0);

		Ball ball = new Ball(GAME_WIDTH/2,random.nextInt(150) + 150, angle);

		objectManager.add(PLAYER_PADDLE, player1);
		objectManager.add(COMPUTER_PADDLE, player2);
		objectManager.add(BALL, ball);

		player1.setState(new PlayerControlledState(PLAYER_PADDLE));
		player2.setState(new CPUControlledState(COMPUTER_PADDLE));

		setGameState(new RunningState(this));

		while (true) {
            state.update();
            state.draw();

            try {
				Thread.sleep(5);
			} catch (Exception ex) {
				System.out.println("Couldn't sleep for some reason.");
			}
        }
	}

    public void setGameState(GameState state) {
	    this.state = state;
    }

    private class Canvas extends JPanel{

		public void paint(Graphics g){
			Graphics2D g2D = (Graphics2D) g;
			draw(g2D);

			Graphics2D backBufferDrawer = (Graphics2D) buffer.getGraphics();
			draw(backBufferDrawer);

			g2D.drawImage(buffer, 0, 0, this);
		}

		private void draw(Graphics2D g){
		    clear(g);
            g.setColor(Color.WHITE);
		    drawBackground(g);
		    drawObjects(g);
		    drawScore(g);
        }

		private void clear(Graphics2D g){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GAME_HEIGHT, GAME_WIDTH);
        }

        private void drawBackground(Graphics2D g){
            for (int i =0; i < GAME_WIDTH; i+=10){ //dotted line
                g.drawLine(GAME_WIDTH/2,i,GAME_WIDTH/2,i +5);
            }
        }

        private void drawObjects(Graphics2D g){
            for(GameObject object : objectManager.getGameObjects().values()) {
                object.draw(g);
            }
        }

        private void drawScore(Graphics2D g){
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 72));
            g.drawString("" + ((Paddle) objectManager.getObject(PLAYER_PADDLE)).getScore(), GAME_WIDTH/2 - 150, 100);
            g.drawString("" + ((Paddle) objectManager.getObject(PLAYER_PADDLE)).getScore(), GAME_WIDTH/2 + 100, 100);
        }
	}
}
