package com.stone.njubbs.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.stone.njubbs.R;
import com.stone.njubbs.Utils.UrlUtils;

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
    private ArrayList<Map<String, Object>> mTopTenData = new ArrayList<Map<String, Object>>();

    private OnFragmentInteractionListener mListener;
    ListView mList;

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
        final StringRequest topTenRequest = new StringRequest(UrlUtils.URL_TOP_TEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response.substring(response.indexOf(',') + 1, response.length() - 1);
                        try {
                            JSONObject myJsonObject = new JSONObject(s);
                            JSONArray mJsonArray = new JSONArray(myJsonObject.getString("tp"));
                            mTopTenData.clear();
                            Map<String, Object> map;
                            for(int i = 0; i< mJsonArray.length(); i++) {
                                map = new HashMap<String, Object>();
                                map.put("board", mJsonArray.getJSONObject(i).getString("b"));
                                map.put("title", mJsonArray.getJSONObject(i).getString("t"));
                                map.put("file", mJsonArray.getJSONObject(i).getString("f"));
                                mTopTenData.add(map);
                            }
                            final StringRequest mRequest = new StringRequest(String.format(UrlUtils.BBS_URL_FORMAT, mTopTenData.get(1).get("board"), mTopTenData.get(1).get("file")),
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Log.v("stone", response);
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Log.v("stone", error.toString());
                                        }
                                    });
                            mQueue.add(mRequest);
                        } catch (JSONException e) {
                        }

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
        mList = (ListView) mView.findViewById(R.id.list);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] strs = {"1","2","3","4","5","6","7", "8", "9", "10", "11", "12", "13", "1","2","3","4","5","6","7", "8", "9", "10", "11", "12", "13"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext() , android.R.layout.simple_expandable_list_item_1, strs);
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
}
