import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class Task3Test {

    @Test
    void processedCount_shouldMatchNumberOfRecords() throws Exception {
        BankStatementBatchProcessor processor =
                new BankStatementBatchProcessor();

        // Prepare test data
        List<StatementRecord> records = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            records.add(new StatementRecord());
        }

        // Execute
        processor.process(records);

        // Assert
        assertEquals(
                records.size(),
                processor.getProcessedCount(),
                "Processed count should equal number of records"
        );
    }
}