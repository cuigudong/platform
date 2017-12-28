package com.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/**
 * Jackson util
 * 
 * obj need private and set/get�� do not support inner class��
 * 
 * @author gd.cui
 */
public class JacksonUtil {
	private final static ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper getInstance() {
		return objectMapper;
	}

	/**
	 * bean��array��List��Map --> json
	 * 
	 * @param obj
	 * @return json string
	 * @throws Exception
	 */
	public static String writeValueAsString(Object obj) {
		try {
			return getInstance().writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * string --> bean��Map��List(array)
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return obj
	 * @throws Exception
	 */
	public static <T> T readValue(String jsonStr, Class<T> clazz) {
		try {
			return getInstance().readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T readValueRefer(String jsonStr, Class<T> clazz) {
		try {
			return getInstance().readValue(jsonStr, new TypeReference<T>() {
			});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("hiding")
	public static <T> List<T> jsonToListBean(String json, Class<T> clazz) {
		try {
			JavaType javaType = getCollectionType(ArrayList.class, clazz);
			return getInstance().readValue(json, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return getInstance().getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}

	public static void main(String[] args) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("aaa", "111");
			map.put("bbb", "222");
			String json = writeValueAsString(map);
			System.out.println(json);
			System.out.println(readValue(json, Map.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
