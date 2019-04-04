package com.pancou.ad.dao;

import hib.HibernateSessionFactory;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


@SuppressWarnings("unchecked")
public class PlatDao {
	
	/** ���Session */
	public synchronized Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	/**
	 * һ��ҳ��(�з�ҳ)
	 * @param clazz ���Ӧ������
	 * @param firstIndex ��һ��
	 * @param pagesize ��ʾ����
	 * @param pname ��ѯ������
	 * @param intername ��ѯ����ֵ
	 * @return
	 */
	public List findAll(Class clazz, int firstIndex, 
			int pagesize, String pname, String nameValue) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			if (nameValue != null && !"".equals(nameValue)) {
				hql.append(" where ").append(pname).append(" like :value ");
			}
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// ���ò���
			if (nameValue != null && !"".equals(nameValue)) {
				qry.setString("value", "%"+nameValue+"%");
			}
			// ��һ��
			qry.setFirstResult(firstIndex);
			// ��ҳ���һ��
			qry.setMaxResults(pagesize);
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	/**
	 * HQLȫ��ѯ���з�ҳ��
	 * @param hql HQL���
	 * @param firstIndex ��ҳ����
	 * @param pagesize  ��ҳ����
	 * @param condition ƴ������
	 * @param parms ��������
	 * @return ����List
	 */
	public List findAll(int firstIndex, 
			int pagesize,String hql,String condition,Map<String,Object> parms) { 
		try {
			List l = null;
			Query qry = getSession().createQuery(hql+condition);
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			// ��һ��
			qry.setFirstResult(firstIndex);
			// ��ҳ���һ��
			qry.setMaxResults(pagesize);
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	/**
	 * HQLȫ��ѯ���޷�ҳ��
	 * @param hql hql���
	 * @param condition �������
	 * @param parms ��������
	 * @return
	 */
	public List findAll(String hql,String condition,Map<String,Object> parms) { 
		try {
			List l = null; 
			Query qry = getSession().createQuery(hql+condition);
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	/**
	 * ȫ��ѯ���з�ҳ��
	 * @param clazz
	 * @param firstIndex
	 * @param pagesize
	 * @return
	 */
	public List findAll(Class clazz,int firstIndex,int pagesize) { 
		return findAll(clazz,firstIndex,pagesize,"","");
	}
	/**
	 * ȫ��ѯ���޷�ҳ��
	 * @param clazz
	 * @return
	 */
	
	public List findAll(Class clazz) { 
		String[] names= new String[0];
		String[] values=new String[0];
		return findAll(clazz,names,values);
	}
	/**
	 * ����������ѯ���޷�ҳ��
	 * @param clazz ����
	 * @param pname ����
	 * @param pvalue ����ֵ
	 * @return
	 */
	public List findAll(Class clazz, String[] pname, String[] pvalue) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			for (int i =0; i< pname.length; i++) {
				// ѭ����ѯ��������
				if (i == 0 ) {
					// ��һ����ѯ����������ֻ��һ����ѯ���� ʱ
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
				} else {
					// ������ѯ����
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}

			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// ���ò���
			for (int i =0; i< pname.length; i++) {
				// ѭ����ѯ��������
				qry.setString("value"+i, pvalue[i]);
			}
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	/**
	 * ����������ѯ���޷�ҳ�� ��findAll��ͬ���ǣ����Դ�order�Ӿ�
	 * @param clazz ����
	 * @param pname ����
	 * @param pvalue ����ֵ
	 * @param ordersql order�Ӿ� �磺��order by id desc��
	 * @return
	 */
	public List findAllOrder(Class clazz, String[] pname, String[] pvalue,String ordersql) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			for (int i =0; i< pname.length; i++) {
				// ѭ����ѯ��������
				if (i == 0 ) {
					// ��һ����ѯ����������ֻ��һ����ѯ���� ʱ
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
				} else {
					// ������ѯ����
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}
			hql.append(" ").append(ordersql);
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// ���ò���
			for (int i =0; i< pname.length; i++) {
				// ѭ����ѯ��������
				qry.setString("value"+i, pvalue[i]);
			}
			l = qry.list();
			Collections.sort(l);
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}

	
	/**
	 * һ��ҳ��(�з�ҳ)
	 * @param clazz ���Ӧ������
	 * @param firstIndex ��һ��
	 * @param pagesize ��ʾ����
	 * @param pname ��ѯ������
	 * @param intername ��ѯ����ֵ
	 * @return
	 */
	public List findAll(Class clazz, int firstIndex, 
			int pagesize, String[] pname, String[] pvalue) { 
		try {
			boolean flag = false;
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			for (int i =0; i< pname.length; i++) {
				// ѭ����ѯ��������
				if ((i == 0 || flag == false) && pvalue[i] != null) {
					// ��һ����ѯ����������ֻ��һ����ѯ���� ʱ
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
					flag = true;
				} else if (pvalue[i] != null && flag == true) {
					// ������ѯ����
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}

			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// ���ò���
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);
			}
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}
	
	/** ����ʵ�� */
	public int save(Object entity) {
		int id=0;
		try {
			id = Integer.parseInt(getSession().save(entity).toString());
//			System.out.println("�����ʵ���IDΪ��"+id);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
		return id;
	}

	/** ɾ��ʵ�� */
	public void delete(Object entity) {
		try {
			getSession().delete(entity);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			getSession().beginTransaction().rollback();
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
	}
	
	/** ����ʵ�� */
	public void load(Class clazz, java.io.Serializable id){
	
		getSession().load(clazz, id);
	}

	/** ����ID����ʵ�� */
	
	public Object findById(Class clazz, int id) {
		Object obj = getSession().get(clazz, id);
		return obj;
	}
	
	/** �������м�¼*/

	public boolean update(Object entity) {
		boolean flag = false;
		;
		try {
			getSession().beginTransaction().begin();
			getSession().merge(entity);
			getSession().getTransaction().commit();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			getSession().close();
		}
		return flag;
	}
	
	/**
	 * ͳ�ƶ����¼�� 
	 * @param clazz ���Ӧ������
	 * @param pname ��ѯ������
	 * @param nameValue ��ѯ����ֵ
	 * @return
	 */
	public int getCount(Class clazz, String pname, String nameValue) {
		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			sb.append("select count(*) from " + clazz.getName());
			if (nameValue != null && !"".equals(nameValue)) {
				sb.append(" where ").append(pname).append(" like :value ");
			}
			Query qry = getSession().createQuery(sb.toString());
			// ���ò���
			if (nameValue != null && !"".equals(nameValue)) {				
				qry.setString("value", "%"+nameValue.trim()+"%");
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			return 0;
		} finally {
			getSession().close();
		}
				
	}
	/**
	 *  ���ݲ�ѯ����ͳ�ƶ����¼�� 
	 * @param clazz ��ѯʵ����
	 * @param condition ������䣨������ͷ��and��ʼ���ֶ���ǰ��ʵ�����Сд����ĸ����: and u.userId = :userId��
	 * @param parms ��������
	 * @return
	 */
	public int getCount(Class clazz, String condition,Map<String,Object> parms) {
		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			String buffux = "";
			if(null != clazz.getName()){
				int index = clazz.getName().lastIndexOf(".");
				buffux = clazz.getName().substring(index+1,index+2).toLowerCase();
			}
			sb.append("select count(*) from " + clazz.getName()).append(" ").append(buffux).append(" where 1=1 ").append(condition);
			Query qry = getSession().createQuery(sb.toString());
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			getSession().close();
		}
				
	}
	/**
	 * 
	 *  ���ݲ�ѯ����ͳ�ƶ����¼�� (����SQL���)
	 * @param sql sql��ѯ����Դ��������
	 * @param condition where����
	 * @param parms ��������
	 * @return
	 */
	public int getCount(String sql,Map<String,Object> parms) {
		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			sb.append(sql);
			Query qry = getSession().createSQLQuery(sb.toString());
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			getSession().close();
		}
				
	}
	/**
	 * ͨ�����Զ����������ȡ�б�
	 * @param clazz ���Ӧ������
	 * @param firstIndex ��ʼ�α�
	 * @param pagesize ÿҳ����
	 * @param strsql �������
	 * @return
	 */
	public List findAllBySql(Class clazz, int firstIndex, 
			int pagesize, String strsql) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			if (strsql != null && !"".equals(strsql)) {
				hql.append(" where "+strsql);
			}
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// ��һ��
			qry.setFirstResult(firstIndex);
			// ��ҳ���һ��
			qry.setMaxResults(pagesize);
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	
	}

	/**
	 * ���ݱ�����������ѯ����
	 * @param tabName ����
	 * @param index ��һҳ
	 * @param pagesize ��ʾ����
	 * @param pname ������
	 * @param pvalue ����ֵ
	 * @return
	 */
	public List findByPara(String tabName, int index, int pagesize, String[] pname, String[] pvalue){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(tabName);

		for (int i =0; i< pname.length; i++) {
			// ѭ����ѯ��������
			if (i == 0 ) {
				// ��һ����ѯ����������ֻ��һ����ѯ���� ʱ
				sql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
			} else {
				// ������ѯ����
				sql.append(" and ").append(pname[i]).append("=:value"+i+" ");
			}
		}
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			// ���ò���
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);
			}
			// ��ҳ��һ��
			qry.setFirstResult(index);
			// ��ҳ��ҳ����ʾ����
			qry.setMaxResults(pagesize);
			return qry.list();
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
			
		} finally{
			getSession().close();
		}
	}
	

	/**
	 * ����������ѯ������������
	 * @param tabName ����
	 * @param pname   ��ѯ����
	 * @param pvalue  ����ֵ
	 * @return
	 */
	public int countByPara(String tabName, String[] pname, String[] pvalue){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from ").append(tabName);

		for (int i =0; i< pname.length; i++) {
			// ѭ����ѯ��������
			if (i == 0 ) {
				// ��һ����ѯ����������ֻ��һ����ѯ���� ʱ
				sql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
			} else {
				// ������ѯ����
				sql.append(" and ").append(pname[i]).append("=:value"+i+" ");
			}
		}
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());
			// ���ò���
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);
			}
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list().get(0).toString());
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
			
		} finally{
			getSession().close();
		}
	}
	/**
	 * ��������ԭ��̬SQL��ѯ
	 * @param firstIndex
	 * @param pagesize
	 * @param strsql
	 * @return
	 */
	public List findBySql(int firstIndex, int pagesize, String strsql) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			
			qry.setFirstResult(firstIndex);
			
			qry.setMaxResults(pagesize);
			l = qry.list();
			return l;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {			
			getSession().close();
		}
	}
	/**
	 * ԭ��̬SQL��ѯ���Ա�������ʽ���÷���ֵ
	 * @param firstIndex  ��ҳ��ʼ
	 * @param pagesize ÿҳ����
	 * @param strsql sql���
	 * @param parms ����map
	 * @param objectes ��ʾ�������Ƽ���(�����ƶ�Ӧsql����б������������Ϊclass����������sql�ж������ΪСд����ĸ)
	 * @return 
	 */
	
	public List findBySql(int firstIndex, int pagesize, String strsql,Map<String,Object> parms,List<Object> objectes) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			for(int i=0;i<objectes.size();i++){
				Object o = objectes.get(i);
				if(o instanceof String){
					qry.addScalar(o.toString(),Hibernate.STRING);
				}else if(o instanceof Class){
					int index = ((Class)o).getName().lastIndexOf(".");
					String className = ((Class)o).getName().substring(index+1,index+2).toLowerCase();
					qry.addEntity(className,(Class)o);
				}
			}
//			qry.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			qry.setFirstResult(firstIndex);
			
			qry.setMaxResults(pagesize);
			l = qry.list();
			return l;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {			
			getSession().close();
		}
	}
	/**
	 * ��������ԭ��̬sql��ѯ
	 * @param firstIndex 
	 * @param pagesize 
	 * @param strsql SQL���
	 * @param parms ����MAP
	 * @return
	 */
	
	public List findBySql(int firstIndex, int pagesize, String strsql,Map<String,Object> parms) {
		List l = null;
		try {
			SQLQuery qry = (SQLQuery)getSession().createSQLQuery(strsql);
//			.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			qry.setFirstResult(firstIndex);
			
			qry.setMaxResults(pagesize);
			l = qry.list();
			return l;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {			
			getSession().close();
		}
	}
	/**
	 * ��������ԭ��̬sql��ѯ���޷�ҳ��
	 * @param firstIndex 
	 * @param pagesize 
	 * @param strsql SQL���
	 * @param parms ����MAP
	 * @return
	 */
	
	public List findBySql(String strsql,Map<String,Object> parms) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			l = qry.list();
			return l;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {			
			getSession().close();
		}
	}
	/**
	 * ��������ԭ��̬sql��ѯ���޷�ҳ��
	 * @param strsql
	 * @param parms
	 * @param cols
	 * @return
	 */
	
	public List findBySql(String strsql,Map<String,Object> parms,List<Object> cols) {
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			for(int i=0;i<cols.size();i++){
				Object o = cols.get(i);
				if(o instanceof String){
					qry.addScalar(o.toString(),Hibernate.STRING);
				}else if(o instanceof Class){
					int index = ((Class)o).getName().lastIndexOf(".");
					String className = ((Class)o).getName().substring(index+1,index+2).toLowerCase();
					qry.addEntity(className,(Class)o);
				}
			}
			Set keys = parms.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()){
				String key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			l = qry.list();
			return l;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {			
			getSession().close();
		}
	}
	/**
	 * �Զ���SQL��ѯSUM
	 * @param strsql
	 * @return
	 */
	public List getSum(String strsql){
		List l = null;
		try {
			SQLQuery qry = getSession().createSQLQuery(strsql);
			l = qry.list();
			return l;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}finally {			
			getSession().close();
		}
	}
	
	/**
	 * ͨ���������Ӿ��ѯ�ܼ���Ŀ
	 * @param tabName ����
	 * @param sqlCondi  sql�����Ӿ�
	 * @return
	 */
	public int countBySql(String tabName, String sqlCondi){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from ").append(tabName);
		if (sqlCondi!=""&&sqlCondi!=null)
			sql.append(" where ").append(sqlCondi);
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());			
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list().get(0).toString());
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
			
		} finally{
			getSession().close();
		}
	}
	
	/**
	 * ��ѯ�ֶ����ֵ
	 * @param tabName ����
	 * @param colName  �ֶ���
	 * @return
	 */
	public int getColMax(String tabName, String colName){
		String sql ="";
		sql="select max("+colName+") from "+tabName;
		try{
			SQLQuery qry = getSession().createSQLQuery(sql.toString());			
			return qry.list().get(0) == null ? 0 : Integer.parseInt(qry.list().get(0).toString());
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
			
		} finally{
			getSession().close();
		}
	}
	
	/**
	 * �Զ���sql��ɾ��
	 * @param sql
	 */
	public void doSql (String sql) {
		try{			
			getSession().beginTransaction();
			Query qry = getSession().createQuery(sql);
			qry.executeUpdate();
			getSession().beginTransaction().commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			getSession().close();
			
		} finally {
			
			getSession().close();
		}
	}
	/**
	 * �Զ���sql��ɾ�ģ���������
	 * @param sql
	 */
	public int doSql (String sql,Map<String,Object> parms) {
		int count = 0;
		try{			
			 
			getSession().beginTransaction();
			Query qry = getSession().createQuery(sql).setProperties(parms);
			count = qry.executeUpdate();
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().close();
		} finally {
			
			getSession().close();
		}
		return count;
	}
	/**
	 * ִ�д洢����
	 * @param strsql �洢�������
	 * @param parms ����
	 * @return
	 */
	
	public int doCall(String strsql,Map<Integer,Object> parms) {
		int count = 0;
		try {
			getSession().beginTransaction();
			SQLQuery qry = getSession().createSQLQuery(strsql);
			Set keys = parms.keySet();
			Iterator<Integer> it = keys.iterator();
			while(it.hasNext()){
				int key = it.next();
				qry.setParameter(key, parms.get(key));
			}
			count = qry.executeUpdate();
			getSession().beginTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		}finally {			
			getSession().close();
		}
		return count;
	}
	/**
	 * �Զ���sql��ѯ,���ֶ��ر�����
	 * @param sql
	 */
	public Query query(String sql,int firstIndex,int pagesize) {
		Query qry= query(sql);
		if(0!=firstIndex && 0!=pagesize){
			qry.setFirstResult(firstIndex);
			qry.setMaxResults(pagesize);
		}
		return qry;
	}
	/**
	 * �Զ���sql��ѯ,���ֶ��ر�����
	 * @param sql
	 */
	public Query query(String sql) {
		Query qry = getSession().createQuery(sql);
		return qry;
	}
	public void closeSession(){
		if(getSession() !=null && getSession().isOpen()){
			getSession().close();
		}
	}
	/** ��ҳ��:��ȡָ�����������¼ ���� HQL,��ʼλ��,����¼��; 2010-4-27*/
	public List getPage(Class clazz, int firstIndex, int pagesize, HttpServletRequest request) {
		
		try {
			getSession().clear();
			StringBuffer sb = new StringBuffer();
			sb.append("select * from cp_user where 1=1 ");
			if("1".equals(request.getParameter("sonid"))) {//��ѯ�����˻� �������˻�
				sb.append(" and pcpid=0 ");
			} else {
				sb.append(" and pcpid>-1 "); //������һ���˻�
			}
			if("".equals(request.getParameter("cpid"))||request.getParameter("cpid")==null) {//����ѯcpid
				sb.append(" and cpid>-1 ");
			} else {
				sb.append(" and cpid=:cpid ");
			}
			if(!"".equals(request.getParameter("qryname")) && request.getParameter("qryname")!=null) {// ��¼����ѯ				
				sb.append(" and name like '%qryname%' ");
			}
			
			SQLQuery query = getSession().createSQLQuery(sb.toString());

		//	Query query = getSession().createSQLQuery("select name from sp_service group by name");
			query.setFirstResult(firstIndex);
			query.setMaxResults(pagesize);
			if(!"".equals(request.getParameter("cpid")) && request.getParameter("cpid")!=null) {//����ѯcpid
				query.setString("cpid", request.getParameter("cpid"));
			}
			if(!"".equals(request.getParameter("qryname")) && request.getParameter("qryname")!=null) {// ��¼����ѯ				
				query.setString("qryname", request.getParameter("qryname"));
			}
			
			
			
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
		
	}
}
