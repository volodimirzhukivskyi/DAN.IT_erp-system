package com.danit.erp.controller.dictionary;

import com.danit.erp.dto.sessions.SessionsRequest;
import com.danit.erp.dto.sessions.SessionsResponse;
import com.danit.erp.service.dictionary.SessionsService;
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
@RequestMapping("${api.version}/sessions")
public class SessionsController {
  private final SessionsService sessionsService;

  @GetMapping("/")
  public List<SessionsResponse> getAllTweets() {
    return sessionsService.findAll();
  }

  @GetMapping("/{id}")
  public SessionsResponse getById(@PathVariable("id") String userId) throws Exception {
    return sessionsService.findById(Long.parseLong(userId));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") String userId) throws Exception {
    sessionsService.delete(Long.parseLong(userId));
  }

  @PutMapping("/update")
  public void update(@RequestBody SessionsRequest sessionsRequest) {
    sessionsService.update(sessionsRequest);
  }



  @PostMapping("/create")
  public SessionsResponse create(@RequestBody SessionsRequest sessionsRequest) {
    return sessionsService.create(sessionsRequest);
  }
}
