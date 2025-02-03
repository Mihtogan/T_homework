package com.tinkoff.android_homework.presentation.general_statistics

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.android_homework.databinding.FragmentGeneralStatisticsBinding
import com.tinkoff.android_homework.presentation.adapter.OperationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GeneralStatisticsFragment : Fragment() {
    private val viewModel: GeneralStatisticsViewModel by viewModels()

    private val operationAdapter = OperationAdapter()

    private var _binding: FragmentGeneralStatisticsBinding? = null

    private val operationsRecyclerView: RecyclerView by lazy { _binding!!.operationsRecycler }
    private val totalSum: TextView by lazy { _binding!!.statisticCard.sum }
    private val outcome: TextView by lazy { _binding!!.statisticCard.outcome }
    private val income: TextView by lazy { _binding!!.statisticCard.income }
    private val progressBar: ProgressBar by lazy { _binding!!.statisticCard.progressBar }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralStatisticsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOperationsRecycler()
        subscribeToOperations()
        subscribeToTotal()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToTotal() {
        lifecycleScope.launch {
            viewModel.total.collect { totalItem ->
                income.text = totalItem?.income.toString()
                outcome.text = totalItem?.outcome.toString()
                totalSum.text = totalItem?.total.toString()

                Log.e("TAGRTRT", "totalItem?.progress :${totalItem?.progress}")
                progressBar.progress = totalItem?.progress?.toInt() ?: 0
            }
        }
    }

    private fun subscribeToOperations() {
        lifecycleScope.launch {
            viewModel.operations.collect {
                operationAdapter.data = it
            }
        }
    }

    private fun initOperationsRecycler() {
        operationsRecyclerView.adapter = operationAdapter
        operationsRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}