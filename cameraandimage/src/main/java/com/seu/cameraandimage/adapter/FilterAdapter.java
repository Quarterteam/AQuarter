package com.seu.cameraandimage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.seu.as.filter.helper.MagicFilterType;
import com.seu.cameraandimage.R;
import com.seu.cameraandimage.helper.FilterTypeHelper;

/**
 * Created by why8222 on 2016/3/17.
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder>{
    
    private MagicFilterType[] filters;
    private Context context;
    private int selected = 0;

    public FilterAdapter(Context context, MagicFilterType[] filters) {
        this.filters = filters;
        this.context = context;
    }

    @Override
    public FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.filter_item_layout,
//                parent, false);
//        FilterHolder viewHolder = new FilterHolder(view);
//        viewHolder.thumbImage = (ImageView) view
//                .findViewById(R.id.filter_thumb_image);
//        viewHolder.filterName = (TextView) view
//                .findViewById(R.id.filter_thumb_name);
//        viewHolder.filterRoot = (FrameLayout)view
//                .findViewById(R.id.filter_root);
//        viewHolder.thumbSelected = (FrameLayout) view
//                .findViewById(R.id.filter_thumb_selected);
//        viewHolder.thumbSelected_bg = view.
//                findViewById(R.id.filter_thumb_selected_bg);
//        return viewHolder;
        return new FilterHolder(LayoutInflater.from(context).inflate(R.layout.filter_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(FilterHolder holder,final int position) {
        holder.thumbImage.setImageResource(FilterTypeHelper.FilterType2Thumb(filters[position]));
        holder.filterName.setText(FilterTypeHelper.FilterType2Name(filters[position]));
        holder.filterName.setBackgroundColor(context.getResources().getColor(
                FilterTypeHelper.FilterType2Color(filters[position])));
        if(position == selected){
            holder.thumbSelected.setVisibility(View.VISIBLE);
            holder.thumbSelected_bg.setBackgroundColor(context.getResources().getColor(
                    FilterTypeHelper.FilterType2Color(filters[position])));
            holder.thumbSelected_bg.setAlpha(0.7f);
        }else {
            holder.thumbSelected.setVisibility(View.GONE);
        }

//        holder.filterRoot.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if(selected == position)
//                    return;
//                int lastSelected = selected;
//                selected = position;
//                notifyItemChanged(lastSelected);
//                notifyItemChanged(position);
//                onFilterChangeListener.onFilterChanged(filters[position]);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return filters == null ? 0 : filters.length;
    }

    class FilterHolder extends RecyclerView.ViewHolder {
        ImageView thumbImage;
        TextView filterName;
        FrameLayout thumbSelected;
        FrameLayout filterRoot;
        View thumbSelected_bg;
        int position = -1;

        public FilterHolder(View itemView) {
            super(itemView);
            thumbImage = (ImageView) itemView.findViewById(R.id.filter_thumb_image);
            filterName = (TextView) itemView.findViewById(R.id.filter_thumb_name);
            filterRoot = (FrameLayout)itemView.findViewById(R.id.filter_root);
            thumbSelected = (FrameLayout) itemView.findViewById(R.id.filter_thumb_selected);
            thumbSelected_bg = itemView.findViewById(R.id.filter_thumb_selected_bg);

            filterRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();
                    if(selected == position)
                        return;
                    int lastSelected = selected;
                    selected = position;
                    notifyItemChanged(lastSelected);
                    notifyItemChanged(position);
                    onFilterChangeListener.onFilterChanged(filters[position]);
                }
            });
        }
    }

    public interface onFilterChangeListener{
        void onFilterChanged(MagicFilterType filterType);
    }

    private onFilterChangeListener onFilterChangeListener;

    public void setOnFilterChangeListener(onFilterChangeListener onFilterChangeListener){
        this.onFilterChangeListener = onFilterChangeListener;
    }
}