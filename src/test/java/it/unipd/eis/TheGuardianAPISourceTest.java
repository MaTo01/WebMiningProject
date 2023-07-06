package it.unipd.eis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TheGuardianAPISourceTest {
    private static final String FILE_PATH = "./Storage";
    private Source source;

    @BeforeEach
    public void setup() {
        source = new TheGuardianAPISource();
    }

    @Test
    public void testDownloadArticles() {

    }
}
