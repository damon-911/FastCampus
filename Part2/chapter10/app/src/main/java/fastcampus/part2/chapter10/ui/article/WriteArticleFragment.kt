package fastcampus.part2.chapter10.ui.article

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import fastcampus.part2.chapter10.R
import fastcampus.part2.chapter10.data.ArticleModel
import fastcampus.part2.chapter10.databinding.FragmentWriteArticleBinding
import java.util.*

class WriteArticleFragment : Fragment(R.layout.fragment_write_article) {

    private lateinit var binding: FragmentWriteArticleBinding
    private lateinit var viewModel: WriteArticleViewModel

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.updateSelectedUri(uri)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWriteArticleBinding.bind(view)

        setupViewModel()

        if (viewModel.selectedUri.value == null) {
            startPicker()
        }

        setupPhotoImageView()
        setupDeleteButton()
        setupBackButton()
        setupSubmitButton(view)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get()

        viewModel.selectedUri.observe(viewLifecycleOwner) {
            binding.ivPhoto.setImageURI(it)

            if (it != null) {
                binding.btnPlus.isVisible = false
                binding.btnDelete.isVisible = true
            } else {
                binding.btnPlus.isVisible = true
                binding.btnDelete.isVisible = false
            }
        }
    }

    private fun setupPhotoImageView() {
        binding.ivPhoto.setOnClickListener {
            if (viewModel.selectedUri.value == null) {
                startPicker()
            }
        }
    }

    private fun setupDeleteButton() {
        binding.btnDelete.setOnClickListener {
            viewModel.updateSelectedUri(null)
        }
    }

    private fun setupBackButton() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(WriteArticleFragmentDirections.actionBack())
        }
    }

    private fun setupSubmitButton(view: View) {
        binding.btnSubmit.setOnClickListener {
            showProgress()

            if (viewModel.selectedUri.value != null) {
                val photoUri = viewModel.selectedUri.value ?: return@setOnClickListener
                uploadImage(
                    uri = photoUri,
                    successHandler = {
                        uploadArticle(it, binding.etDescription.text.toString())
                    },
                    errorHandler = {
                        Snackbar.make(view, "이미지 업로드에 실패했습니다.", Snackbar.LENGTH_SHORT).show()
                        hideProgress()
                    })
            } else {
                Snackbar.make(view, "이미지가 선택되지 않았습니다.", Snackbar.LENGTH_SHORT).show()
                hideProgress()
            }
        }
    }

    private fun startPicker() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun showProgress() {
        binding.progressBarLayout.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBarLayout.isVisible = false
    }

    private fun uploadImage(
        uri: Uri,
        successHandler: (String) -> Unit,
        errorHandler: (Throwable?) -> Unit
    ) {
        val fileName = "${UUID.randomUUID()}.png"
        Firebase.storage.reference.child("articles/photo").child(fileName)
            .putFile(uri)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Firebase.storage.reference.child("articles/photo/$fileName")
                        .downloadUrl
                        .addOnSuccessListener {
                            successHandler(it.toString())
                        }.addOnFailureListener {
                            errorHandler(it)
                        }
                } else {
                    errorHandler(task.exception)
                }
            }
    }

    private fun uploadArticle(photoUrl: String, description: String) {
        val articleId = UUID.randomUUID().toString()
        val articleModel = ArticleModel(
            articleId = articleId,
            createdAt = System.currentTimeMillis(),
            description = description,
            imageUrl = photoUrl
        )

        Firebase.firestore.collection("articles").document(articleId)
            .set(articleModel)
            .addOnSuccessListener {
                findNavController().navigate(WriteArticleFragmentDirections.actionWriteArticleFragmentToHomeFragment())
                hideProgress()
            }.addOnFailureListener {
                it.printStackTrace()
                view?.let { view ->
                    Snackbar.make(view, "글 작성에 실패했습니다.", Snackbar.LENGTH_SHORT).show()
                }
                hideProgress()
            }
    }
}