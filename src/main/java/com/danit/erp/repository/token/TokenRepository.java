package com.danit.erp.repository.token;

import com.danit.erp.domain.token.Token;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {
  @Query(value = """
      select t from Token t inner join PersonalCard p
      on t.personalCard.id = p.id
      where p.id = :id and (t.expired = false or t.revoked = false)
      """)
  List<Token>findAllValidTokensByPersonalCard(Long id);
  Optional<Token> findByToken(String token);


}
