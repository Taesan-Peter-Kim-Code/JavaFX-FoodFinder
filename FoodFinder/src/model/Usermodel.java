 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author taesankim
 */
@Entity
@Table(name = "USERMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usermodel.findAll", query = "SELECT u FROM Usermodel u")
    , @NamedQuery(name = "Usermodel.findById", query = "SELECT u FROM Usermodel u WHERE u.id = :id")
    , @NamedQuery(name = "Usermodel.findByFirstname", query = "SELECT u FROM Usermodel u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "Usermodel.findByLastname", query = "SELECT u FROM Usermodel u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "Usermodel.findByEmail", query = "SELECT u FROM Usermodel u WHERE u.email = :email")
    , @NamedQuery(name = "Usermodel.findByPassword", query = "SELECT u FROM Usermodel u WHERE u.password = :password")})
public class Usermodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

    public Usermodel() {
    }

    public Usermodel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof Usermodel)) {
            return false;
        }
        Usermodel other = (Usermodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usermodel[ id=" + id + " ]";
    }
    
}
