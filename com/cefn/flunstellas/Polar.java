package com.cefn.flunstellas;

public interface Polar extends Saveable, MatrixFactory{

	public void setHeight(float height);
	public void setRadius(float radius);
	public void setAngle(float angle);
	
	public float getHeight();
	public float getRadius();
	public float getAngle();
	
}
