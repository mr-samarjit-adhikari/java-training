package edu.samar.spring.shapes;

public class Point {
	private int xlocation;
	private int ylocation;
	
	public Point() {
		System.out.println("POINT CONTRUCTOR");
	}
	
	public int getXlocation() {
		return xlocation;
	}
	public void setXlocation(int xlocation) {
		this.xlocation = xlocation;
	}
	public int getYlocation() {
		return ylocation;
	}
	public void setYlocation(int ylocation) {
		this.ylocation = ylocation;
	}
	
}
