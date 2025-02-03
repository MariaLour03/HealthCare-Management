package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@ToString(exclude = {"patients", "appointments","office"})
@Entity
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoctorID")
    private int doctorId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Specialty")
    private String specialty;

    @Column(name = "Email")
    private String email;

   // Relationship
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor",cascade = CascadeType.PERSIST)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name  = "DoctorID"),
            inverseJoinColumns = @JoinColumn(name = "PatientID")
    )
    private Set<Patient> patients = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "doctor",cascade = CascadeType.PERSIST)
    @JoinColumn(name = "OfficeID")
    private Office office;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor that = (Doctor) o;
        return doctorId == that.doctorId;    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId);
    }

}
