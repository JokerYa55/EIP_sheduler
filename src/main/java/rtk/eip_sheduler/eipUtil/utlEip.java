/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.eipUtil;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import rtk.eip_sheduler.beans.TUsers;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static rtk.eip_sheduler.XMLUtil.utlXML.xmlToString;
import rtk.eip_sheduler.httpUtil.utlHttp;

/**
 *
 * @author vasil
 */
public class utlEip {

    private URL url;
    private Logger log = Logger.getLogger(getClass().getName());

    public utlEip(URL url) {
        this.url = url;
    }

    /**
     *
     * @param user
     * @return
     */
    public String addUser(TUsers user) {
        log.info("addUser");
        String res = null;
        try {
            // Добавляем XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("request");
            /*
            user="petrov" surname="Петров" name="Петр" patronymic="Иванович" dob="1985-08-08T00:00:00+06:00" region="23" contactEmail="petrov@gmail.com" contactPhone="9652323232"
             */
            rootElement.setAttribute("reqType", "CREATE_USER");
            rootElement.setAttribute("user", user.getUsername());
            if (user.getFirst_name() != null) {
                rootElement.setAttribute("surname", user.getFirst_name());
            } else {
                rootElement.setAttribute("surname", "");
            }

            if (user.getSecond_name() != null) {
                rootElement.setAttribute("name", user.getSecond_name());
            } else {
                rootElement.setAttribute("name", "");
            }
            if (user.getThird_name() != null) {
                rootElement.setAttribute("patronymic", user.getThird_name());
            } else {
                rootElement.setAttribute("patronymic", "");
            }
            if (user.getUser_region() != null) {
                rootElement.setAttribute("region", user.getUser_region().toString());
            } else {
                rootElement.setAttribute("region", "");
            }
            if (user.getEmail() != null) {
                rootElement.setAttribute("contactEmail", user.getEmail());
            } else {
                rootElement.setAttribute("contactEmail", "");
            }
            if (user.getPhone() != null) {
                rootElement.setAttribute("contactPhone", user.getPhone());
            } else {
                rootElement.setAttribute("contactPhone", "");
            }
//            if (user.getDate_birthday() != null) {
//                rootElement.setAttribute("dob", formatDateForXML(user.getDate_birthday()));
//            } else {
//                rootElement.setAttribute("dob", "");
//            }

            doc.appendChild(rootElement);

            String dataXml = xmlToString(doc);
            log.debug(dataXml);

            utlHttp http = new utlHttp();
            res = http.doPost(url.toString(), dataXml, null);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;
    }

    /**
     *
     * @param user
     * @return
     */
    public String updateUser(TUsers user) {
        log.info("updateUser");
        String res = null;

        try {
            // Добавляем XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("request");
            /*
            user="petrov" surname="Петров" name="Петр" patronymic="Иванович" dob="1985-08-08T00:00:00+06:00" region="23" contactEmail="petrov@gmail.com" contactPhone="9652323232"
             */
            rootElement.setAttribute("reqType", "EDIT_USER");
            rootElement.setAttribute("user", user.getUsername());
            if (user.getFirst_name() != null) {
                rootElement.setAttribute("surname", user.getFirst_name());
            } else {
                rootElement.setAttribute("surname", "");
            }

            if (user.getSecond_name() != null) {
                rootElement.setAttribute("name", user.getSecond_name());
            } else {
                rootElement.setAttribute("name", "");
            }
            if (user.getThird_name() != null) {
                rootElement.setAttribute("patronymic", user.getThird_name());
            } else {
                rootElement.setAttribute("patronymic", "");
            }
            if (user.getUser_region() != null) {
                rootElement.setAttribute("region", user.getUser_region().toString());
            } else {
                rootElement.setAttribute("region", "");
            }
            if (user.getEmail() != null) {
                rootElement.setAttribute("contactEmail", user.getEmail());
            } else {
                rootElement.setAttribute("contactEmail", "");
            }
            if (user.getPhone() != null) {
                rootElement.setAttribute("contactPhone", user.getPhone());
            } else {
                rootElement.setAttribute("contactPhone", "");
            }
//            if (user.getDate_birthday() != null) {
//                rootElement.setAttribute("dob", formatDateForXML(user.getDate_birthday()));
//            } else {
//                rootElement.setAttribute("dob", "");
//            }

            doc.appendChild(rootElement);

            String dataXml = xmlToString(doc);
            log.debug(dataXml);

            utlHttp http = new utlHttp();
            res = http.doPost(url.toString(), dataXml, null);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;
    }

    /**
     *
     * @param date
     * @return
     */
    private String formatDateForXML(Date date) {
        String res = null;
        try {
            SimpleDateFormat dateFormatYear = new SimpleDateFormat("YYYY-MM-dd'T'HH-mm-ssZZ");
            StringBuilder temp = new StringBuilder();
            temp.append(dateFormatYear.format(date));
            temp.insert(temp.length() - 2, ":");
            res = temp.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;

    }
}
