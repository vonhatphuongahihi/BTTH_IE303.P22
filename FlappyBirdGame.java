import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdGame extends JPanel implements ActionListener, KeyListener {

    private static final int WIDTH = 360, HEIGHT = 640;

    private Image background, birdImage, topPipeImage, bottomPipeImage;

    private int birdY = HEIGHT / 2, velocity = 0;
    private final int GRAVITY = 1, JUMP_STRENGTH = -15;

    private Timer timer;
    private ArrayList<Pipe> pipes;
    private Random random;
    private int score = 0;
    private boolean gameOver = false;

    public FlappyBirdGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        background = new ImageIcon("flappybirdbg.png").getImage();
        birdImage = new ImageIcon("flappybird.png").getImage();
        topPipeImage = new ImageIcon("toppipe.png").getImage();
        bottomPipeImage = new ImageIcon("bottompipe.png").getImage();

        pipes = new ArrayList<>();
        random = new Random();

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
        g.drawImage(birdImage, WIDTH / 4, birdY, 34, 24, this);

        for (Pipe pipe : pipes) {
            g.drawImage(topPipeImage, pipe.x, pipe.topY, pipe.width, pipe.height, this);
            g.drawImage(bottomPipeImage, pipe.x, pipe.bottomY, pipe.width, pipe.height, this);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", WIDTH / 4, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", WIDTH / 4, HEIGHT / 2 + 40);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            velocity += GRAVITY;
            birdY += velocity;

            if (birdY > HEIGHT - 50)
                gameOver = true;
            if (birdY < 0)
                birdY = 0;

            for (Pipe pipe : pipes) {
                pipe.x -= 5;

                if (pipe.x + pipe.width < 0) {
                    pipes.remove(pipe);
                    score++;
                    break;
                }

                if (checkCollision(pipe)) {
                    gameOver = true;
                }
            }

            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).x < WIDTH - 250) {
                addPipe();
            }
        }
        repaint();
    }

    private void addPipe() {
        int gap = 150;
        int pipeHeight = random.nextInt(200) + 100;
        pipes.add(new Pipe(WIDTH, 0, 50, pipeHeight));
        pipes.add(new Pipe(WIDTH, pipeHeight + gap, 50, HEIGHT - pipeHeight - gap));
    }

    private boolean checkCollision(Pipe pipe) {
        Rectangle birdRect = new Rectangle(WIDTH / 4, birdY, 34, 24);
        Rectangle topPipeRect = new Rectangle(pipe.x, pipe.topY, pipe.width, pipe.height);
        Rectangle bottomPipeRect = new Rectangle(pipe.x, pipe.bottomY, pipe.width, pipe.height);
        return birdRect.intersects(topPipeRect) || birdRect.intersects(bottomPipeRect);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!gameOver) {
                velocity = JUMP_STRENGTH;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame();
        }
    }

    private void restartGame() {
        birdY = HEIGHT / 2;
        velocity = 0;
        pipes.clear();
        score = 0;
        gameOver = false;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        // Khởi tạo cửa sổ game
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBirdGame game = new FlappyBirdGame();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class Pipe {
        int x, topY, width, height, bottomY;

        Pipe(int x, int topY, int width, int height) {
            this.x = x;
            this.topY = topY;
            this.width = width;
            this.height = height;
            this.bottomY = topY + height + 150;
        }
    }
}
