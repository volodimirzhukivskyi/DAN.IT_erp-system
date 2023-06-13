package com.danit.erp.controller.dictionary.status;

import com.danit.erp.domain.dictionary.status.SessionsStatus;
import com.danit.erp.service.dictionary.status.SessionsStatusService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("${api.version}/sessions_status")
public class SessionsStatusController {
  private final SessionsStatusService sessionsStatusService;

  @GetMapping("/")
  public List<SessionsStatus> getAll() {
    return sessionsStatusService.findAll();
  }

  @GetMapping("/{id}")
  public SessionsStatus getById(@PathVariable("id") String userId) throws Exception {
    return sessionsStatusService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    sessionsStatusService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody SessionsStatus personalCard) {
    sessionsStatusService.update(personalCard);
  }



  @PostMapping("/create")
  public SessionsStatus create(@RequestBody SessionsStatus personalCard) {
    return sessionsStatusService.create(personalCard);
  }
}
