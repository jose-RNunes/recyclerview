package br.com.recyclerview.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.recyclerview.R
import br.com.recyclerview.databinding.RecyclerViewHeaderBinding
import br.com.recyclerview.databinding.RecyclerViewItemBinding
import br.com.recyclerview.model.DataModel
import br.com.recyclerview.model.HeaderDataModel
import br.com.recyclerview.model.ItemDataModel

class VerticalAdapter : ListAdapter<DataModel, RecyclerView.ViewHolder>(VerticalDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.recycler_view_item -> ItemViewHolder.newInstance(parent)
            else -> HeaderViewHolder.newInstance(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val dataModel = getItem(position)) {
            is HeaderDataModel -> (holder as HeaderViewHolder).bind(dataModel)
            is ItemDataModel -> (holder as ItemViewHolder).bind(dataModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HeaderDataModel -> R.layout.recycler_view_header
            is ItemDataModel -> R.layout.recycler_view_item
            else -> super.getItemViewType(position)
        }
    }
}

abstract class VerticalViewHolder<T>(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(dataModel: T)
}

class ItemViewHolder(private val binding: RecyclerViewItemBinding) : VerticalViewHolder<ItemDataModel>(binding) {
    override fun bind(dataModel: ItemDataModel) {
        binding.tvItem.text = dataModel.item
    }

    companion object {
        fun newInstance(viewGroup: ViewGroup) = ItemViewHolder(
            binding = RecyclerViewItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }
}

class HeaderViewHolder(
    private val binding: RecyclerViewHeaderBinding,
    private val listener: ((header: HeaderDataModel) -> Unit)? = null
) : VerticalViewHolder<HeaderDataModel>(binding) {
    override fun bind(dataModel: HeaderDataModel) {
        binding.tvHeader.text = dataModel.header
        binding.root.setOnClickListener {
            listener?.invoke(dataModel)
        }
    }

    companion object {
        fun newInstance(
            viewGroup: ViewGroup,
            listener: ((header: HeaderDataModel) -> Unit)? = null
        ) = HeaderViewHolder(
            binding = RecyclerViewHeaderBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            ),
            listener = listener
        )
    }
}

class VerticalDiffUtil : DiffUtil.ItemCallback<DataModel>() {
    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem == newItem
    }
}

class HorizontalDiffUtil : DiffUtil.ItemCallback<HeaderDataModel>() {
    override fun areItemsTheSame(oldItem: HeaderDataModel, newItem: HeaderDataModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HeaderDataModel, newItem: HeaderDataModel): Boolean {
        return oldItem == newItem
    }
}