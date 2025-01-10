package com.spring.boot.jpa.Persistence.Services.lecture;

import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.repositories.lecture.LectureRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@AllArgsConstructor
public class LectureService {
    private LectureRepository lectureRepository;
    private ModelMappers modelMappers;
}
