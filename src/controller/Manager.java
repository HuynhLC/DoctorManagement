package controller;

import common.Library;
import model.Doctor;
import java.util.ArrayList;
import view.Menu;

public class Manager extends Menu {

    ArrayList<Doctor> list_D = new ArrayList<>();
    Library l;
    
    public Manager (String td, String[] mc, String exit) {
        super (td, mc, exit);
        l = new Library();
        list_D = new ArrayList<>();
        
        list_D.add(new Doctor("d1", "nghia", "orthopedics", 3));
        list_D.add(new Doctor("d2", "phong", "obstetrics", 2));
        list_D.add(new Doctor("d3", "lien", "orthodontic", 1));
    }
    
    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createDoctor();
                break;
            case 2:
                updateDoctor();
                break;
            case 3:
                deleteDoctor();
                break;
            case 4:
                searchDoctor();
                break;
            case 5:
                System.exit(0);
        }
    }
    
    public void addDoctor() {
        System.out.println("--------- Add Doctor ----------");
        System.out.print("Enter code: "); 
        String code = l.checkInputString();
        if (!l.checkCodeExist(list_D, code)) {
            System.err.println("Code exist.");
            return;
        }
        System.out.print("Enter name: ");
        String name = l.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = l.checkInputString();
        System.out.print("Enter availability: ");
        int availability = l.checkInputInt();
        //check worker duplicate
        if (!l.checkDuplicate(list_D, code, name, specialization, availability)) {
            System.err.println("Duplicate.");
            return;
        }
        list_D.add(new Doctor(code, name, specialization, availability));
        System.err.println("Add successful.");
    }

    //allow user update doctor
    public void updateDoctor() {
        System.out.println("--------- Update Doctor ----------");
        System.out.print("Enter code: ");
        String code = l.checkInputString();
        //check code exist or not
        if (l.checkCodeExist(list_D, code)) {
            System.err.println("Not found doctor");
            return;
        }
        System.out.print("Enter code: ");
        String codeUpdate = l.checkInputString();
        Doctor doctor = getDoctorByCode(list_D, code);
        System.out.print("Enter name: ");
        String name = l.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = l.checkInputString();
        System.out.print("Enter availability: ");
        int availability = l.checkInputInt();
        //check user change infomation or not
        if (!l.checkChangeInfo(doctor, code, name, specialization, availability)) {
            System.err.println("No change");
            return;
        }
        doctor.setCode(codeUpdate);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setAvailability(availability);
        System.err.println("Update successful");
    }

    //allow user delete doctor
    public void deleteDoctor() {
        System.out.println("--------- Delete Doctor ----------");
        System.out.print("Enter code: ");
        String code = l.checkInputString();
        Doctor doctor = getDoctorByCode(list_D, code);
        if (doctor == null) {
            System.err.println("Not found doctor.");
            return;
        } else {
            list_D.remove(doctor);
        }
        System.err.println("Delete successful.");
    }

    //allow user search doctor
    public void searchDoctor() {
        System.out.println("--------- Search Doctor ----------");
        System.out.print("Enter name: ");
        String nameSearch = l.checkInputString();
        ArrayList<Doctor> listFoundByName = listFoundByName(list_D, nameSearch);
        if (listFoundByName.isEmpty()) {
            System.err.println("List empty.");
        } else {
            System.out.printf("%-10s%-15s%-25s%-20s\n", "Code", "Name",
                    "Specialization", "Availability");
            for (Doctor doctor : listFoundByName) {
                System.out.printf("%-10s%-15s%-25s%-20d\n", doctor.getCode(),
                        doctor.getName(), doctor.getSpecialization(),
                        doctor.getAvailability());
            }
        }
    }

    //get docter by code
    public Doctor getDoctorByCode(ArrayList<Doctor> ld, String code) {
        for (Doctor doctor : ld) {
            if (doctor.getCode().equalsIgnoreCase(code)) {
                return doctor;
            }
        }
        return null;
    }

    public ArrayList<Doctor> listFoundByName(ArrayList<Doctor> ld, String name) {
        ArrayList<Doctor> listFoundByName = new ArrayList<>();
        for (Doctor doctor : ld) {
            if (doctor.getName().contains(name)) {
                listFoundByName.add(doctor);
            }
        }
        return listFoundByName;
    }
    
    public void createDoctor() {
    System.out.println("--------- Check Doctor ----------");
    System.out.print("Enter code: ");
    String code = l.checkInputString();
    if (getDoctorByCode(list_D, code) != null) {
        System.err.println("Code exist. Updating doctor.");
        updateDoctor();
    } else {
        addDoctor();
    }
}
}
