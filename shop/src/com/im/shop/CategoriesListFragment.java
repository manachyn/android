package com.im.shop;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment that displays the categories list.
 *
 * This Fragment displays a list with the categories.
 * When an item is selected, it notifies the configured listener that a category was selected.
 */
public class CategoriesListFragment extends ListFragment implements OnItemClickListener {
    // The list of categories that we are displaying
    List<String> mCategoriesList = new ArrayList<String>();

    // The list adapter for the list we are displaying
    ArrayAdapter<String> mListAdapter;

    // The listener we are to notify when a category is selected
    OnCategorySelectedListener mCategorySelectedListener = null;

    /**
     * Represents a listener that will be notified of headline selections.
     */
    public interface OnCategorySelectedListener {
        /**
         * Called when a given category is selected.
         * @param index the index of the selected category.
         */
        public void onCategorySelected(int index);
    }

    /**
     * Default constructor required by framework.
     */
    public CategoriesListFragment() {
        super();
    }

    @Override
    public void onStart() {
        super.onStart();
        setListAdapter(mListAdapter);
        getListView().setOnItemClickListener(this);
        loadCategories();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new ArrayAdapter<String>(getActivity(), R.layout.category_item,
                mCategoriesList);
    }

    /**
     * Sets the listener that should be notified of category selection events.
     * @param listener the listener to notify.
     */
    public void setOnCategorySelectedListener(OnCategorySelectedListener listener) {
        mCategorySelectedListener = listener;
    }

    /**
     * Load and display the categories.
     */
    public void loadCategories() {
        mCategoriesList.clear();
//        int i;
//        Category cat = ShopSource.getInstance().getCategory(categoryIndex);
//        for (i = 0; i < cat.getArticleCount(); i++) {
//            mHeadlinesList.add(cat.getArticle(i).getHeadline());
//        }

        int i;
        for (i = 0; i < Ipsum.Categories.length; i++) {
            mCategoriesList.add(Ipsum.Categories[i]);
        }
        mListAdapter.notifyDataSetChanged();
    }

    /**
     * Handles a click on a headline.
     *
     * This causes the configured listener to be notified that a headline was selected.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mCategorySelectedListener) {
            mCategorySelectedListener.onCategorySelected(position);
        }
    }

    /** Sets choice mode for the list
     *
     * @param selectable whether list is to be selectable.
     */
    public void setSelectable(boolean selectable) {
        if (selectable) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
        else {
            getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
        }
    }
}
