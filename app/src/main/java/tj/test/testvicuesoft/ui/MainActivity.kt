package tj.test.testvicuesoft.ui

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import tj.test.testvicuesoft.ExoPlayerUtil
import tj.test.testvicuesoft.data.models.VideoData
import tj.test.testvicuesoft.databinding.ActivityMainBinding
import tj.test.testvicuesoft.ui.AddTextDialogFragment.Companion.TAG_ADDTEXT_DIALOG_FRAGMENT
import tj.test.testvicuesoft.ui.adapter.VideoAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var addTextDialogFragment: AddTextDialogFragment? = null

    private val adapterVideo = VideoAdapter(
        onViewClicked = { item ->
            startVideo(item)
        },
    )
    private var exoPlayer: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() = with(binding) {
        rvVideoList.adapter = adapterVideo

        btnText.setOnClickListener {
            showAddTextDialogFragment()
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.videoList.observe(this) { list ->
            startVideo(list[0])
            adapterVideo.submitList(list)
        }
    }

    @Override
    override fun onPause() {
        super.onPause()
        stopVideo()
    }

    private fun startVideo(video: VideoData) {
        exoPlayer = ExoPlayerUtil.initEmptyPlayer(applicationContext)
        exoPlayer?.apply {
            val source = ExoPlayerUtil.getMediaSource(video.file_url)
            setMediaSource(source)
            prepare()
            addListener(object : Player.Listener {
                override fun onRenderedFirstFrame() {
                    exoPlayer?.removeListener(this)
                }
            })
            volume = 1f
            seekTo(0, 0)
            repeatMode = Player.REPEAT_MODE_ALL
            playWhenReady = true
        }

        binding.player.player = exoPlayer
    }

    private fun stopVideo() {
        exoPlayer?.let {
            it.repeatMode = Player.REPEAT_MODE_OFF
            it.stop()
            it.release()
        }
    }

    var x: Float = 0f
    var y: Float = 0f
    var dx: Float = 0f
    var dy: Float = 0f

    @Override
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            x = event.x
            y = event.y
        }

        if (event?.action == MotionEvent.ACTION_MOVE) {
            dx = event.x - x
            dy = event.y - y

            binding.tvDrag.x = binding.tvDrag.x + dx
            binding.tvDrag.y = binding.tvDrag.y + dy

            x = event.x
            y = event.y

        }
        return super.onTouchEvent(event)
    }

    private fun showAddTextDialogFragment() {
        if (addTextDialogFragment == null) {
            addTextDialogFragment = AddTextDialogFragment.newInstance()
        }
        if (addTextDialogFragment?.isAdded == false) {
            addTextDialogFragment?.show(supportFragmentManager, TAG_ADDTEXT_DIALOG_FRAGMENT)
        }

        addTextDialogFragment?.setListeners { text ->
            binding.tvDrag.text = text
            binding.tvDrag.isVisible = true

        }
    }
}