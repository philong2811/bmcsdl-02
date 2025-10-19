package bmcsdl_02.bmcsdl.Config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import bmcsdl_02.bmcsdl.Common.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;

  private final AuthenticationProvider authenticationProvider;
  @Bean
  MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http
        .cors().and()
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests()
        .requestMatchers(antMatcher("/css/**")).permitAll()
        .requestMatchers(antMatcher("/login")).permitAll()
<<<<<<< HEAD
        .requestMatchers(antMatcher("/")).permitAll()
=======
>>>>>>> dcbb09afc8152b0040ed818043b67b4c6a60270f
        .requestMatchers(antMatcher("/register")).permitAll()
        .requestMatchers(antMatcher("/user/**")).hasAuthority(RoleEnum.USER.name())
        .requestMatchers(antMatcher("/xt/**")).hasAuthority(RoleEnum.XT.name())
        .requestMatchers(antMatcher("/xd/**")).hasAuthority(RoleEnum.XD.name())
        .requestMatchers(antMatcher("/lt/**")).hasAuthority(RoleEnum.LT.name())
        .requestMatchers(antMatcher("/gs/**")).hasAuthority(RoleEnum.GS.name())
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(logout -> logout
            .logoutUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID"));
    return http.build();
  }
}