package com.boot;

import com.boot.dao.AddressDao;
import com.boot.dao.CarDao;
import com.boot.dao.PersonDao;
import com.boot.entity.Address;
import com.boot.entity.Car;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootInitialProjectApplication {

    private final AddressDao addressDao;

    private final CarDao carDao;

    private final PersonDao personDao;

    private final PersonService personService;

    @Autowired
    public SpringBootInitialProjectApplication(final AddressDao addressDao, final CarDao carDao, final PersonDao personDao, final PersonService personService) {
        this.addressDao = addressDao;
        this.carDao = carDao;
        this.personDao = personDao;
        this.personService = personService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInitialProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            final Person person = new Person("frgeghgedy", "fge15gr@553gmgil.com", Gender.M, true, 2);
//
            final Car car = carDao.save(new Car("DW 213532"));
//            final Car car2 = carDao.save(new Car("OGL 213532"));
//            person.addCar(car);
//            person.addCar(car2);
//            personDao.save(person);
//
//            car.setRegistrationNumber("BLC 239895");
//
//            carDao.update(car);
//
//            final Person person2 = new Person("frgeghgedy", "fge52gr@553gmgil.com", Gender.M, true, 2);
        };
    }
}