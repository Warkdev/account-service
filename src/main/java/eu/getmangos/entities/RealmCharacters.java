package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "realmcharacters")
@NamedQueries({
    @NamedQuery(name = "RealmCharacters.findAll", query = "SELECT rc FROM RealmCharacters rc"),
    @NamedQuery(name = "RealmCharacters.findById", query = "SELECT rc FROM RealmCharacters rc WHERE rc.id = :id"),
    @NamedQuery(name = "RealmCharacters.findByRealm", query = "SELECT rc FROM RealmCharacters rc WHERE rc.id.realmID = :realmID"),
    @NamedQuery(name = "RealmCharacters.findByAccount", query = "SELECT rc FROM RealmCharacters rc WHERE rc.id.accountID = :accountID"),
    @NamedQuery(name = "RealmCharacters.findDeadLinks", query = "SELECT rc FROM RealmCharacters as rc LEFT JOIN Realm as r ON rc.id.realmID = r.id LEFT JOIN Account as a ON rc.id.accountID = a.id WHERE r.id IS NULL OR a.id IS NULL"),
    @NamedQuery(name = "RealmCharacters.deleteDeadLink", query= "DELETE FROM RealmCharacters rc WHERE rc.id.realmID = :realmId AND rc.id.accountID = :accountId"),
})
public class RealmCharacters implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RealmCharactersID id;

    @Column(name = "numchars")
    @NotNull
    private int numChars;

    public RealmCharactersID getId() {
        return id;
    }

    public void setId(RealmCharactersID id) {
        this.id = id;
    }

    public int getNumChars() {
        return numChars;
    }

    public void setNumChars(int numChars) {
        this.numChars = numChars;
    }
}
