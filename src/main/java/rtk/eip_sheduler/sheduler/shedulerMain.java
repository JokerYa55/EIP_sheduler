/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.sheduler;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import rtk.eip_sheduler.DAO.TUsersLogDAO;
import rtk.eip_sheduler.beans.TUsersLog;

/**
 *
 * @author vasil
 */
public class shedulerMain {

    /**
     * @param args the command line arguments
     */    

    public static void main(String[] args) {
        // TODO code application logic here
        Logger log = Logger.getLogger(shedulerMain.class.getName());
        log.info("Start");
        EntityManager em = Persistence.createEntityManagerFactory("EIP_shaduler_eip_sheduler_jar_1PU").createEntityManager();
        List<TUsersLog> logItems = (new TUsersLogDAO(em)).getList();
        for (TUsersLog item : logItems) {
            log.info(item);
        }
    }

}
