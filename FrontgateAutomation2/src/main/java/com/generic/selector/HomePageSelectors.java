package com.generic.selector;

import com.generic.setup.cselector;

/**
 * RULE — Separate locators from page logic.
 * RULE — Prefer id > CSS. No XPath.
 *
 * These selectors are taken exactly from the original working HomePage.java.
 * Do NOT change them — they are proven to work on the Frontgate site.
 */
public class HomePageSelectors {

    // Taken from original: By.cssSelector("a[title='Frontgate LOGO']")
    public static final cselector logo =
        new cselector("css,a[title='Frontgate LOGO']");

    // Taken from original: By.id("my-account-button")
    public static final cselector myAccountButton =
        new cselector("id,my-account-button");

    // Taken from original: By.cssSelector("button[data-analytics-name='show_mini_cart']")
    public static final cselector myBagButton =
        new cselector("css,button[data-analytics-name='show_mini_cart']");

    // Taken from original: By.cssSelector("a[title='Sign In / Register']")
    public static final cselector signInLink =
        new cselector("css,a[title='Sign In / Register']");

    // Taken from original: By.cssSelector("div.c-list-tile__content-welcome")
    public static final cselector welcomeMessage =
        new cselector("css,div.c-list-tile__content-welcome");
}
