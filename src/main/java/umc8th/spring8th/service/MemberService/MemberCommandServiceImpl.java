package umc8th.spring8th.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring8th.apiPayload.code.status.ErrorStatus;
import umc8th.spring8th.apiPayload.exception.handler.MemberHandler;
import umc8th.spring8th.config.security.jwt.JwtTokenProvider;
import umc8th.spring8th.converter.MemberConverter;
import umc8th.spring8th.converter.MemberPreferConverter;
import umc8th.spring8th.domain.FoodCategory;
import umc8th.spring8th.domain.Member;
import umc8th.spring8th.domain.RefreshToken;
import umc8th.spring8th.domain.mapping.MemberPrefer;
import umc8th.spring8th.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc8th.spring8th.repository.MemberRepository.MemberRepository;
import umc8th.spring8th.repository.RefreshTokenRepository.RefreshTokenRepository;
import umc8th.spring8th.web.dto.Member.MemberRequestDTO;
import umc8th.spring8th.web.dto.Member.MemberResponseDTO;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements  MemberCommandService{

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);

        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

//        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
//                .map(category -> {
//                    // @ExistCategories로 검증하고 있기 때문에 없애고 밑에 코드로 대체
//                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
//                }).toList();

        // @ExistCategories로 검증하고 있기 때문에 이 코드로 대체
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(foodCategoryRepository::getReferenceById)
                .toList();

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        // Redis에 RefreshToken 저장
        RefreshToken refreshTokenEntity = new RefreshToken(refreshToken, member.getId());
        refreshTokenRepository.save(refreshTokenEntity);

        return MemberConverter.toLoginResultDTO(
                member.getId(),
                accessToken,
                refreshToken
        );
    }
}
