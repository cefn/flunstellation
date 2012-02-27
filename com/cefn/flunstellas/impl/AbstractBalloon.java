package com.cefn.flunstellas.impl;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.cefn.flunstellas.Balloon;
import com.cefn.flunstellas.Polar;
import com.cefn.flunstellas.Rotation;

@SuppressWarnings("serial")
public abstract class AbstractBalloon implements Balloon{

	long id;
	String title,description;
	float scale;
	Rotation rotation;
	Polar center;
	boolean showLabel = true;
	
	protected AbstractBalloon() {
		
	}
	
	public AbstractBalloon(long id, String title, String description, Polar center, float scale, Rotation rotation){
		this.id = id;
		this.title = title;
		this.description = description;
		this.center = center;
		this.scale = scale;
		this.rotation = rotation;
	}
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	@JsonDeserialize(as=FixedPolar.class)
	public Polar getCenter() {
		return center;
	}

	public float getScale() {
		return scale;
	}
	
	@JsonDeserialize(as=FixedRotation.class)
	public Rotation getRotation() {
		return rotation;
	}

	public boolean getShowLabel() {
		return showLabel;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}

	public void setCenter(Polar center) {
		this.center = center;
	}
	
	public void setShowLabel(boolean showLabel) {
		this.showLabel = showLabel;
	}

}
