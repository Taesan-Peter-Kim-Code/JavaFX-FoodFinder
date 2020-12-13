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
@Table(name = "SAVEDEVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Savedevent.findAll", query = "SELECT s FROM Savedevent s")
    , @NamedQuery(name = "Savedevent.findById", query = "SELECT s FROM Savedvent s WHERE s.id = :id")
    , @NamedQuery(name = "Savedevent.findByEventid", query = "SELECT s FROM Savedevent s WHERE s.eventid = :eventid")
    , @NamedQuery(name = "Savedevent.findByUserid", query = "SELECT s FROM Savedevent s WHERE s.userid = :userid")
    , @NamedQuery(name = "Savedevent.findByEventidAndUserid", query = "SELECT u FROM Savedevent u WHERE u.eventid = :eventid AND u.userid = :userid")
})
public class Savedevent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "EVENTID")
    private Integer eventid;
    @Column(name = "USERID")
    private Integer userid;

    public Savedevent() {
    }

    public Savedevent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
        if (!(object instanceof Savedevent)) {
            return false;
        }
        Savedevent other = (Savedevent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Savedevent[ id=" + id + " ]";
    }
    
}
