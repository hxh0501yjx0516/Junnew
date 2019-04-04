package hib;

import org.hibernate.cfg.Configuration;

import com.pancou.ad.vo.AdBox;
import com.pancou.ad.vo.AdCreative;
import com.pancou.ad.vo.AdPlan;
import com.pancou.ad.vo.AdPlanCycle;
import com.pancou.ad.vo.Cms;
import com.pancou.ad.vo.Customer;
import com.pancou.ad.vo.Pay;
import com.pancou.ad.vo.PayType;
import com.pancou.ad.vo.ReadyBox;
import com.pancou.ad.vo.ReadyPlan;
import com.pancou.ad.vo.ReportCount;
import com.pancou.ad.vo.Service;
import com.pancou.ad.vo.UrlAddress;
import com.pancou.ad.vo.Users;
import com.pancou.ad.vo.ViewAdBoxCount;
import com.pancou.ad.vo.ViewReadyBox;
import com.pancou.ad.vo.WebMaster;

public class BaseHibernateClassImport implements HibernateImport{

	@Override
	public void build(Configuration configuration) {
		configuration.addClass(Cms.class);
		configuration.addClass(Service.class);
		configuration.addClass(AdPlan.class);
		configuration.addClass(WebMaster.class);
		configuration.addClass(Customer.class);
		configuration.addClass(AdPlanCycle.class);
		configuration.addClass(Users.class);
		configuration.addClass(ReadyPlan.class);
		configuration.addClass(UrlAddress.class);
		configuration.addClass(ViewAdBoxCount.class);
		configuration.addClass(AdBox.class);
		configuration.addClass(ReadyBox.class);
		configuration.addClass(AdCreative.class);
		configuration.addClass(PayType.class);
		configuration.addClass(Pay.class);
		configuration.addClass(ReportCount.class);
		configuration.addClass(ViewReadyBox.class);
	}
}
