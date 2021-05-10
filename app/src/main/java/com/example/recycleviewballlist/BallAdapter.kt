package com.example.recycleviewballlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.recycleviewballlist.databinding.MyLayoutBinding
import com.example.recycleviewballlist.databinding.MyLayoutCardBinding

class BallAdapter(val ballList: List<Balls>): Adapter<BallAdapter.ViewHolder>(), Filterable {

    var filterList = ArrayList<Balls>()

    init {
        filterList = ballList as ArrayList<Balls>
    }

    /** ViewBinding
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val ballImage: ImageView = view.findViewById(R.id.imageView)
    val ballName: TextView = view.findViewById(R.id.textView)
    }
     **/

    inner class ViewHolder(val binding: MyLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /**ViewBinding
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_layout, parent, false)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.my_layout_vertical, parent, false)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.my_layout_card, parent, false)
         **/


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MyLayoutBinding.inflate(layoutInflater, parent, false)
        //val binding = MyLayoutCardBinding.inflate(layoutInflater,parent,false)

        /**ViewBinding
        val viewHolder = ViewHolder(view)
         **/


        val viewHolder = ViewHolder(binding)

        viewHolder.itemView.setOnClickListener {
            Toast.makeText(
                parent.context,
                //ballList[viewHolder.bindingAdapterPosition].name,
                filterList[viewHolder.bindingAdapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        //return ballList.size
        return filterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val ball = ballList[position]

        val ball = filterList[position]

        /**ViewBinding
        holder.ballImage.setImageResource(ball.imageId)
        holder.ballName.text = ball.name
         **/


        holder.binding.ball = ball
        //enforce to refresh the UI
        holder.binding.executePendingBindings()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchKey = constraint.toString()
                if (searchKey.isEmpty()) {
                    filterList = ballList as ArrayList<Balls>
                } else {
                    val resultList = ArrayList<Balls>()  //temporary array
                    ballList.forEach {
                        if (it.name.toLowerCase().contains(searchKey.toLowerCase())) {
                            resultList.add(it)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Balls>
                //inform the recyclerview to refresh the data
                notifyDataSetChanged()
            }
        }
    }
}