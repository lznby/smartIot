package com.lznby.smartiot.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lznby.smartiot.R;
import com.lznby.smartiot.entity.BaseBoardEntity;

/**
 * @author Lznby
 * @time 2018/6/20 13:06
 * Class Note: 所有板的适配器
 */
public class AllBoardAdapter extends BaseQuickAdapter<BaseBoardEntity,BaseViewHolder> {

    public AllBoardAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseBoardEntity item) {

        TextView tvState = helper.getView(R.id.tv_board_state);

        String  state = "";

        if (item.getBoardStatus()==0){
            state = "正常";
            tvState.setTextColor(Color.GREEN);
        } else {
            state = "异常";
            tvState.setTextColor(Color.RED);
        }

       helper.setText(R.id.tv_board_count,item.getBoardName())
                .setText(R.id.tv_board_state,state)
                .setText(R.id.tv_board_update_time,item.getBoardTime())
                .setText(R.id.tv_board_information,item.getBoardDescription()+"");





    }
}
