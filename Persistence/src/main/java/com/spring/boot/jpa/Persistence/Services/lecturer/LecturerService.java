package com.spring.boot.jpa.Persistence.Services.lecturer;

import com.spring.boot.jpa.Persistence.repositories.lecturer.LecturerRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
public class LecturerService {
    private LecturerRepository lecturerRepository;
}
