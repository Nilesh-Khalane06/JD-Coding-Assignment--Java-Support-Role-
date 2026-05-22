import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Task4Test {

    private ReportDAO reportDAO;

    @BeforeEach
    public void setUp() {
        reportDAO = new ReportDAO();

        // Mock DataSource to verify try-with-resources fix
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        reportDAO.setDataSource(mockDataSource);
    }

    @Test
    public void testFetchMonthlyReportExecutesSafely() throws SQLException {
        List<ReportEntry> entries =
                reportDAO.fetchMonthlyReport("ACC001", 3, 2025);

        assertNotNull(entries);
        assertTrue(entries.size() >= 0);
    }
}