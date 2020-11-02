package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
* Composite Primary Key for DbVersion
*/
@Embeddable
public class DbVersionId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int version;
    private int structure;
    private int content;

    private DbVersionId() {

    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getStructure() {
        return structure;
    }

    public void setStructure(int structure) {
        this.structure = structure;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + content;
        result = prime * result + structure;
        result = prime * result + version;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DbVersionId other = (DbVersionId) obj;
        if (content != other.content)
            return false;
        if (structure != other.structure)
            return false;
        if (version != other.version)
            return false;
        return true;
    }
}
