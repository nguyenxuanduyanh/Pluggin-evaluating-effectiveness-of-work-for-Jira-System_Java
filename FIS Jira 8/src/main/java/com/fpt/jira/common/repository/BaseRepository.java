package com.fpt.jira.common.repository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.fpt.jira.common.constant.CommonConstant;

import net.java.ao.Entity;
import net.java.ao.Query;

public class BaseRepository<T extends Entity> {

    protected ActiveObjects ao;

    public BaseRepository(ActiveObjects ao) {
        this.ao = ao;
    }

    /**
     * To create empty entity
     * @return
     */
    public T create() {
        return ao.create(getGenericClass());
    }

    /**
     * To save or update entity
     *
     * @param t
     */
    public void saveOrUpdate(T t) {
        t.save();
    }

    /**
     * To remove entity
     *
     * @param t
     */
    public void remove(T t) {
        ao.delete(t);
    }

    /**
     * To get entity by id
     *
     * @param id
     * @return
     */
    public T get(int id) {
    	T [] t = ao.find(getGenericClass(), Query.select().where("ID = ?", id));
    	if(t.length == 0) {
    		return null;
    	}
        return t[0];
    }
    
    /**
     * To get entity by id
     *
     * @param id
     * @return
     */
    public List<T> findAll() {
    	T [] t = ao.find(getGenericClass(), Query.select());
    	if(t.length == 0) {
    		return new ArrayList<>();
    	}
    	List<T> list = Arrays.asList(t);
        return list;
    }

    /**
     * To get generic class
     *
     * @return
     */
    @SuppressWarnings("unchecked")
	protected Class<T> getGenericClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

