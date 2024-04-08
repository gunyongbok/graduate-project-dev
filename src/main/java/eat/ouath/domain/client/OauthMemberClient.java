package eat.ouath.domain.client;

import eat.ouath.OauthMember;
import eat.ouath.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}

