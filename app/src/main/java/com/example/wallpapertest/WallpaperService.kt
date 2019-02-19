package com.example.wallpapertest

import android.media.MediaPlayer
import android.net.Uri
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import com.trampoline.app.preferences.PreferencesProvider


class WallpaperService : WallpaperService() {

    override fun onCreateEngine(): Engine = MyWallpaperEngine()

    inner class MyWallpaperEngine : Engine() {

        private var mediaPlayer: MediaPlayer = MediaPlayer.create(baseContext, Uri.parse(PreferencesProvider.path))

        init {
            mediaPlayer.isLooping = true
        }

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            mediaPlayer.setSurface(holder.surface)
            mediaPlayer.start()
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            playHeadTime = mediaPlayer.currentPosition
            mediaPlayer.reset()
            mediaPlayer.release()
        }
    }

    companion object {
        private var playHeadTime = 0
    }
}