/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rti.eip_sheduler.sheduler;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rti.eip_sheduler.DAO.AppPropertiesDAO;
import rti.eip_sheduler.DAO.UserEntityDAO;
import rti.eip_sheduler.DAO.UsersLogDAO;
import rti.eip_sheduler.XMLUtil.utlXML;
import static rti.eip_sheduler.XMLUtil.utlXML.stringToXml;
import rti.eip_sheduler.beans.AppProperties;
import rti.eip_sheduler.beans.UserEntity;
import rti.eip_sheduler.beans.UsersLog;
import rti.eip_sheduler.eipUtil.utlEip;

/**
 *
 * @author vasil
 */
public class shedulerMain {

    /**
     * @param args the command line arguments
     */
    private static EntityManager em = Persistence.createEntityManagerFactory("EIP_shaduler_eip_sheduler_jar_1PU").createEntityManager();
    private static Logger log = Logger.getLogger(shedulerMain.class.getName());

    public static void main(String[] args) {

        try {
            // TODO code application logic here
            // http://192.168.1.150:8080/elkAdminRest/elkadm/addUser1
            log.debug("***************************************************************************************");
            log.debug("Start => " + (new Date()).toString());

            String url = getAppParams("url", "null");
            log.info(" URL = " + url);
            String sendCount = getAppParams("max_send_count", "10");
            log.info("send_count = " + sendCount);
            String maxRecUserLog = getAppParams("max_rec_user_log", "30");
            log.info("max_rec_user_log = " + maxRecUserLog);

            utlEip Eip = new utlEip(new URL(url));
            log.info("Start => " + (new Date()).toString());

            Map<String, Object> param = new HashMap<>();
            param.put("flag", false);
            param.put("send_count", new Integer(sendCount));
            param.put("limit", new Integer(maxRecUserLog));
            List<UsersLog> logList = (new UsersLogDAO(em)).getList("UsersLog.findByFlag", UsersLog.class, param);
            log.info("log record count => " + logList.size());

            for (UsersLog item : logList) {
                try {
                    log.info("************************** LOG RECORD BEGIN *****************************");
                    log.info("log item => " + item);
                    UserEntity user = null;
                    try {
                        log.info("GET USER REC => ");
                        user = (new UserEntityDAO(em)).getItem(item.getUserId(), "userEntity.findById", UserEntity.class);
                    } catch (Exception e11) {
                        log.log(Priority.ERROR, "getuser error => " + e11);
                        log.log(Priority.ERROR, e11);
                    }

                    log.debug("\tuser => " + user.toString());

                    if (user != null) {
                        //log.info("tuser => " + user);

                        String res = null;
                        Document resXml = null;
                        Element root;
                        String resultCode = null;
                        String lastCommand;
                        String resultComment;

                        switch (item.getOperType().toUpperCase()) {
                            case "I":
                                res = Eip.addUser(user);
                                if (res != null) {
                                    item.setLast_res(res);
                                    resXml = stringToXml(res);
                                    if (resXml != null) {
                                        log.info(resXml);
                                        root = resXml.getDocumentElement();
                                        log.info("resXml => " + utlXML.xmlToString(resXml));
                                        resultCode = root.getAttribute("resultCode");
                                        lastCommand = root.getAttribute("lastCommand");
                                        item.setLast_command("EIP Error => " + lastCommand);
                                        resultComment = root.getAttribute("resultComment");
                                        log.info("resultCode = " + resultCode);
                                        switch (resultCode) {
                                            case "0":
                                                item.setFlag(true);
                                                item.setSend_count(item.getSend_count() + 1);
                                                break;
                                            case "-1":
                                                log.log(Priority.ERROR, resultComment);
                                                break;
                                            case "36":
                                                item.setFlag(true);
                                                item.setSend_count(1000);
                                                break;
                                            default:
                                                item.setFlag(false);
                                                item.setSend_count(item.getSend_count() + 1);
                                                break;
                                        }
                                    } else {
                                        // Если ошибка при работе с EIP
                                        item.setFlag(false);
                                        item.setSend_count(item.getSend_count() + 1);
                                        item.setLast_res("EIP Error => " + res);
                                    }
                                } else {
                                    // Если EIP ничего не вернул
                                    log.error("add_user() res => NULL");
                                    item.setFlag(true);
                                    item.setSend_count(item.getSend_count() + 1);
                                    item.setLast_res("add_user() res => NULL");
                                }

                                break;

                            case "U":
                                // Если поменялся пароль
//                            Pattern p = Pattern.compile("^(<\\w+>)*$");
//                            Matcher m = p.matcher(item.getInfo());

                                res = Eip.updateUser(user);
                                if (res != null) {
                                    item.setLast_res(res);
                                    resXml = stringToXml(res);
                                    if (resXml != null) {
                                        if (resXml != null) {
                                            log.info(resXml);
                                            root = resXml.getDocumentElement();
                                            log.info("resXml = " + utlXML.xmlToString(resXml));
                                            resultCode = root.getAttribute("resultCode");
                                            lastCommand = root.getAttribute("lastCommand");
                                            resultComment = root.getAttribute("resultComment");
                                            item.setLast_command("EIP Error => " + lastCommand);
                                            log.info("resultCode = " + resultCode);
                                            switch (resultCode) {
                                                case "0":
                                                    item.setFlag(true);
                                                    break;
                                                case "-1":
                                                    log.log(Priority.WARN, resultComment);
                                                    break;
                                                default:
                                                    item.setFlag(false);
                                                    break;
                                            }
                                            item.setSend_count(item.getSend_count() + 1);
                                            // Обновляем если пароль
                                            if (item.getInfo().contains("<password>")) {
                                                res = Eip.changePassword(user);
                                                item.setLast_res(res);
                                                resXml = stringToXml(res);
                                                log.info(resXml);
                                                root = resXml.getDocumentElement();
                                                log.info("resXml = " + utlXML.xmlToString(resXml));
                                                resultCode = root.getAttribute("resultCode");
                                                lastCommand = root.getAttribute("lastCommand");
                                                item.setLast_command("EIP Error => " + lastCommand);
                                                log.info("resultCode = " + resultCode);
                                                if (resultCode.equals("0")) {
                                                    item.setFlag(true);
                                                } else {
                                                    item.setFlag(false);
                                                }
                                                item.setSend_count(item.getSend_count() + 1);
                                            }
                                        } else {
                                        }
                                    } else {
                                        // Если ошибка при работе с EIP
                                        item.setFlag(false);
                                        item.setSend_count(item.getSend_count() + 1);
                                        item.setLast_res("EIP Error => " + res);
                                    }
                                } else {
                                    // Если EIP ничего не вернул
                                    log.error("upd_user() res => NULL");
                                    item.setFlag(false);
                                    item.setSend_count(item.getSend_count() + 1);
                                    item.setLast_res("upd_user() res => NULL");
                                }
                                break;
                            case "D":
                                break;
                            default: ;
                        }
                    } else {
                        // Если не найден пользователь
                        item.setFlag(true);
                        item.setInfo("<NO User id = " + item.getUserId() + " name = " + item.getUsername() + ">");
                        item.setLast_res("USER => NULL process_id ");
                    }
                    log.info("************************** LOG RECORD END *****************************");
                } catch (NullPointerException ex1) {
                    // Если не найден пользователь
                    log.log(Priority.ERROR, ex1);
                    log.error("USER => NULL");
                    item.setFlag(true);
                    //item.setSend_count(item.getSend_count() + 1);
                    item.setInfo("<NO User id = " + item.getUserId() + " name = " + item.getUsername() + " in table>");
                    item.setLast_res("USER => NULL process_id => ");
                } catch (Exception ex23) {
                    log.log(Priority.ERROR, ex23);
                }

                log.info("\n\n");
                log.info("item => " + item);
                //em.merge(item);
                (new UsersLogDAO(em)).updateItem(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getAppParams(String pName, String pDefVal) {
        String res = null;
        try {
            AppPropertiesDAO appDAO = new AppPropertiesDAO(em);
            AppProperties prop = null;
            try {
                prop = appDAO.getItem(pName);
                res = prop.getParam_value();
            } catch (NoResultException e) {
                prop = new AppProperties();
                prop.setParam_name(pName);
                prop.setParam_value(pDefVal);
                appDAO.addItem(prop);
                res = prop.getParam_value();
            }
        } catch (Exception e1) {
            log.error(e1);
        }
        return res;
    }

}
