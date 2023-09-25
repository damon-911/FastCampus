package fastcampus.part3.chapter2

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import fastcampus.part3.chapter2.databinding.ActivityIdentityInputBinding
import fastcampus.part3.chapter2.util.ViewUtil.closeKeyboard
import fastcampus.part3.chapter2.util.ViewUtil.setOnEditorActionListener
import fastcampus.part3.chapter2.util.ViewUtil.showKeyboard
import fastcampus.part3.chapter2.util.ViewUtil.showKeyboardDelay

class IdentityInputActivity : AppCompatActivity() {

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\$"
        private const val REGEX_BIRTHDAY = "^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1])"
        private const val REGEX_PHONE = "^01([016789])([0-9]{3,4})([0-9]{4})\$"
    }

    private lateinit var binding: ActivityIdentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        binding.nameEdit.showKeyboardDelay()
    }

    private fun initView() {
        binding.view = this
        with(binding) {
            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if (validName()) {
                    nameLayout.error = null
                    if (phoneLayout.isVisible) {
                        confirmButton.isVisible = true
                    } else {
                        birthdayLayout.isVisible = true
                        birthdayEdit.showKeyboard()
                    }
                } else {
                    confirmButton.isVisible = false
                    nameLayout.error = "1자 이상의 한글을 입력해주세요."
                }
            }

            birthdayEdit.doAfterTextChanged {
                if (birthdayEdit.length() > 7) {
                    if (validBirthDay()) {
                        birthdayLayout.error = null
                        if (phoneLayout.isVisible) {
                            confirmButton.isVisible = true
                        } else {
                            genderLayout.isVisible = true
                            birthdayEdit.closeKeyboard()
                        }
                    } else {
                        confirmButton.isVisible = false
                        birthdayLayout.error = "생년월일 형식이 다릅니다."
                    }
                }
            }

            birthdayEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                val isValid = validBirthDay() && birthdayEdit.length() > 7
                if (isValid) {
                    confirmButton.isVisible = phoneLayout.isVisible
                    birthdayLayout.error = null
                } else {
                    birthdayLayout.error = "생년월일 형식이 다릅니다."
                }

                birthdayEdit.closeKeyboard()
            }

            telecomChipGroup.setOnCheckedStateChangeListener { _, _ ->
                if (!phoneLayout.isVisible) {
                    phoneLayout.isVisible = true
                    phoneEdit.showKeyboard()
                }
            }

            genderChipGroup.setOnCheckedStateChangeListener { _, _ ->
                if (!telecomLayout.isVisible) {
                    telecomLayout.isVisible = true
                }
            }

            phoneEdit.doAfterTextChanged {
                if (phoneEdit.length() > 10) {
                    if (validPhone()) {
                        phoneLayout.error = null
                        confirmButton.isVisible = true
                        phoneEdit.closeKeyboard()
                    } else {
                        confirmButton.isVisible = false
                        phoneLayout.error = "전화번호 형식이 다릅니다."
                    }

                }
            }

            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                confirmButton.isVisible = phoneEdit.length() > 9 && validPhone()
                phoneEdit.closeKeyboard()
            }
        }
    }

    fun onClickDone() {
        if (!validName()) {
            binding.nameLayout.error = "1자 이상의 한글을 입력해주세요."
            return
        }

        if (!validBirthDay()) {
            binding.birthdayLayout.error = "생년월일 형식이 다릅니다."
            return
        }

        if (!validPhone()) {
            binding.phoneLayout.error = "전화번호 형식이 다릅니다."
            return
        }

        startActivity(Intent(this, VerifyOtpActivity::class.java))
    }

    private fun validName() = !binding.nameEdit.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.nameEdit.text!!)

    private fun validBirthDay() = !binding.birthdayEdit.text.isNullOrBlank()
            && REGEX_BIRTHDAY.toRegex().matches(binding.birthdayEdit.text!!)

    private fun validPhone() = !binding.phoneEdit.text.isNullOrEmpty()
            && REGEX_PHONE.toRegex().matches(binding.phoneEdit.text!!)
}