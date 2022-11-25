package tj.test.testvicuesoft.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tj.test.testvicuesoft.data.models.VideoData
import tj.test.testvicuesoft.databinding.ItemPreviewVideoBinding

class VideoAdapter(
    private val onViewClicked: (VideoData) -> Unit,
) : ListAdapter<VideoData, VideoViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            binding = ItemPreviewVideoBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            ),
            onViewClicked = onViewClicked,
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<VideoData>() {
            override fun areItemsTheSame(
                oldItem: VideoData,
                newItem: VideoData
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: VideoData,
                newItem: VideoData
            ): Boolean =
                oldItem == newItem
        }
    }
}