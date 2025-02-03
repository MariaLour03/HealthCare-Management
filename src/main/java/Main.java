import model.Appointment;
import model.Doctor;
import model.Office;
import model.Patient;
import repository.AppointmentRepositoryImpl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.DoctorRepositoryImpl;
import repository.OfficeRepositoryImpl;
import repository.PatientRepositoryImpl;
import service.AppointmentService;
import service.DoctorService;
import service.OfficeService;
import service.PatientService;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        PatientService patientService = new PatientService(patientRepository);

        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        DoctorService doctorService = new DoctorService(doctorRepository);

        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        OfficeRepositoryImpl officeRepository = new OfficeRepositoryImpl(sessionFactory);
        OfficeService officeService = new OfficeService(officeRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Offices");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    managePatients(patientService, scanner);
                    break;
                case 2:
                    manageDoctors(doctorService, officeService,appointmentService,scanner);
                    break;
                case 3:
                    manageAppointments(appointmentService,patientService,doctorService, scanner);
                    break;
                case 4:
                    manageOffices(officeService,doctorService, scanner);
                    break;
                case 5:
                    System.out.println("Exiting Main Menu");
                    scanner.close();
                    sessionFactory.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void managePatients(PatientService patientService, Scanner scanner) {
        while (true) {
            System.out.println("\nPatient Management:");
            System.out.println("1. Create Patient");
            System.out.println("2. Read Patient by ID");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:  // Code to create a new patient
                    // Application calls the service layer, not the repository directly
                    Patient newPatient = new Patient();
                    System.out.print("Enter first name: ");
                    newPatient.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newPatient.setLastName(scanner.nextLine());
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    newPatient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newPatient.setEmail(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newPatient.setPhoneNumber(scanner.nextLine());

                    patientService.createPatient(newPatient);  // Use service here
                    System.out.println("Patient created successfully.");
                    break;
                case 2:   // Code to read patient details by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    Patient patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.println("Patient ID: " + patient.getPatientId());
                        System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
                        System.out.println("Date of Birth: " + patient.getDateOfBirth());
                        System.out.println("Email: " + patient.getEmail());
                        System.out.println("Phone: " + patient.getPhoneNumber());
                        System.out.println("Doctors: " + patient.getDoctors());
                        System.out.println("Appointments: " + patient.getAppointments());
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 3:  // Code to update patient details
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    patient = patientService.getPatientById(patientId);  // Use service here
                    if (patient != null) {
                        System.out.print("Enter new first name: ");
                        patient.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        patient.setLastName(scanner.nextLine());
                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                        patient.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        patient.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        patient.setPhoneNumber(scanner.nextLine());
                        patientService.updatePatient(patient);  // Use service here
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:   // Code to delete a patient by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    patientService.deletePatient(patientId);  // Use service here
                    System.out.println("Patient deleted successfully.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageDoctors(DoctorService doctorService, OfficeService officeService,AppointmentService
            appointmentService, Scanner scanner) {
        while (true) {
            System.out.println("\nDoctor Management:");
            System.out.println("1. Create Doctor");
            System.out.println("2. Read Doctor by ID");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:  // Code to create a new doctor
                    // Application calls the service layer, not the repository directly
                    Doctor newDoctor = new Doctor();
                    System.out.print("Enter first name: ");
                    newDoctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newDoctor.setLastName(scanner.nextLine());
                    System.out.print("Enter your specialty: ");
                    newDoctor.setSpecialty(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newDoctor.setEmail(scanner.nextLine());

                    doctorService.createDoctor(newDoctor);  // Use service here
                    System.out.println("Doctor created successfully.");
                    break;
                case 2:  // Code to read doctor details by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    Doctor doctor = doctorService.getDoctorById(doctorId);// Use service here
                    if (doctor != null) {
                        System.out.println("Doctor ID: " + doctor.getDoctorId());
                        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                        System.out.println("Email: " + doctor.getEmail());
                        System.out.println("Specialty: " + doctor.getSpecialty());
                        System.out.println("Patients: " + doctor.getPatients());
                        System.out.println("Appointments: " + doctor.getAppointments());
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 3:  // Code to update doctor details
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    doctor = doctorService.getDoctorById(doctorId);  // Use service here
                    if (doctor != null) {
                        System.out.print("Enter new first name: ");
                        doctor.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        doctor.setLastName(scanner.nextLine());
                        System.out.print("Enter speciality: ");
                        doctor.setSpecialty(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        doctor.setEmail(scanner.nextLine());

                        doctorService.updateDoctor(doctor);  // Use service here
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4:  // Code to delete a doctor by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    Doctor doctor2 = doctorService.getDoctorById(doctorId);
                    if (doctor2 != null) {
                       if (doctor2.getAppointments()!=null && !doctor2.getAppointments().isEmpty()) {
                           System.out.println("Doctor ID:"  + doctor2.getDoctorId() + " has " +
                                   doctor2.getAppointments().size() + " appointments. " +
                                   "Please delete all of the above appointments before deleting this Doctor");
                           break;
                       }
                    }
                    else {
                        System.out.println("Doctor not found.");
                        break;
                    }
                    doctorService.deleteDoctor(doctorId);  // Use service here
                    System.out.println("Doctor deleted successfully.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageAppointments(AppointmentService appointmentService, PatientService patientService,
                                           DoctorService doctorService, Scanner scanner) {
        while (true) {
            System.out.println("\nAppointment Management:");
            System.out.println("1. Create Appointment");
            System.out.println("2. Read Appointment by ID");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:  // Code to create a new appointment
                    // Application calls the service layer, not the repository directly
                    Appointment newAppointment = new Appointment();
                    Doctor aptDoctor;
                    Patient aptPatient;
                    System.out.print("Enter Patient ID: ");
                    aptPatient = patientService.getPatientById(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    aptDoctor = doctorService.getDoctorById(scanner.nextInt());
                    scanner.nextLine();
                    System.out.print("Enter Appointment Date: ");
                    newAppointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter Notes: ");
                    newAppointment.setNotes(scanner.nextLine());

                    newAppointment.setPatient(aptPatient);
                    newAppointment.setDoctor(aptDoctor);

                    doctorService.addPatientToDoctor(aptDoctor.getDoctorId(), aptPatient);
                    patientService.addDoctorToPatient(aptPatient.getPatientId(), aptDoctor);

                    appointmentService.createAppointment(newAppointment);  // Use service here
                    System.out.println("Appointment created successfully.");
                    break;
                case 2:  // Code to read appointment details by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);// Use service here
                    if (appointment != null) {
                        System.out.println("Appointment ID: " + appointment.getAppointmentId());
                        System.out.println("Patient ID: " + appointment.getPatient().getPatientId());
                        System.out.println("Doctor ID: " + appointment.getDoctor().getDoctorId());
                        System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                        System.out.println("Notes: " + appointment.getNotes());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 3:  // Code to update appointment details
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    appointment = appointmentService.getAppointmentById(appointmentId);  // Use service here
                    int originalDoctorId = appointment.getDoctor().getDoctorId();
                    int originalPatientId = appointment.getPatient().getPatientId();

                    if (!appointmentService.hasOtherAppointmentsBetween(
                            originalDoctorId, originalPatientId)) {
                        doctorService.removePatientFromDoctor(originalDoctorId, appointment.getPatient());
                        patientService.removeDoctorFromPatient(originalPatientId, appointment.getDoctor());
                    }
                    if (appointment != null) {
                        System.out.print("Enter Patient ID: ");
                        appointment.getPatient().setPatientId(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Enter Doctor ID: ");
                        appointment.getDoctor().setDoctorId(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Enter Appointment Date: ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter Notes: ");
                        appointment.setNotes(scanner.nextLine());

                        doctorService.addPatientToDoctor(appointment.getDoctor().getDoctorId(), appointment.getPatient());
                        patientService.addDoctorToPatient(appointment.getPatient().getPatientId(), appointment.getDoctor());

                        appointmentService.updateAppointment(appointment);  // Use service here
                        System.out.println("Appointment updated successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                case 4:  // Code to delete an appointment by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    scanner.nextLine();
                    Appointment apt = appointmentService.getAppointmentById(appointmentId);
                    if (apt != null) {
                        if (!appointmentService.hasOtherAppointmentsBetween(
                                apt.getDoctor().getDoctorId(), apt.getPatient().getPatientId())) {
                            doctorService.removePatientFromDoctor(apt.getDoctor().getDoctorId(), apt.getPatient());
                            patientService.removeDoctorFromPatient(apt.getPatient().getPatientId(), apt.getDoctor());
                        }
                    }
                    appointmentService.deleteAppointment(appointmentId);     // Use service here

                    System.out.println("Appointment deleted successfully.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageOffices(OfficeService officeService,DoctorService doctorService, Scanner scanner) {
        while (true) {
            System.out.println("\nOffice Management:");
            System.out.println("1. Create Office");
            System.out.println("2. Read Office by ID");
            System.out.println("3. Update Office");
            System.out.println("4. Delete Office");
            System.out.println("5. List all Offices");
            System.out.println("6. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:  // Code to create a new office
                    // Application calls the service layer, not the repository directly
                    Office newOffice = new Office();
                    Doctor officeDoctor = new Doctor();

                    System.out.print("Enter Location: ");
                    newOffice.setLocation(scanner.nextLine());
                    System.out.print("Enter Phone Number: ");
                    newOffice.setPhone(scanner.nextLine());
                    System.out.print("Enter Doctor ID: ");
                    officeDoctor.setDoctorId(scanner.nextInt());
                    scanner.nextLine();

                    newOffice.setDoctor(officeDoctor);

                    officeService.createOffice(newOffice);  // Use service here
                    System.out.println("Office created successfully.");
                    break;
                case 2:  // Code to read office details by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    int officeId = scanner.nextInt();
                    Office office = officeService.getOfficeById(officeId);// Use service here
                    if (office != null) {
                        System.out.println("Office ID: " + office.getOfficeId());
                        System.out.println("Location: " + office.getLocation());
                        System.out.println("Phone Number: " + office.getPhone());
                        System.out.println("Doctor ID: " + office.getDoctor().getDoctorId());

                    } else {
                        System.out.println("Office not found.");
                    }
                    break;
                case 3:  // Code to update office details
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    officeId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    office = officeService.getOfficeById(officeId);  // Use service here
                    if (office != null) {
                        System.out.print("Enter Location: ");
                        office.setLocation(scanner.nextLine());
                        System.out.print("Enter Phone Number: ");
                        office.setPhone(scanner.nextLine());

                        System.out.print("Enter Doctor ID: ");
                        office.getDoctor().setDoctorId(scanner.nextInt());
                        scanner.nextLine();

                        officeService.updateOffice(office);  // Use service here
                        System.out.println("Office updated successfully.");
                    } else {
                        System.out.println("Office not found.");
                    }
                    break;
                case 4:  // Code to delete an office by ID
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    officeId = scanner.nextInt();
                    scanner.nextLine();
                    officeService.deleteOffice(officeId);  // Use service here
                    System.out.println("Office deleted successfully.");
                    break;
                 case 5:  // Code to list all offices
                    // Application calls the service layer, not the repository directly
                    System.out.print("List all Offices\n");
                    System.out.println(officeService.getAllOffices());
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
 }




