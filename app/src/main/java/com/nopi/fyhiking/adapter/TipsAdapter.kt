package com.nopi.fyhiking.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nopi.fyhiking.R
import com.nopi.fyhiking.activities.DetailTipsActivity
import com.nopi.fyhiking.model.ModelPeralatan
import kotlinx.android.synthetic.main.list_item_peralatan_tips.view.*

class TipsAdapter(private val context: Context, private val modelPeralatan:
List<ModelPeralatan>) : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_peralatan_tips, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelPeralatan[position]

        Glide.with(context)
            .load(data.strImagePeralatan)
            .into(holder.imagePeralatan)

        holder.tvNamaAlat.text = data.strNamaPeralatan
        holder.tvTipeAlat.text = data.strTipePeralatan

        holder.cvListPeralatan.setOnClickListener {
            val intent = Intent(context, DetailTipsActivity::class.java)
            intent.putExtra(DetailTipsActivity.DETAIL_PERALATAN, modelPeralatan[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelPeralatan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListPeralatan: CardView
        var imagePeralatan: ImageView
        var tvNamaAlat: TextView
        var tvTipeAlat: TextView

        init {
            cvListPeralatan = itemView.cvListPeralatan
            imagePeralatan = itemView.imagePeralatan
            tvNamaAlat = itemView.tvNamaAlat
            tvTipeAlat = itemView.tvTipeAlat
        }
    }

}