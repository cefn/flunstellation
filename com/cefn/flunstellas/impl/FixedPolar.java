package com.cefn.flunstellas.impl;

import processing.core.PApplet;
import processing.core.PMatrix3D;

import com.cefn.flunstellas.Polar;

@SuppressWarnings("serial")
public class FixedPolar implements Polar {
	
	float radius,height,angle;
	
	public FixedPolar(){
		this(0, 0, 0);
	}
	
	public FixedPolar(float radius, float height, float angle){
		this.radius = radius;
		this.height = height;
		this.angle = angle;
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getRadius() {
		return radius;
	}

	public float getHeight() {
		return height;
	}
	
	public float getAngle() {
		return angle;
	}
			
	public PMatrix3D createMatrix() {
		PMatrix3D matrix = new PMatrix3D();
		matrix.translate(PApplet.sin(angle) * radius, PApplet.cos(angle) * radius, height);
		return matrix;
	}
	
}
