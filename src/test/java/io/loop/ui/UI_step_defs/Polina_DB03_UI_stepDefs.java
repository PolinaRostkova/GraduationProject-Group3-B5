package io.loop.ui.UI_step_defs;

import io.cucumber.java.en.Given;
import io.loop.ui.pages.POM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Polina_DB03_UI_stepDefs {
    POM pages = new POM();
    private static final Logger logger = LogManager.getLogger(Polina_DB03_UI_stepDefs.class);
    @Given("user validates document is uploaded with the name {string}")
    public void user_validates_document_is_uploaded_with_the_name(String documentName) {
        pages.getMyUploadsPage().validateUploadDocument(documentName);
        logger.info("Document is uploaded with the name " + documentName);
    }
}
