package com.lubna.jpa_workshop;

import com.lubna.jpa_workshop.dao.AppUserDao;
import com.lubna.jpa_workshop.entity.Details;
import com.lubna.jpa_workshop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class JpaWorkshopApplication implements CommandLineRunner {

    private final AppUserDao appUserDao;

    @Autowired
    public JpaWorkshopApplication(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaWorkshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User lubna = new User("Lubna", "12345", LocalDate.now());
        Details details = new Details("lubna@gmail.com", "lubna", LocalDate.now());
        lubna.setDetails(details);
        details.setUser(lubna);
        appUserDao.create(lubna);

        System.out.println(appUserDao.findAll());

    }
}
