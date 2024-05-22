package com.cunoc.library.adapters.out.entities;

import com.cunoc.library.domain.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "CAREER_code", nullable = true)
    private CareerEntity career;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "dob", nullable = true)
    private String dob;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
