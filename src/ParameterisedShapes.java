// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2024T1, Assignment 2
 * Name:
 * Username:
 * ID:
 */

 import ecs100.*;
 import java.awt.Color;
 import javax.swing.JColorChooser;
 
 
 /** Parameterised Shapes: 
  * Pass/Fail level: draw rectangles with three horizontal stripes
  * Challenge: draw the flag of China
  */
 public class ParameterisedShapes{
 
     /**
      * Asks user for a position, three colours, three heights and whether the circles are filled.
      * Then calls the drawFancyRect method, passing the appropriate arguments
      */
     public void doFancyRect(){
         double left = UI.askDouble("Left of rectangle");
         double top = UI.askDouble("Top of rectangle");
         UI.println("Now choose the colours");
         Color col1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
         Color col2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
         Color col3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
         UI.println("Now choose the sizes");
         double top_rect_height = UI.askDouble("How tall would you like the top rectangle ?");
         double middle_rect_height = UI.askDouble("How tall would you like the middle rectangle ?");
         double bottom_rect_height = UI.askDouble("How tall would you like the bottom rectangle ?");

         boolean filled = UI.askBoolean("Would you like the holes to be filled ?");

         drawFancyRect(left,top,top_rect_height,col1,middle_rect_height,col2,bottom_rect_height,col3,filled);

     }
 
     /**
      * Calculates the total height and width of the rectangle.
      * The width of the rectangle is 1.5 times the height of the rectangle.
      * It then calls drawStripe three times to draw the three stripes,
      * and outlines the rectangle with a black contour.
      */
     public void drawFancyRect(double LEFT,double TOP,double top_rect_height,Color top_rect_color, double middle_rect_height,Color middle_rect_color,double bottom_rect_height,Color bottom_rect_color,boolean filled){
         UI.clearGraphics();

         double width = (top_rect_height + middle_rect_height +bottom_rect_height)*1.5f;

         drawStripe(LEFT,TOP,width,top_rect_height,top_rect_color,1,filled);
         drawStripe(LEFT,TOP+top_rect_height,width,middle_rect_height,middle_rect_color,2,filled);
         drawStripe(LEFT,TOP+top_rect_height+middle_rect_height,width,bottom_rect_height,bottom_rect_color,3,filled);
     }
 
     /**
      * Draws a stripe at the given position that has the right circle at the right place.
      */
     public void drawStripe(double LEFT,double TOP,double width, double height, Color color,float stripe_num,boolean filled){

         UI.setColor(color);
         UI.fillRect(LEFT, TOP, width, height);
         UI.setColor(Color.black);
        if(filled){
            UI.fillOval(LEFT+((((stripe_num*2f)-1f)/6f)*width) - (1f/10f*height), TOP + (1f/2f)*height - (1f/10f)*height, (1f/5f)*height, (1f/5f)*height);
        }else{
            UI.drawOval(LEFT+((((stripe_num*2f)-1f)/6f)*width) - (1f/10f*height), TOP + (1f/2f)*height - (1f/10f)*height, (1f/5f)*height, (1f/5f)*height);
        }
 
     }


     public void chineseFlag(){
        double LEFT = UI.askDouble("Left of flag ?");
        double TOP = UI.askDouble("Top of flag ?");
        double Width = UI.askDouble("width of flag ?");
        
        chineseFlagDrawStar();
     }

     public void chineseFlagDrawStar(){

     }
 
     public void setupGUI(){
         UI.initialise();
         UI.addButton("Clear", UI::clearPanes );
         UI.addButton("Fancy Rect", this::doFancyRect );
         // Add a button here to call your method for the challenge part
         UI.addButton("Quit", UI::quit );
     }
 
     public static void main(String[] args){
         ParameterisedShapes ps = new ParameterisedShapes ();
         ps.setupGUI();
     }
 
 }
 