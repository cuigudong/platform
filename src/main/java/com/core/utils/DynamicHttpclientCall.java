//package com.core.utils;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;
//import org.apache.commons.lang3.StringUtils;
//import org.dom4j.Attribute;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//
//import com.core.utils.JacksonUtil;
//
//// 动态构造调用串，灵活性更大
//public class DynamicHttpclientCall {
//
//	private String namespace;
//	private String methodName;
//	private String wsdlLocation;
//	private String soapResponseData;
//
//	public DynamicHttpclientCall(String namespace, String methodName, String wsdlLocation) {
//
//		this.namespace = namespace;
//		this.methodName = methodName;
//		this.wsdlLocation = wsdlLocation;
//	}
//
//	private int invoke(Map<String, String> patameterMap) throws Exception {
//		PostMethod postMethod = new PostMethod(wsdlLocation);
//		String soapRequestData = buildRequestData(patameterMap);
//
//		byte[] bytes = soapRequestData.getBytes("utf-8");
//		InputStream inputStream = new ByteArrayInputStream(bytes, 0, bytes.length);
//		RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, bytes.length,
//				"application/soap+xml; charset=utf-8");
//		postMethod.setRequestEntity(requestEntity);
//
//		HttpClient httpClient = new HttpClient();
//		int statusCode = httpClient.executeMethod(postMethod);
//		soapResponseData = postMethod.getResponseBodyAsString();
//
//		return statusCode;
//	}
//
//	private String buildRequestData(Map<String, String> patameterMap) {
//		StringBuffer soapRequestData = new StringBuffer();
//		// soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//		soapRequestData.append(
//				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ref=\"refundMerchantOrder\">");
//		soapRequestData.append("<soapenv:Header/>");
//		soapRequestData.append("<soapenv:Body>");
//		soapRequestData.append("<ref:refund>");
//		soapRequestData.append("<refundReqDTO>");
//
//		Set<String> nameSet = patameterMap.keySet();
//		for (String name : nameSet) {
//			soapRequestData.append("<" + name + ">" + patameterMap.get(name) + "</" + name + ">");
//		}
//		soapRequestData.append("</refundReqDTO>");
//		soapRequestData.append("</ref:refund>");
//		soapRequestData.append("</soapenv:Body>");
//		soapRequestData.append("</soapenv:Envelope>");
//
//		return soapRequestData.toString();
//	}
//
//	/**
//	 * @param args
//	 * @throws Exception
//	 */
//	public static void execute(String url) throws Exception{
//
//		DynamicHttpclientCall dynamicHttpclientCall = new DynamicHttpclientCall("refundMerchantOrder",
//				"refundMerchantOrder", url);
//		// 创建参数队列
//		//RefundReqDTO order = new RefundReqDTO();
//		//String json = JacksonUtil.writeValueAsString(order);
//		//System.out.println(json);
//		Map<String, String> map = JacksonUtil.readValue("", Map.class);
//		//String signText = order.signatureData("123456");
//		//System.out.println(signText);
//		//ap.put("signMsg", getSHA256Str(signText));
//		System.out.println(map.get("signMsg"));
//		System.out.println(JacksonUtil.writeValueAsString(map));
//		String soapRequestData = dynamicHttpclientCall.buildRequestData(map);
//		System.out.println(soapRequestData);
//		int statusCode = dynamicHttpclientCall.invoke(map);
//		if (statusCode == 200) {
//			System.out.println("调用成功！");
//			System.out.println(dynamicHttpclientCall.soapResponseData);
//		} else {
//			System.out.println("调用失败！错误码：" + statusCode);
//		}
//		String result = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:refundResponse xmlns:ns2=\"refundMerchantOrder\"><refundResDTO><version>2.0</version><merchantId>801128553112016</merchantId><charset>utf-8</charset><language>cn</language><signType>SHA256</signType><signMsg>B776104158EE8BDD2D30EEC9441673CD2E3B4C127F1147EA9C771C2C11DA7993</signMsg><resultCode>11</resultCode><errorMsg>未知异常</errorMsg><refundOrderNo>201710250000001</refundOrderNo><errCode>9002</errCode></refundResDTO></ns2:refundResponse></soap:Body></soap:Envelope>";
//		getRoot(result);
//
//	}
//
//	public static void getRoot(String xmlString) throws DocumentException {
//		Document document = DocumentHelper.parseText(xmlString);
//		final Element root = document.getRootElement();// 获取根节点
//		Map<String, String> resultMap = new HashMap<String, String>();
//		getNodesIsTextNotNull(resultMap, root);
//		System.out.println(JacksonUtil.writeValueAsString(resultMap));
//	}
//
//	// 从指定节点Element node开始,递归遍历其所有子节点*/
//	@SuppressWarnings("unchecked")
//	public static void getNodesIsTextNotNull(Map<String, String> resultMap, final Element node) {
//		String name = node.getName();
//		String textTrim = node.getTextTrim();
//		if (StringUtils.isNotEmpty(textTrim)) {
//			System.out.println("节点名称：" + name + ",节点内容：" + textTrim);
//			resultMap.put(name, textTrim);
//		}
//		// 递归遍历当前节点所有的子节点
//		final List<Element> listElement = node.elements();// 所有一级子节点的list
//		for (final Element e : listElement) {// 遍历所有一级子节点
//			getNodesIsTextNotNull(resultMap, e);// 递归
//		}
//	}
//
//	// 从指定节点Element node开始,递归遍历其所有子节点*/
//	@SuppressWarnings("unchecked")
//	public static void getNodes(final Element node) {
//		System.out.println("-------开始新节点-------------");
//		// 当前节点的名称、文本内容和属性
//		System.out.println("当前节点名称：" + node.getName());// 当前节点名称
//		System.out.println("当前节点的内容：" + node.getTextTrim());// 当前节点内容
//		final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
//		for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
//			final String name = attr.getName();// 属性名称
//			final String value = attr.getValue();// 属性的值
//			System.out.println("属性名称：" + name + "---->属性值：" + value);
//		}
//		// 递归遍历当前节点所有的子节点
//		final List<Element> listElement = node.elements();// 所有一级子节点的list
//		for (final Element e : listElement) {// 遍历所有一级子节点
//			getNodes(e);// 递归
//		}
//	}
//}