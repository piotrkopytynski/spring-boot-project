package com.boot;

import com.boot.dao.AddressDao;
import com.boot.dao.CarDao;
import com.boot.dao.PersonDao;
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
//            final Person person = new Person("frgeghgedy", "fge15gr@553gmgil.com", Gender.M, true, 2);
//            final Person person2 = new Person("frgeghgedy", "fge52gr@553gmgil.com", Gender.M, true, 2);
//            final Person person3 = new Person("frgeghgedy", "fge53gr@553gmgil.com", Gender.M, true, 2);
//            final Person person4 = new Person("frgeghgedy", "fge54gr@553gmgil.com", Gender.F, true, 1);
//            final Person person5 = new Person("frgeghgedy", "fge55gr@553gmgil.com", Gender.M, true, 2);
//            final Person person6 = new Person("frgeghgedy", "fge56gr@553gmgil.com", Gender.M, true, 2);
////            final Address address = new Address("g1e4ij", "e1ji4le", "643g1", "215423");
////
//            personDao.save(person);
//            personDao.save(person2);
//            personDao.save(person3);
//            personDao.save(person4);
//            personDao.save(person5);
//            personDao.save(person6);
//
//            personService.countPersonsByGenderAndMinimalChildrenNumber(Gender.F, 1);

////            person.addAddress(address);
//
//            final Car car = new Car("DW 213532");
//            final Car car2 = new Car("OGL 213532");
//            final Person person3 = personDao.save(new Person("Cherry", "abd@op.pl", Gender.F, false, 3));
//
//            car.setPerson(person);
//            carDao.save(car);
//
//            person3.addCar(car2);
//            carDao.save(car2);
//            personDao.update(person3);
//
//            final Address address2 = new Address("Wrocław", "Sienkiewicza", "643g1241", "215421423");
//            final Person person4 = new Person("Lebron", "ojggeg@gmial.com", Gender.M, true, 52);
//            address2.addPerson(person4);
//            addressDao.save(address2);
        };
    }
}