public class BankStatementBatchProcessor {
		
    private int processedCount = 0;
 
    public void process(List<StatementRecord> records) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
 
        for (StatementRecord record : records) {
            executor.submit(() -> {
                processRecord(record);
                incrementCount();   // <-- inconsistent in production(now it Fix)
            });
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
    }

	// FIX: synchronized prevents race condition
       private synchronized void incrementCount() {
          processedCount++;
    }
 
    public int getProcessedCount() {
        return processedCount;
    }
}
