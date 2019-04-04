package com.pancou.ad.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页基本处理类
 * 
 * @author tmb
 * @version 1.00
 */
public class PagingHandle  {

	/**
	 * 获得分页的参数集
	 * 
	 * @param page 当前页
	 * @param size 每页条数
	 * @param rows 总条数
	 * @return
	 */
	public final static Map<String, Integer> getPagingParams (int page, int size, int rows) {
		Map<String, Integer> _result = new HashMap<String, Integer> ();
		int first, last, count;
		if (size <= 0) size = 20;
			count = rows / size + ((rows % size) != 0 ? 1 : 0); 
		if (count <= 0) count = 1;
		if (page < 1) page = 1;
		else if (page > count) page = count;
		first = (page - 1) * size;
		last = first + size - 1;
		if (last >= rows)
			last = rows - 1;
		_result.put("page", page);//起始页
		_result.put("size", size);//每页显示条数
		_result.put("count", count);//总页数
		_result.put("first", first);//条目起始数
		_result.put("last", last);//条目最终数
		_result.put("rows", rows);//数据总条数
		return _result;
	}
	
	/**
	 * 获得分页返回到前台的结果集
	 * 
	 * @param pars getPagingParams返回的Map
	 * @param resultListName 返回的list名字
	 * @param resultList 返回至前台的数据集
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final static Map<String, Object> getResultMap (Map<String, Integer> pars, String resultListName, List resultList) {
		Map<String, Object> _result = new HashMap<String, Object>();
		_result.put("pageNum", pars.get("page"));
		_result.put("totalCount", pars.get("rows"));
		_result.put("numPerPage", pars.get("size"));
		_result.put("pageCount", pars.get("count"));
		_result.put("first", pars.get("first")+1);
		_result.put("last", pars.get("last")+1);
		_result.put(resultListName, resultList);
		return _result;
	}
}
