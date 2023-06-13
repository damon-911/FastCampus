# 단위변환기 앱

### 구현 기능
- cm 를 m 로 변환
- 값을 입력하면, 바로 변환된 값이 노출
    - 입력값은 자연수로 한정
- 단위를 반대로 변경
- 단위 변환 연산
    - cm → m (X 0.01)
    - m → cm (X 100)


### 사용 기술
- UI
    - [ConstraintLayout](https://developer.android.com/training/constraint-layout?hl=ko)
    - EditText
- Android
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
    - [ActivityLifecycle](https://developer.android.com/guide/components/activities/activity-lifecycle)
    - [onSaveInstanceState](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ko#save-simple,-lightweight-ui-state-using-onsaveinstancestate)