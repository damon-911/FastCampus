# 나만의 액자 앱

### 구현 기능
- 권한 처리
- 갤러리 이미지 가져오기
- 여러가지 타입의 리스트 구현해보기


### 사용 기술
- UI
    - RecyclerView, [ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)
        - Multiple item type
    - [ViewPager2](https://developer.android.com/training/animation/screen-slide-2?hl=ko)
    - [Toolbar](https://developer.android.com/guide/fragments/appbar?hl=ko)
    - selector, drawable
- Kotlin
    - sealed class, data class
- Android
    - [Permission](https://developer.android.com/training/permissions/requesting)
    - [Storage Access Framework](https://developer.android.com/guide/topics/providers/document-provider)
    - registerForActivityResult