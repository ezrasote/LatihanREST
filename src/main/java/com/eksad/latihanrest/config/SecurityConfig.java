package com.eksad.latihanrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eksad.latihanrest.service.UsersService;

@Configuration
@EnableWebSecurity //menjadikan class sebagai konfigurasi default keamanan saat mengakses web

public class SecurityConfig extends WebSecurityConfigurerAdapter { // berisi method yang akan di implements sebagai konfigurasi
	
	@Autowired
	private UsersService usersService; // class untuk mencari dan menampung data username, password, role

	@Override
 	protected void configure(HttpSecurity http) throws Exception { // konfigurasi akses melalui controller 
		
		http.httpBasic().and() // membaca header basic auth(username dan password)
			.csrf().disable() //method yg diakses hanya bisa get, jd untuk bisa mengaktifkan semua method, di disable
			.authorizeRequests() // akses masuk ke url
			.antMatchers("/admin/**").hasAuthority("ADMIN") // memilih url mana yg mau dikses, has role itu utk hak akses mana yg boleh masuk dan disini hanya disetting admin yg boleh masuk
			.antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")  // url user, admin bisa masuk
			.and().formLogin().permitAll() // menampilkan loginform bentuk html
			;
 	}

 	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // konfigurasi mencari akses username password dan role
 		/*
 		String admin = "admin"; //di line 18
 		String adminPassword = encoder().encode("1234"); //passwordnya
 		
 		String user = "user";
 		String userPassword = encoder().encode("qwerty");
 		
 		auth.inMemoryAuthentication()
 			.withUser(admin).password(adminPassword).roles("ADMIN", "USER");
 		
 		auth.inMemoryAuthentication()
 			.withUser(user).password(userPassword).roles("USER");
 		
 			*/
 		//kalo gini user line 27 hanya diberikan akses sebagai user, sehingga ketika localhost8080/admin (line
 		// 18 dia gaakan bisa
 		
 		auth.userDetailsService(usersService).passwordEncoder(encoder());
 	}

 	@Bean
 	public BCryptPasswordEncoder encoder() { // merubah password jadi ga keliatan
 		
 		return new BCryptPasswordEncoder();
 	}
}
