package com.tinkoff.android_homework.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tinkoff.android_homework.R
import com.tinkoff.android_homework.databinding.FragmentDetailBinding
import com.tinkoff.android_homework.domain.main.entities.OperationType
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author d.shtaynmets
 */

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel by viewModels<DetailViewModel>()

    private val args: DetailFragmentArgs by navArgs()
    private var binding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.gt(args.id)

        viewModel.details.observe(viewLifecycleOwner) { detail ->
            with(binding!!) {
                detailIcon.setImageResource(
                    when (detail.type) {
                        OperationType.OUTCOME -> R.drawable.spending_icon
                        OperationType.INCOME -> R.drawable.income_icon
                    }
                )
                detailComment.text = detail.comment
                detailSum.text = when (detail.type) {
                    OperationType.OUTCOME -> "-"
                    OperationType.INCOME -> "+"
                } + detail.amount.toString()

                detailDetails.text = detail.positions
            }
        }
    }
}
