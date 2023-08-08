package com.bookshop;

import com.bookshop.model.CategoryBook;
import com.bookshop.model.Role;
import com.bookshop.model.User;
import com.bookshop.repository.CategoryRepository;
import com.bookshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendBookShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBookShopApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          CategoryRepository categoryRepository
    ){
        return args -> {
            userRepository.save(new User(1L,"admin","manager",
                    passwordEncoder.encode("password"),
                    "19130015@st.hcmuaf.edu.vn",Role.ADMIN));
            categoryRepository.save(new CategoryBook(1L,"tho","Thơ"));
            categoryRepository.save(new CategoryBook(2L,"tieu_thuyet","Tiểu thuyết"));
            categoryRepository.save(new CategoryBook(3L,"van_hoc","Văn học"));
        };
    }
}
