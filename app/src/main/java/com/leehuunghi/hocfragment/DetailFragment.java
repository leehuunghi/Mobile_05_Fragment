package com.leehuunghi.hocfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends android.app.Fragment implements FragmentCallbacks, View.OnClickListener {

    MainActivity main;
    TextView txtDetail;
    Button btn1,btn2,btn3,btn4;


    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String strArg) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( " Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout layout_detail = (LinearLayout) inflater.inflate(R.layout.fragment_detail, null);
        txtDetail=layout_detail.findViewById(R.id.txtDetail);
        btn1=layout_detail.findViewById(R.id.btn1);
        btn2=layout_detail.findViewById(R.id.btn2);
        btn3=layout_detail.findViewById(R.id.btn3);
        btn4=layout_detail.findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        return layout_detail;
    }
    Integer currentChoose=new Integer(0);
    @Override
    public void onMsgFromMainToFragment(String strValue) {
        String content=new String();
        if(strValue=="+1") {
            currentChoose = currentChoose == 9? 1 : currentChoose+1;
            strValue=currentChoose.toString();
        }
        else if(strValue=="-1") {
            currentChoose = currentChoose == 1 ? 9 : currentChoose - 1;
            strValue=currentChoose.toString();
        }
        switch (strValue)
        {
            case "1":
            {
                content = "1.Lê Hữu Nghị\n2.Trần Thị Nhã";
                break;
            }
            case "2":
            {
                content = "1.Đỗ Hữu Nhật Nguyên\n2.Trịnh Thiên Yến Nhi";
                break;
            }
            case "3":
            {
                content = "1.Nguyễn Thùy Nhiên\n2.Lý Văn Nam";
                break;
            }
            case "4":
            {
                content = "1.Đỗ Thành Nhơn\n2.Nguyễn Mỹ Linh";
                break;
            }
            case "5":
            {
                content = "1.Châu Hoàng Long\n2.Bùi Thị Thanh Nguyệt";
                break;
            }
            case "6":
            {
                content="1.Trần Hạo Nam\n2.Lê Quang Nhật";
                break;
            }
            case "7":
            {
                content="1.Trần Thị Sáu Râu\n2.Nguyễn Thị Tèo";
                break;
            }
            case "8":
            {
                content="1.Trần Văn Tủn\n2.Công Tằng Tôn Nữ Long Lanh Lóng Lánh Ánh Bình Minh";
                break;
            }
            case "9": {
                content = "1.Trần Thị Đẹp Trai\n2.Lê Thị Đẹp Gái";
                break;
            }
        }
        txtDetail.setText(content);
        currentChoose=Integer.parseInt(strValue.toString());
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btn1.getId())
        {
            main.onMsgFromFragToMain("DETAIL-FRAG","head");
        }
        if(view.getId()==btn2.getId())
        {
            main.onMsgFromFragToMain("DETAIL-FRAG","next");

        }
        if(view.getId()==btn3.getId())
        {
            main.onMsgFromFragToMain("DETAIL-FRAG","previous");

        }
        if(view.getId()==btn4.getId())
        {
            main.onMsgFromFragToMain("DETAIL-FRAG","end");

        }
    }
}
