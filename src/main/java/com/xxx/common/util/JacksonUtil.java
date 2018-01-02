package com.xxx.common.util;

/**
 * Created by sks on 2018/1/2.
 */

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jackson util
 * <p>
 * obj need private and set/get； do not support inner class；
 *
 * @author gd.cui
 */
public class JacksonUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * bean、array、List、Map --> json
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
     * string --> bean、Map、List(array)
     *
     * @param jsonStr
     * @param clazz
     * @return obj
     * @throws Exception
     */
    public static <T> T readValue(String jsonStr, Class<T> clazz) {
        try {
            ObjectMapper mapper = getInstance();
            // Unrecognized field
            mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonStr, clazz);
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
            // Map<String, String> map = new HashMap<String, String>();
            // map.put("aaa", "111");
            // map.put("bbb", "222");
            // String json = writeValueAsString(map);
            // System.out.println(json);
            // System.out.println(readValue(json, Map.class));
            Sutdent st = new Sutdent();
            User user = new User();
            user.setAge("11");
            user.setName("ww");
            user.setList(null);
            //String json = writeValueAsString(user);
            String json = "{\"name\":\"ww\",\"age\":\"11\",\"list\":[]}";
            System.out.println(json);
            User us = readValue(json, User.class);
            System.out.println(us.getAge() + "," + us.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class User {
        private String name;
        private String age;
        private List<Sutdent> list = new ArrayList();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public List<Sutdent> getList() {
            return list;
        }

        public void setList(List<Sutdent> list) {
            this.list = list;
        }


    }

    public static class Sutdent {
        private String cls;
        private String sts;

        public String getCls() {
            return cls;
        }

        public void setCls(String cls) {
            this.cls = cls;
        }

        public String getSts() {
            return sts;
        }

        public void setSts(String sts) {
            this.sts = sts;
        }

    }
}
