package fastcampus.part2.chapter6.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fastcampus.part2.chapter6.key.Key
import fastcampus.part2.chapter6.LoginActivity
import fastcampus.part2.chapter6.R
import fastcampus.part2.chapter6.databinding.FragmentMypageBinding
import fastcampus.part2.chapter6.userlist.UserItem

class MyPageFragment : Fragment(R.layout.fragment_mypage) {

    private lateinit var binding: FragmentMypageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMypageBinding.bind(view)

        val currentUserId = Firebase.auth.currentUser?.uid ?: ""
        val currentUserDB = Firebase.database.reference.child(Key.DB_USERS).child(currentUserId)

        currentUserDB.get().addOnSuccessListener {
            val currentUserItem = it.getValue(UserItem::class.java) ?: return@addOnSuccessListener
            binding.etUsername.setText(currentUserItem.username)
            binding.etDescription.setText(currentUserItem.description)
        }

        binding.btnApply.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val description = binding.etDescription.text.toString()

            if (username.isEmpty()) {
                Toast.makeText(context, "유저 이름은 빈 값으로 두실 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = mutableMapOf<String, Any>()
            user["username"] = username
            user["description"] = description
            currentUserDB.updateChildren(user)
        }

        binding.btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }
    }
}