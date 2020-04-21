package com.searchoutfit.app.imageprocess.aboutYouPOJO2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination{

	@JsonProperty("next")
	private int next;

	@JsonProperty("current")
	private int current;

	@JsonProperty("total")
	private int total;

	@JsonProperty("perPage")
	private int perPage;

	@JsonProperty("last")
	private int last;

	@JsonProperty("prev")
	private int prev;

	@JsonProperty("page")
	private int page;

	@JsonProperty("first")
	private int first;

	public void setNext(int next){
		this.next = next;
	}

	public int getNext(){
		return next;
	}

	public void setCurrent(int current){
		this.current = current;
	}

	public int getCurrent(){
		return current;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setPerPage(int perPage){
		this.perPage = perPage;
	}

	public int getPerPage(){
		return perPage;
	}

	public void setLast(int last){
		this.last = last;
	}

	public int getLast(){
		return last;
	}

	public void setPrev(int prev){
		this.prev = prev;
	}

	public int getPrev(){
		return prev;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setFirst(int first){
		this.first = first;
	}

	public int getFirst(){
		return first;
	}

	@Override
 	public String toString(){
		return 
			"Pagination{" + 
			"next = '" + next + '\'' + 
			",current = '" + current + '\'' + 
			",total = '" + total + '\'' + 
			",perPage = '" + perPage + '\'' + 
			",last = '" + last + '\'' + 
			",prev = '" + prev + '\'' + 
			",page = '" + page + '\'' + 
			",first = '" + first + '\'' + 
			"}";
		}
}
