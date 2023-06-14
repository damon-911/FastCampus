# Android의 이해

### [1. Activity 생명주기](https://developer.android.com/guide/components/activities/activity-lifecycle)
- 앱의 **완성도**와 **안정성**을 높이기 위해 반드시 알아야 함
    - 다른 앱으로 전환 시, 비정상 종료 되는 문제
    - 사용자가 앱을 사용하지 않는데, 시스템 리소스가 소비되는 문제
    - 사용자가 앱을 나갔다가 돌아왔을 때, 진행상태가 저장되지 않는 문제
    - 화면이 가로 ↔ 세로 전환 될 때, 비정상 종료되거나, 진행상태가 저장되지 않는 문제
- 콜백
    - onCreate
        - 필수적으로 구현해야함
        - Activity 의 생명주기 중 한 번만 발생해야하는 로직을 실행
            - 멤버 변수 정의
            - UI 구성 (setContentView, xml 레이아웃 파일 정의)
        - saveInstanceState 매개 변수 수신 → Activity 이전 저장 상태가 포함된 Bundle 객체
    - onStart
        - Activity 가 사용자에게 표시
        - 앱은 Activity 를 포그라운드로 보내 상호작용할 수 있도록 준비
    - onResume
        - Activity 가 포그라운드에 표시되어, 사용자와 상호 작용 할 수 있는 상태
        - 앱에서 포커스가 떠날 때 까지 onResume 상태에 머무름
    - onPause
        - 사용자가 활동을 떠나는 첫 번째 신호
        - 매우 짧음
        - 활동이 포그라운드에 있지 않지만, 잠시 후 다시 시작할 작업을 일시 중지 하거나 조정
        - ex) 반투명 Activity 가 띄워져 포커스는 없지만 화면에 보이는 상태
        - 이 상태를 통해서, 실행중이지 않을 때 필요하지 않은 리소스를 해지할 수 있음
        - 이 상태에서, 데이터를 저장하거나, 네트워크 호출, DB 의 IO 작업을 하면 안됨
            - 매우 짧은 시간이라 메서드가 끝나기 전에 Activity 가 종료될 수 있음
    - onStop
        - Activity 가 사용자에게 더 이상 표시 되지 않는 상태
        - CPU 를 비교적 많이 소모하는 종료 작업을 실행해야함
            - DB 저장
        - Activity 가 중단되면, Android OS 에서 리소스 관리를 위해, 해당 Activity 가 포함된 프로세스를 소멸시킬 수 있음
    - onDestroy
        - Activity 가 완전히 종료되기 전에 실행
        - 호출되는 케이스
            - finish 호출 되어 Activity 가 종료될 때
            - configurationChange (ex 기기 회전, 멀티 윈도우) 로 인해, 시스템이 Activity 를 일시적으로 소멸 시킬 때
        
![Untitled](https://user-images.githubusercontent.com/24618293/204137047-50113f27-3a2d-41c4-9eb9-fd6a8809ecdc.png)


### [2. View 그리기](https://developer.android.com/guide/topics/ui/how-android-draws)

![Untitled](https://user-images.githubusercontent.com/24618293/204136897-70a64a1d-54e5-4fd1-bef5-251492733a80.png)

- 전위순회 방식을 쓰기 때문에, 부모 뷰부터 자식 뷰 순서로 그려지게 됨
- measure
    - 뷰의 크기를 계산
    - 모든 뷰는 각각 자신의 width, height 를 계산
    - measure 과정에서, 부모 - 자식 뷰간의 크기 정보 전달을 위해 2가지 클래스 사용
        - ViewGroup.LayoutParams : 자식 뷰가 부모 뷰에게 자신이 어떻게 측정되고 위치를 정할지 요청 할 때 사용, (how big)
            - DP, PX.. : 자식뷰가 원하는 사이즈
            - MATCH_PARENT : 부모 뷰 사이즈와 똑같이 자식뷰 사이즈 지정
            - WRAP_CONTENT : 부모 뷰 안에서, content 를 표현할 수 있는 fit 한 사이즈 지정
        - ViewGroup.MeasureSpecs : 부모 뷰가 자식 뷰에게 요구사항을 전달할 때 사용
            - UNSPECIFIED : 부모 뷰는 자식 뷰가 원하는 사이즈로 결정
            - EXACTLY : 부모 뷰가 자식 뷰의 사이즈를 정확히 지정할 때
            - AT_MOST : 부모 뷰가 자식 뷰의 최대 사이즈를 지정할 때
- layout
    - 뷰의 크기와 위치를 할당
    - 부모기준의 상대적 위치 (left, top, right, bottom) 을 계산
- draw
    - 뷰를 그리는 단계
        - Canvas : 뷰의 모양을 그리는 객체
        - Paint : 뷰의 색상을 칠하는 객체
    - measure, layout 에서 측정한 크기와, 계산한 위치에 뷰를 그림
    - 이 콜백은 언제든 다시 호출 될 수 있음
        - scroll 이나 swipe 를 하게 되면 뷰는 onDraw 다시 호출
        - 객체 할당과 같이 리소스가 많이 소모되는 로직은 추가하지 말 것
- ViewUpdate : 런타임에 뷰를 다시 그리게 하는 함수
    - invalidate : view 에 변화가 생겨서 다시 그려야 할 때
        - color 변화 등
    - requestLayout : view 를 처음부터 그려야 할 때
        - 크기가 변화해서 measure 부터 다시 해야할 때