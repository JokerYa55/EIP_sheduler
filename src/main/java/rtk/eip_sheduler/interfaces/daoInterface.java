/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.interfaces;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author vasil
 */
public interface daoInterface<T, V> {

    /**
     *
     * @return
     */
    public EntityManager getEM();

    /**
     *
     * @param id
     * @return
     */
    public T getItem(V id);

    /**
     *
     * @return
     */
    public List<T> getList();

    /**
     *
     * @param startIdx
     * @param stopIdx
     * @return
     */
    public List<T> getList(V startIdx, V stopIdx);

    /**
     *
     * @param Item
     * @return
     */
    default public T addItem(T Item) {
        T res = null;
        try {
            EntityManager em = getEM();
            res = em.merge(Item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *
     * @param Item
     * @return
     */
    default public boolean deleteItem(T Item) {
        boolean res = true;
        try {
            EntityManager em = getEM();
            em.detach(Item);
        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        return res;
    }

    /**
     *
     * @param Item
     * @return
     */
    default public boolean updateItem(T Item) {
        boolean res = true;
        try {

        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        return res;
    }

}
