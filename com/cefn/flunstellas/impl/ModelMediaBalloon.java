package com.cefn.flunstellas.impl;

import java.io.File;
import java.io.IOException;

import com.cefn.flunstellas.Polar;
import com.cefn.flunstellas.Rotation;
import com.cefn.flunstellas.Util;

import processing.core.PApplet;
import processing.core.PConstants;
import saito.objloader.OBJModel;

@SuppressWarnings("serial")
public class ModelMediaBalloon extends AbstractBalloon{

	File modelFile;
	File mediaFile;
	OBJModel model;
	PApplet applet = null;
	
	public ModelMediaBalloon(){
		this(null,null);
	}

	public ModelMediaBalloon(File modelFile, File mediaFile){
		this(Util.createRandomId(), "", "", new FixedPolar(), 1.0f, new FixedRotation(), modelFile, mediaFile);
	}
	
	public ModelMediaBalloon(long id, String title, String description, Polar center, float scale, Rotation rotation, File modelFile, File mediaFile){
		super(id,title,description,center,scale,rotation);
		this.modelFile = modelFile;
		this.mediaFile = mediaFile;
	}
			
	public void draw(PApplet applet) {
		applet.pushMatrix();
		if(this.applet != applet){
			if(this.modelFile != null){
				model = new OBJModel(applet, modelFile.getAbsolutePath(),"absolute", PConstants.TRIANGLES);
				model.scale(0.01f);
				model.translateToCenter();
				this.applet = applet;				
			}
		}
		if(this.model != null){
			model.draw();  			
		}
		applet.popMatrix();
	}
	
	public String getModelPath(){
		try{
			if(modelFile != null){
				return modelFile.getCanonicalPath();				
			}
		}
		catch(IOException e){
			System.out.println("Could not derive path for model " + e);
		}
		return null;
	}

	public String getMediaPath(){
		try{
			if(mediaFile != null){
				return mediaFile.getCanonicalPath();				
			}
		}
		catch(IOException e){
			System.out.println("Could not derive path for media " + e);
		}
		return null;
	}
	
	public void setModelPath(String modelPath){
		if(modelPath != null){
			modelFile = new File(modelPath);			
		}
		else{
			modelFile = null;
		}
		model = null;
	}

	public void setMediaPath(String mediaPath){
		if(mediaPath != null){
			mediaFile = new File(mediaPath);
		}
		else{
			mediaFile = null;
		}
	}
	
	
}
