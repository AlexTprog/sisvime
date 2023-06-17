package com.sisvime.app.security;


import com.sisvime.app.config.LoginSuccessMessage;
import com.sisvime.app.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private LoginSuccessMessage successMessage;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                // Los recursos estáticos no requieren autenticación
//                .antMatchers("/css/**", "/imagen/**", "/js/**").permitAll()
//                // Las vistas públicas no requieren autenticación
//                .antMatchers("/", "/login", "/signup", "/bcrypt/**").permitAll()
//                // Asignar permisos a URLs por ROLES
//                .antMatchers("/views/personal/createper/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/views/personal/saveper/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/views/personal/editper/**").hasAnyAuthority("ADMIN")
//                .antMatchers("/views/personal/deleteper/**").hasAnyAuthority("ADMIN")
//                // Todas las demás URLs de la Aplicación requieren autenticación
//                .anyRequest().authenticated()
//                //El formulario de Login no requiere autenticacion
//                .and().formLogin().successHandler(successMessage).loginPage("/login").permitAll()
//                .and().logout().permitAll();
        http.authorizeRequests().anyRequest().permitAll();
        return http.build();
    }


}
