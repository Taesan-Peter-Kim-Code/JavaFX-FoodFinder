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
 * @author Josh
 */
@Entity
@Table(name = "EMAILS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emails.findAll", query = "SELECT e FROM Emails e")
    , @NamedQuery(name = "Emails.findById", query = "SELECT e FROM Emails e WHERE e.id = :id")
    , @NamedQuery(name = "Emails.findBySender", query = "SELECT e FROM Emails e WHERE e.sender = :sender")
    , @NamedQuery(name = "Emails.findByContent", query = "SELECT e FROM Emails e WHERE e.content = :content")
    , @NamedQuery(name = "Emails.findByTimestamp", query = "SELECT e FROM Emails e WHERE e.timestamp = :timestamp")})
public class Emails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SENDER")
    private String sender;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Emails() {
    }

    public Emails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
        if (!(object instanceof Emails)) {
            return false;
        }
        Emails other = (Emails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Emails[ id=" + id + " ]";
    }
    
}
