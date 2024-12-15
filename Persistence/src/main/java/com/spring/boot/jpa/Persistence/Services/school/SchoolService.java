package com.spring.boot.jpa.Persistence.Services.school;

import com.spring.boot.jpa.Persistence.repositories.school.SchoolRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
public class SchoolService {
    private SchoolRepository schoolRepository;
}
