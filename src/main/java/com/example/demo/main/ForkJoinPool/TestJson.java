package com.example.demo.main.ForkJoinPool;

public class TestJson{
	
	private int offerId;
	
	private String name;
	
	private String url;
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getOfferId() {
		return offerId;
	}
	
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	
	@Override
	public String toString() {
		return name+";"+url;
	}
}
