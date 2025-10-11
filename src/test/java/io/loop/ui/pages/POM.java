package io.loop.ui.pages;

public class POM {

    private LoginPageDocuport loginPage;
    private BaseDocuportPage baseDocuportPage;
    private ReceivedDocsPage receivedDocsPage;
    private LeftNavigatePage leftNavigatePage;
    private InvitationsPage invitationsPage;
    private MyUploadsPage myUploadsPage;


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

    public ReceivedDocsPage getReceivedDocsPage() {
        if (receivedDocsPage == null){
            receivedDocsPage = new ReceivedDocsPage();
        }
        return receivedDocsPage;
    }
    public LeftNavigatePage getLeftNavigatePage() {
        if (leftNavigatePage == null){
            leftNavigatePage = new LeftNavigatePage();
        }
        return leftNavigatePage;
    }

    public InvitationsPage getInvitationsPage() {
        if (invitationsPage == null){
            invitationsPage = new InvitationsPage();
        }
        return invitationsPage;
    }
    public MyUploadsPage getMyUploadsPage() {
        if (myUploadsPage == null){
            myUploadsPage = new MyUploadsPage();
        }
        return myUploadsPage;
    }

}
