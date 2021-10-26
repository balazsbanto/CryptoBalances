package feature_tokens.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptobalances.R
import feature_tokens.domain.ERC20Token
import kotlinx.android.synthetic.main.row_layout.view.*
import kotlin.math.pow

class TokenListAdapter(private val tokenList: ArrayList<ERC20Token>) :
    RecyclerView.Adapter<TokenListAdapter.ViewHolder>() {

    companion object {
        const val RED_COLOR = "#FF5733"
        const val GREEN_COLOR = "#00FF00"
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tokenList[position], position)
    }

    override fun getItemCount(): Int = tokenList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(token: ERC20Token, position: Int) {
            itemView.setBackgroundColor(if (token.balance > 0) Color.parseColor(GREEN_COLOR) else Color.parseColor(RED_COLOR))
            itemView.token_name.text = token.name
            itemView.token_balance.text = "${token.balance * 10.toDouble().pow(-18)} ${token.name}"
        }

    }
}