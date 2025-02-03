package model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Objects;


@Data
@Entity
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private int appointmentId;

    @Column(name = "AppointmentDate")
    private String appointmentDate;

    @Column(name = "Notes")
    private String notes;

    // Relationship
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DoctorID",referencedColumnName = "DoctorID",nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PatientID",referencedColumnName = "PatientID",nullable = false)
    private Patient patient;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId);
    }
}
