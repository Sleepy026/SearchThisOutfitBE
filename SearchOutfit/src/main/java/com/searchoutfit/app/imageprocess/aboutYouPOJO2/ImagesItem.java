package com.searchoutfit.app.imageprocess.aboutYouPOJO2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagesItem{

	@JsonProperty("hash")
	private String hash;

	public void setHash(String hash){
		this.hash = hash;
	}

	public String getHash(){
		return hash;
	}

	@Override
 	public String toString(){
		return 
			"ImagesItem{" + 
			"hash = '" + hash + '\'' + 
			"}";
		}
}
