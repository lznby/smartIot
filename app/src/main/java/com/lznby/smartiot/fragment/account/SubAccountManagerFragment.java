package com.lznby.smartiot.fragment.account;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lznby.smartiot.R;
import com.lznby.smartiot.adapter.ChildAccountAdapter;
import com.lznby.smartiot.entity.ChildAccountEntity;
import com.lznby.smartiot.entity.ChildAccountInformationEventBus;
import com.lznby.smartiot.network.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lznby
 * @time 2018/6/12 9:18
 * Class Note:
 */
public class SubAccountManagerFragment extends Fragment {

    private String mUserInformation;
    private ChildAccountAdapter mChildAccountAdapter;
    private RecyclerView mRecyclerView;
    private List<ChildAccountEntity.DataBean> mChildAccountInformationEntities = new ArrayList<>();
    /**
     * add???
     */
    private static final String SUB_ACCOUNT_MANAGER_DIALOG = "SubAccountManagerDialog";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * EventBus 1.
         */
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_manager,container,false);

        //设置adapter
        mChildAccountAdapter = new ChildAccountAdapter(R.layout.cv_child_account_item);
        mRecyclerView = view.findViewById(R.id.rv_child_account);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mChildAccountAdapter);
        mChildAccountAdapter.setNewData(mChildAccountInformationEntities);


        mChildAccountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(getContext(),mChildAccountInformationEntities.get(position).getUserUsername(), Toast.LENGTH_SHORT).show();
                //创建并显示DialogFragment视图
                FragmentManager manager = getFragmentManager();
                //传值
                ChoseEventDialogFragment dialogFragment = ChoseEventDialogFragment.newInstance(mChildAccountInformationEntities.get(position));
                //这句干嘛用的不知道
                dialogFragment.show(manager, SUB_ACCOUNT_MANAGER_DIALOG);
            }
        });
        return view;
    }

    /**
     * EventBus 2.
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChildAccountInformationEventBus event) {
        //订阅
        //更新个人信息
        mUserInformation = event.getUserInformationEntity();
        ChildAccountEntity entity = Utility.handleChildAccountInformation(mUserInformation, getActivity());
        mChildAccountInformationEntities = null;
        if (entity != null) {
            mChildAccountInformationEntities = entity.getData();
            mChildAccountAdapter.setNewData(mChildAccountInformationEntities);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 3. EventBus解除
         */
        EventBus.getDefault().unregister(this);
    }
}
