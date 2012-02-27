package com.cefn.flunstellas;

public interface Balloon extends Drawable, Saveable{
	
	public long getId();
	public String getTitle();
	public String getDescription();
	
	public void setId(long id);
	public void setTitle(String title);
	public void setDescription(String description);

	public Polar getCenter();
	public float getScale();
	public Rotation getRotation();

	public void setCenter(Polar center);
	public void setScale(float scale);
	public void setRotation(Rotation rotation);
	
	public boolean getShowLabel();
	public void setShowLabel(boolean showLabel);
	
}
