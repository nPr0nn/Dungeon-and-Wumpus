package mc322.engine;

public class GameContainer implements Runnable{
      
      private Thread thread;
      private AbstractGame game;
      private Input input;
      private Window window;
      private Renderer renderer;

      private boolean running = false;
      private final double UPDATE_CAP = 1.0/60.0;
      
      private int width = 540, height = 340;
      private double scale = 2;
      private String title = "Dungeons & Wumpus";

      public GameContainer(AbstractGame game){
            this.game = game;
      }

      public void start(){
            window = new Window(this);
            renderer = new Renderer(this);
            input = new Input(this);

            thread = new Thread(this);
            thread.run();
      }

      public void stop(){

      }

      public void run(){
            running = true;
            boolean render = false;
            double firstTime  = 0;
            double lastTime   = System.nanoTime() / 1e9d;
            double passedTime = 0;
            double unprocessedTime = 0;

            double frameTime = 0;
            int frames = 0;
            int fps = 0;

            while(running){
                  render = false;

                  firstTime = System.nanoTime() / 1e9d;
                  passedTime = firstTime - lastTime;
                  lastTime = firstTime;

                  unprocessedTime += passedTime;
                  frameTime += passedTime;

                  while(unprocessedTime >= UPDATE_CAP){
                        unprocessedTime -= UPDATE_CAP;
                        render = true;

                        game.update(this, UPDATE_CAP);
                        input.update();
                        
                        if(frameTime >= 1.0){
                              frameTime = 0;
                              fps = frames;
                              frames = 0;
                              //System.out.println("FPS: " + fps);
                        }
                  }

                  if(render){
                        renderer.clear();
                        game.renderer(this, renderer);
                        window.update();
                        frames++;
                  }
                  else{
                        try{
                              Thread.sleep(1);
                        }
                        catch(InterruptedException e){
                              e.printStackTrace();
                        }
                  }
            }

            dispose();
      }

      private void dispose(){
            return;
      }

      // Getters and Setters:
      
      public void setHeight(int height){
            this.height = height;
      }
      public void setWidht(int width){
            this.width = width;
      }
      public void setScale(double scale){
            this.scale = scale;
      }
      public void setTitle(String title){
            this.title = title;
      }

      public int getHeight(){
            return this.height;
      }
      public int getWidth(){
            return this.width;
      }
      public double getScale(){
            return this.scale;
      }
      public String getTitle(){
            return this.title;
      }
      public Window getWindow(){
            return this.window;
      }
      public Input getInput(){
            return this.input;
      }


}
