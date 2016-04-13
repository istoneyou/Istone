package com.stone.njubbs.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.stone.njubbs.R;
import com.stone.njubbs.Utils.UrlUtils;
import com.stone.njubbs.network.TopTenRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopTenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopTenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopTenFragment extends Fragment {

    private static RequestQueue mQueue;
    private ArrayList<Map<String, String>> mTopTenData = new ArrayList<>();

    private OnFragmentInteractionListener mListener;
    RecyclerView mList;
    TextView mTextView;

    public TopTenFragment() {
        // Required empty public constructor
    }


    public static TopTenFragment newInstance(RequestQueue queue) {
        TopTenFragment fragment = new TopTenFragment();
        mQueue = queue;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TopTenRequest topTenRequest = new TopTenRequest(Request.Method.GET, UrlUtils.URL_TOP_TEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("youlei", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("stone", error.toString());
                    }
                });
        mQueue.add(topTenRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_top_ten, container, false);
        mList = (RecyclerView) mView.findViewById(R.id.list);
        mTextView = (TextView) mView.findViewById(R.id.text);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] strs = {"1","2","3","4","5","6","7", "8", "9", "10", "11", "12", "13", "1","2","3","4","5","6","7", "8", "9", "10", "11", "12", "13", "1","2","3","4","5","6","7", "8", "9", "10", "11", "12", "13"};
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mList.setLayoutManager(mLayoutManager);
        MyAdapter adapter = new MyAdapter(strs);
        mList.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;

        // Provide a reference to the type of views that you are using
        // (custom viewholder)
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
            // set the view's size, margins, paddings and layout parameters
            TextView textView = (TextView) v.findViewById(android.R.id.text1) ;
            ViewHolder vh = new ViewHolder(textView);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
