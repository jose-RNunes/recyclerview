package br.com.recyclerview.presentation

import android.content.Context
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import br.com.recyclerview.business.GetDataModelUseCase
import br.com.recyclerview.business.GetHeaderModelUseCase
import br.com.recyclerview.databinding.RecyclerViewBinding
import br.com.recyclerview.model.HeaderDataModel

class RecyclerViewActivity : AppCompatActivity() {

    private val binding: RecyclerViewBinding by lazy { RecyclerViewBinding.inflate(layoutInflater) }

    private val getDataModel = GetDataModelUseCase()

    private val getHeaderModel = GetHeaderModelUseCase()

    private val verticalAdapter = VerticalAdapter()

    private val verticalLayoutManager = SnappingLinearLayoutManager(this).apply {
        orientation = RecyclerView.VERTICAL
    }

    private val horizontalLayoutManager = SnappingLinearLayoutManager(this).apply {
        orientation = RecyclerView.HORIZONTAL
    }

    private val horizontalAdapter = HorizontalAdapter { header ->

        val items = getDataModel()

        val position = items.indexOf(header)

        if (position != -1) {
            binding.rvVertical.smoothScrollToPosition(position)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvHorizontal.layoutManager = horizontalLayoutManager
        binding.rvHorizontal.adapter = horizontalAdapter
        horizontalAdapter.submitList(getHeaderModel())

        binding.rvVertical.layoutManager = verticalLayoutManager
        binding.rvVertical.adapter = verticalAdapter
        verticalAdapter.submitList(getDataModel())

        binding.rvVertical.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val position = verticalLayoutManager.findFirstCompletelyVisibleItemPosition()
                val isLastPosition =
                    verticalLayoutManager.findLastCompletelyVisibleItemPosition() == getDataModel().lastIndex
                val item = getDataModel().getOrNull(position)

                if (item != null && item is HeaderDataModel) {
                    if (isLastPosition) {
                        binding.rvHorizontal.smoothScrollToPosition(getHeaderModel().lastIndex)
                    } else {
                        val headerPosition = getHeaderModel().indexOf(item)
                        if (headerPosition != -1) {
                            binding.rvHorizontal.smoothScrollToPosition(headerPosition)
                        }
                    }
                }
            }
        })
    }
}

class SnappingLinearLayoutManager(val context: Context) : LinearLayoutManager(context) {

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        val smoothScroller = object : LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int = SNAP_TO_START
            override fun getHorizontalSnapPreference(): Int = SNAP_TO_START
            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                return this@SnappingLinearLayoutManager.computeScrollVectorForPosition(targetPosition)
            }
        }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

}