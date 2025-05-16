package umc8th.spring8th.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberValidationServiceImpl implements MemberValidationService {

    private MemberRepository memberRepository;

    @Override
    public boolean isMemberExist(Long memberId) {

        return memberRepository.existsById(memberId);
    }
}
