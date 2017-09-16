/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import rtk.eip_sheduler.beans.userEntity;
import rtk.eip_sheduler.interfaces.daoInterface;

/**
 *
 * @author vasil
 */
public class TUsersDAO implements daoInterface<userEntity, Long> {

    private EntityManager em;
    private Logger log = Logger.getLogger(getClass().getName());

    public TUsersDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEM() {
        return this.em;
    }

    @Override
    public userEntity getItem(Long id) {
        userEntity res = null;
        try {
            TypedQuery<userEntity> namedQuery = em.createNamedQuery("userEntity.findById", userEntity.class);
            namedQuery.setParameter("id", id);
            res = namedQuery.getSingleResult();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;
    }

    @Override
    public List<userEntity> getList() {
        List<userEntity> res = null;
        try {
            TypedQuery<userEntity> namedQuery = em.createNamedQuery("userEntity.findAll", userEntity.class);
            res = namedQuery.getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return res;
    }

    @Override
    public List<userEntity> getList(Long startIdx, Long stopIdx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
