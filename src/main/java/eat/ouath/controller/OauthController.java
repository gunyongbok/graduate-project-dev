package eat.ouath.controller;

import eat.ouath.OauthMember;
import eat.ouath.OauthMemberRepository;
import eat.ouath.OauthServerType;
import eat.ouath.application.OauthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows
    @GetMapping("/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{oauthServerType}")
    ResponseEntity<Long> login(
            @PathVariable OauthServerType oauthServerType,
            @RequestParam("code") String code
    ) {
        Long login = oauthService.login(oauthServerType, code);
        return ResponseEntity.ok(login);
    }

    @PostMapping("/create")
    public ResponseEntity<OauthMember> saveMember(@RequestBody OauthMember member) {
        OauthMember savedMember = oauthService.save(member);
        return ResponseEntity.ok(savedMember);
    }

    @GetMapping("/members")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(oauthService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Optional<OauthMember>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(oauthService.findById(id));
    }
}

