package com.searchoutfit.app.imageprocess.aboutYouPOJO2;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntitiesItem{

	@JsonProperty("createdAt")
	private String createdAt;

	@JsonProperty("images")
	private List<ImagesItem> images;

	@JsonProperty("masterKey")
	private String masterKey;

	@JsonProperty("isSoldOut")
	private boolean isSoldOut;

	@JsonProperty("id")
	private int id;

	@JsonProperty("isNew")
	private boolean isNew;

	@JsonProperty("isActive")
	private boolean isActive;

	@JsonProperty("updatedAt")
	private String updatedAt;

	@JsonProperty("referenceKey")
	private String referenceKey;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setImages(List<ImagesItem> images){
		this.images = images;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public void setMasterKey(String masterKey){
		this.masterKey = masterKey;
	}

	public String getMasterKey(){
		return masterKey;
	}

	public void setIsSoldOut(boolean isSoldOut){
		this.isSoldOut = isSoldOut;
	}

	public boolean isIsSoldOut(){
		return isSoldOut;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setIsNew(boolean isNew){
		this.isNew = isNew;
	}

	public boolean isIsNew(){
		return isNew;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setReferenceKey(String referenceKey){
		this.referenceKey = referenceKey;
	}

	public String getReferenceKey(){
		return referenceKey;
	}

	@Override
 	public String toString(){
		return 
			"EntitiesItem{" + 
			"createdAt = '" + createdAt + '\'' + 
			",images = '" + images + '\'' + 
			",masterKey = '" + masterKey + '\'' + 
			",isSoldOut = '" + isSoldOut + '\'' + 
			",id = '" + id + '\'' + 
			",isNew = '" + isNew + '\'' + 
			",isActive = '" + isActive + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			",referenceKey = '" + referenceKey + '\'' + 
			"}";
		}
}
