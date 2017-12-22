package com.eventbususedinfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.eventbususedinfragment.fragment.Fragment1;
import com.eventbususedinfragment.fragment.Fragment2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment1 fragment1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            fragment1 = new Fragment1();
            fragment2 = new Fragment2();
        } else {
            fragment1 = (Fragment1) fragmentManager.getFragment(savedInstanceState, "Fragment1");
            fragment2 = (Fragment2) fragmentManager.getFragment(savedInstanceState, "Fragment2");
        }
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main1);
        initFragmentSelected(0);


    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void FragmentButtonClicked(String str) {
        switch (str) {
            case "fragment1":
                Toast.makeText(getApplicationContext(), "fragment1点击", Toast.LENGTH_LONG).show();
                initFragmentSelected(1);//展示fragment2界面
                break;
            case "fragment2":
                Toast.makeText(getApplicationContext(), "fragment2点击", Toast.LENGTH_LONG).show();
                initFragmentSelected(0);//展示fragment1界面
                break;

            default:
                break;
        }

    }

    /**
     * 初始化fragment
     *
     * @param i
     */
    private void initFragmentSelected(int i) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hindFragments(fragmentTransaction);
        switch (i) {
            case 0:
                if (!fragment1.isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragment1, "navitionFragment1");
                }
                fragmentTransaction.show(fragment1);
                break;
            case 1:
                if (!fragment2.isAdded()) {
                    fragmentTransaction.add(R.id.fl, fragment2, "navitionFragment1");
                }
                fragmentTransaction.show(fragment2);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    /**
     * 隐藏fragment
     *
     * @param fragmentTransaction
     */
    private void hindFragments(FragmentTransaction fragmentTransaction) {
        if (fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }
    }

}
