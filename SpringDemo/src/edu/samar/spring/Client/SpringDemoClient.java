package edu.samar.spring.Client;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import sun.management.FileSystemImpl;
import edu.samar.spring.shapes.Point;
import edu.samar.spring.shapes.Rectangle;

public class SpringDemoClient {

	public static void main(String[] args) {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("spring_config.xml");
		Rectangle rect = (Rectangle) appCtx.getBean("rectangle");
//		Point pointA = (Point) rect.getContext().getBean("pointAbean");
//		Point pointB = (Point) rect.getContext().getBean("pointBbean");
//		Point pointC = (Point) rect.getContext().getBean("pointCbean");
//		Point pointD = (Point) rect.getContext().getBean("pointDbean");
//		
//		rect.setPointA(pointA);
//		rect.setPointB(pointB);
//		rect.setPointC(pointC);
//		rect.setPointD(pointD);
		
		rect.draw();

	}

}
