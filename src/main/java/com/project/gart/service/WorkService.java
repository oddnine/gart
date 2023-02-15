package com.project.gart.service;

import com.project.gart.domain.User;
import com.project.gart.domain.Work;
import com.project.gart.repository.WorkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public Long uploadWork(User user, Work work) {
        work.setUser(user);
        workRepository.save(work);
        return work.getWorkId();
    }

    public void updateWork(Work originalWork, Work updateWork) {
        originalWork.updateWork(updateWork);
    }

    public Work findByWorkId(Long workId) {
        return workRepository.findById(workId).orElse(null);
    }
}
