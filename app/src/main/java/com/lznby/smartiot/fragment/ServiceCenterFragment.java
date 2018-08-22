package com.lznby.smartiot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lznby.smartiot.R;
import com.lznby.smartiot.activity.SensorActivity;
import com.lznby.smartiot.adapter.AllBoardAdapter;
import com.lznby.smartiot.entity.AllBoardsInformationEntity;
import com.lznby.smartiot.entity.AllBoardsInformationEventBus;
import com.lznby.smartiot.entity.BaseBoardEntity;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestHandling;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.network.Utility;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lznby
 * @time 2018/6/12 9:47
 * Class Note: 服务信息界面碎片
 */
public class ServiceCenterFragment extends Fragment {

    private String mUserInformation;
    private TextView tv_all_boards;
    private TextView tv_error_boards;
    private AllBoardAdapter mAllBoardAdapter;
    private RecyclerView mRecyclerView;

    private List<BaseBoardEntity> mAllBoardInformationEntities = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
         * 服务基本信息请求
         */
        RequestHandling.requestApplicationInformation(Constants.APPLICATION_INFORMATION_URL, getActivity(), null, RequestType.GET, CacheUtils.getString(getContext(), AppState.LOGIN_COOKIE));


        /**
         * 所有板信息请求
         */
        RequestHandling.requestBoardsInformation(Constants.BOARDS_INFORMATION_URL, getActivity(), null, RequestType.GET, CacheUtils.getString(getContext(), AppState.LOGIN_COOKIE));


        /**
         * EventBus 1.
         */
        EventBus.getDefault().register(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_center, container, false);
        tv_all_boards = view.findViewById(R.id.tv_all_count);
        tv_error_boards = view.findViewById(R.id.tv_error_count);

        //设置adapter
        mAllBoardAdapter = new AllBoardAdapter(R.layout.cv_board_item);
        mRecyclerView = view.findViewById(R.id.rv_board_override);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAllBoardAdapter);
        mAllBoardAdapter.setNewData(mAllBoardInformationEntities);


        mAllBoardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //转跳单块板界面
                //Toast.makeText(getContext(), mAllBoardInformationEntities.get(position).getBoardName(), Toast.LENGTH_SHORT).show();
                //                Intent intent = new Intent(ListCardActivity.this,DetailedActivity.class);
                //                intent.putExtra("hydrant",mAllBoardInformationEntities.get(position).getBoardName());
                //                startActivity(intent);
                /**
                 * 请求单块板所有传感器信息
                 */
                Intent intent = new Intent(getContext(), SensorActivity.class);
                intent.putExtra("boardId",mAllBoardInformationEntities.get(position).getBoardId());
                startActivity(intent);
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
    public void onMessageEvent(AllBoardsInformationEventBus event) {
        //订阅
        //板信息
        mUserInformation = event.getUserInformationEntity();
        AllBoardsInformationEntity entity = Utility.handleAllBoardsInformation(mUserInformation, getActivity());
        mAllBoardInformationEntities = new ArrayList<>();
        if (entity != null) {
            if (entity.getData() != null) {
                if (entity.getData().getNormalBoards() != null || entity.getData().getAbnormalBoards() != null) {
                    if (entity.getData().getNormalBoards() != null) {
                        for (int i = 0; i < entity.getData().getNormalBoards().size(); i++) {
                            BaseBoardEntity baseBoardEntity = new BaseBoardEntity();
                            baseBoardEntity.setBoardId(entity.getData().getNormalBoards().get(i).getBoardId());
                            baseBoardEntity.setApplicationFlag(entity.getData().getNormalBoards().get(i).getApplicationFlag());
                            baseBoardEntity.setBoardName(entity.getData().getNormalBoards().get(i).getBoardName());
                            baseBoardEntity.setBoardStatus(entity.getData().getNormalBoards().get(i).getBoardStatus());
                            baseBoardEntity.setBoardTime(entity.getData().getNormalBoards().get(i).getBoardTime());
                            baseBoardEntity.setBoardDescription(entity.getData().getNormalBoards().get(i).getBoardDescription()+"");
                            mAllBoardInformationEntities.add(baseBoardEntity);
                        }
                    }
                    if (entity.getData().getAbnormalBoards() != null) {
                        for (int i = 0; i < entity.getData().getAbnormalBoards().size(); i++) {
                            BaseBoardEntity baseBoardEntity = new BaseBoardEntity();
                            baseBoardEntity.setBoardId(entity.getData().getAbnormalBoards().get(i).getBoardId());
                            baseBoardEntity.setApplicationFlag(entity.getData().getAbnormalBoards().get(i).getApplicationFlag());
                            baseBoardEntity.setBoardName(entity.getData().getAbnormalBoards().get(i).getBoardName());
                            baseBoardEntity.setBoardStatus(entity.getData().getAbnormalBoards().get(i).getBoardStatus());
                            baseBoardEntity.setBoardTime(entity.getData().getAbnormalBoards().get(i).getBoardTime());
                            baseBoardEntity.setBoardDescription(entity.getData().getAbnormalBoards().get(i).getBoardDescription()+"");
                            mAllBoardInformationEntities.add(baseBoardEntity);
                        }
                        tv_error_boards.setText(entity.getData().getAbnormalBoards().size() + "");
                    }
                }
                tv_all_boards.setText(mAllBoardInformationEntities.size() + "");
                mAllBoardAdapter.setNewData(mAllBoardInformationEntities);
            }
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
