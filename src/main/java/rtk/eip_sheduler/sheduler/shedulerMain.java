/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.sheduler;

import java.net.URL;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rtk.eip_sheduler.DAO.TUsersDAO;
import rtk.eip_sheduler.DAO.TUsersLogDAO;
import rtk.eip_sheduler.XMLUtil.utlXML;
import static rtk.eip_sheduler.XMLUtil.utlXML.stringToXml;
import rtk.eip_sheduler.beans.TUsers;
import rtk.eip_sheduler.beans.TUsersLog;
import rtk.eip_sheduler.eipUtil.utlEip;

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
            utlEip Eip = new utlEip(new URL("http://192.168.1.150:8080/adminREST-1/webresources/userREST/getInfo"));
            List<TUsersLog> logItems = (new TUsersLogDAO(em)).getList();
            for (TUsersLog item : logItems) {
                log.info(item);
                // Получаем данные о пользователе
                TUsers user = (new TUsersDAO(em)).getItem(item.getUserId());
                log.debug(user);

                // Определяем тип операции ADD или UPD
                String res = null;
                Document resXml = null;
                Element root;
                String resultCode;
                switch (item.getOperType().toUpperCase()) {
                    case "I":
                        res = Eip.addUser(user);
                        res = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                                + "<response resultCode =\"1\" resultComment=\"Логин создан автоматически\" user=\"petrov-elk-rtk\" dateTime=\"2013-05-08T12:39:00+06:00\"/>";
                        resXml = stringToXml(res);
                        log.info(resXml);
                        root = resXml.getDocumentElement();
                        log.info("resXml = " + utlXML.xmlToString(resXml));
                        resultCode = root.getAttribute("resultCode");
                        log.info("resultCode = " + resultCode);
                        if (resultCode.equals("0")) {
                            item.setFlag(true);
                        } else {
                            item.setFlag(false);
                            item.setSend_count(item.getSend_count() + 1);
                        }

                        break;
                    case "U":
                        res = Eip.updateUser(user);
                        res = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                                + "<response resultCode =\"0\" resultComment=\"Логин создан автоматически\" user=\"petrov-elk-rtk\" dateTime=\"2013-05-08T12:39:00+06:00\"/>";
                        resXml = stringToXml(res);
                        log.info(resXml);
                        root = resXml.getDocumentElement();
                        log.info("resXml = " + utlXML.xmlToString(resXml));
                        resultCode = root.getAttribute("resultCode");
                        log.info("resultCode = " + resultCode);
                        if (resultCode.equals("0")) {
                            item.setFlag(true);
                        } else {
                            item.setFlag(false);
                            item.setSend_count(item.getSend_count() + 1);
                        }
                        break;
                    case "D":
                        break;
                    default: ;
                }

                // Если получен положительный ответ то ставим отметку об успешной отправке
                //item.setFlag(true);
                (new TUsersLogDAO(em)).updateItem(item);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

}
