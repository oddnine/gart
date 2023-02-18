package com.project.gart.service;

import com.project.gart.domain.User;
import com.project.gart.domain.Work;
import com.project.gart.domain.WorkGenre;
import com.project.gart.domain.dto.WorkDto;
import com.project.gart.repository.WorkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public Long save(User user, Work work) {
        work.setUser(user);
        workRepository.save(work);
        return work.getWorkId();
    }

    public void updateWork(Work originalWork, Work updateWork) {
        originalWork.updateWork(updateWork);
    }

    public WorkDto findByWorkId(Long workId) {
        return new WorkDto(Objects.requireNonNull(workRepository.findById(workId).orElse(null)));
    }

    public void delete(Long workId) {
        Work findWork = workRepository.findById(workId).orElse(null);
        findWork.deleteWork();
    }

    public List<WorkDto> findByWorkGenre(List<WorkGenre> workGenres) {
        List<Long> longs = new ArrayList<>();
        for (WorkGenre workGenre : workGenres) {
            longs.add(workGenre.getFkWorkId().getWorkId());
        }

        List<Work> findWorks = workRepository.findAllById(longs);

        List<WorkDto> findWorkDtoList = new ArrayList<>();

        for (Work findWork : findWorks) {
            findWorkDtoList.add(new WorkDto(findWork));
        }

        return findWorkDtoList;
    }
}
