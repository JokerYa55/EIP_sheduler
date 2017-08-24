/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtk.eip_sheduler.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vasil
 */
@Entity
@Table(name = "t_users")
@NamedQueries({
    @NamedQuery(name = "TUsers.findAll", query = "SELECT t FROM TUsers t")
    , @NamedQuery(name = "TUsers.findById", query = "SELECT t FROM TUsers t WHERE t.id = :id")
    , @NamedQuery(name = "TUsers.findByAddress", query = "SELECT t FROM TUsers t WHERE t.address = :address")
    , @NamedQuery(name = "TUsers.findByEmail", query = "SELECT t FROM TUsers t WHERE t.email = :email")
    , @NamedQuery(name = "TUsers.findByHashType", query = "SELECT t FROM TUsers t WHERE t.hashType = :hashType")
    , @NamedQuery(name = "TUsers.findByIdApp1", query = "SELECT t FROM TUsers t WHERE t.idApp1 = :idApp1")
    , @NamedQuery(name = "TUsers.findByIdApp10", query = "SELECT t FROM TUsers t WHERE t.idApp10 = :idApp10")
    , @NamedQuery(name = "TUsers.findByIdApp11", query = "SELECT t FROM TUsers t WHERE t.idApp11 = :idApp11")
    , @NamedQuery(name = "TUsers.findByIdApp12", query = "SELECT t FROM TUsers t WHERE t.idApp12 = :idApp12")
    , @NamedQuery(name = "TUsers.findByIdApp13", query = "SELECT t FROM TUsers t WHERE t.idApp13 = :idApp13")
    , @NamedQuery(name = "TUsers.findByIdApp14", query = "SELECT t FROM TUsers t WHERE t.idApp14 = :idApp14")
    , @NamedQuery(name = "TUsers.findByIdApp15", query = "SELECT t FROM TUsers t WHERE t.idApp15 = :idApp15")
    , @NamedQuery(name = "TUsers.findByIdApp16", query = "SELECT t FROM TUsers t WHERE t.idApp16 = :idApp16")
    , @NamedQuery(name = "TUsers.findByIdApp17", query = "SELECT t FROM TUsers t WHERE t.idApp17 = :idApp17")
    , @NamedQuery(name = "TUsers.findByIdApp18", query = "SELECT t FROM TUsers t WHERE t.idApp18 = :idApp18")
    , @NamedQuery(name = "TUsers.findByIdApp19", query = "SELECT t FROM TUsers t WHERE t.idApp19 = :idApp19")
    , @NamedQuery(name = "TUsers.findByIdApp2", query = "SELECT t FROM TUsers t WHERE t.idApp2 = :idApp2")
    , @NamedQuery(name = "TUsers.findByIdApp20", query = "SELECT t FROM TUsers t WHERE t.idApp20 = :idApp20")
    , @NamedQuery(name = "TUsers.findByIdApp21", query = "SELECT t FROM TUsers t WHERE t.idApp21 = :idApp21")
    , @NamedQuery(name = "TUsers.findByIdApp22", query = "SELECT t FROM TUsers t WHERE t.idApp22 = :idApp22")
    , @NamedQuery(name = "TUsers.findByIdApp23", query = "SELECT t FROM TUsers t WHERE t.idApp23 = :idApp23")
    , @NamedQuery(name = "TUsers.findByIdApp24", query = "SELECT t FROM TUsers t WHERE t.idApp24 = :idApp24")
    , @NamedQuery(name = "TUsers.findByIdApp25", query = "SELECT t FROM TUsers t WHERE t.idApp25 = :idApp25")
    , @NamedQuery(name = "TUsers.findByIdApp27", query = "SELECT t FROM TUsers t WHERE t.idApp27 = :idApp27")
    , @NamedQuery(name = "TUsers.findByIdApp28", query = "SELECT t FROM TUsers t WHERE t.idApp28 = :idApp28")
    , @NamedQuery(name = "TUsers.findByIdApp29", query = "SELECT t FROM TUsers t WHERE t.idApp29 = :idApp29")
    , @NamedQuery(name = "TUsers.findByIdApp3", query = "SELECT t FROM TUsers t WHERE t.idApp3 = :idApp3")
    , @NamedQuery(name = "TUsers.findByIdApp30", query = "SELECT t FROM TUsers t WHERE t.idApp30 = :idApp30")
    , @NamedQuery(name = "TUsers.findByIdApp4", query = "SELECT t FROM TUsers t WHERE t.idApp4 = :idApp4")
    , @NamedQuery(name = "TUsers.findByIdApp5", query = "SELECT t FROM TUsers t WHERE t.idApp5 = :idApp5")
    , @NamedQuery(name = "TUsers.findByIdApp6", query = "SELECT t FROM TUsers t WHERE t.idApp6 = :idApp6")
    , @NamedQuery(name = "TUsers.findByIdApp7", query = "SELECT t FROM TUsers t WHERE t.idApp7 = :idApp7")
    , @NamedQuery(name = "TUsers.findByIdApp8", query = "SELECT t FROM TUsers t WHERE t.idApp8 = :idApp8")
    , @NamedQuery(name = "TUsers.findByIdApp9", query = "SELECT t FROM TUsers t WHERE t.idApp9 = :idApp9")
    , @NamedQuery(name = "TUsers.findByPassword", query = "SELECT t FROM TUsers t WHERE t.password = :password")
    , @NamedQuery(name = "TUsers.findByPasswordNotHash", query = "SELECT t FROM TUsers t WHERE t.passwordNotHash = :passwordNotHash")
    , @NamedQuery(name = "TUsers.findByPhone", query = "SELECT t FROM TUsers t WHERE t.phone = :phone")
    , @NamedQuery(name = "TUsers.findByUsername", query = "SELECT t FROM TUsers t WHERE t.username = :username")
    , @NamedQuery(name = "TUsers.findBySalt", query = "SELECT t FROM TUsers t WHERE t.salt = :salt")})
public class TUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "hash_type")
    private String hashType;
    @Column(name = "id_app_1")
    private String idApp1;
    @Column(name = "id_app_10")
    private String idApp10;
    @Column(name = "id_app_11")
    private String idApp11;
    @Column(name = "id_app_12")
    private String idApp12;
    @Column(name = "id_app_13")
    private String idApp13;
    @Column(name = "id_app_14")
    private String idApp14;
    @Column(name = "id_app_15")
    private String idApp15;
    @Column(name = "id_app_16")
    private String idApp16;
    @Column(name = "id_app_17")
    private String idApp17;
    @Column(name = "id_app_18")
    private String idApp18;
    @Column(name = "id_app_19")
    private String idApp19;
    @Column(name = "id_app_2")
    private String idApp2;
    @Column(name = "id_app_20")
    private String idApp20;
    @Column(name = "id_app_21")
    private String idApp21;
    @Column(name = "id_app_22")
    private String idApp22;
    @Column(name = "id_app_23")
    private String idApp23;
    @Column(name = "id_app_24")
    private String idApp24;
    @Column(name = "id_app_25")
    private String idApp25;
    @Column(name = "id_app_27")
    private String idApp27;
    @Column(name = "id_app_28")
    private String idApp28;
    @Column(name = "id_app_29")
    private String idApp29;
    @Column(name = "id_app_3")
    private String idApp3;
    @Column(name = "id_app_30")
    private String idApp30;
    @Column(name = "id_app_4")
    private String idApp4;
    @Column(name = "id_app_5")
    private String idApp5;
    @Column(name = "id_app_6")
    private String idApp6;
    @Column(name = "id_app_7")
    private String idApp7;
    @Column(name = "id_app_8")
    private String idApp8;
    @Column(name = "id_app_9")
    private String idApp9;
    @Column(name = "password")
    private String password;
    @Column(name = "password_not_hash")
    private String passwordNotHash;
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "salt")
    private String salt;

    public TUsers() {
    }

    public TUsers(Long id) {
        this.id = id;
    }

    public TUsers(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashType() {
        return hashType;
    }

    public void setHashType(String hashType) {
        this.hashType = hashType;
    }

    public String getIdApp1() {
        return idApp1;
    }

    public void setIdApp1(String idApp1) {
        this.idApp1 = idApp1;
    }

    public String getIdApp10() {
        return idApp10;
    }

    public void setIdApp10(String idApp10) {
        this.idApp10 = idApp10;
    }

    public String getIdApp11() {
        return idApp11;
    }

    public void setIdApp11(String idApp11) {
        this.idApp11 = idApp11;
    }

    public String getIdApp12() {
        return idApp12;
    }

    public void setIdApp12(String idApp12) {
        this.idApp12 = idApp12;
    }

    public String getIdApp13() {
        return idApp13;
    }

    public void setIdApp13(String idApp13) {
        this.idApp13 = idApp13;
    }

    public String getIdApp14() {
        return idApp14;
    }

    public void setIdApp14(String idApp14) {
        this.idApp14 = idApp14;
    }

    public String getIdApp15() {
        return idApp15;
    }

    public void setIdApp15(String idApp15) {
        this.idApp15 = idApp15;
    }

    public String getIdApp16() {
        return idApp16;
    }

    public void setIdApp16(String idApp16) {
        this.idApp16 = idApp16;
    }

    public String getIdApp17() {
        return idApp17;
    }

    public void setIdApp17(String idApp17) {
        this.idApp17 = idApp17;
    }

    public String getIdApp18() {
        return idApp18;
    }

    public void setIdApp18(String idApp18) {
        this.idApp18 = idApp18;
    }

    public String getIdApp19() {
        return idApp19;
    }

    public void setIdApp19(String idApp19) {
        this.idApp19 = idApp19;
    }

    public String getIdApp2() {
        return idApp2;
    }

    public void setIdApp2(String idApp2) {
        this.idApp2 = idApp2;
    }

    public String getIdApp20() {
        return idApp20;
    }

    public void setIdApp20(String idApp20) {
        this.idApp20 = idApp20;
    }

    public String getIdApp21() {
        return idApp21;
    }

    public void setIdApp21(String idApp21) {
        this.idApp21 = idApp21;
    }

    public String getIdApp22() {
        return idApp22;
    }

    public void setIdApp22(String idApp22) {
        this.idApp22 = idApp22;
    }

    public String getIdApp23() {
        return idApp23;
    }

    public void setIdApp23(String idApp23) {
        this.idApp23 = idApp23;
    }

    public String getIdApp24() {
        return idApp24;
    }

    public void setIdApp24(String idApp24) {
        this.idApp24 = idApp24;
    }

    public String getIdApp25() {
        return idApp25;
    }

    public void setIdApp25(String idApp25) {
        this.idApp25 = idApp25;
    }

    public String getIdApp27() {
        return idApp27;
    }

    public void setIdApp27(String idApp27) {
        this.idApp27 = idApp27;
    }

    public String getIdApp28() {
        return idApp28;
    }

    public void setIdApp28(String idApp28) {
        this.idApp28 = idApp28;
    }

    public String getIdApp29() {
        return idApp29;
    }

    public void setIdApp29(String idApp29) {
        this.idApp29 = idApp29;
    }

    public String getIdApp3() {
        return idApp3;
    }

    public void setIdApp3(String idApp3) {
        this.idApp3 = idApp3;
    }

    public String getIdApp30() {
        return idApp30;
    }

    public void setIdApp30(String idApp30) {
        this.idApp30 = idApp30;
    }

    public String getIdApp4() {
        return idApp4;
    }

    public void setIdApp4(String idApp4) {
        this.idApp4 = idApp4;
    }

    public String getIdApp5() {
        return idApp5;
    }

    public void setIdApp5(String idApp5) {
        this.idApp5 = idApp5;
    }

    public String getIdApp6() {
        return idApp6;
    }

    public void setIdApp6(String idApp6) {
        this.idApp6 = idApp6;
    }

    public String getIdApp7() {
        return idApp7;
    }

    public void setIdApp7(String idApp7) {
        this.idApp7 = idApp7;
    }

    public String getIdApp8() {
        return idApp8;
    }

    public void setIdApp8(String idApp8) {
        this.idApp8 = idApp8;
    }

    public String getIdApp9() {
        return idApp9;
    }

    public void setIdApp9(String idApp9) {
        this.idApp9 = idApp9;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordNotHash() {
        return passwordNotHash;
    }

    public void setPasswordNotHash(String passwordNotHash) {
        this.passwordNotHash = passwordNotHash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
        if (!(object instanceof TUsers)) {
            return false;
        }
        TUsers other = (TUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rtk.eip_sheduler.DAO.TUsers[ id=" + id + " ]";
    }
    
}
