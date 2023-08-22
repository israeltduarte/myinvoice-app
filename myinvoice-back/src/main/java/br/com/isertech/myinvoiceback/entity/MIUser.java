package br.com.isertech.myinvoiceback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MIUser extends RepresentationModel<MIUser> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "iser-uuid-generator")
    @GenericGenerator(
            name = "iser-uuid-generator",
            strategy = "br.com.isertech.myinvoiceback.util.IserUUIDGenerator",
            parameters = @Parameter(name = "prefix", value = "MIUser")
    )
    private String id;
    private String name;
    private String lastName;
    private String email;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private LocalDateTime created;
    private LocalDateTime updated;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

}

