package com.example.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//3
public class JwtAuthenticationFilter extends OncePerRequestFilter { //bu extends sayesinde bunun bir filter classı olduğu doğrulanır ve tüm istekler controllerdan öce buraya düşer

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService; //bu servise önemlidir. User entitysini userdetailsdan implements ettiğimiz için, kullanabiliyoruz. Username veritabanında var mı yok mu içindeki metotla bakabilyoruz. Beanini kendimiz oluşturacağız bir sonraki adımda

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //bir istek atıldığında artık bundan sonra buraya düşer. Yani SÜREÇ BURADAN BAŞLAR.

        String header;
        String token;
        String username;

        header = request.getHeader("Authorization"); // -> "Bearer Fsda ..." değeri verir
        if (header == null ) {
            filterChain.doFilter(request, response); // eğer doru header gelmdeyise geri gönderdik. bu süreci devam ettirmek için kullanılır, ancak bu ifte boş return ettik
            return;
        }
        token = header.substring(7); // gelen deper Bearer le başladığı için, suubstring ile bu bearer kısmını sileriz

        try {
            username = jwtService.getUsernameByToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(userDetails!= null && jwtService.isTokenNotExpired(token)) {
                    // Artık kiişiyi security contexte koyabiliriz demek.

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

                    authentication.setDetails(userDetails);

                    SecurityContextHolder.getContext().setAuthentication(authentication); //    burası doldurulursa ancak istek filterı geçip controller katmanına sızar.
                }

            }
        }
        catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Generl bir hata oluştu: " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }


        /*
        JWT token'ı şifre doğrulamasını ilk login işleminde yapar. Token alındıktan sonra, her gelen istekte şifreye gerek kalmaz.
        Token imzası doğrulanarak kimlik doğrulaması yapılır. Bu nedenle, her istekte şifre doğrulaması yapılmaz; sadece token doğrulaması yapılır.
        Şifre doğrulaması ilk giriş sırasında yapılır ve ardından JWT token'ı şifre bilgisi olmadan kimlik doğrulaması yapmak için kullanılır.
         */


}
