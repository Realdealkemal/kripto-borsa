package com.example.jwt.securityconfig;

import com.example.jwt.JwtAuthenticationFilter;
import com.example.jwt.appconfig.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
//5
public class SecurityConfig {
    //şu url isteklerine izin ver, şu url isteklerini yetkilendirmeye tabii tut vs işlemleri burada yapılır

    private static final String REGISTER = "/register";
    private static final String AUTHENTICATE = "/authenticate";

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests ->
                requests.requestMatchers(AUTHENTICATE,REGISTER) //eğer bir istek authenticate veya register ucuna gelirse, filtreleme devreye girme, direkt controllera düşssün diyoruz
                .permitAll()
                .anyRequest().
                authenticated()) //authenticate ve register haricindeki tüm urleri jwt filtresine sok
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // bu metot aslında her şeyin nasıl işleyeceğini belirttiğimiz. EN KRİTİK METOTTUR. UYGULAMA AYAĞA İLK KALTIĞINDA 1 KERE ÇALIŞIR
        // hangi metotun yetkisiz açılacağını, hangi metotun yetkiye tabii tutulacağı burada belirlenir (register ve authenticate dışında hepsi genelde yetkilendirilir)
        // yetki gerekiyosa kullanıcı adı şifre kontrolünü hangi metoda göre yapacağı belirlenir (authenticationProvider)
        // bu kontrolden sonra alınan tokenin nasıl doğrulanacağı belirlenir jwtAuthenticationFilter

                return http.build();
    }
}
