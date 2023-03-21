package project.frmr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import project.frmr.entity.User;

import javax.persistence.*;
import java.util.Date;

public abstract class ModifiableEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "ID")
    @CreatedBy
    @JsonIgnore
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_date")
    @JsonIgnore
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "ID")
    @LastModifiedBy
    @JsonIgnore
    private User lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE")
    @LastModifiedDate
    @JsonIgnore
    private Date lastModifiedDate;

}
