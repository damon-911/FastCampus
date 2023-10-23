package fastcampus.part3.chapter4.mvp

interface MvpContractor {

    interface View {
        fun showImage(imageUrl : String, color: String)

        fun showImageCountText(count : Int)
    }

    interface Presenter {
        fun attachView(view: View)

        fun detachView()

        fun loadRandomImage()
    }
}