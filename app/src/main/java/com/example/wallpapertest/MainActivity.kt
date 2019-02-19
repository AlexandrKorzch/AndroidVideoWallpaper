package com.example.wallpapertest

import android.Manifest
import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.codyy.rx.permissions.RxPermissions
import com.trampoline.app.preferences.PreferencesProvider
import io.reactivex.disposables.Disposable


class MainActivity : AppCompatActivity() {

    //todo - Set your path to video
    private val path = ""

    private var permissionDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_wallpaper)
    }

    private fun permissionStorage(allow: (granted: Boolean) -> Unit) {
        permissionDisposable = RxPermissions(supportFragmentManager).request(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe { allow(it) }
    }

    fun onClick(v: View) {
        setWallPaper()
    }

    private fun setWallPaper() {
        permissionStorage {
            PreferencesProvider.path = path
            Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER).apply {
                putExtra(
                    WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    ComponentName(this@MainActivity, WallpaperService::class.java)
                )
                startActivity(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        permissionDisposable?.dispose()
    }
}