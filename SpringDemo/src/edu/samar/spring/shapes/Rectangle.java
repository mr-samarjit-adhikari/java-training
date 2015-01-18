package edu.samar.spring.shapes;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.css.Rect;

public class Rectangle implements ApplicationContextAware{
	private Point		pointA;
	private Point		pointB;	
	private Point		pointC;
	private Point		pointD;	
	private ApplicationContext  context;

	public Rectangle() {
		System.out.println("RECT CONTRUCTOR");
	}

	public Point getPointA() {
		return pointA;
	}

	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}

	public Point getPointB() {
		return pointB;
	}

	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}

	public Point getPointC() {
		return pointC;
	}

	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}

	public Point getPointD() {
		return pointD;
	}

	public void setPointD(Point pointD) {
		this.pointD = pointD;
	}

	public void draw(){
		System.out.println("Rectangle is drawn. Points are");
		System.out.println("("+getPointA().getXlocation()+"," + 
							  getPointA().getYlocation()+")");
		System.out.println("("+getPointB().getXlocation()+"," + 
				  getPointB().getYlocation()+")");
		System.out.println("("+getPointC().getXlocation()+"," + 
				  getPointC().getYlocation()+")");
		System.out.println("("+getPointD().getXlocation()+"," + 
				  getPointD().getYlocation()+")");		
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
		
	}

	public ApplicationContext getContext() {
		return context;
	}

}
