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
    @NamedQuery(name = "RealmCharacters.findById", query = "SELECT rc FROM RealmCharacters rc where rc.id = :id"),
    @NamedQuery(name = "RealmCharacters.findByRealm", query = "SELECT rc FROM RealmCharacters rc where rc.id.realmID = :realmID"),
    @NamedQuery(name = "RealmCharacters.findByAccount", query = "SELECT rc FROM RealmCharacters rc where rc.id.accountID = :accountID")
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
