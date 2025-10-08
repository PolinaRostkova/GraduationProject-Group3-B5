package io.loop.ui.pages;

public class POM {

    private LoginPageDocuport loginPage;
    private BaseDocuportPage baseDocuportPage;
    private Jane_LogOutPage logOutPage;
    private Jane_LeftNavigatePage leftNavigatePage;


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

    public Jane_LogOutPage getLogOutPage() {
        if (logOutPage == null){
            logOutPage = new Jane_LogOutPage();
        }
        return logOutPage;
    }

    public Jane_LeftNavigatePage getLeftNavigatePage() {
        if (leftNavigatePage == null){
            leftNavigatePage = new Jane_LeftNavigatePage();
        }
        return leftNavigatePage;
    }


}
