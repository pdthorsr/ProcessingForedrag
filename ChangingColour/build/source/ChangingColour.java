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

public class ChangingColour extends PApplet {

public void setup(){
  
  background(255, 0, 0);
  textAlign(CENTER);
}

public void draw(){
  background(random(0, 255), random(0, 255), random(0, 255));
  fill(random(0, 255), random(0, 255), random(0, 255));
  textSize(70);
  text("Hello World!", 450, 400);
  delay(500);
}
  public void settings() {  size(900, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ChangingColour" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
