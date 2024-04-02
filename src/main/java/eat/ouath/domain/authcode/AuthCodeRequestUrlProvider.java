package eat.ouath.domain.authcode;

import eat.ouath.OauthServerType;

//AuthCode를 발급할 URL을 제공하는 기능을 제공

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}
