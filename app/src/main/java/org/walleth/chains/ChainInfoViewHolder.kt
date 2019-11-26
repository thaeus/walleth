package org.walleth.chains

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_network_definition.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.walleth.data.chaininfo.ChainInfo
import org.walleth.data.networks.CurrentAddressProvider
import org.walleth.tokens.prepareFaucetButton

class ChainInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {

    fun bind(chainInfo: ChainInfo, onClickAction: (entry: ChainInfo) -> Unit,
             onInfoClick: (entry: ChainInfo) -> Unit) {

        itemView.network_title.text = chainInfo.name
        itemView.info_indicator.visibility = if (chainInfo.infoURL.isNotEmpty()) View.VISIBLE else View.INVISIBLE

        val currentAddressProvider: CurrentAddressProvider by inject()
        itemView.faucet_indicator.prepareFaucetButton(chainInfo, currentAddressProvider, postAction = {
            onClickAction.invoke(chainInfo)
        })

        itemView.setOnClickListener {
            onClickAction.invoke(chainInfo)
        }

        itemView.info_indicator.setOnClickListener {
            onInfoClick.invoke(chainInfo)
        }

    }

}