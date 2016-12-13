package matauang;

/**
 * Interface class Main
 *
 * @author Amalia Yusincha
 */
public interface MainInterface {

    /**
     * Proses inisialiasi komponen-komponen, dimana method ini dijalankan hanya
     * sekali pada konstruktor
     */
    public void inisialisasi();

    /**
     * Method proses konversi nominal awal ke nominal akhir
     */
    public void prosesKonversiAwalKeAkhir();

    /**
     * Method proses konversi nominal awal ke nominal akhir
     */
    public void prosesKonversiAkhirKeAwal();

}
