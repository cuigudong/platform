package com.xxx.common.util;

/**
 * Created by sks on 2018/1/2.
 */

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

/**
 *
 * 1. 持有Mapper的单例. 2. 返回值类型转换. 3. 批量转换Collection中的所有对象. 4.
 * 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 *
 * @ClassName: BeanMapper
 * @Description: 将Dozer换成orika, 实现深度转换Bean<->Bean的Map per.实现:
 * @author gd.cui
 * @date
 */

public class BeanUtil {

    private static MapperFacade mapper = null;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    /**
     *
     * @Description:转换为对象类型
     * @param source
     * @param destinationClass
     * @return
     * @author gd.cui
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     *
     * @Description:转换成Collection
     * @param soucre
     * @param destinationClass
     * @return
     * @author gd.cui
     */
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

    // /**
    // * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
    // */
    // private static DozerBeanMapper dozer = new DozerBeanMapper();
    //
    // /**
    // * 基于Dozer转换对象的类型.
    // */
    // public static <T> T map(Object source, Class<T> destinationClass) {
    // return dozer.map(source, destinationClass);
    // }
    //
    // /**
    // * 基于Dozer转换Collection中对象的类型.
    // */
    // @SuppressWarnings("rawtypes")
    // public static <T> List<T> mapList(Collection sourceList, Class<T>
    // destinationClass) {
    // List<T> destinationList = Lists.newArrayList();
    // for (Object sourceObject : sourceList) {
    // T destinationObject = dozer.map(sourceObject, destinationClass);
    // destinationList.add(destinationObject);
    // }
    // return destinationList;
    // }
    //
    // /**
    // * 基于Dozer将对象A的值拷贝到对象B中.
    // */
    // public static void copy(Object source, Object destinationObject) {
    // dozer.map(source, destinationObject);
    // }

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
