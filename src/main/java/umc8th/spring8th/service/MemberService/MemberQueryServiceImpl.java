package umc8th.spring8th.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.apiPayload.exception.handler.MemberHandler;
import umc8th.spring8th.config.security.jwt.JwtTokenProvider;
import umc8th.spring8th.converter.MemberConverter;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    private final JwtTokenProvider jwtTokenProvider;

    // 마이페이지
    @Override
    public MemberResponseDTO.MemberMyPageInfoDTO findMyPageInfo(Long memberId) {

        return memberRepository.findMyPageInfo(memberId);
    }

    @Override
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request) {

        // 방금 JwtTokenProvider에 정의한 extractAuthentication 메소드를 통해 토큰을 파싱하고, Authentication 객체를 추출
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);

        // 추출해낸 인증 객체 (Authentication)을 통해 사용자 정보를 가져오고, MemberRepository로부터 사용자 정보 조회
        String email = authentication.getName();
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        // 정보 조회에 성공하면, MemberInfoDTO로 변환하여 반환
        return MemberConverter.toMemberInfoDTO(member);
    }
}
