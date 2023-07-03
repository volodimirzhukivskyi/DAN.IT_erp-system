package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.exception.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.ProgramRepository;
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
public class ProgramService implements BaseService<Program,Short> {
  private final ProgramRepository programRepository;

  @Override
  public List<Program> findAll() {
    return programRepository.findByDeletedFalse();
  }

  @Override
  public Page<Program> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return programRepository.findByDeletedFalse(pageable);
  }

  @Override
  public Program findById(Short userId) {
    return programRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("The program"));
  }


  @Override
  public Program create(Program obj) {
    Program program =
      Program.builder().program(obj.getProgram()).programHours(obj.getProgramHours()).build();
    return programRepository.save(program);
  }

  @Override
  public void update(Program obj) {
    Program findProgram = programRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("The program"));

    Program program = Program.builder().id(findProgram.getId()).program(obj.getProgram())
      .programHours(obj.getProgramHours()).build();
    programRepository.save(program);
  }

  @Override
  public void delete(Short userId) {
    Program program =
      programRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("The program"));

    program.setDeleted(true);
    programRepository.save(program);

  }
}
