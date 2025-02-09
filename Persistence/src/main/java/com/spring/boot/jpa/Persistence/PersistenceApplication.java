package com.spring.boot.jpa.Persistence;

import com.github.javafaker.Faker;
import com.spring.boot.jpa.Persistence.Services.lecturer.LecturerService;
import com.spring.boot.jpa.Persistence.Services.student.StudentService;
import com.spring.boot.jpa.Persistence.dtos.lecturer.LecturerRequestDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.department.Department;
import com.spring.boot.jpa.Persistence.models.lecturer.Lecturer;
import com.spring.boot.jpa.Persistence.models.program.Program;
import com.spring.boot.jpa.Persistence.models.school.School;
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.department.DepartmentRepository;
import com.spring.boot.jpa.Persistence.repositories.lecturer.LecturerRepository;
import com.spring.boot.jpa.Persistence.repositories.program.ProgramRepository;
import com.spring.boot.jpa.Persistence.repositories.school.SchoolRepository;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Arrays;


//In the beginning, there was darkness
//Until someone set themselves aflame
//Only then did the universe know light
//That person was Tumaini(Hope)
@SpringBootApplication
public class PersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(SchoolRepository schoolRepository,
											   DepartmentRepository departmentRepository,
											   ProgramRepository programRepository,
											   LecturerRepository lecturerRepository,
											   StudentService studentService,
											   LecturerService lecturerService,
											   StudentRepository studentRepository,
											   ModelMappers modelMappers,
											   PasswordEncoder passwordEncoder){
		Faker faker = new Faker();
		String[] nrc = new String[10], snumber = new String[10];
		return args -> {
			School school = new School();
				school.setSchoolCode("NS");
				school.setSchoolName("School Of Natural Sciences");
				school = schoolRepository.save(school);
			School school2 =  new School();
				school2.setSchoolCode("BS");
				school2.setSchoolName("Business Sciences");
				school2 = schoolRepository.save(school2);
			Department department = new Department();
				department.setDepartmentCode("CS");
				department.setDepartmentName("Computer Science");
				department.setSchool(school);
				department = departmentRepository.save(department);
			Department department2 = new Department();
				department2.setDepartmentCode("A");
				department2.setDepartmentName("Administration");
				department2.setSchool(school2);
				department2 = departmentRepository.save(department2);
			Program program = new Program();
				program.setProgramName("Software Engineering");
				program.setProgramCode("SE");
				program.setSchool(school);
				program.setDepartment(department);
				program = programRepository.save(program);
			Program program2 = new Program();
				program2.setProgramName("Human Resource");
				program2.setProgramCode("HR");
				program2.setSchool(school2);
				program2.setDepartment(department2);
				program2 = programRepository.save(program2);
			Lecturer lecturer = new Lecturer();
				lecturer.setDepartment(department);
				lecturer.setDepartmentHead(department);
				lecturer.setLecturerNumber("202177123");
			//lecturer = lecturerRepository.save(lecturer);
			int last = 100;
			for(int i = 0, j = 0; i < last; i++) {
				Student student = new Student();
				student.setStudentNumber(faker.number().numberBetween(1999, 2500) +""+ faker.number().randomNumber(6, true));
				student.setFirstname(faker.name().firstName());
				student.setLastname(faker.name().lastName());
				student.setDob(new Date(faker.date().birthday(17, 30).getTime()).toLocalDate());
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
						).getTime()).toLocalDate().atStartOfDay()
				);
				student.setProgram(i%2 == 0? program: program2);
				student.setSchool(i%2 == 0? school: school2);
				student.setDepartment(i%2 == 0? department:department2);
				student.setEmail(student.getFirstname()
						.concat(student.getLastname())
						.concat(faker.number().randomDigitNotZero() + "")
						.concat("gmail.com"));
				student.setUsername(student.getStudentNumber());
				student.setPassword(passwordEncoder.encode(faker.number().numberBetween(9999, 999999)+""));
				var s = studentRepository.save(student);
				if(i == last-1){
					//lecturer.setEmail(s.getEmail());
					lecturer.setDob(s.getDob());
					lecturer.setNrcNumber(s.getNrcNumber());
					lecturer.setFirstname(s.getFirstname());
					lecturer.setLastname(s.getLastname());
				}
				if(j < nrc.length){
					snumber[j] = s.getStudentNumber();
					nrc[j++] = s.getNrcNumber();
				}



			}
////			studentService.setStudentRepository(studentRepository);
////			studentService.setModelMappers(modelMappers);
////			studentService.findAllStudentsUsingPages()
////					.stream()
////					.parallel()
////					.forEach(System.out::println);
////
////			studentRepository.findAllByFirstnameContaining("J")
////					.stream()
////					.parallel()
////					.map(arg ->{
////						return ("Name: " + arg.getFirstname() + " " + arg.getLastname());
////					})
////					.forEach(System.out::println);
////			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
////			studentRepository.findAllByNationality("america")
////					.stream()
////					.parallel()
////					.forEach(System.out::println);
////			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
////
////			for(String i: nrc){
////				System.out.println("nrc: " + i);
////				System.out.println(
////						modelMappers.mapToStudentResponse(studentRepository.findByNrcNumber(i)));
////				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
////
////			}
////			for(String i: snumber){
////				System.out.println("student number: " + i);
////				System.out.println(
////						modelMappers.mapToStudentResponse(studentRepository.findByStudentNumber(i)));
////				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
////			}
////			System.out.println(studentService.findAllStudentsUsingPages().indexOf(studentService.findAllStudentsUsingPages().getLast()));
////			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
////
////			int count = 0;
////			for(String i: nrc){
////				if(count++ > 5)
////					break;
////				System.out.println("NRC COUNT: " + studentRepository.deleteByNrcNumber(i));
////
////			}
////			for(String i: snumber){
////				System.out.println("STUDENT-NUMBER: " + i);
////			}
////			System.out.println(studentService.findAllStudentsUsingPages().indexOf(studentService.findAllStudentsUsingPages().getLast()));
////			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
////			for(String i: snumber){
////				System.out.println("NUMBER COUNT: " + studentRepository.deleteByStudentNumber(i));
////			}
////			System.out.println(studentService.findAllStudentsUsingPages().indexOf(studentService.findAllStudentsUsingPages().getLast()));
////			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		lecturer = lecturerRepository.save(lecturer);
			department2.setHod(lecturer);
			departmentRepository.save(department2);
			lecturer.setLecturerNumber("2021873649");
			lecturer.setId(null);
			lecturer.setNrcNumber("648068/64/1");
			lecturerRepository.save(lecturer);
		department.setHod(lecturer);
		departmentRepository.save(department);

		if(true){
			Student student = new Student();
			student.setStudentNumber("3031504689");
			student.setFirstname(faker.name().firstName());
			student.setLastname(faker.name().lastName());
			student.setDob(new Date(faker.date().birthday(17, 30).getTime()).toLocalDate());
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
					).getTime()).toLocalDate().atStartOfDay()
			);
			student.setProgram(program);
			student.setSchool(school);
			student.setDepartment(department);
			student.setEmail(student.getFirstname()
					.concat(student.getLastname())
					.concat(faker.number().randomDigitNotZero() + "")
					.concat("gmail.com"));
			student.setUsername("3031504689");
			student.setPassword(passwordEncoder.encode("123456789"));
			var s = studentRepository.save(student);
		}

//		studentService.findAllStudentsWithCustomFields("lastname", "firstname")
//				.stream()
//				.parallel()
//				.forEach(obj->{
//					System.out.print((String)obj[0] + "  ");
//					System.out.println((String)obj[1]);
//				});
//		studentService.findAllStudentsWithCustomFieldsSafe("firstname","lastname","dob", "studentNumber")
//				.stream()
//				.parallel()
//				.forEach(obj->{
//								System.out.print("Student={ ");
//								Arrays.stream(obj).forEach(o ->{
//									System.out.print(o);
//									System.out.print(", ");
//								});
//								System.out.println("}");
//						}
//				);
//			var studentWithId = studentService.findStudentWithCustomFieldsSafe(snumber[0],"firstname","lastname","dob", "studentNumber");
//								System.out.print("Student={ ");
//								Arrays.stream(studentWithId).forEach(o ->{
//									System.out.print(o);
//									System.out.print(", ");
//								});
//								System.out.println("}");
//
//
//			lecturerService.findAllLecturersWithCustomFieldsSafe("firstname","lastname","dob", "lecturerNumber")
//					.stream()
//					.parallel()
//					.forEach(obj->{
//								System.out.print("Lecturer={ ");
//								Arrays.stream(obj).forEach(o ->{
//									System.out.print(o);
//									System.out.print(", ");
//								});
//								System.out.println("}");
//							}
//					);

		///Error Testing

//			studentService.findAllStudentsWithCustomFieldsSafe("firstname","las","dob")
//					.stream()
//					.parallel()
//					.forEach(obj->{
//								System.out.print((String)obj[0] + "  ");
//								System.out.println((String)obj[1]);
//							}
//					);
//			studentService.findAllStudentsWithCustomFields("lastname", "first")
//					.stream()
//					.parallel()
//					.forEach(obj->{
//						System.out.print((String)obj[0] + "  ");
//						//System.out.println((String)obj[1]);
//					});

		};
	}

}
