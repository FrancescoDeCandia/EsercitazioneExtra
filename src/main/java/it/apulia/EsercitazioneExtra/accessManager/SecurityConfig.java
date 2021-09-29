package it.apulia.EsercitazioneExtra.accessManager;

import it.apulia.EsercitazioneExtra.accessManager.filter.CustomAuthenticationFilter;
import it.apulia.EsercitazioneExtra.accessManager.filter.CustomAuthorizationFilter;
import it.apulia.EsercitazioneExtra.repository.BranoRepository;
import it.apulia.EsercitazioneExtra.services.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

//altro esempio su https://www.bezkoder.com/spring-boot-jwt-auth-mongodb/

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final BranoRepository branoRepository;
    private final UtenteService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/accessManager/login");
        http.csrf().disable(); //disabilita il cross site request forgery
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.authorizeRequests().antMatchers("/accessManager/login/**", "/accessManager/token/refresh/**").permitAll();
        //NON AUTENTICATO - registrazione utente
        http.authorizeRequests().antMatchers(POST,"/accessManager/utenti").permitAll();

        //PERMESSI ADMIN
        http.authorizeRequests().antMatchers( "/accessManager/utenti/**", "/accessManager/roles/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers( "/songManager/**").hasAnyAuthority("ROLE_ADMIN");

        //AUTENTICATO - può fare la GET di tutte le liste di canzoni
        http.authorizeRequests().antMatchers(GET, "/songManager/listaBrani/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET, "/songManager/listaPerVoti/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET, "/songManager/listaPerAutori/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(GET, "/songManager/listaPerTitoli/**").hasAnyAuthority("ROLE_USER");

        http.authorizeRequests().anyRequest().authenticated(); //verificare poi accesso per utenti se non lo specifico
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
