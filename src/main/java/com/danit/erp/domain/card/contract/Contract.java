package com.danit.erp.domain.card.contract;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.status.ContractStatus;
import com.danit.erp.domain.dictionary.roles.Coordinator;
import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.dictionary.LegalEntity;
import com.danit.erp.domain.dictionary.roles.Manager;
import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.card.personal_card.PersonalCard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "contract_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Contract extends BaseEntity {
  @Column(name = "contract_no",unique = true)
  private Long contractNo;
  private LocalDateTime contractDate;
  private Double contractValue;
  private String docLink;

  @ManyToOne(targetEntity = LegalEntity.class)
  private LegalEntity legalEntity;
  @ManyToOne(targetEntity = PersonalCard.class)
  @JoinColumn(name = "id_code")
  private PersonalCard personalCard;
  @ManyToOne(targetEntity = Manager.class, cascade = CascadeType.PERSIST)
  private Manager manager;
  @ManyToOne(targetEntity = Coordinator.class, cascade = CascadeType.PERSIST)
  private Coordinator coordinator;
  @ManyToOne(targetEntity = ContractStatus.class, cascade = CascadeType.PERSIST)
  private ContractStatus contractStatus;
  @ManyToOne(targetEntity = Program.class, cascade = CascadeType.PERSIST)
  private Program program;
  @ManyToOne(targetEntity = Group.class, cascade = CascadeType.PERSIST)
  private Group group;
  private String paymentMethod;

}
