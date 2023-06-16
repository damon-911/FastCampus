# 웹툰 앱

### 구현 기능
- ViewPager2 를 이용해 N개의 Fragment 를 구성함
- 각 Fragment 는 WebView 를 전체화면으로 구성함
- TabLayout 과 ViewPager2 를 연동하고, Tab 이름을 동적으로 바꿀 수 있음
- 웹툰의 마지막 조회 시점을 로컬에 저장하고, 앱 실행 시 불러옴


### 사용 기술
- [WebView](https://developer.android.com/guide/webapps/webview)
- [ViewPager2](https://developer.android.com/training/animation/screen-slide-2) 
    - ViewPager2 를 Fragment 와 함께 쓰기
    - TabLayoutMediator 를 통해 TabLayout 과 함께 쓰기
- [Fragment](https://developer.android.com/guide/components/fragments)
- SharedPreference
- Dialog