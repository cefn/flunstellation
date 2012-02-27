package com.cefn.flunstellas.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;


import com.cefn.flunstellas.Author;
import com.cefn.flunstellas.Balloon;
import com.cefn.flunstellas.Graph;

@SuppressWarnings("serial")
public class BasicGraph implements Graph{
		
	Author author;
	
	String title;
	String description;
	String radiusLabel;
	String heightLabel;
	String angleLabel;
	
	List<Balloon> balloons;
	
	public BasicGraph(){
		
	}

	public BasicGraph(Author author, String title, String description){
		this(author, title, description, null);
	}
	
	public BasicGraph(Author author, String title, String description, Collection<Balloon> balloons){
		this.author = author;
		setTitle(title);
		setDescription(description);
		this.balloons = new ArrayList<Balloon>();
		if(balloons != null){
			this.balloons.addAll(balloons);
		}
	}
		
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getRadiusLabel() {
		return radiusLabel;
	}

	public String getHeightLabel() {
		return heightLabel;
	}

	public String getAngleLabel() {
		return angleLabel;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setRadiusLabel(String radiusLabel) {
		this.radiusLabel = radiusLabel;
	}

	public void setHeightLabel(String heightLabel) {
		this.heightLabel = heightLabel;
	}

	public void setAngleLabel(String angleLabel) {
		this.angleLabel = angleLabel;
	}

	@JsonDeserialize(as=BasicAuthor.class)
	public Author getAuthor() {
		return author;
	}	
	
	@JsonDeserialize(as=ArrayList.class, contentAs=ModelMediaBalloon.class)
	public List<Balloon> getBalloons() {
		return balloons;
	}
			
}
