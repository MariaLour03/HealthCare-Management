package repository;

import model.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AppointmentRepositoryImpl {
    SessionFactory sessionFactory;

    // Constructor
    public AppointmentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createAppointment(Appointment appointment){
        try(Session session = this.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(appointment);
            transaction.commit();
        }
    }

    public Appointment getAppointmentById(int appointmentId){
        try(Session session = this.sessionFactory.openSession()){
            return session.get(Appointment.class, appointmentId);
        }
    }

    public void updateAppointment(Appointment appointment){
        try(Session session = this.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(appointment);
            transaction.commit();
        }
    }

    public void deleteAppointment(int appointmentId){
        try(Session session = this.sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, appointmentId);

            if (appointment != null){
                session.delete(appointment);
            }
            transaction.commit();
        }
    }

    public List<Appointment> getAllAppointments(){
        try(Session session = this.sessionFactory.openSession()){
            return session.createQuery("from Appointment", Appointment.class).list();
        }
    }
}

