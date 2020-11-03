package eu.getmangos.dto;

import java.io.Serializable;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class DbVersionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "The version of the release")
    private int version;

    @Schema(description = "The current core structure level")
    private int structure;

    @Schema(description = "The current core content level")
    private int content;

    @Schema(description = "A short description of the latest database revision")
    private String description;

    @Schema(description = "A comment about the latest database revision")
    private String comment;

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
