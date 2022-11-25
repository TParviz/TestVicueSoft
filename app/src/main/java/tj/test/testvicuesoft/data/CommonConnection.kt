package tj.test.testvicuesoft.data

import tj.test.testvicuesoft.data.api.VideoApi

class CommonConnection: ConnectionFactory() {
    val videoApi: VideoApi = getRetrofit().create(VideoApi::class.java)
}