package dao.impl;

import dao.IAppointmentDAO;
import model.Appointment;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Created by Stefan on 1/29/17.
 */
public class AppointmentDAO implements IAppointmentDAO {

    private DataSource dataSource;

    public void setDataSource( DataSource dataSource ) {
        this.dataSource = dataSource;
    }


    public void insert(Appointment appointment) {
        String sql = "INSERT INTO APPOINTMENT " +
                     "(DATE, TIME, DURATION, EMPLOYEE, PATIENT_NAME, PATIENT_PHONE)" +
                     "VALUES ( ?, ?, ?, ?, ?, ?  );";

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appointment.getDate().toString());
            ps.setString(2, appointment.getTime());
            ps.setString(3, String.valueOf(appointment.getDuration()));
            ps.setString(4, appointment.getEmployee());
            ps.setString(5, appointment.getPatientName());
            ps.setString(6, appointment.getPatientPhone());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public Appointment findAppointmentById( int id ) {
        String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, custId);
            Customer customer = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("CUST_ID"),
                        rs.getString("NAME"),
                        rs.getInt("Age")
                );
            }
            rs.close();
            ps.close();
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

        return null;
    }
}
