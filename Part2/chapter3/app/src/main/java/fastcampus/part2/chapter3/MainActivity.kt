package fastcampus.part2.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fastcampus.part2.chapter3.databinding.ActivityMainBinding
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import okhttp3.*
import java.io.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val client = OkHttpClient()
    private var serverHost = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etServerHost.addTextChangedListener {
            serverHost = it.toString()
        }

        binding.btnConfirm.setOnClickListener {
            val request: Request = Request.Builder()
                .url("http://$serverHost:8080")
                .build()

            /**
             *    OKHttp 로 통신하기
             */
            val callback = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "수신에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("Client", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        val gsonMessage = Gson().fromJson(responseBody, Message::class.java)

                        runOnUiThread {
                            binding.tvInformation.isVisible = true
                            binding.tvInformation.text = gsonMessage.message

                            binding.etServerHost.isVisible = false
                            binding.btnConfirm.isVisible = false
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "수신에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            client.newCall(request).enqueue(callback)
        }
    }

//    // Socket Client 직접 구현
//    private fun makeSocketClient() {
//        Thread {
//            try {
//                val socket = Socket("10.0.2.2", 8080)
//                val printer = PrintWriter(socket.getOutputStream())
//                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
//
//                printer.println("GET / HTTP/1.1")
//                printer.println("Host: 127.0.0.1:8080")
//                printer.println("User-Agent: android")
//                printer.println("\r\n")
//                printer.flush()
//
//                var input: String? = "-1"
//                while (input != null) {
//                    input = reader.readLine()
//                    Log.e("Client", "$input")
//                }
//
//                printer.close()
//                reader.close()
//                socket.close()
//            } catch (e: Exception) {
//                Log.e("Client", e.toString())
//            }
//        }.start()
//    }
}