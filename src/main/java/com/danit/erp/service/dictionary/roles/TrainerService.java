package com.danit.erp.service.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Trainer;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.roles.TrainerRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TrainerService implements BaseService<Trainer> {
  private final TrainerRepository trainerRepository;

  @Override
  public List<Trainer> findAll() {
    return trainerRepository.findByDeletedFalse();
  }

  @Override
  public List<Trainer> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Trainer findById(Long userId) {
    return trainerRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new CouldNotFindException("Тренера"));
  }


  @Override
  public Trainer create(Trainer obj) {
    Trainer trainer = Trainer.builder().fullName(obj.getFullName()).build();
    return trainerRepository.save(trainer);
  }

  @Override
  public void update(Trainer obj) {
    Trainer findTrainer =
      trainerRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new CouldNotFindException("Тренера"));

    Trainer trainer =
      Trainer.builder().id(findTrainer.getId()).fullName(obj.getFullName()).build();
    trainerRepository.save(trainer);
  }

  @Override
  public void delete(Long userId) {
    Trainer trainer =
      trainerRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Тренера"));

    trainer.setDeleted(true);
    trainerRepository.save(trainer);

  }
}
