# 사다리 게임

## 진행 방법

* 사다리 게임 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/nextstep-step/nextstep-docs/tree/master/codereview)

## 2단계

### 요구사항

- 기능
  - 사다리 게임에 참여하는 사람에 이름을 최대 5 글자까지 부여할 수 있다. 사다리를 출력할 때 사람 이름도 같이 출력한다.
  - 사람 이름은 쉼표(,)를 기준으로 구분한다.
  - 사람 이름을 5자 기준으로 출력하기 때문에 사다리 폭도 넓어져야 한다.
  - 사다리 타기가 정상적으로 동작하려면 라인이 겹치지 않도록 해야 한다.
    - |-----|-----| 모양과 같이 가로 라인이 겹치는 경우 어느 방향으로 이동할지 결정할 수 없다.
- 프로그래밍
  - 자바 8의 스트림과 람다를 적용해 프로그래밍한다.
  - 규칙 6: 모든 엔티티를 작게 유지한다.

### 구현할 사항

- 입력
  - [x] 참가자 이름을 입력 받는다.
    - [x] 쉼표 기준으로 잘라 리스트로 반환한다.
  - [x] 사다리 높이를 입력 받는다.
- 출력
  - [x] 사다리를 출력한다.
    - [x] 참가자 이름 출력 다섯 글자보다 작은 길이의 이름은 뒤에 공백을 추가한다.
    - [x] 마지막 글자를 기준으로 사다리 기둥('|') 을 출력한다.
- 기능
  - 참가자
    - [x] 참가자 이름은 최대 다섯 글자여야 한다.
  - 사다리
    - [x] 사다리 높이는 1 이상이어야 한다.
    - [x] 사다리 가로 라인은 겹치지 않게 무작위로 생성한다.
  - 라인
    - [x] 사다리의 한 라인은 여러 개의 지점으로 구성된다.
    - [x] 지점의 개수는 참가자의 수와 같아야 한다.
  - 지점 (Point)
    - [x] 한 지점은 다른 지점과 이어질 수도, 그렇지 않을 수도 있다.
    - [x] 이미 이어진 지점은 다른 지점과 다시 이어질 수 없다.

## 3단계

### 요구사항

- 기능
  - 사다리 실행 결과를 출력해야 한다.
  - 개인별 이름을 입력하면 개인별 결과를 출력하고, "all"을 입력하면 전체 참여자의 실행 결과를 출력한다.
- 프로그래밍
  - 자바 8의 스트림과 람다를 적용해 프로그래밍한다. 
  - 규칙 6: 모든 엔티티를 작게 유지한다. 
  - **규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.**
  
### 구현할 사항

- 입력
  - [ ] 사다리 게임의 실행 결과를 입력 받는다.
    - [ ] 출력 형식을 위해 네 자릿수까지만 입력받는다. 
  - [ ] 결과를 보고 싶은 사람의 이름을 입력 받는다.
- 출력
  - [ ] 사다리 게임 결과를 사다리 아래에 출력한다.
  - [ ] 결과를 보고 싶은 참가자 한 명을 입력한 경우, 결과만 출력한다.
  - [ ] 모든 참가자의 결과를 보고 싶을 때 이름과 결과를 한 줄에 하나 씩 출력한다.
- 구현
  - [ ] 참가자의 결과를 알아내는 메소드를 구현한다.
  
#### 명확하지 않는 사항들

- 결과 출력은 딱 한 번만 해야하는가?
  - 편의성을 위해서, 모든 참가자의 결과가 최소 한 번 씩 노출되면 종료하도록 한다.
  - 이 사항을 추가적으로 화면에 출력한다.
  
### 도메인 설계

- 실행 결과
  - 실행 결과는 어디에 두어야 하는가?
    - `LadderGame` 또는 `Ladder` 가 적절
      - 그러나 객체지향 생활체조 규칙 6에 의해 `LadderGame` 에 둘 수는 없음
      - `Ladder` 가 실행 결과를 다루도록 결정.
  - 실행 결과는 출력 형식 때문에 최대 네 자릿수까지만 허용
    - Validation 로직이 필요하므로, 별도의 클래스로 구현
    - `Rewards` 라는 이름 사용
- 참가자의 게임 결과
  - 결과를 미리 계산 or 필요할 때 계산
    - 둘 다 가능하지만, 필요할 때 계산하고 그 값을 캐싱
  - 결과는 어떻게 알아낼 것인가?
    - `Ladder` 의 입장에서는 결과를 보고 싶은 사다리 위치만이 관심사
      - `LadderGame` 이 `Player` 의 위치와 `Ladder` 의 결과 값을 매핑하는 역할을 수행
      - `Ladder` 는 위치 정보를 받으면 최종 결과를 반환하도록 구현 (`Reward` 반환)
    - 참가자 위치도 알아내야 하므로, `Players` 일급 컬렉션에서 반환하도록 구현
- 입력 받은 참가자로 결과를 알아내기 
  - 없는 사용자가 입력되면 예외 처리가 필요함
  - `all` 이 입력되면 특수 처리가 필요
  - 이건 `LadderGame` 이 처리하도록 구현
  

