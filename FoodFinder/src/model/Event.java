/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author taesankim
 */
@Entity
@Table(name = "EVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event_1 e")
    , @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event_1 e WHERE e.id = :id")
    , @NamedQuery(name = "Event.findByLocation", query = "SELECT e FROM Event_1 e WHERE e.location = :location")
    , @NamedQuery(name = "Event.findByDate", query = "SELECT e FROM Event_1 e WHERE e.date = :date")
    , @NamedQuery(name = "Event.findByOrganizationname", query = "SELECT e FROM Event_1 e WHERE e.organizationname = :organizationname")
    , @NamedQuery(name = "Event_1.findByDescription", query = "SELECT e FROM Event_1 e WHERE e.description = :description")
    , @NamedQuery(name = "Event_1.findByEventname", query = "SELECT e FROM Event_1 e WHERE e.eventname = :eventname")
    , @NamedQuery(name = "Event_1.findByTime", query = "SELECT e FROM Event_1 e WHERE e.time = :time")})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "ORGANIZATIONNAME")
    private String organizationname;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EVENTNAME")
    private String eventname;
    @Column(name = "TIME")
    @Temporal(TemporalType.TIME)
    private Date time;

    public Event() {
    }

    public Event(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Event_1[ id=" + id + " ]";
    }
    
}
