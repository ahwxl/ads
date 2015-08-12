package com.bplow.netconn.base.chart;

public class XAxis {
	
	private String [] categories;
	private boolean   crosshair = true;
	public XAxis() {
		super();
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public boolean isCrosshair() {
		return crosshair;
	}
	public void setCrosshair(boolean crosshair) {
		this.crosshair = crosshair;
	}
	
	

}
