package com.dispinar.butcetakip.server.itemoperations.query;

import com.dispinar.butcetakip.server.common.query.AbstractQueryParamsWrapper;

public class ResourceItemQueryParamsWrapper extends AbstractQueryParamsWrapper {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
