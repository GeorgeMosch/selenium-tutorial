package alerts;

import base.BaseTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FIleUploadTests extends BaseTests {

    @Test
    public void testFileUpload() {
        var uploadPage = homePage.clickFileUpload();
        uploadPage.uploadFile("/Users/George/dev/Java_Test/selenium-tutorial/src/test/resources/browserBinaries/chromedriverMac");
        assertEquals(uploadPage.getUploadedFiles(), "chromedriverMac", "Uploaded file");
    }
}
