package com.example.mauricioarce.addressbook;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Mauricio Arce on 30/06/2015.
 */
public class ContactsAdapter extends BaseExpandableListAdapter {

    private final List<ItemsGroup> contacts;
    private LayoutInflater inflater;
    private Activity activity;

    public ContactsAdapter(Activity activity, List<ItemsGroup> contacts) {
        this.activity = activity;
        this.contacts = contacts;
        inflater = this.activity.getLayoutInflater();
    }

    @Override
    public int getGroupCount() {
        return contacts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return contacts.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return contacts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return contacts.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items_layout, null);
        }
        ItemsGroup group = (ItemsGroup) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(group.getText());
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView textView;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.subitems_layout, null);
        }
        textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
}
