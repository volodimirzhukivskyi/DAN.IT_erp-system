package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.ProgramRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProgramService implements BaseService<Program> {
  private final ProgramRepository programRepository;

  @Override
  public List<Program> findAll() {
    return programRepository.findByDeletedFalse();
  }

  @Override
  public List<Program> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Program findById(Long userId) {
    return programRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new CouldNotFindException("Прогами"));
  }


  @Override
  public Program create(Program obj) {
    Program program = Program.builder().program(obj.getProgram()).programHours(obj.getProgramHours()).build();
    return programRepository.save(program);
  }

  @Override
  public void update(Program obj) {
    Program findProgram =
      programRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new CouldNotFindException("Прогами"));

    Program program =
      Program.builder().id(findProgram.getId()).program(obj.getProgram()).programHours(obj.getProgramHours()).build();
    programRepository.save(program);
  }

  @Override
  public void delete(Long userId) {
    Program program =
      programRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Прогами"));

    program.setDeleted(true);
    programRepository.save(program);

  }
}
