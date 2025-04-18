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
    private static final double FOREIGNER_MULTIPLIER = 1.5;

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {

        int tax = 0;

        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }
        
        int yearlyIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        
        // base penghasilan yang tidak kena pajak
        int nonTaxIncome = BASE_EXCLUSION;
        
        // jika pegawai menikah penghasilan yang tidak kena pajak bertambah base + spouse exclusion

        if (isMarried) {
            tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (BASE_EXCLUSION + SPOUSE_EXCLUSION + (numberOfChildren * CHILD_EXCLUSION))));
        } else {
            tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
        }

        if (tax < 0) {
            return 0;
        } else {
            return tax;
        }

    }

}
