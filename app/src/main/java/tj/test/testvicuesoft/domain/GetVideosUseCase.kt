package tj.test.testvicuesoft.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tj.test.testvicuesoft.data.VideoRepository
import tj.test.testvicuesoft.data.models.VideoData
import javax.inject.Inject

interface GetVideosUseCase : FlowUseCase<Unit, List<VideoData>>

class GetVideoUseCaseImpl @Inject constructor(
    private val videoRepository: VideoRepository
) : GetVideosUseCase {
    override fun execute(param: Unit): Flow<Result<List<VideoData>>> = flow {
        emit(Result.success(videoRepository.getVideos()))
    }
}
