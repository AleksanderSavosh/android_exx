package com.savosh.exx.exx14.fragment;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.felipecsl.asymmetricgridview.*;
import com.savosh.exx.R;

import java.util.ArrayList;
import java.util.List;

public class AsymmetricLayoutFragment extends Fragment {

    AsymmetricRecyclerView asymmetricRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.exx14_asymmetric_layout_fragment, container, false);

        asymmetricRecyclerView = (AsymmetricRecyclerView) v.findViewById(R.id.exx14_asymmetric_layout);

        List<DemoItem> demoItems = new ArrayList<DemoItem>(){{
            add(new DemoItem(2));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(2));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(2));
            add(new DemoItem(1));
            add(new DemoItem(1));
            add(new DemoItem(2));
        }};

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(demoItems);
        asymmetricRecyclerView.setRequestedColumnCount(3);
//        asymmetricRecyclerView.addItemDecoration(
//                new SpacesItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_padding)));
        asymmetricRecyclerView.setAdapter(new AsymmetricRecyclerViewAdapter<>(getActivity(), asymmetricRecyclerView, adapter));

        return v;
    }

    class RecyclerViewAdapter extends AGVRecyclerViewAdapter<ViewHolder> {
        private final List<DemoItem> items;

        public RecyclerViewAdapter(List<DemoItem> items) {
            this.items = items;
        }

        @Override
        public AsymmetricItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(viewGroup);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            //not need
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(ViewGroup parent) {
            super(parent);
            imageView = (ImageView) LayoutInflater
                    .from(parent.getContext()).inflate(R.layout.exx14_square_image_view, parent, false);
        }
    }

    class DemoItem implements AsymmetricItem {

        private int span = 1;

        public DemoItem(int span) {
            this.span = span;
        }

        @Override
        public int getColumnSpan() {
            return span;
        }

        @Override
        public int getRowSpan() {
            return span;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }

}
