package com.okmillet.regulate.framework;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

/**
 * @author yanming
 */
public abstract class BaseService <E,PK extends Serializable> {
	
	protected Log logger = LogFactory.getLog(getClass());

	protected abstract BaseMapper<E,PK> getBaseMapper();

	public E getById(PK id) {
		return (E)getBaseMapper().getById(id);
	}
	
	/** 插入数据 */
	public void save(E entity) throws Exception {
		getBaseMapper().save(entity);
	}
	
	public void removeById(PK id) throws Exception {
		getBaseMapper().deleteById(id);
	}
	
	public void update(E entity) throws Exception {
		getBaseMapper().update(entity);
	}

	public Number count(VEntity p) throws DataAccessException {
		return getBaseMapper().findcount(p);
	}
	
	public List<E> find(VEntity p) throws DataAccessException {
		return getBaseMapper().findlist(p);
	}

	public Page findPage(VEntity p) throws DataAccessException {
		Number totalCount = count(p);
		if(totalCount == null || totalCount.longValue() <= 0) {
			return new Page(1,20,0);
		}
		if(p.getCpage()==null) {
			p.setCpage(1);
		}
		Page page = new Page(p.getCpage(),p.getPageSize(),totalCount.intValue());
		page.setResult(find(p));
		return page;
	}
}
