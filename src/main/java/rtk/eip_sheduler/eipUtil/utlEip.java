/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.eipUtil;

import java.net.URL;
import rtk.eip_sheduler.beans.TUsers;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
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
            rootElement.setAttribute("dob", user.getCreate_date().toString());
            rootElement.setAttribute("region", "23");
            rootElement.setAttribute("contactEmail", user.getEmail());
            rootElement.setAttribute("contactPhone", user.getPhone());           
            doc.appendChild(rootElement);
            
            String dataXml = XMLtoString(doc);            
            log.debug(dataXml);
            
            utlHttp http = new utlHttp();
            http.doPost(url.toString(), dataXml, null);
            
        } catch (Exception e) {
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
}
