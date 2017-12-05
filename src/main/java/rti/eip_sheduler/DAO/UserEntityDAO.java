/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rti.eip_sheduler.DAO;

import javax.persistence.EntityManager;
import rti.eip_sheduler.beans.UserEntity;
import rti.eip_sheduler.interfaces.daoInterface;

/**
 *
 * @author vasil
 */
public class UserEntityDAO implements daoInterface<UserEntity, Long> {

    private EntityManager em;

    public UserEntityDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEM() {
        return this.em;
    }

    
    
}
