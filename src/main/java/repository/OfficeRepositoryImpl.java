package repository;

import model.Doctor;
import model.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OfficeRepositoryImpl {

    SessionFactory sessionFactory;

    // Constructor
    public OfficeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createOffice(Office office) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(office);
            transaction.commit();
        }
    }

    public Office getOfficeById(int officeId) {
        try(Session session = sessionFactory.openSession()) {
           return session.get(Office.class, officeId);
        }
    }

    public void updateOffice(Office office) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(office);
            transaction.commit();
        }
    }

    public void deleteOffice(int officeId) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Office office = session.get(Office.class, officeId);
            //Doctor doctor =session.get(Doctor.class, office.getDoctor().getDoctorId());
            //doctor.setOffice(null);//use this as alternate

            session.delete(office);
            transaction.commit();
        }
    }

    public List<Office> getAllOffices() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("from Office", Office.class).list();

        }
    }

}
