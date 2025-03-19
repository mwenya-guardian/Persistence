package com.spring.boot.jpa.Persistence.services.lecture;

import com.spring.boot.jpa.Persistence.services.course.CourseService;
import com.spring.boot.jpa.Persistence.services.lecturer.LecturerService;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureRequestDto;
import com.spring.boot.jpa.Persistence.dtos.lecture.LectureResponseDto;
import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.repositories.lecture.LectureRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Service
@AllArgsConstructor
public class LectureService {
    private LectureRepository lectureRepository;
    private LecturerService lecturerService;
    private CourseService courseService;
    private ModelMappers modelMappers;

    //Create
    public LectureResponseDto createLecture(LectureRequestDto lectureRequestDto){
        var newLecture = modelMappers.mapToLecture(lectureRequestDto);
            var lecturer = lecturerService.findByLecturerNumber(lectureRequestDto.lecturerNumber());
            var course = courseService.findByCourseCode(lectureRequestDto.courseCode());
                newLecture.setLecturer(lecturer);
                newLecture.setCourse(course);
        var savedLecture = lectureRepository.saveAndFlush(newLecture);
        return modelMappers.mapToLectureResponse(savedLecture);
    }


    //Update
    public LectureResponseDto updateLecture(LectureRequestDto lectureRequestDto){
        var lecture = modelMappers.mapToLecture(lectureRequestDto);
        var id = lectureRepository.findByLectureCode(
                lecture.getLectureCode())
                .orElseThrow()
                .getLectureId();
        lecture.setLectureId(id);
        var updatedLecture = lectureRepository.save(lecture);
        return modelMappers.mapToLectureResponse(updatedLecture);
    }
    //Retrieve
    public List<LectureResponseDto> findAllLecturesUsingPages(Integer pageNumber, Integer pageSize, String sort){
        Pageable pageable;
        if(!sort.isEmpty())
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort).ascending());
        else
            pageable = PageRequest.of(pageNumber, pageSize);
        return lectureRepository.findAll(pageable)
                .stream()
                .map(modelMappers::mapToLectureResponse)
                .toList();
    }
    public List<LectureResponseDto> findAllLectures(){
        return lectureRepository.findAll()
                .stream()
                .parallel()
                .map(modelMappers::mapToLectureResponse)
                .toList();
    }
    public List<LectureResponseDto> findAllLecturesWithStartTimeBetween(Timestamp start , Timestamp end){
        return lectureRepository.findAllByStartTimeBetween(start, end)
                .stream()
                .parallel()
                .map(modelMappers::mapToLectureResponse)
                .toList();
    }
    public List<LectureResponseDto> findAllLecturesWithCourse(String courseCode){
        var courseId = courseService.getCourseId(courseCode);
        return lectureRepository.findAllByCourseId(courseId)
                .stream()
                .parallel()
                .map(modelMappers::mapToLectureResponse)
                .toList();
    }
    public List<LectureResponseDto> findAllLecturesWithLecturer(String lecturerNumber){
        var lecturerId = lecturerService.getLecturerId(lecturerNumber);
        return lectureRepository.findAllByLecturerId(lecturerId)
                .stream()
                .parallel()
                .map(modelMappers::mapToLectureResponse)
                .toList();
    }
    public LectureResponseDto findLectureWithCode(String code){
        return lectureRepository.findByLectureCode(code)
                .map(modelMappers::mapToLectureResponse)
                .orElseThrow();
    }

    //Delete
    public Integer deleteLectureWithLectureCode(String lectureCode){
        return lectureRepository.deleteByLectureCode(lectureCode);
    }
}
