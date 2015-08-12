package com.bplow.netconn.base.chart;

public class HgChartConf {
	
	private XAxis xAxis;
	private YAxis yAxis;
	private Serie[] series;
	
	public HgChartConf() {
		super();
	}
	public XAxis getxAxis() {
		return xAxis;
	}
	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}
	public YAxis getyAxis() {
		return yAxis;
	}
	public void setyAxis(YAxis yAxis) {
		this.yAxis = yAxis;
	}
	public Serie[] getSeries() {
		return series;
	}
	public void setSeries(Serie[] series) {
		this.series = series;
	}
	
	

}
