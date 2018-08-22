package com.lznby.smartiot.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lznby.smartiot.R;
import com.lznby.smartiot.entity.ChildAccountEntity;

/**
 * @author Lznby
 * @time 2018/6/20 15:11
 * Class Note:子账号的适配器
 */
public class ChildAccountAdapter extends BaseQuickAdapter<ChildAccountEntity.DataBean,BaseViewHolder> {

    public ChildAccountAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChildAccountEntity.DataBean item) {
        helper.setText(R.id.tv_child_username,item.getUserUsername())
                .setText(R.id.tv_child_password,item.getUserPassword());
    }
}
