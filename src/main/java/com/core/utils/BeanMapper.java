package com.core.utils;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BeanMapper {

	private static MapperFacade mapper = null;

	static {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapper = mapperFactory.getMapperFacade();
	}

	/**
	 * 
	 * @Description:
	 * @param source
	 * @param destinationClass
	 * @return
	 * @author gd.cui
	 */
	public static <S, D> D map(S source, Class<D> destinationClass) {
		return mapper.map(source, destinationClass);
	}

	public static <S, D> List<D> mapList(Iterable<S> soucre, Class<D> destinationClass) {
		return mapper.mapAsList(soucre, destinationClass);
	}

	/**
	 * 
	 * @Description: S -> D
	 * @param source
	 * @param destinationObject
	 * @author sks
	 */
	public static void copy(Object source, Object destinationObject) {
		mapper.map(source, destinationObject);
	}

	/**
	 * 
	 * @Description: xml to bean
	 * @param xmlStr
	 * @param cls
	 * @return
	 * @author gd.cui
	 */
	public static <T> T toBean(String xmlStr, Class<T> cls) {
		// XStream xs = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-",
		// "_")));
		XStream xs = new XStream(new DomDriver("GBK", new XmlFriendlyNameCoder("_-", "_")));
		xs.processAnnotations(cls);
		@SuppressWarnings("unchecked")
		T obj = (T) xs.fromXML(xmlStr);
		return obj;
	}

	/**
	 * 
	 * @Description: bean to xml
	 * @param obj
	 * @return
	 * @author gd.cui
	 */
	public static String toXml(Object obj) {
		// XStream xs = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-",
		// "_")));
		XStream xs = new XStream(new DomDriver("GBK", new XmlFriendlyNameCoder("_-", "_")));
		xs.autodetectAnnotations(true);
		return xs.toXML(obj);
	}
}