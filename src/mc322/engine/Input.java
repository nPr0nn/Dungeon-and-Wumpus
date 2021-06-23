package mc322.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

      private GameContainer gc;

      private final int NUM_KEYS = 256;
      private boolean[] keys = new boolean[NUM_KEYS];
      private boolean[] keysLast = new boolean[NUM_KEYS];

      private final int NUM_BUTTONS = 5;
      private boolean[] buttons = new boolean[NUM_BUTTONS];
      private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

      private int mouseX, mouseY;
      private int scroll;
      
      private int lastClickX;
      private int lastClickY;
      private boolean clicked = false;


      public Input(GameContainer gc){
            this.gc = gc;
            this.mouseX = 0;
            this.mouseY = 0;
            this.scroll = 0;

            gc.getWindow().getCanvas().addKeyListener(this);
            gc.getWindow().getCanvas().addMouseListener(this);
            gc.getWindow().getCanvas().addMouseMotionListener(this);
            gc.getWindow().getCanvas().addMouseWheelListener(this);

      }

      public void update(){
            scroll = 0;
            for(int i = 0; i < NUM_KEYS; i++){
                  keysLast[i] = keys[i];
            }
            for(int i = 0; i < NUM_BUTTONS; i++){
                  buttonsLast[i] = buttons[i];
            }
      }

      // Useful Methods
      
      public boolean isKey(int keyCode){
            return this.keys[keyCode];
      }
      public boolean isKeyUp(int keyCode){
            return ( !(this.keys[keyCode]) && this.keysLast[keyCode] );
      }
      public boolean isKeyDown(int keyCode){
            return ( this.keys[keyCode] && !(this.keysLast[keyCode]) );
      }

      public boolean isButton(int buttonCode){
            return this.buttons[buttonCode];
      }
      public boolean isButtonUp(int buttonCode){
            return ( !(this.buttons[buttonCode]) && this.buttonsLast[buttonCode] );
      }
      public boolean isButtonDown(int buttonCode){
            return ( this.buttons[buttonCode] && !(this.buttonsLast[buttonCode]) );
      }


      // Imported Methods

      @Override
      public void mouseWheelMoved(MouseWheelEvent e){
            scroll = e.getWheelRotation();
      }
      @Override
      public void mouseDragged(MouseEvent e){
            mouseX = (int)(e.getX()/gc.getScale());
            mouseY = (int)(e.getY()/gc.getScale());
      }
      @Override
      public void mouseMoved(MouseEvent e){
            mouseX = (int)(e.getX()/gc.getScale());
            mouseY = (int)(e.getY()/gc.getScale());
      }
      @Override
      public void mouseClicked(MouseEvent e){
    	  lastClickX=e.getX();
    	  lastClickY=e.getY();
    	  clicked = true;
      }
      @Override
      public void mouseEntered(MouseEvent e){

      }
      @Override
      public void mouseExited(MouseEvent e){

      }
      @Override
      public void mousePressed(MouseEvent e){
            buttons[e.getButton()] = true;
      }
      @Override
      public void mouseReleased(MouseEvent e){
            buttons[e.getButton()] = false;
      }

      public Pair<Integer, Integer> getClick(){
    	  clicked = false;
    	  return Pair.of(lastClickX, lastClickY );
      }
      public boolean wasClicked(){
    	  return clicked;
      }

      
      @Override
      public void keyPressed(KeyEvent e){
            keys[e.getKeyCode()] = true;
      }
      @Override
      public void keyReleased(KeyEvent e){
            keys[e.getKeyCode()] = false;
      }
      @Override
      public void keyTyped(KeyEvent e){
    	  
      }


      // Getters
      public int getMouseX(){
            return this.mouseX;
      }
      public int getMouseY(){
            return this.mouseY;
      }
      public int getScroll(){
            return this.scroll;
      }
      
}
