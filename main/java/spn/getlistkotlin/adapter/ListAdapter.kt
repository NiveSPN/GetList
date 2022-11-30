package spn.getlistkotlin.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import spn.getlistkotlin.R
import spn.getlistkotlin.data.Heirarchy
import spn.getlistkotlin.databinding.ItemFilterBinding

class ListAdapter(applicationContext: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val context = applicationContext

    private var list = ArrayList<Heirarchy>()

    fun setList(list: List<Heirarchy>) {
        this.list = list as ArrayList<Heirarchy>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.nameTxt.text = list[position].contactName
        holder.binding.departmentTxt.text = list[position].designationName

        holder.binding.phoneIv.setOnClickListener {

            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            dialIntent.data = Uri.parse("tel:" + list[position].contactNumber)
            context.startActivity(dialIntent)
        }

        holder.binding.msgIv.setOnClickListener {

            val messageIntent = Intent(Intent.ACTION_VIEW)
            messageIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            messageIntent.data = Uri.parse("smsto:" + Uri.encode(list[position].contactNumber))
            context.startActivity(messageIntent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}