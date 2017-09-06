package com.yang.mac.disklrucachedemo.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.util.LruCache
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by bo on 2017/9/6.
 */
class PhotoRvAdapter(val context: Context) : RecyclerView.Adapter<PhotoRvAdapter.ViewHolder>() {
    val lruCache = LruCache<String, Bitmap>(10 * 1024 * 1024)

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        init {

        }
    }
}