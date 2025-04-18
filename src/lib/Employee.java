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
    private LocalDate dateJoined;
    private int monthWorkingInYear;
    private boolean isForeigner;
    // ganti tipe boolean untuk gender menjadi enum
    private Gender gender;
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    private Spouse spouse;
    private List<Child> children = new LinkedList<>();

    // memperbaiki bad smells long parameter list
    public Employee(String employeeId, PersonalData personalData, LocalDate datejoined, boolean isForeigner, Gender gender) {
        this.employeeId = employeeId;
        this.personalData = personalData;
        this.dateJoined = datejoined;
        this.isForeigner = isForeigner;
        this.gender = gender;
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade
     * kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per
     * bulan, grade 3: 7.000.000 per bulan) Jika pegawai adalah warga negara
     * asing gaji bulanan diperbesar sebanyak 50%
     */
    public void setMonthlySalary(Grade grade) {
        int baseSalary = getBaseSalary(grade);
        monthlySalary = ForeignerSalary(baseSalary);
    }

    public int getBaseSalary(Grade grade) {
        return switch (grade) {
            case Grade1 ->
                3_000_000;
            case Grade2 ->
                5_000_000;
            case Grade3 ->
                7_000_000;
        };
    }
    
    public int ForeignerSalary(int Salary){
      if(isForeigner){
          return (int) (Salary * 1.5);
      }else{
          return Salary;
      }
    };

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouse = new Spouse(spouseName, spouseIdNumber);
    }

    public void addChild(String childName, String childIdNumber) {
        this.children.add(new Child(childName, childIdNumber));
    }

    public int getAnnualIncomeTax() {

        //Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate date = LocalDate.now();

        if (date.getYear() == dateJoined.getYear()) {
            monthWorkingInYear = (int) ChronoUnit.MONTHS.between(dateJoined, date);
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouse.equals(""), children.size());
    }
}
