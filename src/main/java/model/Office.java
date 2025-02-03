package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@Entity
@ToString(exclude = {"doctor"})
@Table(name = "Offices")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfficeID")
    private int officeId;

    @Column(name = "Location" )
    private String location;

    @Column(name = "Phone")
    private String phone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DoctorID")
    private Doctor doctor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office that = (Office) o;
        return officeId == that.officeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(officeId);
    }

}
