import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SnowflakeCatcher extends PApplet {

SnowFlake[] snowFlakes; 

PGraphics offScreenLayerBasket;

public void setup()
{
  size(300, 300);
  offScreenLayerBasket = createGraphics(300, 300);
  offScreenLayerBasket.beginDraw();
  offScreenLayerBasket.background(0);
  offScreenLayerBasket.endDraw();

  snowFlakes = new SnowFlake[500];
  for (int i = 0; i < snowFlakes.length; i++)
    snowFlakes[i] = new SnowFlake();
}

public void draw()
{
  background(0);
  image(offScreenLayerBasket, 0, 0);

  for (int i = 0; i < snowFlakes.length; i++)
  {
    snowFlakes[i].lookDown();
    snowFlakes[i].show();
    snowFlakes[i].wrap();
  }
}

public void keyPressed()
{
  if (key == ' ')
  {
    offScreenLayerBasket.beginDraw();
    offScreenLayerBasket.background(0);
    offScreenLayerBasket.endDraw();
  }  
}

public void mouseDragged()
{
  offScreenLayerBasket.beginDraw();
  offScreenLayerBasket.noStroke();

  if (mouseButton == LEFT)
  {
    offScreenLayerBasket.fill(255,0, 0);
    offScreenLayerBasket.ellipse(mouseX, mouseY, 20, 20);
  }
  else if (mouseButton == RIGHT)
  {
    offScreenLayerBasket.fill(0);
    offScreenLayerBasket.ellipse(mouseX, mouseY, 20, 20);
  }
  else if (mouseButton == CENTER)
  {
    offScreenLayerBasket.fill(0, 255, 0);
    offScreenLayerBasket.rect(mouseX - 10, mouseY - 10, 20, 20);
  }

  // draw offscreen basket
  offScreenLayerBasket.stroke(0);
  offScreenLayerBasket.endDraw();
}

class SnowFlake
{
  int x;
  int y;

  SnowFlake()
  {
    x = (int)(Math.random()*300);
    y = (int)(Math.random()*100) - 150;
  }

  public void lookDown()
  {
    if (y < 0 || get(x, y) == color(0))
      y+=3;
  }
  public void show()
  {
    fill(255);
    ellipse(x, y, 5, 5);
  }
  public void wrap()
  {
    if (y >= 300)
    {
      x = (int)(Math.random()*300);
      y = 0;
    }  
  }
}


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SnowflakeCatcher" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
