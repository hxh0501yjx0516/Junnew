package com.pancou.ad.dao;

import java.util.ArrayList;
import java.util.List;
import hib.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


public class Dao {
	
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
				qry.setString("value", "%"+nameValue.trim()+"%");
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
					// ��һ����ѯ������ֻ��һ����ѯ�������
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
	 * һ��ҳ�棨�з�ҳ��
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
				if ((i == 0 || flag == false) && pvalue[i] != null && !"".equals(pvalue[i])) {
					// ��һ����ѯ��������ֻ��һ����ѯ����ʱ
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
					flag = true;
				} else if (pvalue[i] != null && !"".equals(pvalue[i]) && flag==true) {
					// ������ѯ����
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}

			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			// ���ò���
			for (int i =0; i< pname.length; i++) {
				if ( pvalue[i] != null && !"".equals(pvalue[i])) {
					qry.setString("value"+i, pvalue[i]);
				}
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
	public void save(Object entity) {
		try {
			getSession().save(entity);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
	}
	/** ����ʵ�� */
	public void saveOrUpdate(Object entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			getSession().beginTransaction().rollback();
		} finally {
			getSession().close();
		}
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
	
	/** ����ʵ��*/
	public void load(Class clazz, java.io.Serializable id){
	
		getSession().load(clazz, id);
	}


	
	/** �������м�¼ */

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
	 * ͳ�ƶ����¼
	 * @param clazz ���Ӧ������
	 * @param pname ��ѯ�Ĳ�����
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
			qry.setString("value", "%"+nameValue.trim()+"%");
			List list = qry.list();
			return list.get(0) == null ? 0 : Integer.parseInt(list.get(0).toString());
		} catch (Exception e) {
			return 0;
		} finally {
			getSession().close();
		}
				
	}
	
	/**
	 * ͳ�ƶ����¼
	 * @param clazz ���Ӧ������
	 * @param pname ��ѯ������
	 * @param nameValue ��ѯ����ֵ
	 * @return
	 */
	public int getCount(Class clazz, String[] pname, String[] pvalue) {
		try {
			getSession().clear();			
			StringBuffer hql = new StringBuffer();
			hql.append("select count(*) from ").append(clazz.getName());
			
			for (int i =0; i< pname.length; i++) {
				// ѭ����ѯ��������
				if (i == 0 && pvalue[i] != null) {
					// ��һ����ѯ��������ֻ��һ����ѯ����ʱ
					hql.append(" where ").append(pname[i]).append("=:value"+i+" ");	
				} else if (pvalue[i] != null) {
					// ������ѯ����
					hql.append(" and ").append(pname[i]).append("=:value"+i+" ");
				}
			}
			Query qry = getSession().createQuery(hql.toString());
			// ���ò���
			for (int i =0; i< pname.length; i++) {
				qry.setString("value"+i, pvalue[i]);
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
	 * ͨ���Զ����������ȡ�б�
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
	 * ���ݱ�����������ѯ
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
				// ��һ����ѯ������ֻ��һ����ѯ����ʱ
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
	 * ����������ѯ��������
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
				// ��һ����ѯ��������ֻ��һ����ѯ���� ʱ
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
	 * ԭ��sql��ѯ
	 * @param firstIndex ��ʼ�α�
	 * @param pagesize ÿҳ����
	 * @param strsql  ������sql
	 * @return
	 */
	public List findBySql(int firstIndex, int pagesize, String strsql){
		try{
			Query qry = getSession().createSQLQuery(strsql);
			// ��һ��
			qry.setFirstResult(firstIndex);
			// ��ҳ���һ��
			qry.setMaxResults(pagesize);
			List l = qry.list();
			return l;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			getSession().close();
		}		
	}

	/**
	 * ͨ���������Ӿ�����Ŀ
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
	 * ��ѯ�ֶε����ֵ
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
	 * �Զ���sql����ɾ��
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
	 * ����������ѯ���޷�ҳ��
	 * @param clazz ����
	 * @param pname ����
	 * @param pvalue ����ֵ
	 * @return
	 */
	public List findNews(Class clazz) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName()).append(" where state=1 ");
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			
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
	 * ����������ѯ���޷�ҳ��
	 * @param clazz ����
	 * @param pname ����
	 * @param pvalue ����ֵ
	 * @return
	 */
	public List findAllByTab(Class clazz) { 
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("FROM ").append(clazz.getName());
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			
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
	 * ����SPͨ�� SQL
	 * @return
	 */
	public List findAllSp (Class clazz1, Class clazz2, int cpid) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("select t2.interid as interid,t2.name as name,t2.longcode as longcode, t2.alias as alias FROM ").append(clazz1.getName()).append(" t1, ");
			hql.append(clazz2.getName()).append(" t2 where t1.interid = t2.interid " );
			hql.append(" and t1.cpid =:cpid and t1.state=1 and t1.balance > 0 order by t1.cpid ");
			
			
			List l = null;
			Query qry = getSession().createQuery(hql.toString());
			qry.setInteger("cpid", cpid);
			l = qry.list();
			
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			getSession().close();
		}
	}

}
