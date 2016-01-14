package com.im.shop;

/**
 * Source of strange and wonderful news.
 *
 * This singleton functions as the repository for the news we display.
 */
public class ShopSource {
    // the instance
    static ShopSource instance = null;

    // the category names
    final String[] CATEGORIES = { "Top Stories", "US", "Politics", "Economy" };

    // category objects, representing each category
    Category[] mCategory;

    /** Returns the singleton instance of this class. */
    public static ShopSource getInstance() {
        if (instance == null) {
            instance = new ShopSource();
        }
        return instance;
    }

    public ShopSource() {
        int i;
        mCategory = new Category[CATEGORIES.length];
        for (i = 0; i < CATEGORIES.length; i++) {
            mCategory[i] = new Category();
        }
    }

    /** Returns the list of categories. */
    public String[] getCategories() {
        return CATEGORIES;
    }

    /** Returns a category by index. */
    public Category getCategory(int categoryIndex) {
        return mCategory[categoryIndex];
    }
}
