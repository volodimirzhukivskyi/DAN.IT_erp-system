package com.danit.erp.facade.list.group_list;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.dictionary.Program;
import com.danit.erp.domain.dictionary.roles.Coordinator;
import com.danit.erp.domain.dictionary.roles.Mentor;
import com.danit.erp.domain.dictionary.roles.Trainer;
import com.danit.erp.domain.dictionary.status.GroupStatus;
import com.danit.erp.domain.list.group_list.GroupList;
import com.danit.erp.dto.list.group_list.GroupListRequest;
import com.danit.erp.facade.GeneralFacade;
import com.danit.erp.repository.list.GroupListRepository;
import com.danit.erp.repository.dictionary.GroupRepository;
import com.danit.erp.repository.dictionary.ProgramRepository;
import com.danit.erp.repository.dictionary.roles.CoordinatorRepository;
import com.danit.erp.repository.dictionary.roles.MentorRepository;
import com.danit.erp.repository.dictionary.roles.TrainerRepository;
import com.danit.erp.repository.dictionary.status.GroupStatusRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service

public class GroupListRequestMapper extends GeneralFacade<GroupList, GroupListRequest> {
  private final GroupListRepository groupListRepository;
  private final GroupRepository groupRepository;
  private final ProgramRepository programRepository;
  private final GroupStatusRepository groupStatusRepository;
  private final MentorRepository mentorRepository;
  private final TrainerRepository trainerRepository;
  private final CoordinatorRepository coordinatorRepository;


  public GroupListRequestMapper(
    GroupListRepository groupListRepository, GroupRepository groupRepository,
    ProgramRepository programRepository, GroupStatusRepository groupStatusRepository,
    MentorRepository mentorRepository, TrainerRepository trainerRepository,
    CoordinatorRepository coordinatorRepository) {
    super(GroupList.class, GroupListRequest.class);


    this.groupListRepository = groupListRepository;
    this.groupRepository = groupRepository;
    this.programRepository = programRepository;
    this.groupStatusRepository = groupStatusRepository;
    this.mentorRepository = mentorRepository;
    this.trainerRepository = trainerRepository;
    this.coordinatorRepository = coordinatorRepository;
  }

  @Override
  protected void decorateDto(GroupListRequest dto, GroupList entity) {

    super.decorateDto(dto, entity);
  }

  @Override
  protected void decorateEntity(GroupList entity, GroupListRequest dto) {
    Group byGroupName =
      groupRepository.findByGroupName(dto.getGroupName()).orElseThrow(() -> new Error("не має "
        + "групи з такою назвою"));

    Program program =
      programRepository.findByProgram(dto.getProgramName()).orElseThrow(() -> new Error("не має "
        + "такої програми "));
    GroupStatus status =
      groupStatusRepository.findByStatus(dto.getGroupStatus()).orElseThrow(() -> new Error("не має "
        + "такого статутусу "));
    Coordinator coordinator =
      coordinatorRepository.findByFullName(dto.getCoordinatorName()).orElseThrow(() -> new Error("не має "
        + "такого координатора "));

    Trainer trainer =
      trainerRepository.findByFullName(dto.getTrainerName()).orElseThrow(() -> new Error("не має "
        + "такого тренера "));

    Mentor mentor =
      mentorRepository.findByFullName(dto.getMentorName()).orElseThrow(() -> new Error("не має "
        + "такого mentor "));

    entity.setGroup(byGroupName);
    entity.setProgram(program);
    entity.setGroupStatus(status);
    entity.setMentor(mentor);
    entity.setCoordinator(coordinator);
    entity.setTrainer(trainer);
    entity.setGroupGraduation(LocalDateTime.parse(dto.getGroupGraduationDate()));

    super.decorateEntity(entity, dto);
  }
}