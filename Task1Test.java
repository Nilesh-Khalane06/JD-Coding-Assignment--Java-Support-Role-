import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class Task1Test {

    @Test
    void shouldHandleNullAccountListSafely() {
        LoanService service = new LoanService();

        // Reproduces original bug: NullPointerException
        List<LoanAccount> result = service.getOverdueLoans(null);

        // Asserts fix works
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldIgnoreAccountsWithNullDueDate() {
        LoanService service = new LoanService();

        LoanAccount account = new LoanAccount();
        account.setDueDate(null);
        account.setOutstandingBalance(100);

        List<LoanAccount> result =
                service.getOverdueLoans(List.of(account));

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldExcludeZeroBalanceAccounts() {
        LoanService service = new LoanService();

        LoanAccount account = new LoanAccount();
        account.setDueDate(new Date(System.currentTimeMillis() - 86400000)); // yesterday
        account.setOutstandingBalance(0);

        List<LoanAccount> result =
                service.getOverdueLoans(List.of(account));

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOnlyValidOverdueAccounts() {
        LoanService service = new LoanService();

        LoanAccount valid = new LoanAccount();
        valid.setDueDate(new Date(System.currentTimeMillis() - 86400000));
        valid.setOutstandingBalance(500);

        LoanAccount notOverdue = new LoanAccount();
        notOverdue.setDueDate(new Date(System.currentTimeMillis() + 86400000));
        notOverdue.setOutstandingBalance(300);

        List<LoanAccount> result =
                service.getOverdueLoans(List.of(valid, notOverdue));

        assertEquals(1, result.size());
        assertEquals(valid, result.get(0));
    }
}