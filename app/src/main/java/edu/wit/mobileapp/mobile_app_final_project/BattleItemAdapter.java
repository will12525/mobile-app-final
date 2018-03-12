package edu.wit.mobileapp.mobile_app_final_project;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by camasok on 2/16/2018.
 */

public class BattleItemAdapter extends  RecyclerView.Adapter<BattleItemAdapter.ItemViewHolder>{
    public static class ItemViewHolder extends  RecyclerView.ViewHolder
    {
        CardView cv;
        TextView itemName;
        TextView damageDice;
        TextView property;

        ImageView itemImage;
        ImageButton imageButton;



        ItemViewHolder(View itemView){
         super(itemView);

         cv = (CardView) itemView.findViewById(R.id.cardView);
         itemName = (TextView) itemView.findViewById(R.id.itemText);
         damageDice = (TextView) itemView.findViewById(R.id.damageDiceView);
         property = (TextView) itemView.findViewById(R.id.propertyView);

        itemImage = (ImageView) itemView.findViewById(R.id.itemIcon);
        imageButton = (ImageButton) itemView.findViewById(R.id.itemRoll);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence text = "The Roll for " + itemName.getText() + " goes here: \n" + damageDice.getText();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(imageButton.getContext(), text, duration);
                toast.show();
            }
        });

        }
    }

    List<BattleItem> battleItemListing;
    BattleItemAdapter(List<BattleItem> battleItemListing)
    {
        this.battleItemListing = battleItemListing;
    }


    @Override
    public int getItemCount()
    {
        return battleItemListing.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attack_item, viewGroup, false);
        ItemViewHolder ivh = new ItemViewHolder(v);

        return ivh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i)
    {
        itemViewHolder.itemImage.setImageResource(battleItemListing.get(i).photoId);
        itemViewHolder.damageDice.setText(battleItemListing.get(i).damageDice);
        itemViewHolder.property.setText(battleItemListing.get(i).property);
        itemViewHolder.itemName.setText(battleItemListing.get(i).name);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
