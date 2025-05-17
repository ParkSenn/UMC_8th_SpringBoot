package umc8th.spring8th.converter;

import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.Mission;
import umc8th.spring8th.domain.enums.MissionStatus;
import umc8th.spring8th.domain.mapping.MemberMission;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.NewChallengingMemberMissionResultDTO toNewChallengingMemberMissionResultDTO(MemberMission memberMission) {

        return MemberMissionResponseDTO.NewChallengingMemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toChallengingMemberMission(Member member, Mission mission) {

        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
