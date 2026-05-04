package com.generic.selector;

import com.generic.setup.cselector;


public class HomePageSelectors {

    public static final cselector logo =
        new cselector("css,a[title='Frontgate LOGO']");

    public static final cselector myAccountButton =
        new cselector("id,my-account-button");

    public static final cselector myBagButton =
        new cselector("css,button[data-analytics-name='show_mini_cart']");

    public static final cselector signInLink =
        new cselector("css,a[title='Sign In / Register']");

    public static final cselector welcomeMessage =
        new cselector("css,div.c-list-tile__content-welcome");
}
