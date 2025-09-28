package io.loop.ui.pages;

public class POM {

    private LoginPageDocuport loginPage;
    private BaseDocuportPage baseDocuportPage;


    public LoginPageDocuport getLoginPage() {
        if (loginPage == null){
            loginPage = new LoginPageDocuport();
        }
        return loginPage;
    }


    public BaseDocuportPage getBaseDocuportPage() {
        if (baseDocuportPage == null){
            baseDocuportPage = new BaseDocuportPage();
        }
        return baseDocuportPage;
    }
}
