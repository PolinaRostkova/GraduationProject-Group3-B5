package io.loop.db.DB_step_defs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.utilities.DB_Utility;
import org.junit.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertTrue;

public class Halina_DB01_UserRoleAndStatusValidation_stepDefs {


    // QUERY FOR ROLES, EMAIL,STATUS
    @When("I query the database for expected test users")
    public void iQueryTheDatabaseForAllTestUsers() {

        String query =
                "SELECT\n" +
                        "    r.name  AS role_name,\n" +
                        "    u.email_address,\n" +
                        "    u.is_active\n" +
                        "FROM identity.users u\n" +
                        "         JOIN identity.user_roles ur ON u.id = ur.user_id\n" +
                        "         JOIN identity.roles r ON ur.role_id = r.id\n" +
                        "WHERE u.email_address IN (\n" +
                        "                          'b1g2_client@gmail.com',\n" +
                        "                          'b1g2_advisor@gmail.com',\n" +
                        "                          'b1g2_supervisor@gmail.com',\n" +
                        "                          'b1g2_employee@gmail.com'\n" +
                        "    );";
        DB_Utility.runQuery(query);
    }

    @Then("I validate that all users exist, are active, and have valid roles")
    public void iValidateThatAllUsersExistAndAreActive() {

        // Store the result as List <Map<String, String>>
        List<Map<String, String>> result = DB_Utility.getAllRowAsListOfMap();

        // Check the return result
        assertTrue("The Query is empty", !result.isEmpty());


        // Check that number of roles = 4
        Assert.assertEquals(" Expected 4 users in DB!", 4, result.size());

        // Validate the roles
        List<String> validRoles = Arrays.asList("Client", "Advisor", "Supervisor", "Employee");

        for (Map<String, String> row : result) {
            String email = row.get("email_address").toLowerCase();
            String isActive = row.get("is_active").toLowerCase();
            String role = row.get("role_name");

            System.out.println("ROLE: " + role + " --> EMAIL: " + email + " --> ACTIVE: " + isActive);

            // Check that email is active
            assertTrue(" " + email + " is not active!",
                    isActive.equalsIgnoreCase("true") ||
                            isActive.equalsIgnoreCase("t"));


          // Check that role is active
            boolean isValidRole = false;
            for (String valid : validRoles) {
                if (valid.equalsIgnoreCase(role)) {
                    isValidRole = true;
                    break;
                }
            }
            assertTrue("Role is not valid " + email + role, isValidRole);
        }
    }
        }

