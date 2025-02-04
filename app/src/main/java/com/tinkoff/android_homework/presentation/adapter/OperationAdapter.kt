package com.tinkoff.android_homework.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.android_homework.R
import com.tinkoff.android_homework.databinding.OperationRecyclerItemBinding
import com.tinkoff.android_homework.presentation.model.operations.OperationItem
import com.tinkoff.android_homework.presentation.model.operations.OperationType

/**
 * @author d.a.korotkov
 */
class OperationAdapter(private val listener: (Long) -> Unit) :
    ListAdapter<OperationItem, OperationAdapter.Holder>(Comparator()) {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = OperationRecyclerItemBinding.bind(view)

        fun bind(item: OperationItem) = with(binding) {
            val operIcon = when (item.operationType) {
                OperationType.OUTCOME -> R.drawable.spending_icon
                OperationType.INCOME -> R.drawable.income_icon
            }
            operationIcon.setImageResource(operIcon)

            operationTitle.text = item.operationTitle
            operationSum.text = item.operationSum.toString()

            root.setOnClickListener {
                listener(item.id)
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<OperationItem>() {
        override fun areItemsTheSame(
            oldItem: OperationItem,
            newItem: OperationItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: OperationItem,
            newItem: OperationItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.operation_recycler_item, parent, false)
        return Holder(itemView)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
