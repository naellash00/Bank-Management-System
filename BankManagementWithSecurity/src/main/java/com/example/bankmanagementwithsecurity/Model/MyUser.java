package com.example.bankmanagementwithsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "username cannot be empty")
//    @Size(min = 4, max = 10, message = "username must be between 4 - 10 characters")
    @Column(columnDefinition = "varchar(10) unique")
    private String username;

//    @NotEmpty(message = "password cannot be empty")
//    @Size(min = 6, message = "password must be at least 6 characters")
    @Column(columnDefinition = "varchar(200)")
    private String password;

//    @NotEmpty(message = "name cannot be empty")
//    @Size(min = 2, max = 20, message = "name must be between 2 - 20 characters")
    @Column(columnDefinition = "varchar(20)")
    private String name;

//    @NotEmpty(message = "email cannot be empty")
//    @Email(message = "enter a valid email")
   // @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @Pattern(regexp = "(CUSTOMER|EMPLOYEE|ADMIN)", message = "Role must be either 'customer' - 'employee' - 'admin'")
    private String role;


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    //=====================================================================================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
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



















