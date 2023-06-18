package com.sisvime.app.security;


import com.sisvime.app.security.auth.LoginSuccessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private LoginSuccessMessage successMessage;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // Los recursos estáticos no requieren autenticación
                .antMatchers("/css/**", "/imagen/**", "/js/**").permitAll()
                // Las vistas públicas no requieren autenticación
                .antMatchers("/", "/login", "/signup", "/bcrypt/**").permitAll()
                // Asignar permisos a URLs por ROLES
                .antMatchers("/views/personal/createper/**").hasAnyAuthority("ADMIN")
                .antMatchers("/views/personal/saveper/**").hasAnyAuthority("ADMIN")
                .antMatchers("/views/personal/editper/**").hasAnyAuthority("ADMIN")
                .antMatchers("/views/personal/deleteper/**").hasAnyAuthority("ADMIN")
                // Todas las demás URLs de la Aplicación requieren autenticación
                .anyRequest().authenticated()
                // El formulario de Login no requiere autenticacion
                .and().formLogin().successHandler(successMessage).loginPage("/login").permitAll();
        //.and().logout().permitAll();
        return http.build();
    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username, password, estatus from usuarios where username=?")
                .authoritiesByUsernameQuery("select u.username, p.perfil from usuario_perfil up " + "inner join usuarios u on u.id = up.id_Usuario "
                        + "inner join perfiles p on p.id = up.id_Perfil "
                        + "where u.username = ?");
    }
}