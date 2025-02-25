package model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@ToString(exclude = {"doctors","appointments"})
@Entity
@Table(name = "Patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientID")
    private int patientId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "DateOfBirth")
    private String dateOfBirth;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    // Relationship
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient",cascade = CascadeType.PERSIST)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "patients" ,cascade=CascadeType.PERSIST)
      private Set<Doctor> doctors = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient that = (Patient) o;
        return patientId == that.patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);

    }
}

