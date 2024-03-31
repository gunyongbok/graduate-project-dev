

1. 사용자가 프론트엔드를 통해 http://localhost:8080/oauth/kakao로 접속하면 카카오톡 로그인 화면으로 Redirect 시키는 과정을 구현한다. 사용자가 카카오톡 로그인 & 정보 제공 동의를 진행한 이후 카카오톡 인증 서버에서 프론트엔드로 Auth Code를 포함하여 Redirect 시킨다. 프론트엔드는 Redirect 되는 순간 Auth Code를 꺼내서 http://localhost:8080/oauth/login/kakao?code={받은 Auth Code}로 POST 요청을 보낸다.

2. /oauth/login/kakao로 auth code와 함께 POST 요청이 도착하면 백엔드는 받은 auth code와 함께 카카오 인증 서버에 AccessToken을 요청하여 발급받는다.

3. 받아온 AccessToken을 가지고 카카오에 등록된 회원 정보(닉네임, 프로필 사진)를 조회한다.

4. 받아온 정보를 통해 로그인을 진행한다. 이때 회원가입 되어있지 않다면 회원가입도 함께 진행한다.

5. /oauth/login/kakao로 요청이 들어오면 2~4의 과정을 통해 로그인을 진행한 뒤, AccessToken(얘는 백엔드가 인증을 위해 발급해주는애)을 생성하고, 이를 메세지 본문에 담는다.

6. 이후 프론트엔드는 받은 AccessToken을 쿠키에 저장하고, 이후 요청 시마다 쿠키에 저장된 AccessToken을 헤더에 담아 요청을 보낸다.