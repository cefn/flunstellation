package com.cefn.flunstellas.impl;

import com.cefn.flunstellas.Author;

@SuppressWarnings("serial")
public class BasicAuthor implements Author{

	String title;
	
	public BasicAuthor(){
		
	}
	
	public BasicAuthor(String title){
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
				
}
