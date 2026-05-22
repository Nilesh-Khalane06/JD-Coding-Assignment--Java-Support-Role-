public class DocumentValidator {

    private static final Logger log =
            LoggerFactory.getLogger(DocumentValidator.class);

    public ValidationResult validate(Document doc) {
        try {
            // FIX 1: Expected validation failure — no exception, no stack trace
            if (doc == null) {
                log.warn("Validation failed: document is null");
                return null;
            }

            String content = doc.extractContent();

            // FIX 2: Expected validation failure — no exception, no stack trace
            if (content == null || content.isEmpty()) {
                log.warn("Validation failed: empty content");
                return null;
            }

            return runValidationRules(content);

        } catch (Exception e) {
            // FIX 3: Unexpected runtime error — log with stack trace and rethrow
            log.error("Unexpected error during validation", e);
            throw e;
        }
    }

    public void validateBatch(List<Document> docs) {
        for (Document doc : docs) {
            try {
                ValidationResult r = validate(doc);

                // FIX 4: Prevent NullPointerException
                if (r != null && r.isValid()) {
                    saveResult(r);
                }

            } catch (Exception e) {
                // FIX 4: Do not swallow exceptions silently
                log.error("Error while validating document in batch", e);
            }
        }
    }
}