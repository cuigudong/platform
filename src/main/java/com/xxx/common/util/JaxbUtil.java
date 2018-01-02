package com.xxx.common.util;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

/**
 * Created by sks on 2018/1/2.
 *
 *
 */
@SuppressWarnings("restriction")
public class JaxbUtil {

    public static final String NAMESPACE_CEB_URI = "http://www.chinaport.gov.cn/ceb";

    public static final String NAMESPACE_CEB_PREFIX = "ceb";

    public static final String NAMESPACE_XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";

    public static final String NAMESPACE_XSI_PREFIX = "xsi";

    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * jaxb transfer java bean to the xml content
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String marshal(Object obj, String encoding) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        // the propertyName(com.sun.xml.internal.bind.namespacePrefixMapper) may
        // be different, it depends on your marshaller implement class
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
            @Override
            public String getPreferredPrefix(String namespaceUri, String perfix, boolean b) {
                if (NAMESPACE_CEB_URI.equals(namespaceUri)) {
                    return NAMESPACE_CEB_PREFIX;
                } else if (NAMESPACE_XSI_URI.equals(namespaceUri)) {
                    return NAMESPACE_XSI_PREFIX;
                }
                return perfix;
            }
        });
        StringWriter stringWriter = null;
        try {
            stringWriter = new StringWriter();
            marshaller.marshal(obj, stringWriter);
            String xmlContent = stringWriter.toString().replace("standalone=\"yes\"", "");
            return xmlContent;
        } finally {
            if (stringWriter != null) {
                stringWriter.flush();
                stringWriter.close();
            }
        }
    }

    public static String marshal(Object obj) throws Exception {
        return marshal(obj, DEFAULT_ENCODING);
    }

    public static void generateXmlFile(String xmlContent, File file) throws Exception {
        generateXmlFile(xmlContent, file, DEFAULT_ENCODING);
    }

    public static void generateXmlFile(String xmlContent, String fileName) throws Exception {
        File file = new File(fileName);
        generateXmlFile(xmlContent, file);
    }

    /**
     * generate xml file according the the String xml content
     *
     * @param xmlContent The xml format string
     * @param fileName   The fileName must be absolute fileName
     * @throws Exception
     */
    public static void generateXmlFile(String xmlContent, String fileName, String encoding) throws Exception {
        File file = new File(fileName);
        generateXmlFile(xmlContent, file, encoding);
    }

    public static void generateXmlFile(String xmlContent, File file, String encoding) throws Exception {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, encoding);
            osw.write(xmlContent);
        } finally {
            if (osw != null) {
                osw.flush();
                osw.close();
            }
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }
    }

    /**
     * jaxb transfer the specified xml file to java bean
     *
     * @param clazz
     * @param fileName
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T readString(Class<T> clazz, String fileName) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new File(fileName));
    }

    /**
     * validate xml file content according to the specified xsd file
     *
     * @param xsdFileName
     * @param xmlFileName
     * @return
     */
    public static boolean validateXml(String xsdFileName, String xmlFileName) throws Exception {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File xsdFile = new File(xsdFileName);
        Schema schema = schemaFactory.newSchema(xsdFile);
        Validator validator = schema.newValidator();
        Source source = new StreamSource(xmlFileName);
        validator.validate(source);
        return true;
    }

    public static boolean copyAndDeleteFile(File file, String destFilePath) throws Exception {
        File destFile = new File(destFilePath + file.getName());
        boolean isCopyComplete = copyXmlFile(file, destFile, DEFAULT_ENCODING);
        if (isCopyComplete) {
            file.delete();
            return true;
        }
        return false;
    }

    public static boolean copyXmlFile(File sourceFile, File destFile, String encoding) throws Exception {
        if (!sourceFile.exists()) {
            throw new Exception("the sourceFile " + sourceFile + " does not exist!");
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        String fileContent = readXmlFile(sourceFile, encoding);
        generateXmlFile(fileContent, destFile, encoding);
        return true;
    }

    public static String readXmlFile(File file, String encoding) throws Exception {
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(is, encoding));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        } finally {
            if (br != null) {
                br.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    private static long MIN_SEQ = 0;

    private static long MAX_SEQ = 99;

    private static long CUR_SEQ = MIN_SEQ;

    public static synchronized String getReSeq() {
        if (CUR_SEQ == MAX_SEQ) {
            CUR_SEQ = MIN_SEQ;
        }
        return String.format("%02d", CUR_SEQ++);
    }
}