package com.pancou.ad.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadExcel {
	
	private static InputStream is = null;
	private static Workbook rwb = null;

	static String createTableSql = "";// 创建数据库的sql
	static String colType = "TEXT";// 字段类型
	static String key = "id";// 主键
	static String charSet = "utf8";// 表格字符类型
	static String ENGINE = "InnoDB";// 表格类型
	static String tableName = "second1";// 表名称
	static String colName = "col";// 默认字段名
	static Connection conn = null;

	/**
	 * 取出数据后存于ArrayList中
	 * @param fileName Excel文件路径及名
	 * @return 返回 ArrayList
	 */
	public static ArrayList readExcel(HttpServletRequest request, String fileName) {
		
		ArrayList<HashMap<Integer,String>> al = new ArrayList<HashMap<Integer,String>>();
		try {
			is = new FileInputStream(fileName);// 创建输入
			rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0); // 读取第一个sheet
			int colNum = rs.getColumns();// 列数
			int rowNum = rs.getRows();// 行数

			String strValue = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 1; i < rowNum; i++) {
				strValue = "";
				HashMap<Integer,String> hs = new HashMap<Integer,String>();
				for (int j = 0; j < colNum; j++) {
					Cell c = rs.getCell(j, i);
					if ( j == 0 ) {
						DateCell datec00 = (DateCell) rs.getCell(0, i);
						//System.out.println("UUUUUUUUUUUUUUUu"+sdf.format(datec00.getDate()));
						strValue = sdf.format(datec00.getDate());	
					
					} else {
						
						strValue = c.getContents();						
					}
					hs.put(j, strValue);
				}
				al.add(hs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			// 关闭 workbook
			if (rwb != null) {
				rwb.close();
			}
            if(is !=null){
                try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
			// 读完文件后删除该文件
			Common.deleteFile(fileName);
			// 清空session中的 文件名
			request.getSession().setAttribute("fileName", "");
		}
		return al;
	}

}
