package eat.ouath.application;

import eat.ouath.OauthMember;
import eat.ouath.OauthMemberRepository;
import eat.ouath.OauthServerType;
import eat.ouath.domain.authcode.AuthCodeRequestUrlProviderComposite;
import eat.ouath.domain.client.OauthMemberClientComposite;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }


    public Long login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        return saved.id();
    }

    public OauthMember save(OauthMember member) {
        return oauthMemberRepository.save(member);
    }

    public List<OauthMember> findAll() {
        return oauthMemberRepository.findAll();
    }


    public Optional<OauthMember> findById(Long id) {
        return oauthMemberRepository.findById(id);
    }
}
