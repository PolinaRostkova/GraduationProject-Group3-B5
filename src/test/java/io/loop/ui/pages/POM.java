package io.loop.ui.pages;

public class POM {

    private LoginPageDocuport loginPage;
    private BaseDocuportPage baseDocuportPage;
    private LeftNavigatePage leftNavigatePage;
    private Form1099Page form1099Page;
    private ReceivedDocsPage receivedDocsPage;
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
    public Form1099Page getForm1099Page() {
        if (form1099Page == null){
            form1099Page = new Form1099Page();
        }
        return form1099Page;
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
