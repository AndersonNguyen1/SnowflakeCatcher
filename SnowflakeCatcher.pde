SnowFlake[] snowFlakes; 

PGraphics offScreenLayerBasket;

void setup()
{
  size(300, 300);
  offScreenLayerBasket = createGraphics(300, 300);
  offScreenLayerBasket.beginDraw();
  offScreenLayerBasket.background(0);
  offScreenLayerBasket.endDraw();

  snowFlakes = new SnowFlake[20];
  for (int i = 0; i < snowFlakes.length; i++)
    snowFlakes[i] = new SnowFlake();
}

void draw()
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

void keyPressed()
{
  // if you press space, cleans paint off of screen
  if (key == ' ')
  {
    offScreenLayerBasket.beginDraw();
    offScreenLayerBasket.background(0);
    offScreenLayerBasket.endDraw();
  }  
}

void mouseDragged()
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

  void lookDown()
  {
    if (y < 0 || get(x, y) == color(0))
      y+=3;
  }
  void show()
  {
    fill(255);
    ellipse(x, y, 5, 5);
  }
  void wrap()
  {
    if (y >= 300)
    {
      x = (int)(Math.random()*300);
      y = 0;
    }  
  }
}


