package com.web.store.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("genericDao")
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

	private final Class<E> entityClass;
	

	public AbstractGenericDao() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public E findById(final Serializable id) {
		return (E) getSession().get(this.entityClass, id);
	}

	public Serializable save(E entity, int userId) {
		return save(entity);
	}

	public Serializable save(E entity) {
		return getSession().save(entity);
	}

	public void saveOrUpdate(E entity, int userId) {
		saveOrUpdate(entity);
	}

	public List<E> findAll() {
		return getSession().createCriteria(this.entityClass).list();
	}

	public void saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(E entity) {
		getSession().delete(entity);
	}

	public E merge(E e) {
		return (E) getSession().merge(e);
	}

	public List<Object> findByQuery(String properties, String Query) {

		String subQuery1 = (properties == "") ? " " : ("SELECT " + properties);

		String subQuery2 = (Query == "") ? "" : (" WHERE " + Query);

		return getSession().createQuery(subQuery1 + " From " + this.entityClass.getName() + subQuery2).list();
	}

	public Object findById(Serializable id, String properties, String identifier) {

		String subQuery1 = (properties == "") ? " " : ("SELECT " + properties);

		return getSession()
				.createQuery(subQuery1 + " From " + this.entityClass.getName() + " WHERE " + identifier + " = " + id)
				.getSingleResult();
	}

	public List<Object> findAll(String properties) {

		String subQuery = (properties == "") ? " " : ("SELECT " + properties);

		return getSession().createQuery(subQuery + " From " + this.entityClass.getName()).list();
	}

	public void update(Serializable id, Map<String, String> fields, String identifier, int userId, String subQuery1) {

		List<String> subQuery = new ArrayList<String>();

		fields.put("updatedOn", new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		fields.put("updatedBy", Integer.toString(userId));

		int i = 0;
		for (Map.Entry<String, String> entry : fields.entrySet()) {

			if (entry.getValue() == null) {
				subQuery.add(entry.getKey() + " = " + entry.getValue() + " ");
			} else {
				subQuery.add(entry.getKey() + " = '" + entry.getValue() + "'");
			}

			i++;
		}

		if (subQuery1 != "") {
			subQuery1 = " AND " + subQuery1;
		}

		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery("Update " + this.entityClass.getName() + " Set "
				+ String.join(", ", subQuery) + " WHERE " + identifier + " = " + id);
		query.executeUpdate();
	}

	public void clear() {
		getSession().clear();

	}

	public void flush() {
		getSession().flush();
	}
}

