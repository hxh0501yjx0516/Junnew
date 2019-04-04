package hib.dialect;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;

public class MySQLServerDialect extends SQLServerDialect {
	public  MySQLServerDialect() {
		 super();
		 registerHibernateType(Types.LONGVARCHAR, Hibernate.STRING.getName());
		}
}
