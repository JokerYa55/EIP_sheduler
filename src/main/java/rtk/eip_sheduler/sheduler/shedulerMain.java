/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.sheduler;

import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rtk.eip_sheduler.DAO.UserEntityDAO;
import rtk.eip_sheduler.DAO.UsersLogDAO;
import rtk.eip_sheduler.XMLUtil.utlXML;
import static rtk.eip_sheduler.XMLUtil.utlXML.stringToXml;
import rtk.eip_sheduler.beans.UserEntity;
import rtk.eip_sheduler.beans.UsersLog;
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
            // http://192.168.1.150:8080/elkAdminRest/elkadm/addUser1
            log.debug("***************************************************************************************");
            log.debug("Start => " + (new Date()).toString());

            //log.debug("len = " + args.length);
            log.debug("url = " + args[0]);
            EntityManager em = Persistence.createEntityManagerFactory("EIP_shaduler_eip_sheduler_jar_1PU").createEntityManager();
            //em.setProperty(propertyName, log);
            utlEip Eip = new utlEip(new URL(args[0]));
            log.debug(Eip);
            List<UsersLog> logItems = (new UsersLogDAO(em)).getList("TUsersLog.findByNoSend", UsersLog.class);

            for (UsersLog item : logItems) {
                try {
                    log.debug("*************  "  + item + "  ***************************************");
                    //log.debug(item);
                    // Получаем данные о пользователе
                    UserEntity user = (new UserEntityDAO(em)).getItem(item.getUserId(), "userEntity.findById", UserEntity.class);
                    log.debug(user);

                    // Определяем тип операции ADD или UPD
                    String res = null;
                    Document resXml = null;
                    Element root;
                    String resultCode;
                    switch (item.getOperType().toUpperCase()) {
                        case "I":
                            res = Eip.addUser(user);
                            item.setLast_command(res);
                            resXml = stringToXml(res);
                            log.debug(resXml);
                            root = resXml.getDocumentElement();
                            log.debug("resXml = " + utlXML.xmlToString(resXml));
                            resultCode = root.getAttribute("resultCode");
                            log.debug("resultCode = " + resultCode);
                            if (resultCode.equals("0")) {
                                item.setFlag(true);
                            } else {
                                item.setFlag(false);
                                item.setSend_count(item.getSend_count() + 1);
                            }

                            break;
                        case "U":
                            // Если поменялся пароль
//                            Pattern p = Pattern.compile("^(<\\w+>)*$");
//                            Matcher m = p.matcher(item.getInfo());

                            res = Eip.updateUser(user);
                            item.setLast_command(res);
                            resXml = stringToXml(res);
                            log.debug(resXml);
                            root = resXml.getDocumentElement();
                            log.debug("resXml = " + utlXML.xmlToString(resXml));
                            resultCode = root.getAttribute("resultCode");
                            log.debug("resultCode = " + resultCode);
                            if (resultCode.equals("0")) {
                                item.setFlag(true);
                            } else {
                                item.setFlag(false);
                                item.setSend_count(item.getSend_count() + 1);
                            }

                            // Обновляем если пароль
                            if (item.getInfo().contains("<password>")) {
                                res = Eip.changePassword(user);
                                item.setLast_command(res);
                                resXml = stringToXml(res);
                                log.debug(resXml);
                                root = resXml.getDocumentElement();
                                log.debug("resXml = " + utlXML.xmlToString(resXml));
                                resultCode = root.getAttribute("resultCode");
                                log.debug("resultCode = " + resultCode);
                                if (resultCode.equals("0")) {
                                    item.setFlag(true);
                                } else {
                                    item.setFlag(false);
                                    item.setSend_count(item.getSend_count() + 1);
                                }
                            }
                            break;
                        case "D":
                            break;
                        default: ;

                    }
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
                // Если получен положительный ответ то ставим отметку об успешной отправке
                //item.setFlag(true);
                (new UsersLogDAO(em)).updateItem(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
