package com.dispinar.butcetakip.server.common.query;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PeriodQueryParamsWrapper {
	
	private String name;
	
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Date minBeginDate;
	
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Date maxBeginDate;
	
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Date minEndDate;
	
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Date maxEndDate;

	public Date getMinBeginDate() {
		return minBeginDate;
	}

	public void setMinBeginDate(Date minBeginDate) {
		this.minBeginDate = minBeginDate;
	}

	public Date getMaxBeginDate() {
		return maxBeginDate;
	}

	public void setMaxBeginDate(Date maxBeginDate) {
		this.maxBeginDate = maxBeginDate;
	}

	public Date getMinEndDate() {
		return minEndDate;
	}

	public void setMinEndDate(Date minEndDate) {
		this.minEndDate = minEndDate;
	}

	public Date getMaxEndDate() {
		return maxEndDate;
	}

	public void setMaxEndDate(Date maxEndDate) {
		this.maxEndDate = maxEndDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
