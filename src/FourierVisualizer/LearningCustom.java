package FourierVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LearningCustom {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created new GUI on EDT? " + SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Learning Swing Paint");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setUndecorated(false);
        f.setVisible(true);
    }
}

class MyPanel extends JPanel {

    RedSquare redSquare = new RedSquare();

    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

    }

    private void moveSquare(int x, int y) {
        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = redSquare.getX();
        final int CURR_Y = redSquare.getY();
        final int CURR_W = redSquare.getWidth();
        final int CURR_H = redSquare.getHeight();
        final int OFFSET = 1;

        if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background
            // over the old square location.
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            redSquare.setX(x);
            redSquare.setY(y);

            // Repaint the square at the new location.
            repaint(redSquare.getX(), redSquare.getY(),
                    redSquare.getWidth()+OFFSET,
                    redSquare.getHeight()+OFFSET);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Testing Text", 10, 20);
        g.translate(50, 150);
        g.drawLine(0, -120, 0, 120);
        g.drawLine(0, 0, 1000, 0);
        for (int k = 0; k < 720; k++) {
            int x1 = k;
            int x2 = k + 1;
            double y1 = 100 * Math.sin(Math.toRadians(x1));
            double y2 = 100 * Math.sin(Math.toRadians(x2));
            g.drawLine(x1, (int)y1 * -1, x2, (int)y2 * -1);
        }
        g.translate(0, 300);
        g.drawLine(0, -120, 0, 120);
        g.drawLine(0, 0, 1000, 0);
        for (int k = 0; k < 720; k++) {
            int x1 = k;
            int x2 = k + 1;
            double y1 = 100 * Math.sin(Math.toRadians(3 * x1));
            double y2 = 100 * Math.sin(Math.toRadians(3 * x2));
            g.drawLine(x1, (int)y1 * -1, x2, (int)y2 * -1);
        }
        g.translate(-50, -450);
        redSquare.paintSquare(g);

    }
}

class RedSquare {

    private int xPos = 50;
    private int yPos = 50;
    private int width = 20;
    private int height = 20;

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public int getX() {
        return xPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void paintSquare(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xPos, yPos, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(xPos, yPos, width, height);
    }
}
