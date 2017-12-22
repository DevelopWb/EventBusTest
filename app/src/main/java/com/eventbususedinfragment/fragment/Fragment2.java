package com.eventbususedinfragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventbususedinfragment.bean.MsgToSend;
import com.eventbususedinfragment.R;

import org.greenrobot.eventbus.EventBus;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment implements View.OnClickListener {

    private View view;
    /**
     * Hello blank fragment
     */
    private TextView mTv2;
    private Context mContext;

    /**
     * Hello blank fragment
     */

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv2:
                EventBus.getDefault().post("fragment2");
                EventBus.getDefault().post(new MsgToSend("我是fragment2，我给你发个信息"));
                break;
        }
    }

    private void initView(View view) {
        mTv2 = (TextView) view.findViewById(R.id.tv2);
        mTv2.setOnClickListener(this);
    }
}
