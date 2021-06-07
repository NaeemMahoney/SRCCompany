//Na'eem Mahoney
//218190751
//Project 3
//Group 13

package RegistrationCompanyGUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Variables for storing the companies information
public class Company {
    private String url = "jdbc:mysql://localhost:3306/project";
    private String user = "root";
    private String password = "";
    private String companyName;
    private String companyNum;
    private String companyEmail;
    private String companyPass;

    public Company(String companyName, String companyNum, String companyEmail, String companyPass) {
        this.companyName = companyName;
        this.companyNum = companyNum;
        this.companyEmail = companyEmail;
        this.companyPass = companyPass;
    }

    //Getter and Setter for CompanyName
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    //Getter and Setter for CompanyNum
    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    //Getter and Setter for CompanyEmail
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    //Getter and Setter for CompanyPass
    public String getCompanyPass() {
        return companyPass;
    }

    public void setCompanyPass(String companyPass) {
        this.companyPass = companyPass;
    }

    //Operation that saves the information
    public void save() {
        //Opening connection to the databsae
        Connection connection = null;   // manage connections
        PreparedStatement statement = null;     // query statement
        int x;
        String sql = "INSERT INTO company_table VALUES(?, ?, ?, ?)";

        //Adding the variables to the SQL statement
        try {
            connection = DriverManager.getConnection(url, user, password);

            statement = connection.prepareStatement(sql);
            statement.setString(1, companyName);
            statement.setString(2, companyNum);
            statement.setString(3, companyEmail);
            statement.setString(4, companyPass);

            x = statement.executeUpdate();
            if (x > 0) {
                JOptionPane.showMessageDialog(null, "Business details have been added.");
                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Could not add Business details.");
            }
        }
        catch(SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Error: " + sqlException.getMessage());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
            try {
                if (connection != null)
                    connection.close();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
