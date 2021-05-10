package com.okmillet.regulate.framework;

import java.io.Serializable;
import java.util.List;

/**
 * Mapper基类
 * @author yanming
 */
public abstract interface BaseMapper<E,PK extends Serializable> {
    
    public E getById(PK primaryKey);
    
	public void deleteById(PK id);
    
	public void deleteByCondition(E entity);
	
    public void save(E entity);
    
	public void update(E entity);
	
	public List<E> findlist(VEntity p);
	
	public Number findcount(VEntity p);
}
