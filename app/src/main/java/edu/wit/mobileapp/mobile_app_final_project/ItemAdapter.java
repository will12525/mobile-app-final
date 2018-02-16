package edu.wit.mobileapp.mobile_app_final_project;

import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by camasok on 2/16/2018.
 */

public class ItemAdapter extends  RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    public static class ItemViewHolder extends  RecyclerView.ViewHolder
    {
        CardView cv;
        TextView itemName;
        TextView damageDice;
        TextView property;

        ImageView itemImage;

        ItemViewHolder(View itemView){
         super(itemView);

         cv = (CardView) itemView.findViewById(R.id.itemIcon);
         itemName = (TextView) itemView.findViewById(R.id.itemText);
         damageDice = (TextView) itemView.findViewById(R.id.itemRoll);
         property = (TextView) itemView.findViewById(R.id.propertyView);

         itemImage = (ImageView) itemView.findViewById(R.id.itemIcon);
        }
    }

    List<Item> itemListing;
    ItemAdapter(List<Item> itemListing)
    {
        this.itemListing = itemListing;
    }

    @Override
    public int getItemCount()
    {
        return itemListing.size();
    }

    @Override
    public ItemViewHolder onCreateViewHOlder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attack_item, viewGroup, false);
        ItemViewHolder ivh = new ItemViewHolder(v);

        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i)
    {
        itemViewHolder.itemImage.setImageResource(items.get(i).imageId);
    }
}
