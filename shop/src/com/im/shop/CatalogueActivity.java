package com.im.shop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;

/**
 * Main activity: shows headlines list and articles, if layout permits.
 *
 * This is the main activity of the application. It can have several different layouts depending
 * on the SDK version, screen size and orientation. The configurations are divided in two large
 * groups: single-pane layouts and dual-pane layouts.
 *
 * In single-pane mode, this activity shows a list of headlines using a {@link HeadlinesFragment}.
 * When the user clicks on a headline, a separate activity (a {@link ArticleActivity}) is launched
 * to show the news article.
 *
 * In dual-pane mode, this activity shows a {@HeadlinesFragment} on the left side and an
 * {@ArticleFragment} on the right side. When the user selects a headline on the left, the
 * corresponding article is shown on the right.
 *
 * If an Action Bar is available (large enough screen and SDK version 11 or up), navigation
 * controls are shown in the Action Bar (whether to show tabs or a list depends on the layout).
 * If an Action Bar is not available, a regular image and button are shown in the top area of
 * the screen, emulating an Action Bar.
 */
public class CatalogueActivity extends FragmentActivity
        implements CategoriesListFragment.OnCategorySelectedListener {

    // Whether or not we are in dual-pane mode
    boolean mIsDualPane = false;

    // The fragment where the headlines are displayed
    CategoriesListFragment mCategoriesListFragment;

    // The fragment where the article is displayed (null if absent)
    CategoryFragment mCategoryFragment;

    // The category index currently being displayed
    int mCatIndex = 0;
    Category mCurrentCat;

    // List of category titles
    final String CATEGORIES[] = { "Top Stories", "Politics", "Economy", "Technology" };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue_layout);

        // find our fragments
        mCategoriesListFragment = (CategoriesListFragment) getSupportFragmentManager().findFragmentById(
                R.id.categories_list);
        mCategoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentById(
                R.id.category);

        // Determine whether we are in single-pane or dual-pane mode by testing the visibility
        // of the category view.
        View categoryView = findViewById(R.id.category);
        mIsDualPane = categoryView != null && categoryView.getVisibility() == View.VISIBLE;

        // Register ourselves as the listener for the categories list fragment events.
        mCategoriesListFragment.setOnCategorySelectedListener(this);

        // Set up the Action Bar (or not, if one is not available)
        //int catIndex = savedInstanceState == null ? 0 : savedInstanceState.getInt("catIndex", 0);
        //setUpActionBar(mIsDualPane, catIndex);

        // Set up headlines fragment
        mCategoriesListFragment.setSelectable(mIsDualPane);
        restoreSelection(savedInstanceState);

        // Set up the category button (shown if an Action Bar is not available)
//        Button catButton = (Button) findViewById(R.id.categorybutton);
//        if (catButton != null) {
//            catButton.setOnClickListener(this);
//        }
    }

    /** Restore category selection from saved state. */
    void restoreSelection(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (mIsDualPane) {
                int catIndex = savedInstanceState.getInt("catIndex", 0);
                mCategoriesListFragment.setSelection(catIndex);
                onCategorySelected(catIndex);
            }
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        restoreSelection(savedInstanceState);
    }

    /** Sets up Action Bar (if present).
     *
     * @param showTabs whether to show tabs (if false, will show list).
     * @param selTab the selected tab or list item.
     */
//    public void setUpActionBar(boolean showTabs, int selTab) {
//        if (Build.VERSION.SDK_INT < 11) {
//            // No action bar for you!
//            // But do not despair. In this case the layout includes a bar across the
//            // top that looks and feels like an action bar, but is made up of regular views.
//            return;
//        }
//
//        android.app.ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//
//        // Set up a CompatActionBarNavHandler to deliver us the Action Bar nagivation events
//        CompatActionBarNavHandler handler = new CompatActionBarNavHandler(this);
//        if (showTabs) {
//            actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);
//            int i;
//            for (i = 0; i < CATEGORIES.length; i++) {
//                actionBar.addTab(actionBar.newTab().setText(CATEGORIES[i]).setTabListener(handler));
//            }
//            actionBar.setSelectedNavigationItem(selTab);
//        }
//        else {
//            actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_LIST);
//            SpinnerAdapter adap = new ArrayAdapter<String>(this, R.layout.actionbar_list_item,
//                    CATEGORIES);
//            actionBar.setListNavigationCallbacks(adap, handler);
//        }
//
//        // Show logo instead of icon+title.
//        actionBar.setDisplayUseLogoEnabled(true);
//    }

    @Override
    public void onStart() {
        super.onStart();
        setCategory(0);
    }

    /** Sets the displayed news category.
     *
     * This causes the headlines fragment to be repopulated with the appropriate headlines.
     */
    void setCategory(int categoryIndex) {
        mCatIndex = categoryIndex;
        mCurrentCat = new Category();
        mCategoriesListFragment.loadCategories();

        // If we are displaying the article on the right, we have to update that too
        if (mIsDualPane) {
            mCategoryFragment.displayCategory(mCurrentCat);
        }
    }

    /** Called when a headline is selected.
     *
     * This is called by the HeadlinesFragment (via its listener interface) to notify us that a
     * headline was selected in the Action Bar. The way we react depends on whether we are in
     * single or dual-pane mode. In single-pane mode, we launch a new activity to display the
     * selected article; in dual-pane mode we simply display it on the article fragment.
     *
     * @param index the index of the selected headline.
     */
    @Override
    public void onCategorySelected(int index) {
        mCatIndex = index;
        if (mIsDualPane) {
            // display it on the article fragment
            //mCategoryFragment.displayCategory(mCurrentCat);
        }
        else {
            String res = ShopApiClient.apiGet();
            Log.d("myshop", "Result " + res);
            // use separate activity
//            Intent i = new Intent(this, CategoryActivity.class);
//            i.putExtra("catIndex", mCatIndex);
//            startActivity(i);
        }
    }

    /** Save instance state. Saves current category index. */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("catIndex", mCatIndex);
        super.onSaveInstanceState(outState);
    }

//    /**
//     * Called when the activity is first created.
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.catalogue_layout);
//        /*DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int height = metrics.heightPixels;
//        int width = metrics.widthPixels;
//        Log.d("myshop", "Width=" + width);
//        Log.d("myshop", "Height=" + height);
//        float density  = getResources().getDisplayMetrics().density;
//        float dpHeight = height / density;
//        float dpWidth  = width / density;
//        Log.d("myshop", "dpWidth=" + dpWidth);
//        Log.d("myshop", "dpHeight=" + dpHeight);*/
//    }

}
