package dao;

import model.Appointment;

/**
 * Created by Stefan on 1/29/17.
 */
public interface IAppointmentDAO {
    public void insert( Appointment appointment );

    public Appointment findAppointmentById( int id );
}
