# 음악재생 앱

### 구현 기능
- 백그라운드에서 기능 실행
- 음원 재생
- 디바이스 이벤트 캐치 (네트워크 상태 변경, 전원 연결, 배터리 사용량 체크 등)


### 사용 기술
- Android
    - [MediaPlayer](https://developer.android.com/guide/topics/media/mediaplayer?hl=ko)
    - [Service](https://developer.android.com/guide/components/services?hl=ko)
    - [Notification](https://developer.android.com/guide/topics/ui/notifiers/notifications?hl=ko)
        - PendingIntent
        - Intent flag
    - [BroadcastReceiver](https://developer.android.com/guide/components/broadcasts)