package br.com.recyclerview.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.recyclerview.model.HeaderDataModel

class HorizontalAdapter(val listener: ((header: HeaderDataModel) -> Unit)? = null): ListAdapter<HeaderDataModel, HeaderViewHolder>(HorizontalDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder.newInstance(parent, listener)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}