package matauang;

/**
 * Class untuk konversi kurs
 *
 * @author Amalia Yusincha
 */
public class Konversi {

    /**
     * Variabel array string yang menyimpan nama-nama kurs
     */
    private final String[] kursList;

    /**
     * Variabel array double yang menyimpan nilai-nilai kurs
     */
    private final double[] kursValue;

    /**
     * Konstruktor utama, dimana menginisialisasi nama-nama dan nilai kurs
     */
    public Konversi() {
        /**
         * Inisialisasi variabel 'kursList' dan mengisi dengan nama-nama kurs
         */
        kursList = new String[]{
            "IDR", "USD", "AUD", "EUR",
            "GBP", "SGD", "CNY", "JPY"
        };

        /**
         * Inisialisasi variabel 'kursValue' dan mengisi dengan nilai-nilai kurs
         */
        kursValue = new double[]{
            1.00, 13350.00, 9936.61, 14071.83,
            16791.71, 9329.29, 1934.79, 115.77
        };
    }

    /**
     * Method konversi kurs
     *
     * @param kursAwal parameter kurs awal
     * @param nominalAwal parameter nominal kurs awal
     * @param kursAkhir parameter kurs akhir atau tujuan
     * @return hasil konversi kurs awal ke akhir
     */
    public double prosesKonversi(int kursAwal, double nominalAwal, int kursAkhir) {
        /**
         * Mengambil nilai nominal awal dan mengkonversi pada kurs IDR dengan
         * nilai-nilai yang sudah ditentukan pada variabel array 'kursValue'
         */
        double konversi = nominalAwal * kursValue[0];

        if (kursAkhir == 0) {
            /**
             * Jika kurs tujuan IDR, field nominal awal diisi dengan hasil
             * konversi sebelumnya
             */
            return konversi;
        } else {
            /**
             * Jika kurs tujuan bukan IDR, field nominal awal diisi dengan hasil
             * konversi sebelumnya di konversi dengan kurs tujuan
             */
            return konversi / kursValue[kursAkhir];
        }
    }

    /**
     * Method pengambilan nama kurs berdasarkan index
     *
     * @param index parameter index array pada daftar nama kurs
     * @return nama kurs sesuai dengan index array yang ditentukan
     */
    public String getKursNameAt(int index) {
        return kursList[index];
    }

    /**
     * Method pengambilan seluruh nama-nama kurs
     *
     * @return array string yang berisi nama-nama kurs
     */
    public String[] getKursList() {
        return kursList;
    }

    /**
     * Method pengambilan nilai kurs berdasarkan IDR
     *
     * @param index parameter index array pada daftar nilai kurs
     * @return nilai kurs sesuai dengan index array yang ditentukan
     */
    public double getKursValueAt(int index) {
        return kursValue[index];
    }

}
