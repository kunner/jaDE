# jaDE 인사/조직 API 메뉴얼
## 안내
- 유효한 응답데이터가 나오는 것을 확인한 자바 네이티브 API 소스코드는 {저장소루트}/Context.java 입니다.
- https://github.com/maketext/jaDE/blob/main/Context.java

## 준비사항
- 앞서 질문드렸던 이슈 대로 기본 자바 cacerts 클라이언트 SSL 인증서 저장소에 없는 클라이언트 인증서를 요구하는 HTTPS 통신입니다.
- cacerts 파일을 업데이트하셔야 합니다.

## URL : https://{베이스URL}/restful/getToken
### 요청 페이로드

없음

### 응답 페이로드 예시
```
	{"Msg":[{"Error":"", "Success":""}], "Esc":[], "Token":"{엑세스토큰}}
``` 
## URL: https://{베이스URL}/restful/

### 요청 페이로드
- p3 파라미터 값이 발급받은 토큰 값이며 URLEncode 처리해야 합니다.
- "jsonData=" 란 캐릭터 값으로 시작하는 페이로드여야 합니다.
```
	jsonData={"p1":"{p1토큰}", "p2":"{p2토큰}", "p3":"{엑세스토큰}", "PARAM":{"YMD":"2021XXX", "C_CD":"2021XXX", "CP_CD":"2011XXX"}}
``` 
- 파라미터 중 CP_CD 값은 2011XXX, 2011XX1  모두 사용하실 수 있습니다.
- YMD 파라미터 값은 퇴사자를 구해야 할 경우 2021년 10월과 그 이전 날짜에 대해 유효한 값이 DB에 저장되어 있습니다.
-
### 응답 페이로드
- Success 벨류 값이 true 여야 합니다.
```
            "MSG": [{
                "Error":"",
                "Success":"true"
            }
        ],
        "Etc": [],
        "Token":"",
        "Data": [
            ...
        ]
```
