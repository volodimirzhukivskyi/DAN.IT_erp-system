package com.danit.erp.domain.token;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.card.personal_card.PersonalCard;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token extends BaseEntity<Integer> {
  private String token;
  @Enumerated(EnumType.STRING)
  private TokenType tokenType;
  private boolean expired;
  private boolean revoked;
  @ManyToOne
  @JoinColumn(name = "personal_card_id")
  private PersonalCard personalCard;
}
