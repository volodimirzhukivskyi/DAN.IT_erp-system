package com.danit.erp.service.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Trainer;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.roles.TrainerRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TrainerService implements BaseService<Trainer,Integer> {
  private final TrainerRepository trainerRepository;

  @Override
  public List<Trainer> findAll() {
    return trainerRepository.findByDeletedFalse();
  }

  @Override
  public Page<Trainer> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return trainerRepository.findByDeletedFalse(pageable);
  }

  @Override
  public Trainer findById(Integer userId) {
    return trainerRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("Тренера"));
  }


  @Override
  public Trainer create(Trainer obj) {
    Trainer trainer = Trainer.builder().fullName(obj.getFullName()).build();
    return trainerRepository.save(trainer);
  }

  @Override
  public void update(Trainer obj) {
    Trainer findTrainer = trainerRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Тренера"));

    Trainer trainer = Trainer.builder().id(findTrainer.getId()).fullName(obj.getFullName()).build();
    trainerRepository.save(trainer);
  }

  @Override
  public void delete(Integer userId) {
    Trainer trainer =
      trainerRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Тренера"));

    trainer.setDeleted(true);
    trainerRepository.save(trainer);

  }
}
