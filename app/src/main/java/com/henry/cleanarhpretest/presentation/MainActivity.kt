package com.henry.cleanarhpretest.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.henry.cleanarhpretest.databinding.ActivityMainBinding
import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Todo Randomizer"

        observeViewModel()

        binding.btnRandomize.setOnClickListener {
            viewModel.getTodo((1..200).random())
        }
    }

    private fun observeViewModel() {
        viewModel.todoResponse.observe(this) { response ->
            when (response) {
                is Resource.Error -> {
                    isLoading(false)
                    response.message?.let {
                        Log.e(TAG, "onCreate: $it")
                        showToastMessage("An error occurred $it")
                    }
                }
                is Resource.Loading -> {
                    isLoading(true)
                }
                is Resource.Success -> {
                    isLoading(false)
                    loadDetail(response.data)
                }
            }
        }
    }

    private fun loadDetail(data: Todo?) {
        if (data != null) {
            binding.tvTitle.text = data.title
            binding.tvStatus.text = if (data.completed) "Completed" else "Incomplete"
            binding.tvUserId.text = data.userId.toString()
            binding.tvTodoId.text = data.id.toString()
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbLoading.visibility = View.VISIBLE
            binding.btnRandomize.visibility = View.INVISIBLE
            binding.btnRandomize.isEnabled = false
        } else {
            binding.pbLoading.visibility = View.GONE
            binding.btnRandomize.visibility = View.VISIBLE
            binding.btnRandomize.isEnabled = true
        }
    }
}