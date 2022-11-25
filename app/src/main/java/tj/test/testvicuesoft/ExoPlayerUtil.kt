package tj.test.testvicuesoft

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource

object ExoPlayerUtil {
    lateinit var cacheDataSourceFactory: CacheDataSource.Factory
        private set

    private const val MP4 = "mp4"

    fun initCache(cacheDataSourceFactory: CacheDataSource.Factory) {
        this.cacheDataSourceFactory = cacheDataSourceFactory
    }

    fun initEmptyPlayer(
        context: Context,
        doBeforeBuild: ExoPlayer.Builder.() -> ExoPlayer.Builder = { this }
    ): ExoPlayer {
        return ExoPlayer.Builder(context)
            .doBeforeBuild()
            .build()
    }

    fun getMediaSource(videoURL: String?): MediaSource {
        val mediaItem = MediaItem.fromUri(videoURL.orEmpty())
        return if (videoURL.orEmpty().substringAfterLast('.') == MP4) {
            ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(mediaItem)
        } else {
            HlsMediaSource.Factory(cacheDataSourceFactory).createMediaSource(mediaItem)
        }
    }
}
