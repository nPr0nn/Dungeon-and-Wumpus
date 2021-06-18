package mc322.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.*;

public class Window{
      
      JFrame frame;
      private BufferedImage image;
      private Canvas canvas;
      private BufferStrategy bs;
      private Graphics g;


      public Window(GameContainer gc){
            image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
            canvas = new Canvas();
            Dimension s =new Dimension((int)(gc.getWidth()*gc.getScale()),(int)(gc.getHeight()*gc.getScale()));
            canvas.setPreferredSize(s);
            canvas.setMaximumSize(s);
            canvas.setMinimumSize(s);

            frame = new JFrame(gc.getTitle());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(canvas, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setVisible(true);

            canvas.createBufferStrategy(2);
            bs = canvas.getBufferStrategy();
            g = bs.getDrawGraphics();
      }

      public void update(){
            g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
            bs.show();
      }

      //Getters
      
      public BufferedImage getImage(){
            return this.image;
      }
      public Canvas getCanvas(){
            return this.canvas;
      }
      public JFrame getFrame(){
            return this.frame;
      }
}
