package com.cefn.flunstellas.impl;

import com.cefn.flunstellas.Scale;

@SuppressWarnings("serial")
public class FixedScale implements Scale{
	
	float x,y,z;
	
	public FixedScale(){
		this(1.0f,1.0f,1.0f);
	}
	
	public FixedScale(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setZ(float z) {
		this.z = z;
	}

}
