package tj.test.testvicuesoft

import android.app.Application
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initExoPlayerCache()
    }

    private fun initExoPlayerCache() {
        val exoPlayerCacheSize: Long = 90 * 1024 * 1024
        val leastRecentlyUsedCacheEvictor = LeastRecentlyUsedCacheEvictor(exoPlayerCacheSize)
        val exoDatabaseProvider = StandaloneDatabaseProvider(this)
        val simpleCache = SimpleCache(cacheDir, leastRecentlyUsedCacheEvictor, exoDatabaseProvider)

        val httpDataSourceFactory = DefaultHttpDataSource
            .Factory()
            .setAllowCrossProtocolRedirects(true)

        ExoPlayerUtil.initCache(
            cacheDataSourceFactory = CacheDataSource.Factory()
                .setCache(simpleCache)
                .setUpstreamDataSourceFactory(httpDataSourceFactory)
                .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
        )
    }
}