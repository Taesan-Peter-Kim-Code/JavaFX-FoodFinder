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
 * @author hayde
 */
@Entity
@Table(name = "SAVEDEVENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Savedevents.findAll", query = "SELECT s FROM Savedevents s")
    , @NamedQuery(name = "Savedevents.findById", query = "SELECT s FROM Savedevents s WHERE s.id = :id")
    , @NamedQuery(name = "Savedevents.findByEventid", query = "SELECT s FROM Savedevents s WHERE s.eventid = :eventid")
    , @NamedQuery(name = "Savedevents.findByUserid", query = "SELECT s FROM Savedevents s WHERE s.userid = :userid")})
public class SavedEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "EVENTID")
    private int eventid;
    @Basic(optional = false)
    @Column(name = "USERID")
    private Boolean userid;

    public SavedEvent() {
    }

    public SavedEvent(Integer id) {
        this.id = id;
    }

    public SavedEvent(Integer id, int eventid, Boolean userid) {
        this.id = id;
        this.eventid = eventid;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public Boolean getUserid() {
        return userid;
    }

    public void setUserid(Boolean userid) {
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
        if (!(object instanceof SavedEvent)) {
            return false;
        }
        SavedEvent other = (SavedEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Savedevents[ id=" + id + " ]";
    }
    
}
