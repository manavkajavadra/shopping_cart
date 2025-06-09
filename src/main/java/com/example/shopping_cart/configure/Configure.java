//package com.example.shopping_cart.configure;
//
//
//import com.example.shopping_cart.service_impl.UserServiceImpl;
//import com.example.shopping_cart.util.Constants;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//
//public class Configure {
//
//    @Autowired
//    UserServiceImpl userService;
//
//    @Bean
//    public ModelMapper modelMapper() {
//
//        return new ModelMapper();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // CSRF सुरक्षा बंद कर दी गई है (REST API के लिए अक्सर disable करते हैं)
//                .authorizeHttpRequests(auth -> auth // हम अब यह तय करने जा रहे हैं कि कौन-सी URL पर कौन एक्सेस कर सकता है।
//                        .requestMatchers("/public/**").permitAll() // /public/** रूट को बिना लॉगिन एक्सेस की अनुमति है
//                        .requestMatchers("/admin/**").hasRole(Constants.ROLE_ADMIN)
//                        .anyRequest().authenticated() // बाकी सभी रूट्स के लिए Authentication ज़रूरी है
//                )
//                .httpBasic(Customizer.withDefaults()); // Basic Authentication (Username & Password) enable किया गया है
//
//        return http.build(); // सिक्योरिटी चेन को तैयार करके रिटर्न कर रहे हैं
//    }
//
//    // यह method एक AuthenticationManager Bean बनाता है, जो यूज़र को authenticate करने के लिए ज़िम्मेदार होता है
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//
//        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class); // यह लाइन Spring Security को authentication का तरीका बताने के लिए first step है।
//        authBuilder
//                .userDetailsService(userService) // यह बताते हैं कि यूज़र डिटेल्स कहां से आएंगी (UserServiceImpl)
//                .passwordEncoder(passwordEncoder()); // पासवर्ड को verify करने के लिए कौन-सा encoder इस्तेमाल होगा
//        return   authBuilder.build(); // ऊपर दिए गए config के आधार पर AuthenticationManager को build करते हैं
//    }
//
//    // यह method एक PasswordEncoder Bean return करता है जो BCrypt का उपयोग करता है
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // BCrypt एक सुरक्षित hashing algorithm है पासवर्ड को encrypt करने के लिए
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
////        🔐 CSRF क्या है? (Simple शब्दों में)
////        CSRF मतलब है: Cross-Site Request Forgery
////        ये एक तरह का इंटरनेट पर हमला होता है।
////
////        सोचिए आप एक वेबसाइट (जैसे बैंक की साइट) पर लॉगिन हैं। अब अगर आप किसी गलत वेबसाइट पर जाते हैं,
////        तो वो वेबसाइट आपकी जानकारी के बिना बैंक वेबसाइट पर कुछ गलत काम (जैसे पैसे ट्रांसफर) करने की कोशिश कर सकती है।
////        इससे बचने के लिए, वेबसाइटें एक सुरक्षा सिस्टम लगाती हैं जिसे कहते हैं CSRF प्रोटेक्शन।