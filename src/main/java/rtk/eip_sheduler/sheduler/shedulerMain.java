/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.sheduler;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import java.io.StringWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import rtk.eip_sheduler.DAO.TUsersDAO;
import rtk.eip_sheduler.DAO.TUsersLogDAO;
import rtk.eip_sheduler.beans.TUsers;
import rtk.eip_sheduler.beans.TUsersLog;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author vasil
 */
public class shedulerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger log = Logger.getLogger(shedulerMain.class.getName());
        try {
            // TODO code application logic here

            log.info("Start");
            EntityManager em = Persistence.createEntityManagerFactory("EIP_shaduler_eip_sheduler_jar_1PU").createEntityManager();
            //em.setProperty(propertyName, log);
            List<TUsersLog> logItems = (new TUsersLogDAO(em)).getList();
            for (TUsersLog item : logItems) {
                log.info(item);
                // отправка сообщения в ЕИП
                // Получаем данные о пользователе
                TUsers user = (new TUsersDAO(em)).getItem(item.getUserId());
                log.debug(user);
                // Если получен положительный ответ то ставим отметку об успешной отправке
                item.setFlag(true);
                (new TUsersLogDAO(em)).updateItem(item);
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement("request");
            /*
            user="petrov" surname="Петров" name="Петр" patronymic="Иванович" dob="1985-08-08T00:00:00+06:00" region="23" contactEmail="petrov@gmail.com" contactPhone="9652323232"
            */
            rootElement.setAttribute("reqType", "CREATE_USER");
            rootElement.setAttribute("user", "petrov");
            rootElement.setAttribute("surname", "Петров");
            rootElement.setAttribute("name", "Петр");
            rootElement.setAttribute("patronymic", "Иванович");
            rootElement.setAttribute("dob", "1985-08-08T00:00:00+06:00");
            rootElement.setAttribute("region", "23");
            rootElement.setAttribute("contactEmail", "petrov@gmail.com");
            rootElement.setAttribute("contactPhone", "9652323232");
            

            doc.appendChild(rootElement);
           

            log.debug(toString(doc));

        } catch (ParserConfigurationException | DOMException e) {
            log.error(e.getMessage());
        }

    }

    public static String toString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }

}
