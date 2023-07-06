package it.unipd.eis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CSVSourceTest {
    private static final String FILE_PATH = "./Storage";
    private Source source;

    @BeforeEach
    public void setup() {
        source = new CSVSource();
    }

    @Test
    public void testDownloadArticles() {

    }
}