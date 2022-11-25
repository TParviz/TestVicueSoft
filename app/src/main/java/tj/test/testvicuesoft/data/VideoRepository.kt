package tj.test.testvicuesoft.data

import tj.test.testvicuesoft.data.models.VideoData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepository @Inject constructor() {
    suspend fun getVideos(): List<VideoData> {
        return CommonConnection().videoApi.getVideos()
    }
}