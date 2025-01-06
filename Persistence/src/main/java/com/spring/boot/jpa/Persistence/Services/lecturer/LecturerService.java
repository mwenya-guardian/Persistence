package com.spring.boot.jpa.Persistence.Services.lecturer;

import com.spring.boot.jpa.Persistence.mappers.ModelMappers;
import com.spring.boot.jpa.Persistence.repositories.lecturer.LecturerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class LecturerService {
    private LecturerRepository lecturerRepository;
    private ModelMappers modelMappers;
}
