package com.hyperlogy_ban_hang_2.config;

import com.hyperlogy_ban_hang_2.util.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SecurityConfig {
    private final RSAKeyProperties keyProperties;

    public SecurityConfig(RSAKeyProperties keyProperties) {
        this.keyProperties = keyProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    Bean authManager(): Đây là một bean để cung cấp một AuthenticationManager.
//    Bean này sử dụng một DaoAuthenticationProvider và UserDetailsService
//    để xác thực người dùng.
//    Mật khẩu được mã hóa bằng PasswordEncoder đã được định nghĩa trong bean passwordEncoder().
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    //   Bean filterChain(): Đây là một bean để cấu hình các quy tắc bảo mật và xác thực.
//   Các quy tắc này được thiết lập thông qua HttpSecurity.
//   Ví dụ: tất cả các yêu cầu /auth/** sẽ không yêu cầu xác thực,
//   /admin/** yêu cầu vai trò "ADMIN", /user/**
//   yêu cầu vai trò "ADMIN" hoặc "USER", và tất cả các yêu cầu khác đều cần xác thực.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //tat tinh nang csrf(Cross-Site Request Forgery) CSRF là một cuộc tấn công bảo mật phổ biến và việc tắt nó thường được thực hiện trong các ứng dụng API stateless.
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/thong-ke/**").permitAll();
                            auth.requestMatchers("/auth/**").permitAll();
                            auth.requestMatchers("/guest/**").permitAll();
                            auth.requestMatchers("/user/**").hasAnyRole("ADMIN", "USER");
                            auth.requestMatchers("/admin/**").hasRole("ADMIN");
                            auth.anyRequest().authenticated();
                        }
                );
        //cau hinh viec xac thuc JWT = phuong thuc oauth2ResourceServer().jwt
        //=> dieu nay cho phep ung dung xac thuc cac yeu cau su dung jwt
        //su dung phuong thuc jwtAuthenticationConverter() de cau hinh cach
        //chuyen doi thong tin tu JWT => authorities
        httpSecurity.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

        //cau hinh quan ly phien (session management) = SessionCreationPolicy
        // la STATELESS => cho biet rang phien khong duoc tao ra
        // va moi yeu cau deu duoc coi la phien khong giu trang thai
        httpSecurity
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return httpSecurity.build();
    }

    //Trong Spring Security, Session Creation Policy (Chính sách tạo phiên)
// quy định cách quản lý phiên người dùng khi gửi yêu cầu đến ứng dụng.
// Khi SessionCreationPolicy được đặt thành STATELESS,
// nghĩa là ứng dụng sẽ không tạo ra và không duy trì phiên cho người dùng.
//Khi phiên được đặt là STATELESS, các yêu cầu từ người dùng được xem như không giữ trạng thái,
// có nghĩa là mỗi yêu cầu đều phải tự cung cấp các thông tin xác thực (thường là thông qua token),
// à ứng dụng không lưu trữ bất kỳ trạng thái nào về phiên.
//Điều này có ý nghĩa là mỗi yêu cầu đến ứng dụng sẽ được xác thực độc lập
// và không phụ thuộc vào trạng thái của phiên trước đó.
// Việc đặt STATELESS có lợi trong các ứng dụng API
// hoặc ứng dụng phân tán nơi không có yêu cầu duy trì trạng thái phiên.
//Việc cấu hình SessionCreationPolicy thành STATELESS trong đoạn mã
// httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// cho biết rằng ứng dụng sẽ không tạo ra và không duy trì phiên cho
// gười dùng và xem mỗi yêu cầu là phiên không giữ trạng thái.
    //=====================FROM CHAT GPT NHUNG KHONG HIEU LAM :(==========================
    //   Bean jwtDecoder(): Đây là một bean để tạo một JwtDecoder
//   sử dụng khóa công khai RSA được cung cấp trong RSAKeyProperties.
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyProperties.getPublicKey()).build();
    }

    //    Bean jwtEncoder(): Đây là một bean để tạo một JwtEncoder.
//    Bean này sử dụng cặp khóa RSA trong RSAKeyProperties để mã hóa JWT.
    @Bean
    public JwtEncoder jwtEncoder() {
        //tạo một đối tượng JWK bằng cách sử dụng khóa công khai và khóa riêng của RSAKeyProperties.
        // Đối tượng JWK chứa thông tin về khóa RSA sử dụng cho mã hóa JWT.
        JWK jwk = new RSAKey.Builder(keyProperties.getPublicKey()).privateKey(keyProperties.getPrivateKey()).build();
        //tạo một đối tượng JWKSet bằng cách sử dụng JWK
        //tạo một đối tượng JWKSource<SecurityContext>
        // sử dụng JWKSet bằng cách sử dụng ImmutableJWKSet.
        // JWKSource là nguồn cung cấp các khóa JWK cho JwtEncoder.
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
//tạo một đối tượng NimbusJwtEncoder bằng cách sử dụng JWKSource đã được tạo trước đó.
// NimbusJwtEncoder là một implementaion của JwtEncoder từ thư viện Nimbus JOSE+JWT.
        return new NimbusJwtEncoder(jwks);
    }

    //   Bean jwtAuthenticationConverter(): Đây là một bean để tạo một JwtAuthenticationConverter.
//   Bean này cấu hình cách chuyển đổi các thông tin từ JWT thành các quyền hạn (authorities) của người dùng,
//   sử dụng cụ thể các thuộc tính như roles và authorityPrefix.
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5500") // Thay đổi địa chỉ nguồn gốc tại đây
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
