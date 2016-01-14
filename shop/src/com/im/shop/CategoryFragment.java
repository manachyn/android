package com.im.shop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Fragment that displays a category.
 */
public class CategoryFragment extends Fragment {
    // The webview where we display the article (our only view)
    WebView mWebView;

    // The category we are to display
    Category mCategory = null;

    // Parameterless constructor is needed by framework
    public CategoryFragment() {
        super();
    }

    /**
     * Sets up the UI. It consists if a single WebView.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mWebView = new WebView(getActivity());
        loadWebView();
        return mWebView;
    }

    /**
     * Displays a particular category.
     *
     * @param category the article to display
     */
    public void displayCategory(Category category) {
        mCategory = category;
        loadWebView();
    }

    /**
     * Loads article data into the webview.
     *
     * This method is called internally to update the webview's contents to the appropriate
     * article's text.
     */
    void loadWebView() {
        if (mWebView != null) {
            mWebView.loadData(mCategory == null ? "" : mCategory.getBody(), "text/html",
                        "utf-8");
        }
    }
}
