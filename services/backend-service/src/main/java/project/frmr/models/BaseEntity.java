package project.frmr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import org.springframework.util.ClassUtils;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable, Entity {
    public static String PK_FIELD_NAME = "id";

    public abstract String getId();

    @JsonIgnore
    @Column(name = "IS_DELETED", nullable = false)
    private Boolean isDeleted = false;

    @JsonIgnore
    @Column(name = "DELETION_TOKEN", nullable = false)
    private String deletionToken = "-";

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(ClassUtils.getUserClass(obj))) {
            return false;
        }

        BaseEntity that = (BaseEntity) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }
}
