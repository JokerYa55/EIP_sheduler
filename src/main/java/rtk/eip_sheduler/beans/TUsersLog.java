/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vasil
 */
@Entity
@Table(name = "t_users_log")
@NamedQueries({
    @NamedQuery(name = "TUsersLog.findAll", query = "SELECT t FROM TUsersLog t")
    , @NamedQuery(name = "TUsersLog.findById", query = "SELECT t FROM TUsersLog t WHERE t.id = :id")
    , @NamedQuery(name = "TUsersLog.findByFlag", query = "SELECT t FROM TUsersLog t WHERE t.flag = :flag")
    , @NamedQuery(name = "TUsersLog.findByOperType", query = "SELECT t FROM TUsersLog t WHERE t.operType = :operType")
    , @NamedQuery(name = "TUsersLog.findByUserId", query = "SELECT t FROM TUsersLog t WHERE t.userId = :userId")
    , @NamedQuery(name = "TUsersLog.findByUsername", query = "SELECT t FROM TUsersLog t WHERE t.username = :username")
    , @NamedQuery(name = "TUsersLog.findByDateOper", query = "SELECT t FROM TUsersLog t WHERE t.dateOper = :dateOper")})
public class TUsersLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "flag")
    private boolean flag;
    @Column(name = "oper_type")
    private String operType;
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "date_oper")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOper;
    @Column(name = "send_count", nullable = false, columnDefinition = "integer DEFAULT 0")
    private Integer send_count;

    public TUsersLog() {
    }

    public TUsersLog(Integer id) {
        this.id = id;
    }

    public TUsersLog(Integer id, boolean flag, String userId, String username, Date dateOper) {
        this.id = id;
        this.flag = flag;
        this.userId = userId;
        this.username = username;
        this.dateOper = dateOper;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOper() {
        return dateOper;
    }

    public void setDateOper(Date dateOper) {
        this.dateOper = dateOper;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUsersLog)) {
            return false;
        }
        TUsersLog other = (TUsersLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rtk.eip_sheduler.DAO.TUsersLog[ id=" + id + " ]";
    }

    public Integer getSend_count() {
        return send_count;
    }

    public void setSend_count(Integer send_count) {
        this.send_count = send_count;
    }
    
}
