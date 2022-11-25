package tj.test.testvicuesoft.data.api

import retrofit2.http.GET
import tj.test.testvicuesoft.data.models.VideoData

interface VideoApi {

    @GET("?group=video&category_id=1")
    suspend fun getVideos(): List<VideoData>

}