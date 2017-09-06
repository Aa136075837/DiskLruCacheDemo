package com.yang.mac.disklrucachedemo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yang.mac.disklrucachedemo.libcore.io.DiskLruCache
import com.yang.mac.disklrucachedemo.utils.CacheUtils
import com.yang.mac.disklrucachedemo.utils.CacheUtils.hashKeyForDisk
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var disLruCache: DiskLruCache? = null
    val url = "http://mac-yang.s.jcloud.com/test1/Koala.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun initListener() {
        save_btn.setOnClickListener {
            save()
            showCacheSize()
        }
        display_btn.setOnClickListener {
            display()
            showCacheSize()
        }
        remove_btn.setOnClickListener {
            remove()
            showCacheSize()
        }
        delete_btn.setOnClickListener {
            delete()
            showCacheSize()
        }
    }

    private fun showCacheSize() {
        val size = disLruCache?.size()
        val sb = StringBuffer()
        sb.append(size).append("B")
        size_tv.setText(sb.toString())
    }

    private fun delete() {
        disLruCache = CacheUtils.open(this)
        val key = hashKeyForDisk(url)
        disLruCache?.delete()
    }

    private fun remove() {
        disLruCache = CacheUtils.open(this)
        val key = hashKeyForDisk(url)
        disLruCache?.remove(key)
    }

    private fun display() {
        disLruCache = CacheUtils.open(this)
        val key = hashKeyForDisk(url)
        val snapShot = disLruCache?.get(key)
        if (snapShot != null) {
            val `is` = snapShot!!.getInputStream(0)
            val bitmap = BitmapFactory.decodeStream(`is`)
            img.setImageBitmap(bitmap)
        }

    }

    fun save() {
        disLruCache = CacheUtils.open(this)
        Thread(Runnable {
            kotlin.run {
                val key = CacheUtils.hashKeyForDisk(url)
                val editor = disLruCache?.edit(key)
                if (editor != null) {
                    val outputStream = editor.newOutputStream(0)
                    if (CacheUtils.downloadUrlToStream(url, outputStream)) {
                        editor.commit()
                    }
                    else {
                        editor.abort()
                    }
                }
                disLruCache?.flush()
            }

        }).start()

    }

}
