package com.cefn.flunstellas;

import java.util.List;

public interface Graph extends Saveable{
	
	public String getTitle();
	public String getDescription();
	public String getHeightLabel();
	public String getRadiusLabel();
	public String getAngleLabel();

	public void setTitle(String label);
	public void setDescription(String label);
	public void setHeightLabel(String label);
	public void setRadiusLabel(String label);
	public void setAngleLabel(String label);

	public Author getAuthor();
	
	public List<Balloon> getBalloons();
	
}
