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
import org.apache.log4j.Priority;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static rtk.eip_sheduler.XMLUtil.utlXML.XMLtoString;
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
            rootElement.setAttribute("surname", user.getFirst_name());
            rootElement.setAttribute("name", user.getSecond_name());
            rootElement.setAttribute("patronymic", user.getThird_name());
            rootElement.setAttribute("region", user.getUser_region().toString());
            rootElement.setAttribute("contactEmail", user.getEmail());
            rootElement.setAttribute("contactPhone", user.getPhone());
            // dob="1985-08-08T00:00:00+06:00" 

            rootElement.setAttribute("dob", formatDateForXML(user.getDate_birthday()));

            doc.appendChild(rootElement);

            String dataXml = XMLtoString(doc);
            log.debug(dataXml);

            utlHttp http = new utlHttp();
            log.debug(http.doPost(url.toString(), dataXml, null));

        } catch (Exception e) {
            log.log(Priority.ERROR, e.getMessage());
        }
        return res;
    }

    /**
     *
     * @return
     */
    public String updateUser(TUsers user) {
        String res = null;
        try {

        } catch (Exception e) {
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
            temp.insert(temp.length()-2, ":");
            res = temp.toString();            
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;

    }
}
