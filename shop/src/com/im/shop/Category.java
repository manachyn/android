package com.im.shop;

/**
 * A category (collection of products).
 */
public class Category {
    // how many products?
    final int ARTICLES_PER_CATEGORY = 20;

    // array of our products
    Product[] mProducts;

    /**
     * Create a category.
     *
     * The products are dynamically generated with fun and random nonsense.
     */
    public Category() {
        NonsenseGenerator ngen = new NonsenseGenerator();
        mProducts = new Product[ARTICLES_PER_CATEGORY];
        int i;
        for (i = 0; i < mProducts.length; i++) {
            mProducts[i] = new Product(ngen);
        }
    }

    /** Returns how many products exist in this category. */
    public int getProductCount() {
        return mProducts.length;
    }

    /** Gets a particular product by index. */
    public Product getProduct(int index) {
        return mProducts[index];
    }

    public String getBody() {
        return "Body";
    }
}
