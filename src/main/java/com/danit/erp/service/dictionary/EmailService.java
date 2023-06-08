package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Email;
import com.danit.erp.domain.personal_card.PersonalCard;
import com.danit.erp.repository.PersonalCardRepository;
import com.danit.erp.repository.dictionary.EmailRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService implements BaseService<Email> {
  private final EmailRepository emailListRepository;
  private final PersonalCardRepository personalCardRepository;

  @Override
  public List<Email> findAll() {
    return emailListRepository.findAll();
  }

  @Override
  public List<Email> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Email findById(Long userId) {
    return emailListRepository.findById(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Email create(Email obj) {
//TODO  Більш красивіше прописати метод
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
    Email findEmail = emailListRepository.findById(obj.getId()).orElseThrow(() -> new Error());

    Email email =
      Email.builder().id(findEmail.getId()).email(obj.getEmail()).idCode(obj.getIdCode()).build();
    emailListRepository.save(email);
  }

  @Override
  public void delete(Long userId) {
    Email email = emailListRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    emailListRepository.delete(email);
  }
}
