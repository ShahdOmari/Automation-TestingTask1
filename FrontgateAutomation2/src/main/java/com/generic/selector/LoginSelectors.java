package com.generic.selector;

import com.generic.setup.cselector;


public class LoginSelectors {

    public static final cselector emailField =
        new cselector("css,input[type='email']");

    public static final cselector passwordField =
        new cselector("css,input[type='password']");

    public static final cselector loginButton =
        new cselector("css,button.login-button");

    
    public static final cselector cookieBannerCloseBtn =
        new cselector("css,button[aria-label='Close']",
                      "css,#close-pc-btn-handler");

    public static final cselector emailError =
        new cselector("css,#userLogonForm #error-div-logonId",
                      "css,#signInError");

    public static final cselector passwordError =
        new cselector("css,#userLogonForm #error-div-logonPassword",
                      "css,.password-error");

    public static final cselector generalError =
        new cselector("css,#gwt-error-placement-div",
                      "css,.t-login__form-error");

    public static final cselector forgotPasswordLink =
        new cselector("id,login-forgot-password-link");

    public static final cselector forgotPasswordEmailInput =
        new cselector("id,forgottenPwd.email");

    public static final cselector forgotPasswordSubmitBtn =
        new cselector("id,Reset Password");

    public static final cselector forgotPasswordSuccessMsg =
        new cselector("id,success-password-text");

    public static final cselector forgotPasswordEmailError =
        new cselector("id,forgottenPwd.email-error");

    public static final cselector welcomeMsg =
        new cselector("id,welcome-text");

    public static final cselector welcomeMsgMobile =
        new cselector("id,mobile-user");

    public static final cselector signInNavigation =
        new cselector("css,#login a",
                      "css,.my-account-controls-modal a");

    public static final cselector mainMenuButton =
        new cselector("id,toggle-mobile-menu");
}
