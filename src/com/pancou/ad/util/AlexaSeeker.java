package com.pancou.ad.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Administrator
 */
public class AlexaSeeker {
	public static final String baseURL = "http://data.alexa.com/data?cli=10&dat=snba&url=";

	/** Creates a new instance of AlexaSeeker */
	public AlexaSeeker() {
	}

	/**
	 * getValues(String node1,String attrib,String
	 * webadd)，node1为XML的节点，attrib为节点的属性,webadd为要测试的网址
	 * 本方法只能得到SD节点内的子节点属性的值，所有的node,attrib这两值都必需是SD节点里的东东的
	 * 
	 * @param node1
	 *            XML的节点,必须是SD节点里的东东
	 * @param attrib
	 *            节点的属性
	 * @param webadd
	 *            为要测试的网址
	 * @return 返回Alexa值
	 */
	public String getValues(String node1, String attrib, String vwebadd) {
		String sd = "SD";
		String totalURL = ""; // 用来处理的总的网址
		URL url = null;
		URLConnection conn = null;
		Document doc = null; // 存放XML文件的对象
		DocumentBuilderFactory factory; // 定义工厂API，使应用程序能够从 XML 文档获取生成 DOM
										// 对象树的解析器
		DocumentBuilder docBuilder = null; // 定义 API， 使其从 XML 文档获取 DOM 文档实例
		Element root;
		String webadd = vwebadd.trim();
		if(webadd.indexOf("http://") == -1 && webadd.indexOf("www.") == -1){
			webadd = "http://www."+webadd;
		}else if(webadd.indexOf("http://") == -1 && webadd.indexOf("www.") != -1){
			webadd = "http://"+webadd;
		}else if(webadd.indexOf("http://") != -1 && webadd.indexOf("www.") == -1){
			//有http:// ,没有www.
			String pruffex = webadd.substring(0,7);
			String suffex = webadd.substring(7);
			webadd = pruffex+"www."+suffex;
		}
		totalURL = baseURL + webadd;
		try {
			url = new URL(totalURL);
			conn = url.openConnection();
			conn.connect();

			factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false); // 指定由此代码生成的解析器将验证被解析的文档。
			try {
				docBuilder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException ex) {
				ex.printStackTrace();
			}
			try {
				doc = docBuilder.parse(conn.getInputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (SAXException ex) {
				ex.printStackTrace();
			}
			System.out.println("parse successfull");
			root = doc.getDocumentElement(); // 该属性允许直接访问文档的文档元素的子节点。得到XML文档的根节点
			NodeList allchildnodes = root.getChildNodes(); // 得到子节点
			for (int i = 0; i < allchildnodes.getLength(); i++) { // 循环所有子节点

				Node eachno = allchildnodes.item(i); // 得到每一个节点
				if (eachno.getNodeType() == Node.ELEMENT_NODE) {
					if (eachno.getNodeName() == sd) { // 获得SD这个节点，SD节点里有一个节点属性包含了排名值
						NodeList sbnodes = eachno.getChildNodes();// 得到SD节点的所有子节点放入sbnodes中
						for (int j = 0; j < sbnodes.getLength(); j++) {// 循环判断SD的子节点
							Node sbnode = sbnodes.item(j);
							if (sbnode.getNodeName() == node1) { // 如果子节点是传入的第一个参数的话
								String value = sbnode.getAttributes()
										.getNamedItem(attrib).getNodeValue();
								System.out.println(sbnode.getAttributes()
										.getNamedItem(attrib).getNodeValue());
								return value;
							}
						}

					}
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		AlexaSeeker a = new AlexaSeeker();
		System.out.println(a.getValues("POPULARITY", "TEXT","j2mehome.com"));
//		String address = "http://j2mehome.com";
//		int a = address.indexOf("://");
//		System.out.println(address.substring(a));
	}

}
