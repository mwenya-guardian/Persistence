package com.spring.boot.jpa.Persistence;

import com.github.javafaker.Faker;
import com.spring.boot.jpa.Persistence.Services.student.StudentService;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.EntityBaseClass;
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
											   StudentRepository studentRepository, ModelMappers modelMappers){
		Faker faker = new Faker();
		String[] nrc = new String[10], snumber = new String[10];
		return args -> {
			for(int i = 0, j = 0; i < 500; i++) {
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
				student.setNationality(faker.country().name());
				student.setProvince(faker.nation().capitalCity());
				student.setDistrict(faker.country().capital());
				student.setEnrollmentDate(new Date(
						faker.date().between(
						new java.util.Date(),
						new java.util.Date(2000, 01, 01)
						).getTime())
				);
				var s = studentRepository.save(student);
				if(j < nrc.length){
					snumber[j] = s.getStudentId();
					nrc[j++] = s.getNrcNumber();
				}



			}
			studentService.setStudentRepository(studentRepository);
			studentService.findAllStudents()
					.stream()
					.parallel()
					.forEach(System.out::println);

			studentRepository.findAllByFirstnameContaining("J")
					.stream()
					.parallel()
					.map(arg ->{
						return ("Name: " + arg.getFirstname() + " " + arg.getLastname());
					})
					.forEach(System.out::println);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			studentRepository.findAllByNationality("america")
					.stream()
					.parallel()
					.forEach(System.out::println);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			for(String i: nrc){
				System.out.println(
						studentRepository.findByNrcNumber(i));
				studentRepository.deleteByNrcNumber(i);

			}
			for(String i: snumber){
				System.out.println(
						studentRepository.findByStudentId(i));
				studentRepository.deleteByStudentId(i);
			}

		};
	}

}
