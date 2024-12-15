package com.spring.boot.jpa.Persistence.Services.lecture;

import com.spring.boot.jpa.Persistence.repositories.lecture.LectureRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
public class LectureService {
    private LectureRepository lectureRepository;
}
