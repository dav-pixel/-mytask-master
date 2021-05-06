package am.itspace.taskmaster.config;

import am.itspace.taskmaster.filters.JwtRequestFilter;
import am.itspace.taskmaster.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/projects").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/projects/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/projects/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/tasks").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/tasks/{id}").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .antMatchers(HttpMethod.DELETE, "/tasks/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/tasks/all/{sortBy}/{pageNo}").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/users/all").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/tasks/{status}").hasAnyAuthority("ADMIN", "EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/logs").hasAuthority("EMPLOYEE")
                .antMatchers(HttpMethod.PUT, "/logs/{id}").hasAuthority("EMPLOYEE")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }

   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
   }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
