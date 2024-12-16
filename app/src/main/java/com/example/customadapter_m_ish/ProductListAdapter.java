package com.example.customadapter_m_ish;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<Product> mProductList;

    public ProductListAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_product_listview, null);
        }

        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvPrice = convertView.findViewById(R.id.tv_price);
        TextView tvDescription = convertView.findViewById(R.id.tv_description);

        Product product = mProductList.get(position);
        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice() + "$");
        tvDescription.setText(product.getDescription());

        convertView.setTag(product.getId());

        return convertView;
    }
}
