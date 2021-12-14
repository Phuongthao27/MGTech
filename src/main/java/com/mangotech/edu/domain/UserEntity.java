package com.mangotech.edu.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mangotech.edu.config.Constants;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ad_user")
public class UserEntity extends BaseEntity<Long> {

    @NotNull(message = "User name không được trống!")
    @Size(min = 1, max = 50)
    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @NotNull(message = "Password không được trống!")
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @NotNull
    @Size(min = 16, max = 16)
    @Column(name = "salt", length = 16, nullable = false)
    @JsonIgnore
    private String salt;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity department;

    @NotNull(message = "Email không được trống!")
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(
        name = "edu_user_facility",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    @NotNull(message = "Cơ sở làm việc không được trống!")
    private List<FacilityEntity> facilities;

    @Column(length = 500)
    private String note;
}
