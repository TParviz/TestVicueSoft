package tj.test.testvicuesoft.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tj.test.testvicuesoft.data.models.VideoData
import tj.test.testvicuesoft.domain.GetVideoUseCaseImpl
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getVideosUseCase: GetVideoUseCaseImpl
) : ViewModel() {

    private val _videoList = MutableLiveData<List<VideoData>>()
    val videoList: LiveData<List<VideoData>> = _videoList

    init {
        getVideos()
    }

    private fun getVideos() {
        viewModelScope.launch {
            getVideosUseCase(Unit).collect { result ->
                result.onFailure { error ->
                    Log.d("TAG_ERROR", "error : $error")
                }.onSuccess { response ->
                    _videoList.value = response
                    Log.d("TAG_ERROR", "response : $response")
                }
            }
        }
    }
}