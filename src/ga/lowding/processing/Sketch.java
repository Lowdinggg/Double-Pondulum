package ga.lowding.processing;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.KeyEvent;

/**
 * Created by Lowdingg on 28/04/2018.
 *
 * @link http://lowding.ga/
 */
public class Sketch extends PApplet {


    float x1, y1;
    float x2, y2;

    float m1, m2;

    float L1, L2;

    float a1, a2;

    float a1_vel, a2_vel;
    float a1_acc, a2_acc;
    final float g = 1;

    float px2 = -1;
    float py2 = -1;

    PGraphics tracer;


    @Override
    public void settings() {
            size(800,500);
    }

    @Override
    public void setup() {
        m1 = 40;
        m2 = 40;

        L1 = 120;
        L2 = 120;

        a1 = HALF_PI;
        a2 = HALF_PI;

        tracer = createGraphics(width, height);
        tracer.beginDraw();
        tracer.background(255);
        tracer.endDraw();
    }

    @Override
    public void draw() {
        image(tracer, 0,0);



        float num1 = -g * (2 * m1 + m2) * sin(a1);
        float num2 = -m2 * g * sin(a1-2*a2);
        float num3 = -2*sin(a1-a2)*m2;
        float num4 = a2_vel*a2_vel*L2+a1_vel*a1_vel*L1*cos(a1-a2);
        float den = L1 * (2*m1+m2-m2*cos(2*a1-2*a2));
         a1_acc = (num1 + num2 + num3*num4) / den;


        num1 = 2 * sin(a1-a2);
        num2 = (a1_vel*a1_vel*L1*(m1+m2));
        num3 = g * (m1 + m2) * cos(a1);
        num4 = a2_vel*a2_vel*L2*m2*cos(a1-a2);
        den = L2 * (2*m1+m2-m2*cos(2*a1-2*a2));
         a2_acc = (num1*(num2+num3+num4)) / den;

        translate(width / 2, height / 5);

        stroke(0);
        strokeWeight(2);

        // First part

        x1 = L1 * sin(a1);
        y1 = L1 * cos(a1);

        x2 = x1 + L2 * sin(a2);
        y2 = y1 + L2 * cos(a2);

        line(0, 0, x1, y1);
        fill(0);
        ellipse(x1, y1, m1, m1);

        line(x1, y1, x2, y2);
        fill(0);
        ellipse(x2, y2, m2,m2);


        a1_vel += a1_acc;
        a2_vel += a2_acc;

        a1 += a1_vel;
        a2 += a2_vel;



        tracer.beginDraw();
        tracer.translate(width / 2, height /5);
        tracer.stroke(0,0,0, 150);
        if (frameCount > 1) {
            tracer.line(px2, py2, x2, y2);
        }
        tracer.endDraw();
        px2 = x2;
        py2 = y2;

    }


    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == java.awt.event.KeyEvent.VK_S)
            tracer.save("C://Users//ANIS//Desktop//"+millis()+".jpeg");
    }
}
