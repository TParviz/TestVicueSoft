package tj.test.testvicuesoft.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoData(
    val id : String,
    val file_url: String,
    val thumbnail_url: String,
    val poster_url: String,
    val small_thumbnail_url: String,
    val small_poster_url: String
): Parcelable