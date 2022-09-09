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

    private fun loadDetail(data: Todo?) = with(binding) {
        if (data != null) {
            tvTitle.text = data.title
            tvStatus.text = if (data.completed) "Completed" else "Incomplete"
            tvUserId.text = data.userId.toString()
            tvTodoId.text = data.id.toString()
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isLoading(isLoading: Boolean) = with(binding) {
        if (isLoading) {
            pbLoading.visibility = View.VISIBLE
            btnRandomize.visibility = View.INVISIBLE
            btnRandomize.isEnabled = false
        } else {
            pbLoading.visibility = View.GONE
            btnRandomize.visibility = View.VISIBLE
            btnRandomize.isEnabled = true
        }
    }
}