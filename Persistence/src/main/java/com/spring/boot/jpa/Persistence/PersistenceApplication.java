package com.spring.boot.jpa.Persistence;

import com.github.javafaker.Faker;
import com.spring.boot.jpa.Persistence.Services.student.StudentService;
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class PersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentService studentService,
											   StudentRepository studentRepository){
		Faker faker = new Faker();
		return args -> {
			for(long i = 0; i < 10000; i++) {
				Student student = new Student();
				student.setStudentId(faker.number().numberBetween(1999, 2500) +""+ faker.number().randomNumber(6, true));
				student.setFirstname(faker.name().firstName());
				student.setLastname(faker.name().lastName());
				student.setDob(new Date(faker.date().birthday(17, 30).getTime()));
				student.setAddress(faker.address().streetAddress());
				student.setPhoneNumber(faker.phoneNumber().phoneNumber());
				student.setNrcNumber(new StringBuilder().append(faker.number().randomNumber(6, true))
						.append("/")
						.append(faker.number().numberBetween(0, 99))
						.append("/")
						.append(faker.number().numberBetween(0, 5))
						.toString());
				student.setCountry(faker.country().name());
				student.setProvince(faker.name().title());
				student.setDistrict(faker.country().capital());
				studentRepository.save(student);
			}
			studentService.findAllStudents()
					.stream()
					.parallel()
					.forEach(System.out::println);

			studentRepository.findAllByFirstnameContaining("J")
					.stream()
					.parallel()
					.forEach(System.out::println);
			studentRepository.findAllByCountry("america")
					.stream()
					.parallel()
					.forEach(System.out::println);



		};
	}

}
