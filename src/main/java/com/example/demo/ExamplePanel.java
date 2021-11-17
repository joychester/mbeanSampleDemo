package com.example.demo;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

@Component
@Log
public class ExamplePanel extends JPanel{
    @Autowired
    private ExampleModelBean exampleModelBean;
    private int angle = 0;
    private Timer timer;

    @PostConstruct
    public void postConstruct() {
        //set defaults
        log.info("### set default values for Mbean attribute!!");
        exampleModelBean.setLineLength(100);
        exampleModelBean.setDelay(200);
        exampleModelBean.addStopListener(stop -> {
            if (stop) {
                timer.stop();
            } else {
                timer.restart();
            }
        });
        initTimer();
        log.info("### finish set default values for Mbean attribute!!");
    }

    private void initTimer() {
        timer = new Timer(exampleModelBean.getDelay(), e -> {
            angle += 5;
            if (angle >= 360) {
                angle = 0;
            }
            repaint();
            //update delay in case model bean has changed
            if (timer.getDelay() != exampleModelBean.getDelay()) {
                if (exampleModelBean.getDelay() < 0) {
                    exampleModelBean.setDelay(0);
                }
                timer.setDelay(exampleModelBean.getDelay());
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        int lineLength = exampleModelBean.getLineLength();
        //center of panel
        int x1 = getWidth() / 2;
        int y1 = getHeight() / 2;
        //degree to radians
        double angle = this.angle * Math.PI / 180;
        //to polar coordinates
        int x2 = (int) (x1 + lineLength * Math.sin(angle));
        int y2 = (int) (y1 + lineLength * Math.cos(angle));
        //draw line
        g.drawLine(x1, y1, x2, y2);
    }
}
