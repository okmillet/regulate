package com.okmillet.regulate.framework;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Page {

	public Page(Integer cpage, Integer pageSize, Integer totalCount) {
		this.cpage = cpage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	private Integer cpage;
	private Integer totalCount;
	private Integer pageSize;
	private List result;

	public Integer getCpage() {
		return cpage == null ? 1 : cpage;
	}

	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

	public Integer getTotalCount() {
		return totalCount == null ? 0 : totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageSize() {
		return pageSize == null ? 20 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public Integer getTotalPage() {
		if (totalCount <= 0) {
			return 1;
		}
		return (totalCount % pageSize) == 0 ? (totalCount / pageSize) : ((totalCount / pageSize) + 1);
	}
}
