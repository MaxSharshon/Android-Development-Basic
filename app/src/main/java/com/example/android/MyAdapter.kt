import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.Item
import com.example.android.R

class MyAdapter(private val items: List<Item>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val switchButton: Switch = itemView.findViewById(R.id.switchButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = if (item.isChecked) "ON" else "OFF"
        holder.textView.setBackgroundColor(if (item.isChecked) Color.GREEN else Color.RED)
        holder.switchButton.isChecked = item.isChecked

        holder.switchButton.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            holder.textView.text = if (isChecked) "ON" else "OFF"
            holder.textView.setBackgroundColor(if (isChecked) Color.GREEN else Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
