/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.DAO;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import rtk.eip_sheduler.beans.UsersLog;
import rtk.eip_sheduler.interfaces.daoInterface;

/**
 *
 * @author vasil
 */
public class UsersLogDAO implements daoInterface<UsersLog, Long> {

    private final EntityManager em;
    private final Logger log = Logger.getLogger(getClass().getName());

    public UsersLogDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEM() {
        return this.em;
    }

//    @Override
//    public UsersLog getItem(Long id) {
//        UsersLog res = null;
//        try {
//             TypedQuery<TUsersLog> namedQuery = em.createNamedQuery("UsersLog.findById", UsersLog.class);
//             namedQuery.setParameter("id", id);
//             res = namedQuery.getSingleResult();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return res;
//    }
//
//    @Override
//    public List<TUsersLog> getList() {
//        log.debug("getList");
//        List<TUsersLog> res = null;
//        try {
//             TypedQuery<TUsersLog> namedQuery = em.createNamedQuery("UsersLog.findAll", UsersLog.class);
//             namedQuery.setParameter("send_count", 10);
//             res = namedQuery.getResultList();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return res;
//    }
//
//    @Override
//    public List<TUsersLog> getList(Long startIdx, Long stopIdx) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
