package com.github.homework.program.service;

import com.github.homework.program.model.ProgramViewDetailDto;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.repository.ProgramRepository;
import com.github.homework.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramViewService {

    private final ProgramRepository programRepository;
    private final ThemeService themeService;

    public Optional<ProgramViewDetailDto> getBy(Long id) {
        return programRepository.findById(id).map(program ->
                new ProgramViewDetailDto(
                        program.getId(),
                        program.getName(),
                        program.getIntroduction(),
                        program.getIntroductionDetail(),
                        program.getRegion(),
                        program.getTheme().getName(),
                        program.getReservationCount()
                )
        );
    }

    public Page<ProgramViewDto> pageBy(Pageable pageable) {
        return programRepository.findBy(pageable);
    }

    public Page<ProgramViewDetailDto> getPopular(Pageable pageable) {
        return programRepository.getPopular(pageable);
    }
}
