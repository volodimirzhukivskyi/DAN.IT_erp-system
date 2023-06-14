package com.danit.erp.domain.list.group_list;

import com.danit.erp.domain.BaseEntity;
import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.dictionary.roles.Coordinator;
import com.danit.erp.domain.dictionary.roles.Mentor;
import com.danit.erp.domain.dictionary.roles.Trainer;
import com.danit.erp.domain.dictionary.status.GroupStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "groups_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GroupList extends BaseEntity {
  private LocalDateTime groupGraduation;
  @OneToOne(targetEntity = Group.class)
  private Group group;
  @ManyToOne(targetEntity = Program.class)
  private Program program;
  @ManyToOne(targetEntity = GroupStatus.class)
  private GroupStatus groupStatus;

  @ManyToOne(targetEntity = Trainer.class)
  private Trainer trainer;
  @ManyToOne(targetEntity = Mentor.class)
  private Mentor mentor;
  @ManyToOne(targetEntity = Coordinator.class)
  private Coordinator coordinator;


}
