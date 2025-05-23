package umc8th.spring8th.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.repository.MemberMissionRepository.MemberMissionRepository;
import umc8th.spring8th.web.dto.MemberMission.MemberMissionResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    // 특정 회원의 진행 중인 미션 모아보기
    @Override
    public Page<MemberMissionResponseDTO.MemberMissionDTO> findChallengingMissions(Long memberId, Integer page, Integer size) {

        Page<MemberMissionResponseDTO.MemberMissionDTO> pageResult =
                memberMissionRepository.findChallengingMissions(memberId, PageRequest.of(page - 1, size));

        return pageResult;
    }

    // 특정 회원의 진행 완료한 미션 모아보기
    @Override
    public List<MemberMissionResponseDTO.MemberMissionDTO> findCompletedMissions(Long memberId) {

        List<MemberMissionResponseDTO.MemberMissionDTO> memberMissionDTOS = memberMissionRepository.findCompletedMissions(memberId);

        return memberMissionDTOS;
    }


}
