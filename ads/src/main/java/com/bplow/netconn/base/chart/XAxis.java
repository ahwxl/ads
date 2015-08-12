package com.bplow.netconn.base.chart;

public class XAxis {
	
	private Object [] categories;
	private boolean   crosshair = true;
	
	
	
	public XAxis(String[] categories, boolean crosshair) {
		super();
		this.categories = categories;
		this.crosshair = crosshair;
	}
	
	public XAxis() {
		super();
	}
	public Object[] getCategories() {
		return categories;
	}
	public void setCategories(Object[] categories) {
		this.categories = categories;
	}
	public boolean isCrosshair() {
		return crosshair;
	}
	public void setCrosshair(boolean crosshair) {
		this.crosshair = crosshair;
	}
	
	

}
