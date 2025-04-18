package lib;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private PersonalData personalData;

    // ganti waktu join menggunakan Date
    private LocalDate DateJoined;
    private int monthWorkingInYear;

    private boolean isForeigner;

    // ganti tipe boolean untuk gender menjadi enum
    private Gender gender;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId,PersonalData personalData, LocalDate datejoined, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.personalData = personalData;
        this.DateJoined = datejoined;
        this.isForeigner = isForeigner;
        this.gender = gender;

        childNames = new LinkedList<String>();
        childIdNumbers = new LinkedList<String>();
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade
     * kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per
     * bulan, grade 3: 7.000.000 per bulan) Jika pegawai adalah warga negara
     * asing gaji bulanan diperbesar sebanyak 50%
     */
    public void setMonthlySalary(Grade grade) {
        double baseSalary = 0;
        if (grade == grade.Grade1) {
            baseSalary = 3000000;
        } else if (grade == grade.Grade2) {
            baseSalary = 5000000;
        } else if (grade == grade.Grade3) {
            baseSalary = 7000000;
        }
        
        if(isForeigner){
            baseSalary = baseSalary * 1.5;
        }
        
        monthlySalary = (int) baseSalary;
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {

        //Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate date = LocalDate.now();

        if (date.getYear() == DateJoined.getYear()) {
            monthWorkingInYear = (int) ChronoUnit.MONTHS.between(DateJoined, date);
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }
}
