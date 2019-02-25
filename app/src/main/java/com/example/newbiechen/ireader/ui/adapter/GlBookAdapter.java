package com.example.newbiechen.ireader.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.newbiechen.ireader.model.bean.CollBookBean;
import com.example.newbiechen.ireader.ui.adapter.view.CollBookHolder;
import com.example.newbiechen.ireader.ui.adapter.view.GlBookHolder;
import com.example.newbiechen.ireader.ui.base.adapter.BaseViewHolder;
import com.example.newbiechen.ireader.ui.base.adapter.IViewHolder;
import com.example.newbiechen.ireader.utils.SharedPreUtils;
import com.example.newbiechen.ireader.widget.adapter.WholeAdapter;

public class GlBookAdapter extends CollBookAdapter {
    private boolean shelfGrid;
    private int TYPE_ITEM_GRID=1;

    public GlBookAdapter(Boolean shelfGrid) {
        this.shelfGrid= shelfGrid;
    }

    public void setShelfType(Boolean shelfGrid){
        this.shelfGrid=shelfGrid;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType==TYPE_ITEM_GRID? createGridViewHolder(parent,viewType):super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (position < mHeaderList.size()){
            type = mHeaderList.get(position).hashCode();
        }
        else if (position < mHeaderList.size() + getItemSize()){
            type = shelfGrid? TYPE_ITEM_GRID:TYPE_ITEM;
        }
        else {
            int pos = position - mHeaderList.size() - getItemSize();
            type = mFooterList.get(pos).hashCode();
        }
        return type;
    }

    private RecyclerView.ViewHolder createGridViewHolder(ViewGroup parent,int viewType) {
        IViewHolder<CollBookBean> viewHolder = new GlBookHolder();

        View view = viewHolder.createItemView(parent);
        //初始化
        RecyclerView.ViewHolder holder = new BaseViewHolder(view, viewHolder);
        return holder;
    }

}
