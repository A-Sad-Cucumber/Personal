package FourierVisualizer;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.ColorModel;

public class FrameTest {

    private static void createWindow() {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pan = new JPanel();
        JLabel lab = new JLabel("Frequency 1");
        frame.setContentPane(pan);
        JTextField fr1 = new JTextField(2);
        fr1.setSize(new Dimension(100, 100));
        lab.setLabelFor(fr1);
        pan.add(lab);
        pan.add(fr1);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createWindow();
    }
}
