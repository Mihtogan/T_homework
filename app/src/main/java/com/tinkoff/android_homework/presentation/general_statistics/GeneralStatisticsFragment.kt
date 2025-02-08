package com.tinkoff.android_homework.presentation.general_statistics

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinkoff.android_homework.databinding.FragmentGeneralStatisticsBinding
import com.tinkoff.android_homework.presentation.adapter.OperationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralStatisticsFragment : Fragment() {
    private val viewModel: GeneralStatisticsViewModel by viewModels()

    private var _binding: FragmentGeneralStatisticsBinding? = null

    private var operationAdapter: OperationAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralStatisticsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (operationAdapter == null)
            operationAdapter = OperationAdapter { id ->
                findNavController().navigate(
                    GeneralStatisticsFragmentDirections
                        .actionGeneralStatisticsFragmentToDetailFragment(
                            id
                        )
                )
            }

        initOperationsRecycler(operationAdapter!!)
        subscribeToOperations(operationAdapter!!)
        subscribeToTotal()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeToTotal() {
        viewModel.total.observe(viewLifecycleOwner) { totalItem ->
            with(_binding!!.statisticCard) {

                income.text = totalItem?.income.toString()
                outcome.text = totalItem?.outcome.toString()
                sum.text = totalItem?.total.toString()

                progressBar.progress = totalItem?.progress?.toInt() ?: 0
            }
        }
    }

    private fun subscribeToOperations(adapter: OperationAdapter) {
        viewModel.operations.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }
    }

    private fun initOperationsRecycler(adapter: OperationAdapter) {
        with(_binding!!.operationsRecycler) {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}