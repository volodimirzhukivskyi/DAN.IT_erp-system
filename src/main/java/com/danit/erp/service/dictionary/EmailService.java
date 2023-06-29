package com.danit.erp.service.dictionary;

import com.danit.erp.domain.card.personal_card.PersonalCard;
import com.danit.erp.domain.dictionary.Email;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.card.PersonalCardRepository;
import com.danit.erp.repository.dictionary.EmailRepository;
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
public class EmailService implements BaseService<Email,Long> {
  private final EmailRepository emailListRepository;
  private final PersonalCardRepository personalCardRepository;

  @Override
  public List<Email> findAll() {
    return emailListRepository.findAll();
  }


  @Override
  public Page<Email> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return emailListRepository.findAll(pageable);
  }

  @Override
  public Email findById(Long userId) {
    return emailListRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Електронна пошта"));
  }


  @Override
  public Email create(Email obj) {
    PersonalCard findPersonalCard =
      personalCardRepository.findByIdCode(obj.getIdCode()).orElse(null);
    Email findEmail = emailListRepository.findByIdCode(obj.getIdCode()).orElse(null);
    if (findEmail == null) {
      Email email = Email.builder().email(obj.getEmail()).idCode(obj.getIdCode()).build();
      emailListRepository.save(email);
      if (findPersonalCard != null && findPersonalCard.getEmail() == null) {
        findPersonalCard.setEmail(email);
      }
      return email;
    } else {
      throw new Error("email з таким id уже є!");

    }
  }

  @Override
  public void update(Email obj) {
    Email findEmail = emailListRepository.findById(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Електронна пошта"));

    Email email =
      Email.builder().id(findEmail.getId()).email(obj.getEmail()).idCode(obj.getIdCode()).build();
    emailListRepository.save(email);
  }

  @Override
  public void delete(Long userId) {
    Email email = emailListRepository.findById(userId)
      .orElseThrow(() -> new CouldNotFindException("Електронна пошта"));

    emailListRepository.delete(email);
  }
}
