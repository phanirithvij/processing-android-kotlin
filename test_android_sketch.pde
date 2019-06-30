Circle[] circles = new Circle[1000];
int count = 0;
int maxDiameter = 100;
int minDiameter = 10;
int lastAdded = 0;
int lastAddedTimeout = 100;

void setup() {
  size(500, 500);
  smooth();
  background(255);
  noFill();  
}


void draw() {
  //background(255);
  if (count < circles.length) {
    circles[count] = new Circle(5, maxDiameter);
    for (int i=0; i<count; i++) {
      if (circles[count].intersects(circles[i])) {
        circles[count] = null;
        break;
      }
    }
    
    if (circles[count] != null) {
      circles[count].draw();
      
      if (count > 1) {
        float nearest = 100000;
        float current = 0;
        int nearestIndex = -1;
        for (int i=0; i<count; i++) {
          current = dist(circles[i].x, circles[i].y, circles[count].x, circles[count].y);
          if (current < nearest) {
            nearest = current;
            nearestIndex = i;
          }
        }
      
        stroke(255, 255, 0);
        line(circles[nearestIndex].x, circles[nearestIndex].y, circles[count].x, circles[count].y);
        stroke(0);
      }
      
      count++;
      lastAdded = 0;
    } else {
      if (lastAdded > lastAddedTimeout && maxDiameter > minDiameter) {
         maxDiameter--;
         lastAdded = 0;
       }
      lastAdded++;
     }
  }  
}


class Circle
{
  float x, y, radius;
  
  Circle(float minDiameter, float maxDiameter) {
    radius = random(minDiameter, maxDiameter) / 2.0;
    x = random(radius, width - radius);
    y = random(radius, height - radius);
  }
  
  boolean intersects(Circle c) {
    return dist(c.x, c.y, x, y) < c.radius + radius;
  }
  
  void draw() {
    color c = lerpColor(color(255), color(0), radius/50.0);
    fill(c);
    ellipse(x, y, radius*2, radius*2);
  }
  
}
