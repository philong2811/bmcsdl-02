package bmcsdl_02.bmcsdl.Config;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import bmcsdl_02.bmcsdl.Common.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
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
  public SecurityFilterChain applicationSecutiry(HttpSecurity http) throws Exception{
    http
        .cors().and()
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers(antMatcher("/api/auth/**")).permitAll()
        .requestMatchers(antMatcher("/user/**")).hasAuthority(RoleEnum.USER.name())
        .requestMatchers(antMatcher("/XT/**")).hasAuthority(RoleEnum.XT.name())
        .requestMatchers(antMatcher("/XD/**")).hasAuthority(RoleEnum.XD.name())
        .requestMatchers(antMatcher("/LT/**")).hasAuthority(RoleEnum.LT.name())
        .requestMatchers(antMatcher("/GS/**")).hasAuthority(RoleEnum.GS.name())
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
