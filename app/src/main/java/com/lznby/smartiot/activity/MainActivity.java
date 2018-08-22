package com.lznby.smartiot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lznby.smartiot.R;
import com.lznby.smartiot.entity.UserInformationEntity;
import com.lznby.smartiot.entity.UserInformationEventBus;
import com.lznby.smartiot.fragment.AccountManagerFragment;
import com.lznby.smartiot.fragment.FragmentType;
import com.lznby.smartiot.fragment.PushMessageFragment;
import com.lznby.smartiot.fragment.ServiceCenterFragment;
import com.lznby.smartiot.fragment.account.AddChildAccountDialogFragment;
import com.lznby.smartiot.fragment.account.SubAccountManagerFragment;
import com.lznby.smartiot.network.AppState;
import com.lznby.smartiot.network.RequestHandling;
import com.lznby.smartiot.network.RequestType;
import com.lznby.smartiot.network.Utility;
import com.lznby.smartiot.utils.CacheUtils;
import com.lznby.smartiot.utils.Constants;
import com.lznby.smartiot.utils.GlideApp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Lznby
 * @time 2018/6/7 13:32
 * Class Note: 主界面MainActivity
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * MainActivity中填充的碎片
     */
    private AccountManagerFragment mAccountManagerFragment;
    private PushMessageFragment mPushMessageFragment;
    private ServiceCenterFragment mServiceCenterFragment;
    private SubAccountManagerFragment mSubAccountManagerFragment;

    private LinearLayout mHeaderLayout;
    private CircleImageView mHeader_image;
    private TextView tv_username;
    private TextView tv_company_name;

    private Menu menu;
    private MenuItem childAccount;

    private String mUserInformation;

    private static final String ADD_CHILD_ACCOUNT_DIALOG = "AddChildAccountDialog";

    private boolean optionMenuOn = true;  //标示是否要显示optionmenu
    private Menu aMenu;         //获取optionmenu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * EventBus 1.
         */
        EventBus.getDefault().register(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //为侧边菜单栏添加头部信息布局，并设置头部控件信息,填充式，不需要再xml文件中写出
        //LinearLayout headerLayout = (LinearLayout) navigationView.inflateHeaderView(R.layout.nav_header_main);

        //1.为侧边菜单栏添加头部信息布局，并设置头部控件信息,xml式，需要再xml文件中写出。
        //2.在NavigationView的xml中添加如下属性：app:headerLayout="@layout/nav_header_main",其中nav_head_main为所添加的布局文件
        //3.获取头部文件信息,即nav_head_main

        mHeaderLayout = (LinearLayout) navigationView.getHeaderView(0);

        //设置用户公司头像图片
        mHeader_image = mHeaderLayout.findViewById(R.id.profile_image);
        GlideApp.with(this)
                .load(Constants.LOGO_PAGER_URL)
                //缺省情况图片
                .placeholder(R.drawable.iot)
                .into((ImageView) mHeader_image);

        //获取Menu中的item
        //        Menu menu = navigationView.getMenu();
        //        MenuItem nav_camera = menu.getItem(1);
        //        nav_camera.setVisible(false);

        //默认主页面服务中心界面
        initFragment(mServiceCenterFragment, FragmentType.SERVICE_CENTER_TYPE);

        /**
         * 请求个人账号信息
         */
        RequestHandling.requestUserInformation(Constants.USER_INFORMATION_URL, this, null, RequestType.GET,CacheUtils.getString(this,AppState.LOGIN_COOKIE));


        //侧边菜单个人及公司信息
        tv_username = mHeaderLayout.findViewById(R.id.tv_username);
        tv_company_name = mHeaderLayout.findViewById(R.id.tv_company_name);

        menu = navigationView.getMenu();
        childAccount = menu.getItem(1);






    }

    /**
     * EventBus 2.
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserInformationEventBus event) {
        //订阅
        //更新个人信息
        mUserInformation= event.getUserInformationEntity();
        UserInformationEntity entity =  Utility.handleUserInformationResponse(mUserInformation,this);
        if (entity.getData()!=null){
            tv_username.setText(entity.getData().getUserUsername());
            tv_company_name.setText(entity.getData().getApplicationFlag());
            if (entity.getData().getUserStatus()== RequestType.USER_TYPE_NORMAL) {
                //普通用户设置子账号管理隐藏
                childAccount.setVisible(false);
                //隐藏添加账号
                optionMenuOn = false;
            }
        }
    }

    /**
     * 隐藏
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        for (int i = 0; i < menu.size(); i++){
            menu.getItem(i).setVisible(false);
            menu.getItem(i).setEnabled(false);
        }
        aMenu = menu;
        checkOptionMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 判断是否隐藏
     */
    private void checkOptionMenu(){
        if(null != aMenu){
            if(optionMenuOn){
                for (int i = 0; i < aMenu.size(); i++){
                    aMenu.getItem(i).setVisible(true);
                    aMenu.getItem(i).setEnabled(true);
                }
            }else{
                for (int i = 0; i < aMenu.size(); i++){
                    aMenu.getItem(i).setVisible(false);
                    aMenu.getItem(i).setEnabled(false);
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 3. EventBus解除
         */
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //添加子账户

            android.support.v4.app.FragmentManager manager = this.getSupportFragmentManager();
            AddChildAccountDialogFragment dialogFragment = new AddChildAccountDialogFragment();
            dialogFragment.show(manager,ADD_CHILD_ACCOUNT_DIALOG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //填充账号管理界面
            initFragment(mAccountManagerFragment, FragmentType.ACCOUNT_MANAGER_TYPE);
        } else if (id == R.id.nav_gallery) {
            //填充子账号管理界面
            initFragment(mSubAccountManagerFragment, FragmentType.SUB_ACCOUNT_MANAGER_TYPE);
            //请求子账号信息
            RequestHandling.requestUserAccountList(Constants.USER_LIST_URL, this, null, RequestType.GET,CacheUtils.getString(this,AppState.LOGIN_COOKIE));

        } else if (id == R.id.nav_slideshow) {
            //填充推送消息界面
            initFragment(mPushMessageFragment, FragmentType.PUSH_MESSAGE_TYPE);

        } else if (id == R.id.nav_manage) {
            //填充服务中心界面
            initFragment(mServiceCenterFragment, FragmentType.SERVICE_CENTER_TYPE);
            /**
             * 服务基本信息请求
             */
            RequestHandling.requestApplicationInformation(Constants.APPLICATION_INFORMATION_URL, this, null, RequestType.GET, CacheUtils.getString(this, AppState.LOGIN_COOKIE));


            /**
             * 所有板信息请求
             */
            RequestHandling.requestBoardsInformation(Constants.BOARDS_INFORMATION_URL, this, null, RequestType.GET, CacheUtils.getString(this, AppState.LOGIN_COOKIE));

        } else if (id == R.id.nav_share) {
            //联系客服
            Toast.makeText(this, "联系客服", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            //注销并返回登录界面
            CacheUtils.putBoolean(this, AppState.LOGIN_STATE, false);
            /**
             * 解除个人推送绑定
             */
            JPushInterface.deleteAlias(this,3);

            /**
             * 解除分组推送绑定
             */
            UserInformationEntity date= Utility.handleUserInformationResponse(CacheUtils.getString(this,AppState.USER_INFORMATION),this);
            Set<String> tags = new HashSet<String>();
            tags.add(date.getData().getApplicationFlag()+"");
            JPushInterface.deleteTags(this,4, tags);

            /**
             * 注销请求
             */
            RequestHandling.requestLogeOut(Constants.USER_LOGE_OUT_URL, this, null, RequestType.GET, CacheUtils.getString(this, AppState.LOGIN_COOKIE));


            //退回登录界面
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * 显示与填充相对应的fragment
     *
     * @param fragment
     * @param type
     */
    private void initFragment(Fragment fragment, int type) {

        //记录当前的fragment
        Fragment checkedFragment;

        //1.开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //2.第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (fragment == null) {
            if (type == FragmentType.ACCOUNT_MANAGER_TYPE) {
                mAccountManagerFragment = new AccountManagerFragment();
                checkedFragment = mAccountManagerFragment;
            } else if (type == FragmentType.PUSH_MESSAGE_TYPE) {
                mPushMessageFragment = new PushMessageFragment();
                checkedFragment = mPushMessageFragment;
            } else if (type == FragmentType.SUB_ACCOUNT_MANAGER_TYPE) {
                mSubAccountManagerFragment = new SubAccountManagerFragment();
                checkedFragment = mSubAccountManagerFragment;
            } else {
                mServiceCenterFragment = new ServiceCenterFragment();
                checkedFragment = mServiceCenterFragment;
            }
            //若当前选中碎片未添加到事务中,则创建碎片对象并添加到事务中
            transaction.add(R.id.fragment_container, checkedFragment);
        } else {
            //若当前选中碎片已添加到事务中,则将当前碎片赋值给checkedFragment
            checkedFragment = fragment;
        }

        //3.隐藏所有fragment
        hideFragment(transaction);

        //4.显示需要显示的fragment
        transaction.show(checkedFragment);

        //5.提交事务
        transaction.commit();
    }

    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {

        if (mAccountManagerFragment != null) {
            transaction.hide(mAccountManagerFragment);
        }
        if (mPushMessageFragment != null) {
            transaction.hide(mPushMessageFragment);
        }
        if (mServiceCenterFragment != null) {
            transaction.hide(mServiceCenterFragment);
        }
        if (mSubAccountManagerFragment != null) {
            transaction.hide(mSubAccountManagerFragment);
        }
    }
}
