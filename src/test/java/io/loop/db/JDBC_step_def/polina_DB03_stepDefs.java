package io.loop.db.JDBC_step_def;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.loop.utilities.DB_Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class polina_DB03_stepDefs {
    private static final Logger LOGGER = LogManager.getLogger(polina_DB03_stepDefs.class);

    @Then("the document record with name {string} should exist in DB with:")
    public void the_document_record_with_name_should_exist_in_db_with(String fileName, DataTable records) throws SQLException {
        Map<String, String> expected = records.asMap(String.class, String.class);

        String sql = """
   SELECT display_name, is_deleted
                  FROM DOCUMENT.DOCUMENTS
                  WHERE display_name = ? AND is_deleted = false
                  ORDER BY creation_time DESC NULLS LAST, id DESC
""";

        try (Connection cx = DB_Utility.docuportCreateConnection();
             PreparedStatement ps = cx.prepareStatement(sql)) {

           ps.setString(1, fileName);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    Assert.fail("No document row found for display_name = '" + fileName + "'");
                }

                // Map current row -> String values
                Map<String, String> dbRow = currentRowToStringMap(rs);

                // Compare only the fields provided in the DataTable
                for (Map.Entry<String, String> e : expected.entrySet()) {
                    String col = e.getKey();          // e.g., "display_name", "is_deleted"
                    String exp = e.getValue();
                    String act = dbRow.get(col);
                    Assert.assertEquals("Mismatch for column '" + col + "'" + act, exp, act);
                }
            }
        } catch (SQLException e) {
            Assert.fail("DB query failed: " + e.getMessage());
        }

        LOGGER.info("The document record with name {} has been verified in DB", fileName);
    }

    @Then("I delete document {string} from DB")
    public void i_delete_document_from_db(String fileName) {
        String del = "DELETE FROM document.documents WHERE display_name = ?";
        try (Connection cx = DB_Utility.docuportCreateConnection();
             PreparedStatement ps = cx.prepareStatement(del)) {
            ps.setString(1, fileName);
            ps.executeUpdate();
        } catch (SQLException e) {
            Assert.fail("Cleanup delete failed: " + e.getMessage());
        }

        LOGGER.info("The document record with name {} has been deleted in DB", fileName);
    }

    private Map<String, String> currentRowToStringMap(ResultSet rs) throws SQLException {
        Map<String, String> row = new LinkedHashMap<>();
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        for (int i = 1; i <= cols; i++) {
            String label = meta.getColumnLabel(i);   // uses alias
            Object val = rs.getObject(i);
            row.put(label, val == null ? null : String.valueOf(val));
        }
        return row;
    }
}
