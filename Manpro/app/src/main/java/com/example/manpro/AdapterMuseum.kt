package com.example.manpro

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext
import java.io.File

class AdapterMuseum (private var museumList: ArrayList<Museum>): RecyclerView.Adapter<AdapterMuseum.ListViewHolder>()
{
    val db = FirebaseFirestore.getInstance()

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var V : View =itemView
        var Lokasi: TextView = itemView.findViewById(R.id.Lokasi)
        var Nama : TextView = itemView.findViewById(R.id.Nama)
        var detil : Button =itemView.findViewById(R.id.button)
        var Rating : TextView =itemView.findViewById(R.id.Rating)
        var Tipe1 : TextView =itemView.findViewById(R.id.TipeMuseum1)
        var Gambar :ImageView=itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.museum_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var museumlist = museumList[position]
        holder.Nama.setText(museumlist.Nama)
        holder.Lokasi.setText(museumlist.Lokasi)
        holder.Tipe1.setText(museumlist.TipeMuseum1)
        holder.Rating.setText(museumlist.Rating.toString())
        holder.Gambar.setImageResource(museumlist.Image)
//        Log.e("cobba", museumlist.Image.toString())
        holder.detil.setOnClickListener {
            val a = Intent(holder.V.context,DetailMuseum::class.java)
            a.putExtra("Id",museumlist.Nama)
            holder.V.context.startActivity(a)
        }
    }

    override fun getItemCount(): Int {
        return museumList.size
    }
}