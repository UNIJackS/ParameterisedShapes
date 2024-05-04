// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2024T1, Assignment 2
 * Name: Jack Scrivener
 * Username: scrivejack
 * ID: 300658748
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


     //Custom functions to draw chinese flag bellow 

     //takes degre and returns radian value
     public double DegToRad(double deg){
        double rad = deg*Math.PI/180;
        return rad;
     }

     public void drawChinaFlag(){
        UI.clearGraphics();
        double LEFT = UI.askDouble("Left of flag ?");
        double TOP = UI.askDouble("Top of flag ?");
        double Width = UI.askDouble("width of flag ?");

        UI.setColor(Color.red);
        UI.fillRect(LEFT, TOP, Width, (2f/3f)*Width);

        UI.setColor(Color.yellow);
        //draws big star
        drawStar(0.185f*Width,LEFT+ 0.075f*Width,TOP+0.065f*Width,0);

        //draws first star
        
        drawStar(0.067f*Width,LEFT+ 0.3f*Width,TOP+0.033f*Width,DegToRad(50));

        //draws second star
        drawStar(0.067f*Width,LEFT+ 0.37f*Width,TOP+0.1f*Width,DegToRad(30));

        //draws third star
        drawStar(0.067f*Width,LEFT+ 0.37f*Width,TOP+0.2f*Width,DegToRad(0));

        //draws fourth star
        drawStar(0.067f*Width,LEFT+ 0.3f*Width,TOP+0.267f*Width,DegToRad(45));
        
        UI.setColor(Color.black);
        UI.drawRect(LEFT, TOP, Width, (2f/3f)*Width);
     }





     public void drawStar(double width,double left,double top,double rotation){

        //multiplyer lists for drawing a star
        double x_values[] = new double[10];
        double y_values[] = new double[10];

        double outer_angles[] = {Math.PI/10f,Math.PI/2f,9f*Math.PI/10f,13f*Math.PI/10f,17f*Math.PI/10f};
        double inner_angles[] = {3f*Math.PI/10f,7f*Math.PI/10f,11f*Math.PI/10f,3f*Math.PI/2f,19f*Math.PI/10f};

        int points = y_values.length;
        if(x_values.length != y_values.length){
            UI.print("oi the lists arnt the same lenth");
        }else{

            for(int angles =0; angles <9; angles +=2){
                //outer angles cordinates
                x_values[angles] = 15.772+(Math.cos(outer_angles[angles/2]+rotation)*15.772);
                y_values[angles] = 15.772-(Math.sin(outer_angles[angles/2]+rotation)*15.772);
                //inner angle cordinates
                x_values[angles+1] = 6.4743+(Math.cos(inner_angles[angles/2]+rotation)*6.4743) + 9.2477;
                y_values[angles+1] = 6.4743-(Math.sin(inner_angles[angles/2]+rotation)*6.4743)+ 9.2477;
            }

            //UI.println("lenth of x list:"+x_values.length);

            //divides all the values by 30 and multiplys them by the width and adds offsets
            for(int values = 0; values < x_values.length;values+=1){
                x_values[values] = (x_values[values]/30f * width + left)+ width* Math.cos(Math.PI/2f);
                y_values[values] = (y_values[values]/30f * width + top);

                //UI.print("x:"+x_values[values]);
                //UI.print("y"+y_values[values] +"\n");
            }

            UI.fillPolygon(x_values, y_values,points);
        }
     }
 
     public void setupGUI(){
         UI.initialise();
         UI.addButton("Clear", UI::clearPanes );
         UI.addButton("Fancy Rect", this::doFancyRect );
         // Add a button here to call your method for the challenge part
         UI.addButton("Quit", UI::quit );
         UI.addButton("China Flag", this::drawChinaFlag);
     }
 
     public static void main(String[] args){
         ParameterisedShapes ps = new ParameterisedShapes ();
         ps.setupGUI();
     }
 
 }
 