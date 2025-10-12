package io.loop.ui.pages;

public class POM {

    private LoginPageDocuport loginPage;
    private BaseDocuportPage baseDocuportPage;

    // HALINA Left Navigate
    private Halina_LeftModuleNavigatePage  halina_LeftModuleNavigatePage;


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

    // HALINA Left Navigate
public Halina_LeftModuleNavigatePage getHalina_LeftModuleNavigatePage() {
        if (halina_LeftModuleNavigatePage == null){
            halina_LeftModuleNavigatePage = new Halina_LeftModuleNavigatePage();
        }
        return halina_LeftModuleNavigatePage;
}
}
