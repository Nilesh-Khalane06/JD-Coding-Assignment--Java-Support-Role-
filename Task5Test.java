import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Task5Test {

    private DocumentValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DocumentValidator();
    }

    // FIX 1: Null document should NOT throw exception
    @Test
    void testNullDocumentReturnsNull() {
        ValidationResult result = validator.validate(null);
        assertNull(result);
    }

    // FIX 2: Empty content should NOT throw exception
    @Test
    void testEmptyContentReturnsNull() {
        Document doc = Mockito.mock(Document.class);
        Mockito.when(doc.extractContent()).thenReturn("");

        ValidationResult result = validator.validate(doc);
        assertNull(result);
    }

    // FIX 3: Unexpected runtime exception should be rethrown
    @Test
    void testUnexpectedExceptionIsRethrown() {
        Document doc = Mockito.mock(Document.class);
        Mockito.when(doc.extractContent())
               .thenThrow(new RuntimeException("Crash"));

        assertThrows(RuntimeException.class, () -> validator.validate(doc));
    }

    // FIX 4: Batch validation should not throw NullPointerException
    @Test
    void testValidateBatchHandlesNullResultsSafely() {
        Document validDoc = Mockito.mock(Document.class);
        Mockito.when(validDoc.extractContent()).thenReturn("valid content");

        Document invalidDoc = Mockito.mock(Document.class);
        Mockito.when(invalidDoc.extractContent()).thenReturn("");

        List<Document> docs = Arrays.asList(validDoc, invalidDoc);

        assertDoesNotThrow(() -> validator.validateBatch(docs));
    }
}