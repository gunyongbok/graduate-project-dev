package eat.ouath;

import java.util.Locale;

//Oauth2.0 인증을 제공하는 서버의 종류를 명시할 Enum

public enum OauthServerType {

    KAKAO,;

    public static OauthServerType fromName(String type) {
        return OauthServerType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }

}
