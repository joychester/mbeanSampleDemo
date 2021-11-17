package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);

			AnnotationConfigApplicationContext context =
					new AnnotationConfigApplicationContext(AppConfig.class);
			ExamplePanel panel = context.getBean(ExamplePanel.class);
			JFrame frame = createFrame();
			frame.add(panel);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

	}
	private static JFrame createFrame() {
		JFrame frame = new JFrame("Spring JMX Integration Example ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500, 400));
		return frame;
	}

}
