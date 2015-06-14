package com.shahidul.flickr.wallpaper.fragment;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.shahidul.flickr.wallpaper.R;
import com.shahidul.flickr.wallpaper.app.WallpaperApplication;
import com.shahidul.flickr.wallpaper.activity.MainActivity;
import com.shahidul.flickr.wallpaper.util.PhotoUtils;


public class SearchImageGridFragment extends ImageGridFragment implements
		OnCloseListener, MainActivity.HandleSearchCloseListener, OnQueryTextListener {

	private static final String TAG = SearchImageGridFragment.class
			.getSimpleName();
	private SearchView mSearchView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "OnCreateView");
		View view = inflater.inflate(R.layout.searc_image_grid, null);
		mGridView = (GridView) view.findViewById(R.id.imageGrid);
		mSearchView = (SearchView) view.findViewById(R.id.search_view);
		mSearchView.setSubmitButtonEnabled(true);
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setOnCloseListener(this);
		return view;
	}

	@Override
	public boolean onClose() {
		mSearchView.setVisibility(View.GONE);
		return true;
	}

	@Override
	public boolean onQueryTextChange(String arg0) {
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg) {
		WallpaperApplication app = (WallpaperApplication) getActivity()
				.getApplication();

		mUrl = PhotoUtils.getURLByText(arg);
		mPhotos.clear();
		mLoding = true;
		mCurrentPageNumber = 1;
		if (mCurrentAsyncTask != null && !mCurrentAsyncTask.isCancelled())
			mCurrentAsyncTask.cancel(true);
		loadPhotos();
		mLoding = false;
		mSearchView.setVisibility(View.GONE);
		return true;
	}

	@Override
	public void handleClose() {
		if (mSearchView.getVisibility() == View.VISIBLE)
			mSearchView.setVisibility(View.GONE);
		else
			mSearchView.setVisibility(View.VISIBLE);
	}

}
