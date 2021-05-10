package com.okmillet.regulate.framework;

public abstract class VEntity {

	private String sortColumns;
	private Integer cpage;
	private Integer pageSize = 20;

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public Integer getCpage() {
		return cpage;
	}

	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartSize() {
		return pageSize * (cpage - 1);
	}
}
