package com.pancou.ad.util;

/** 分布组件,需先对maxResult赋值  */
public class PageBean {

	private static final long serialVersionUID = 1L;
	/** 总记录数 */
	private int maxResult;
	/** 每页显示记录数 */
	private int pagesize;
	/** 总页数 */
	private int pagecount;
	/** 当前页数 */
	private int currentpage;
	/** 当前记录索引 */
	private int firstIndex;
	/** 当前页显示条数*/
	private int everyPage;

	/** 构造方法;只传入 总记录数;使用默认每页显示数10条;默认当前页为第1页 */
	public PageBean(int maxResult) {
		this(maxResult, 10, null);
	}

	/** 构造方法;只传入总记录数/当前页数;使用默认每页显示数 10条; */
	public PageBean(int maxResult, int currentpage) {
		this(maxResult, 10, currentpage);
	}

	/** 构造方法;传入:总记录数/每页显示数/当前页数 */
	public PageBean(int maxResult, int pagesize, Object currentpage) {
		this.maxResult = maxResult;
		this.pagesize = pagesize;
		setPagecount();
		setCurrentpage(currentpage);
		setIndex();
		setPagecount();
		setEveryPage();
	}

	/** 默认构造方法 */
	public PageBean() {
	}

	public int getMaxResult() {
		return maxResult;
	}

	/** 设置最大记录数;由Dao或手写SQL产生; */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getPagesize() {
		return pagesize;
	}

	/** 设置每页显示数;默认为10条/页; */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagecount() {
		return pagecount;
	}

	/** 设置总页数;总页数= 总记录数%每页显示数为整?总记录数%每页显示数: 总记录数%每页显示数+1; */
	public void setPagecount() {
		pagecount = (maxResult % pagesize == 0 ? maxResult / pagesize
				: maxResult / pagesize + 1);
	}

	public int getCurrentpage() {
		return currentpage;
	}

	/** 设置当前页数;如果当前页数<=0、当前页为Null或当前页数>总页数,则当前页数=1; */
	public void setCurrentpage(Object currentpage) {

		this.currentpage = currentpage == null
				|| Integer.parseInt(currentpage.toString()) <= 0
				|| Integer.parseInt(currentpage.toString()) > getPagecount() ? 1
				: Integer.parseInt(currentpage.toString());
	}

	public int getIndex() {
		return firstIndex;
	}

	/** 设置当前记录索引数 */
	public void setIndex() {
		this.firstIndex = (currentpage - 1) * pagesize;
	}

	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage() {
		if (pagecount > currentpage || maxResult % pagesize ==0){
			everyPage = pagesize;
		} else {
			everyPage =  maxResult % pagesize;
			
		}
	}

}

