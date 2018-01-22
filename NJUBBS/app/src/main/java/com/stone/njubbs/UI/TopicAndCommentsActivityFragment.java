package com.stone.njubbs.UI;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.stone.njubbs.R;
import com.stone.njubbs.data.Article;
import com.stone.njubbs.data.BBSDataConverterFactory;
import com.stone.njubbs.data.NJUBBSApiStore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TopicAndCommentsActivityFragment extends Fragment {

    private static final String ARG_QUERY_URL = "query_url";
    private OnListFragmentInteractionListener mListener;
    private  String queryURl;
    private TopicAndCommentsAdapter mAdapter;
    private static RequestQueue mQueue;

    final LruCache<String, Bitmap> lruCache = new LruCache<>(10 * 1024 * 1024);
    ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
        @Override
        public void putBitmap(String key, Bitmap value) {
            lruCache.put(key, value);
        }

        @Override
        public Bitmap getBitmap(String key) {
            return lruCache.get(key);
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TopicAndCommentsActivityFragment() {
    }

    @SuppressWarnings("unused")
    public static TopicAndCommentsActivityFragment newInstance(String queryUrl, RequestQueue queue) {
        TopicAndCommentsActivityFragment fragment = new TopicAndCommentsActivityFragment();
        mQueue = queue;
        Bundle args = new Bundle();
        args.putString(ARG_QUERY_URL, queryUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            queryURl = getArguments().getString(ARG_QUERY_URL);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTopicAndComments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topic_and_comments, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new TopicAndCommentsAdapter(getContext(), new ArrayList<Article>(), mListener, imageCache);
            recyclerView.addItemDecoration(new DividerItemDecoration(
                    getActivity(), DividerItemDecoration.VERTICAL_LIST));
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Article item);
    }

    private void loadTopicAndComments() {
        NJUBBSApiStore.NJUBBSApi getTopicAndComments = new NJUBBSApiStore().buildGetArticleApi(BBSDataConverterFactory.CONVERTTER_GET_ARTICLE);
        Call<List<Article>> call = getTopicAndComments.getArticleData(queryURl);
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, retrofit2.Response<List<Article>> response) {
                mAdapter.setData(response.body());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
            }
        });
    }
}
