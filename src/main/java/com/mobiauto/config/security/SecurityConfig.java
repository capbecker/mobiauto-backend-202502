package com.mobiauto.config.security;

import com.mobiauto.dao.UsuarioRepository;
import com.mobiauto.model.Perfil;
import com.mobiauto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AutenticadorProvider autenticadorService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthEntryPointJwt entradaNaoAutorizada;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
        return builder.getAuthenticationManager();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    /**
     * Necessário para acessar o userDetailsService por autowired
     * Este metodo tambem converte os usuarios no banco em userDetil
     */
    @Bean
    public UserDetailsService userDetailsService() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UserDetails> userDetails = new ArrayList<>();
        usuarios.forEach(usuario ->
                userDetails.add(usuarioToUserDetails(usuario))
        );
        return new InMemoryUserDetailsManager(userDetails);
    }

    private UserDetails usuarioToUserDetails(Usuario usuario) {
        String[] roles = usuario.getPerfis().stream()
                .map(Perfil::getRole).toArray(String[]::new);
        return User
                .withUsername(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            (request)-> request
                .requestMatchers("/login", "/login/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(e->e.authenticationEntryPoint(entradaNaoAutorizada))
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            ;
        return http.build();
    }
}