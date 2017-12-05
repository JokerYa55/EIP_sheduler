/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rti.eip_sheduler.eipUtil;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import rti.eip_sheduler.beans.UserEntity;
import rti.eip.params.addUserParam;
import rti.eip.params.changePasswordParam;
import rti.eip.params.updUserParam;
import rti.eip_sheduler.XMLUtil.utlXML;
//import static rtk.eip_sheduler.XMLUtil.utlXML.xmlToString;
import rti.eip_sheduler.httpUtil.utlHttp;

/**
 *
 * @author vasil
 */
public class utlEip {

    private final URL url;
    private final Logger log = Logger.getLogger(getClass().getName());

    public utlEip(URL url) {
        this.url = url;
    }

    /**
     *
     * @param user
     * @return
     */
    public String addUser(UserEntity user) {
        log.info("ADD_USER => " + user);
        String res = null;
        try {
            addUserParam param = new addUserParam();
            param.setContactEmail(user.getEmail());
            if (user.getPhone().length() > 10) {
                log.warn("Неврная длина номера телефона : " + user.getPhone() + " len = " + user.getPhone().length());
                param.setContactPhone(user.getPhone().substring(1));
            } else {
                param.setContactPhone(user.getPhone());
            }
            param.setReqType("CREATE_USER_PASSWORD");
            param.setSalt(user.getSalt());
            param.setHash(user.getPassword());
            param.setHash_type(user.getHash_type().toUpperCase());
            param.setUser(user.getUsername());

            // Имя
            param.setName(user.getFirstName());

            // Фамилия
            param.setSurname(user.getLastName());

            param.setPatronymic(user.getThirdName());
            if (user.getUser_region() != null) {
                param.setRegion(user.getUser_region().toString());
            } else {
                log.warn("Не определен регион");
            }
            utlHttp http = new utlHttp();
            utlXML utlxml = new utlXML();

            String dataXml = utlxml.convertObjectToXml(param);
            log.info("dataXml => " + dataXml);
            res = http.doPost(url.toString(), dataXml, null);

            log.info("res1 = " + res);
            StringBuffer resBuf = new StringBuffer(res);
            log.info("len = " + resBuf.length());
            log.info("index = " + resBuf.lastIndexOf("/"));
            resBuf.insert(resBuf.lastIndexOf("/"), "\nlastCommand=\"" + dataXml.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("\"", "'").replaceAll("<", "{").replaceAll("/>", "}").replaceAll(">", "}") + "\"");
            //.replaceAll("<?", "{").replaceAll("?>", "}")
            res = resBuf.toString();
        } catch (Exception e) {
            log.log(Priority.ERROR, e);
        }
        log.debug("res = " + res);
        log.info("****************** END ADD_USER *********************************");
        return res;
    }

    /**
     *
     * @param user
     * @return
     */
    public String updateUser(UserEntity user) {
        log.info("******************** UPD_USER *******************************");
        String res = null;

        try {
            updUserParam param = new updUserParam();
            if (user.getEmail() != null) {
                param.setContactEmail(user.getEmail());
            }
            if (user.getPhone() != null) {
                if (user.getPhone().length() > 10) {
                    log.warn("Неврная длина номера телефона : " + user.getPhone() + " len = " + user.getPhone().length());
                    param.setContactPhone(user.getPhone().substring(1));
                } else {
                    param.setContactPhone(user.getPhone());
                }
                //param.setContactPhone(user.getPhone());
            }

            param.setReqType("EDIT_USER");
            if (user.getUsername() != null) {
                param.setUser(user.getUsername());
            }

            // Имя
            if (user.getFirstName() != null) {
                param.setName(user.getFirstName());

            }

            // Фамилия
            if (user.getLastName() != null) {
                param.setSurname(user.getLastName());
            }

            if (user.getThirdName() != null) {
                param.setPatronymic(user.getThirdName());
            }
            if (user.getUser_region() != null) {
                param.setRegion(user.getUser_region().toString());
            }
            if ((user.getUser_status() != null) && (user.getUser_status() != 0)) {
                param.setUserStatus(user.getUser_status().toString());
            }

            utlHttp http = new utlHttp();
            utlXML utlxml = new utlXML();
            String dataXml = utlxml.convertObjectToXml(param);
            log.info("dataXml => " + dataXml);
            res = http.doPost(url.toString(), dataXml, null);

            log.info("res1 = " + res);
            StringBuffer resBuf = new StringBuffer(res);
            log.info("len = " + resBuf.length());
            log.info("index = " + resBuf.lastIndexOf("/"));
            resBuf.insert(resBuf.lastIndexOf("/"), "\nlastCommand=\"" + dataXml.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("\"", "'").replaceAll("<", "{").replaceAll("/>", "}").replaceAll(">", "}") + "\"");
            //.replaceAll("<?", "{").replaceAll("?>", "}")
            res = resBuf.toString();

        } catch (Exception e) {
            log.log(Priority.ERROR, e);
        }
        log.info("res = " + res);
        log.info("******************** END UPD_USER *******************************");
        return res;
    }

    /**
     *
     * @param user
     * @return
     */
    public String changePassword(UserEntity user) {
        log.debug("CHANGE_PASSWORD => " + user);
        String res = null;
        try {
            changePasswordParam param = new changePasswordParam();
            param.setHash(user.getPassword());
            param.setHashType(user.getHash_type().toUpperCase());
            param.setReqType("CHANGE_PASSWORD");
            param.setSalt(user.getSalt());
            param.setUser(user.getUsername());

            utlHttp http = new utlHttp();
            utlXML utlxml = new utlXML();
            String dataXml = utlxml.convertObjectToXml(param);
            log.info("dataXml => " + dataXml);
            res = http.doPost(url.toString(), dataXml, null);

            log.info("res1 = " + res);
            StringBuffer resBuf = new StringBuffer(res);
            log.info("len = " + resBuf.length());
            log.info("index = " + resBuf.lastIndexOf("/"));
            resBuf.insert(resBuf.lastIndexOf("/"), "\nlastCommand=\"" + dataXml.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("\"", "'").replaceAll("<", "{").replaceAll("/>", "}").replaceAll(">", "}") + "\"");
            //.replaceAll("<?", "{").replaceAll("?>", "}")
            res = resBuf.toString();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("res = " + res);
        return res;
    }

}