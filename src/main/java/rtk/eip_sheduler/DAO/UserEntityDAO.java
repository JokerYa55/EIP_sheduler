/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.DAO;

import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import rtk.eip_sheduler.beans.UserEntity;
import rtk.eip_sheduler.interfaces.daoInterface;

/**
 *
 * @author vasil
 */
public class UserEntityDAO implements daoInterface<UserEntity, Long> {

    private final EntityManager em;
    private final Logger log = Logger.getLogger(getClass().getName());

    public UserEntityDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEM() {
        return this.em;
    }

//    @Override
//    public UserEntity getItem(Long id) {
//        UserEntity res = null;
//        try {
//            TypedQuery<userEntity> namedQuery = em.createNamedQuery("UserEntity.findById", UserEntity.class);
//            namedQuery.setParameter("id", id);
//            res = namedQuery.getSingleResult();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return res;
//    }
//
//    @Override
//    public List<userEntity> getList() {
//        List<userEntity> res = null;
//        try {
//            TypedQuery<userEntity> namedQuery = em.createNamedQuery("UserEntity.findAll", UserEntity.class);
//            res = namedQuery.getResultList();
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return res;
//    }
//
//    @Override
//    public List<userEntity> getList(Long startIdx, Long stopIdx) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
