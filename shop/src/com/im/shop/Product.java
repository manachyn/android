package com.im.shop;

/**
 * A product.
 *
 * An product consists of a headline and a body. In this example app, product text is dynamically
 * generated nonsense.
 */
public class Product {
    // How many sentences in each paragraph?
    final int SENTENCES_PER_PARAGRAPH = 20;

    // How many paragraphs in each article?
    final int PARAGRAPHS_PER_ARTICLE = 5;

    // Headline and body
    String mHeadline, mBody;

    /**
     * Create a news article with randomly generated text.
     * @param ngen the nonsense generator to use.
     */
    public Product(NonsenseGenerator ngen) {
        mHeadline = ngen.makeHeadline();

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h1>" + mHeadline + "</h1>");
        int i;
        for (i = 0; i < PARAGRAPHS_PER_ARTICLE; i++) {
            sb.append("<p>").append(ngen.makeText(SENTENCES_PER_PARAGRAPH)).append("</p>");
        }

        sb.append("</body></html>");
        mBody = sb.toString();
    }

    /** Returns the headline. */
    public String getHeadline() {
        return mHeadline;
    }

    /** Returns the article body (HTML)*/
    public String getBody() {
        return mBody;
    }
}
