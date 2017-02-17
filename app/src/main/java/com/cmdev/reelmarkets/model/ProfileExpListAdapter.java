package com.cmdev.reelmarkets.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cmdev.reelmarkets.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Alexa on 2/16/17.
 */

public class ProfileExpListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> profileHeaders;
    Map<String, List<String>> listContents;

    public ProfileExpListAdapter(Context context, List<String> profileHeaders, Map<String, List<String>> listContents) {
        this.context = context;
        this.profileHeaders = profileHeaders;
        this.listContents = listContents;
    }

    @Override
    public int getGroupCount() {
        //number of headers
        return profileHeaders.size();
    }

    @Override
    public int getChildrenCount(int i) {
        //number of items in list i
        return listContents.get(profileHeaders.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        //return header name
        return profileHeaders.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        //returns item in list at i1 from header i
        return listContents.get(profileHeaders.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String header = (String) getGroup(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_parent, null);
        }

        TextView txtParent = (TextView) view.findViewById(R.id.txtParent);
        txtParent.setText(header);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        String listContent = (String) getChild(i ,i1);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_child, null);
        }

        TextView txtChild = (TextView) view.findViewById(R.id.txtChild);
        txtChild.setText(listContent);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
