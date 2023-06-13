# 스톱워치 앱

### 구현 기능
- 스톱워치 기능
    - 0.1초 마다 숫자 업데이트
    - 시작, 일시정지, 정지
    - 정지 전 다이얼로그 알람
- 시작 전 카운트 다운 추가
- 카운트 다운 3초전 알림음
- 랩타임 기록


### 사용 기술
- UI
    - ConstaintLayout
    - ProgressBar
- Android
    - [AlertDialog](https://developer.android.com/guide/topics/ui/dialogs?hl=ko)
    - [Thread](https://developer.android.com/guide/components/processes-and-threads?hl=ko#Threads)
    - runOnUiThread
    - [ToneGenerator](https://developer.android.com/reference/android/media/ToneGenerator)
    - addView