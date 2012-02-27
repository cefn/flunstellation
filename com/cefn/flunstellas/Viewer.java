package com.cefn.flunstellas;

import processing.core.PApplet;

public interface Viewer {
	
	public float getXAngle();
	public float getYOffset();
	public float getZOffset();
	public void setXAngle(float value);
	public void setYAngle(float value);
	public void setYOffset(float value);
	public void setZOffset(float value);
	
	public void drawGraph(Graph graph, PApplet applet);

}
