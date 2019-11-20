/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.synapse.oauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class User implements Serializable, UserDetails {
    public enum UserStatus{
        INACTIVE, ACTIVE, BLOCKED, REPORTED
    }
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "username", nullable = false, length = 128)
    private String username;
    @Column(name = "phone", nullable = false, length = 16)
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "status")
    @Enumerated
    private UserStatus status;
    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != UserStatus.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == UserStatus.ACTIVE;
    }
}
