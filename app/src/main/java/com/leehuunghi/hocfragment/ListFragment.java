package com.leehuunghi.hocfragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends android.app.Fragment implements FragmentCallbacks, AdapterView.OnItemClickListener {
    ListView lvlList;
    MainActivity main;
    private String items[] = { "Lớp 1", "Lớp 2",
            "Lớp 3", "Lớp 4", "Lớp 5",
            "Lớp 6", "Lớp 7", "Lớp 8",
            "Lớp 9"};
    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( " Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }

    public static ListFragment newInstance(String strArg) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    View lastTouchedView = null;
    int lastPos=-1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // inflate res/layout_red.xml which includes a textview and a button
        LinearLayout view_layout_list = (LinearLayout) inflater.inflate(
                R.layout.fragment_list, null);
        // plumbing - get a reference to widgets in the inflated layout
        lvlList=view_layout_list.findViewById(R.id.lvlList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(main,
                android.R.layout.simple_list_item_1, items);
        lvlList.setAdapter(adapter);
        lvlList.setSelection(0);
        lvlList.smoothScrollToPosition(0);
        lvlList.setOnItemClickListener(this);
        return view_layout_list;
    }// onCreateView

    @Override
    public void onMsgFromMainToFragment(String strValue) {
        if (lastPos==-1 && (strValue == "previous"||strValue=="next")) Toast.makeText(this.main,"Mời chọn lớp lần đầu!",Toast.LENGTH_LONG ).show();
        else {
            if (lastTouchedView != null) lastTouchedView.setBackgroundColor(Color.WHITE);
            View rowView = null;
            switch (strValue) {
                case "head": {
                    lastPos = 0;
                    main.onMsgFromFragToMain("LIST-FRAG", "1");
                    break;
                }
                case "previous": {
                    main.onMsgFromFragToMain("LIST-FRAG", "+1");
                    lastPos = (lastPos+1)%9;
                    break;
                }
                case "next": {
                    main.onMsgFromFragToMain("LIST-FRAG", "-1");
                    lastPos=lastPos == 0 ? 8 : lastPos - 1;
                    break;
                }
                case "end": {
                    main.onMsgFromFragToMain("LIST-FRAG", "9");
                    lastPos = 8;
                    break;
                }
            }
            rowView = lvlList.getChildAt(lastPos);
            rowView.setBackgroundColor(Color.CYAN);
            lastTouchedView = rowView;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(lastTouchedView!=null) lastTouchedView.setBackgroundColor(Color.WHITE);
        view.setBackgroundColor(Color.CYAN);
        lastTouchedView = view;
        main.onMsgFromFragToMain("LIST-FRAG", ""+(i+1));
    }
}
