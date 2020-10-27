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
@Table(
    name="db_version"
)
@NamedQueries({
    @NamedQuery(name = "DbVersion.findAll", query="SELECT d from DbVersion d")
})
public class DbVersion implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DbVersionId id;

    @NotNull
    @Column(name="description", length = 30)
    private String description;

    @Column(name="description", length = 150)
    private String comment;

    public DbVersionId getId() {
        return id;
    }

    public void setId(DbVersionId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
