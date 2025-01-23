package com.spring.boot.jpa.Persistence.Services.student;

import com.spring.boot.jpa.Persistence.Services.department.DepartmentService;
import com.spring.boot.jpa.Persistence.Services.program.ProgramService;
import com.spring.boot.jpa.Persistence.Services.school.SchoolService;
import com.spring.boot.jpa.Persistence.dtos.student.StudentRequestDto;
import com.spring.boot.jpa.Persistence.dtos.student.StudentResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.models.student.Student;
import com.spring.boot.jpa.Persistence.repositories.student.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;
    private SchoolService schoolService;
    private ProgramService programService;
    private DepartmentService departmentService;
    private StudentNumberGenerator studentNumberGenerator;
    private ModelMappers modelMappers;
    @PersistenceContext
    private EntityManager entityManager;

    //Create
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto){
        var newStudent = modelMappers.mapToNewStudent(studentRequestDto);
            newStudent.setStudentNumber(
                    studentNumberGenerator.newStudentNUmberGenerate()
            );
            var school = schoolService.findBySchoolId(studentRequestDto.schoolId());
            var program = programService.findByProgramId(studentRequestDto.programId());
            var department = departmentService.findByDepartmentId(studentRequestDto.departmentId());
                newStudent.setProgram(program);
                newStudent.setSchool(school);
                newStudent.setDepartment(department);
        studentRepository.saveAndFlush(newStudent);
        var savedStudent = studentRepository.findByStudentNumberQuery(newStudent.getStudentNumber())
                .orElseThrow();

        return modelMappers.mapToStudentResponse(savedStudent);
    }

    //Update
    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Integer Id){
        var newStudent = modelMappers.mapToStudent(studentRequestDto);
            newStudent.setId(Id);
        var savedStudent = studentRepository.save(newStudent);
        return modelMappers.mapToStudentResponse(savedStudent);
    }
    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, String studentNumber){
        var newStudent = modelMappers.mapToStudent(studentRequestDto);
            Integer Id = studentRepository.findByStudentNumberQuery(studentNumber)
                    .orElseThrow()
                    .getId();
            newStudent.setId(Id);
        var savedStudent = studentRepository.save(newStudent);
        return modelMappers.mapToStudentResponse(savedStudent);
    }

    //Retrieve
    public List<StudentResponseDto> findAllStudents(){
        return studentRepository.findAllQuery()
                .stream()
                .parallel()
                .map(modelMappers::mapToStudentResponseWithOutJoin)
                .toList();
    }
    public StudentResponseDto findStudentByStudentNumber(String studentId){
        var student = studentRepository.findByStudentNumber(studentId)
                .orElse(new Student());
        return modelMappers.mapToStudentResponse(student);
    }
    public StudentResponseDto findStudentsByNrc(String nrc){
        var student = studentRepository.findByNrcNumber(nrc)
                .orElse(new Student());
        return modelMappers.mapToStudentResponse(student);
    }
    public List<StudentResponseDto> findAllStudentsUsingPages(Integer pageNumber, Integer pageSize, String sort){
        Pageable pageable;
        if(!sort.isEmpty())
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort).ascending());
        else
            pageable = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageable)
                .stream()
                .map(modelMappers::mapToStudentResponse)
                .toList();
    }
    public List<Object[]> findAllStudentsWithCustomFieldsSafe(String... args){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
            List<Selection<?>> columns = new ArrayList<>();
            Arrays.stream(args)
                    .forEach(arg ->{
                        final String column = arg +"";
                        columns.add(studentRoot.get(column));
                    });
            criteriaQuery.multiselect(columns);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    public Object[] findStudentWithCustomFieldsSafe(String id, String... args){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        List<Selection<?>> columns = new ArrayList<>();
        Arrays.stream(args)
                .forEach(arg ->{
                    final String column = String.valueOf(arg);
                    columns.add(studentRoot.get(column));
                });
        Predicate studentNumberEqualTo = criteriaBuilder.equal(studentRoot.get("studentNumber"), id);
        criteriaQuery.multiselect(columns).where(studentNumberEqualTo);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
    public List<StudentResponseDto> findAllStudentsWithFieldValue(String[] columnName, String[] columnValue) throws Exception {
        if(columnValue.length != columnName.length)
            throw new Exception("Different array lengths");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        Predicate[] columns = new Predicate[columnName.length];
        for(int index = 0, length = columnName.length; index < length; index++){
            columns[index] = criteriaBuilder.like(studentRoot.get(columnName[index]), "%" +columnValue[index] + "%");
        }
        criteriaQuery.select(studentRoot).where(criteriaBuilder.and(columns));
        return entityManager.createQuery(criteriaQuery).getResultList()
                .stream()
                .map(modelMappers::mapToStudentResponse)
                .toList();
    }

    //Delete
    public int deleteStudentsWithStudentNumber(String number){
        return studentRepository.deleteByStudentNumber(number);
    }
    public int deleteStudentByNrc(String nrc){
        return studentRepository.deleteByNrcNumber(nrc);
    }
    public void deleteStudent(Student student){
        studentRepository.delete(student);
    }





    //Custom
    //    public List<Object[]> findAllStudentsWithCustomFields(String... args){
    //        StringBuilder queryString = new StringBuilder("SELECT ");
    //          for(int i = 0, length = args.length; i < length; i++){
    //              queryString.append(args[i]);
    //              if(i + 1 < length)
    //                  queryString.append(", ");
    //          }
    //            queryString.append(" FROM Student");
    //        Query query = entityManager.createQuery(queryString.toString());
    //        return query.getResultList();
    //    }
}
