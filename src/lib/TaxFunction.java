package lib;

public class TaxFunction {

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
     * dibayarkan setahun.
     *
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan
     * pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi
     * pemotongan) dikurangi penghasilan tidak kena pajak.
     *
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak
     * kena pajaknya adalah Rp 54.000.000. Jika pegawai sudah menikah maka
     * penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000. Jika
     * pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah
     * sebesar Rp 4.500.000 per anak sampai anak ketiga.
     *
     */
    private static final int BASE_EXCLUSION = 54000000;
    private static final int SPOUSE_EXCLUSION = 4500000;
    private static final int CHILD_EXCLUSION = 4500000;
    private static final double TAX_RATE = 0.05;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        int tax = 0;

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        numberOfChildren = Math.min(numberOfChildren, 3);

        // Hitung penghasilan tahunan
        int annualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;

        // Hitung penghasilan tidak kena pajak
        int nonTaxableIncome = BASE_EXCLUSION;
        
        // jika pegawai menikah penghasilan yang tidak kena pajak bertambah base + spouse exclusion
        if(isMarried){
            nonTaxableIncome += SPOUSE_EXCLUSION;
        }
        // jika pegawai punya anak
        nonTaxableIncome += numberOfChildren * CHILD_EXCLUSION;
        
        // menghitung penghasilan kena pajak
        int TaxableIncome =annualIncome - deductible - nonTaxableIncome;
        
        int Tax = (int) Math.round(TAX_RATE * TaxableIncome);

        return Math.max(Tax, 0);
    }

}
