package tj.test.testvicuesoft.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.test.testvicuesoft.data.models.VideoData
import tj.test.testvicuesoft.databinding.ItemPreviewVideoBinding

class VideoViewHolder(
    private val binding: ItemPreviewVideoBinding,
    private val onViewClicked: (VideoData) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: VideoData) = with(binding) {

        Glide.with(ivPoster)
            .load(item.small_poster_url)
            .centerCrop()
            .into(ivPoster)

        ivPoster.setOnClickListener {
            onViewClicked.invoke(item)
        }
    }
}