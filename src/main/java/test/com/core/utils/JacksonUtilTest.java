package test.com.core.utils; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* JacksonUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 29, 2017</pre> 
* @version 1.0 
*/ 
public class JacksonUtilTest { 

@Before
public void before() throws Exception {
    System.out.println("*******************************");
    System.out.println("        Test    start          ");
    System.out.println("*******************************");
} 

@After
public void after() throws Exception {
    System.out.println("*******************************");
    System.out.println("        Test    end          ");
    System.out.println("*******************************");
} 

/** 
* 
* Method: getInstance() 
* 
*/ 
@Test
public void testGetInstance() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: writeValueAsString(Object obj) 
* 
*/ 
@Test
public void testWriteValueAsString() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: readValue(String jsonStr, Class<T> clazz) 
* 
*/ 
@Test
public void testReadValue() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: readValueRefer(String jsonStr, Class<T> clazz) 
* 
*/ 
@Test
public void testReadValueRefer() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: jsonToListBean(String json, Class<T> clazz) 
* 
*/ 
@Test
public void testJsonToListBean() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) 
* 
*/ 
@Test
public void testGetCollectionType() throws Exception { 
//TODO: Test goes here... 
}

} 
