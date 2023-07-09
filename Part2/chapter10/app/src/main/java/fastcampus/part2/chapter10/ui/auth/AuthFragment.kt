package fastcampus.part2.chapter10.ui.auth

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fastcampus.part2.chapter10.R
import fastcampus.part2.chapter10.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private lateinit var binding: FragmentAuthBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAuthBinding.bind(view)

        setupSignUpButton()
        setupSignInButton()
    }

    override fun onStart() {
        super.onStart()

        if (Firebase.auth.currentUser == null) {
            initViewToSignOutState()
        } else {
            initViewToSignInState()
        }
    }

    private fun setupSignUpButton() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(binding.root, "이메일 또는 패스워드를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Snackbar.make(binding.root, "회원가입에 성공했습니다.", Snackbar.LENGTH_SHORT).show()
                        initViewToSignInState()
                    } else {
                        Snackbar.make(binding.root, "회원가입에 실패했습니다.", Snackbar.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    it.printStackTrace()
                }
        }
    }

    private fun setupSignInButton() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (Firebase.auth.currentUser == null) {
                // 로그인
                if (email.isEmpty() || password.isEmpty()) {
                    Snackbar.make(binding.root, "이메일 또는 패스워드를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                Firebase.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            initViewToSignInState()
                        } else {
                            Snackbar.make(binding.root, "로그인에 실패했습니다. 이메일 또는 패스워드를 확인해주세요.", Snackbar.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Firebase.auth.signOut()
                initViewToSignOutState()
            }
        }
    }

    private fun initViewToSignInState() {
        binding.etEmail.setText(Firebase.auth.currentUser?.email)
        binding.etEmail.isEnabled = false
        binding.etPassword.isVisible = false
        binding.btnSignIn.text = getString(R.string.signOut)
        binding.btnSignUp.isEnabled = false
    }

    private fun initViewToSignOutState() {
        binding.etEmail.text.clear()
        binding.etEmail.isEnabled = true
        binding.etPassword.text.clear()
        binding.etPassword.isVisible = true
        binding.btnSignIn.text = getString(R.string.signIn)
        binding.btnSignUp.isEnabled = true
    }
}