package com.example.newbiechen.ireader.ui.adapter.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newbiechen.ireader.R;
import com.example.newbiechen.ireader.model.bean.CollBookBean;
import com.example.newbiechen.ireader.model.local.BookRepository;
import com.example.newbiechen.ireader.ui.base.adapter.ViewHolderImpl;
import com.example.newbiechen.ireader.utils.Constant;
import com.example.newbiechen.ireader.utils.StringUtils;

/**
 * Created by newbiechen on 17-5-8.
 * CollectionBookView
 */

public class CollBookHolder extends ViewHolderImpl<CollBookBean>{

    private static final String TAG = "CollBookView";
    private ImageView mIvCover;
    private TextView mTvName;
    private TextView mTvChapter;
    private TextView mTvTime;
    private CheckBox mCbSelected;
    private ImageView mIvRedDot;
    private ImageView mIvTop;


    @Override
    public void initView() {
        mIvCover = findById(R.id.coll_book_iv_cover);
        mTvName = findById(R.id.coll_book_tv_name);
        mTvChapter = findById(R.id.coll_book_tv_chapter);
        mTvTime = findById(R.id.coll_book_tv_lately_update);
        mCbSelected = findById(R.id.coll_book_cb_selected);
        mIvRedDot = findById(R.id.coll_book_iv_red_rot);
        mIvTop = findById(R.id.coll_book_iv_top);
    }

    @Override
    public void onBind(CollBookBean value, int pos) {
        if (value.isLocal()){
            //本地文件的图片
            Glide.with(getContext())
                    .load(getLocalCover(value.getExt()))
                    .fitCenter()
                    .into(mIvCover);
        }
        else {
            //书的图片
            Glide.with(getContext())
                    .load(Constant.IMG_BASE_URL+value.getCover())
                    .placeholder(R.drawable.ic_book_loading)
                    .error(R.drawable.ic_load_error)
                    .fitCenter()
                    .into(mIvCover);
        }
        //书名
        mTvName.setText(value.getTitle());
        if (!value.isLocal()){
            //时间
            mTvTime.setText(StringUtils.
                    dateConvert(value.getUpdated(), Constant.FORMAT_BOOK_DATE)+":");
            mTvTime.setVisibility(View.VISIBLE);
        }
        else {
            mTvTime.setText("阅读进度:");
        }
        //章节
        mTvChapter.setText(value.getLastChapter());
        //我的想法是，在Collection中加一个字段，当追更的时候设置为true。当点击的时候设置为false。
        //当更新的时候，最新数据跟旧数据进行比较，如果更新的话，设置为true。
        if (value.isUpdate()){
            mIvRedDot.setVisibility(View.VISIBLE);
        }
        else {
            mIvRedDot.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_coll_book;
    }

    protected int getLocalCover(String ext){
        //chm,ebk,epub,pdf,txt,umd
        Context ctx=getContext();
        int resid=ctx.getResources().getIdentifier("cover_"+ext,"drawable",ctx.getPackageName());
        if(resid==0){
            resid=R.drawable.cover_default_new;
        }
        return resid;
    }
}
