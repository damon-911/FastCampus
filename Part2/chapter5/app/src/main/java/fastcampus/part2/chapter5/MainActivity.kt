package fastcampus.part2.chapter5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part2.chapter5.adapter.NewsAdapter
import fastcampus.part2.chapter5.databinding.ActivityMainBinding
import fastcampus.part2.chapter5.model.NewsRss
import fastcampus.part2.chapter5.model.transform
import fastcampus.part2.chapter5.network.NewsService
import fastcampus.part2.chapter5.network.RetrofitClient
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsService = RetrofitClient.retrofit.create(NewsService::class.java)

        binding.feedChip.isChecked = true
        newsService.mainFeed().submitList()

        newsAdapter = NewsAdapter { url ->
            startActivity(
                Intent(this, WebViewActivity::class.java).apply {
                    putExtra("url", url)
                }
            )
        }

        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        binding.feedChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.feedChip.isChecked = true
            newsService.mainFeed().submitList()
        }

        binding.politicsChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.politicsChip.isChecked = true
            newsService.politicsNews().submitList()
        }

        binding.economyChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.economyChip.isChecked = true
            newsService.economyNews().submitList()
        }

        binding.societyChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.societyChip.isChecked = true
            newsService.societyNews().submitList()
        }

        binding.itChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.itChip.isChecked = true
            newsService.itNews().submitList()
        }

        binding.sportChip.setOnClickListener {
            binding.chipGroup.clearCheck()
            binding.sportChip.isChecked = true
            newsService.sportNews().submitList()
        }

        binding.etSearchText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.chipGroup.clearCheck()
                binding.etSearchText.clearFocus()

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                newsService.search(binding.etSearchText.text.toString()).submitList()

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun Call<NewsRss>.submitList() {
        enqueue(object : Callback<NewsRss> {
            override fun onResponse(call: Call<NewsRss>, response: Response<NewsRss>) {
                val list = response.body()?.channel?.items.orEmpty().transform()
                newsAdapter.submitList(list)

                binding.notFountView.isVisible = list.isEmpty()

                list.forEachIndexed { index, news ->
                    Thread {
                        try {
                            val jsoup = Jsoup.connect(news.link)
                                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                                .get()
                            val elements = jsoup.select("meta[property^=og:]")
                            val ogImageNode = elements.find { node ->
                                node.attr("property") == "og:image"
                            }
                            news.imageUrl = ogImageNode?.attr("content")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                        runOnUiThread {
                            newsAdapter.notifyItemChanged(index)
                        }
                    }.start()
                }
            }

            override fun onFailure(call: Call<NewsRss>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}