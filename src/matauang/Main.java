package matauang;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;

/**
 * Program Konversi Mata Uang Sederhana
 *
 * @author Amalia Yusincha
 */
public class Main extends JFrame implements MainInterface {

    /**
     * Class untuk konversi kurs
     */
    private Konversi konversi;

    /**
     * Object panel yang akan menampung komponen-komponen
     */
    private JPanel panel;

    /**
     * Field untuk mengetik nominal awal dan tujuan konversi
     */
    private JFormattedTextField nominalAwal, nominalAkhir;

    /**
     * Combo box untuk memilih kurs awal dan tujuan konversi
     */
    private JComboBox<String> kursAwal, kursAkhir;

    /**
     * Konstruktor utama, dimana setelah pembuatan object langsung
     * menginisialisasi komponen-komponen
     */
    public Main() {
        inisialisasi();
    }

    /**
     * Method inisialiasi komponen-komponen, dimana method ini dijalankan hanya
     * sekali pada konstruktor
     */
    @Override
    public final void inisialisasi() {
        /**
         * Pengaturan JFrame awal, mulai dari judul, ukuran dan posisi
         */
        setTitle("Amalia Yusincha - 06.2015.1.06498");
        setSize(384, 256);
        setLocationRelativeTo(null); // lokasi frame pada tengah desktop
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * Inisialisasi variabel 'panel'
         */
        panel = new JPanel();

        /**
         * Mengatur panel untuk tidak mengatur posisi komponen secara otomatis
         * (penempatan manual, menggunakan setLocation() untuk per komponen)
         */
        panel.setLayout(null);

        /**
         * Mengatur warna background panel
         */
        panel.setBackground(Color.white);

        /**
         * Pembuatan teks "KONVERSI KURS"
         */
        JLabel labelJudul = new JLabel("KONVERSI KURS", JLabel.CENTER);
        labelJudul.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        labelJudul.setLocation(0, 8);
        labelJudul.setSize(getWidth(), labelJudul.getPreferredSize().height);
        panel.add(labelJudul);

        /**
         * Inisialisasi object untuk menentukan format nominal uang
         */
        FormatMataUang formatMataUang = new FormatMataUang();

        /**
         * Inisialisasi object 'Konversi'
         */
        konversi = new Konversi();

        /**
         * Pembuatan label "Dari" pada atas field nominal awal
         */
        JLabel labelAwal = new JLabel("Dari");
        labelAwal.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        labelAwal.setLocation(32, 64 - 16);
        labelAwal.setSize(
                labelAwal.getPreferredSize().width,
                labelAwal.getPreferredSize().height
        );
        panel.add(labelAwal);

        /**
         * Pembuatan field nominal awal
         */
        nominalAwal = new JFormattedTextField();
        nominalAwal.setFormatterFactory(formatMataUang.getFormat());
        nominalAwal.setLocation(32, 64);
        nominalAwal.setSize(192, nominalAwal.getPreferredSize().height * 2);
        /**
         * Menambah 'listener' dimana akan menjalankan method konversi awal ke
         * akhir saat nominal awal telah diisi atau ada perubahan nilai
         */
        nominalAwal.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                prosesKonversiAwalKeAkhir();
            }
        });
        /**
         * Menambah 'listener' dimana akan menjalankan method konversi awal ke
         * akhir saat menekan tombol enter atau tab pada field nominal awal
         */
        nominalAwal.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER
                        || e.getKeyCode() == KeyEvent.VK_TAB) {
                    prosesKonversiAwalKeAkhir();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panel.add(nominalAwal);

        /**
         * Pembuatan combo box pemilihan kurs awal
         */
        kursAwal = new JComboBox<>(konversi.getKursList());
        kursAwal.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        kursAwal.setLocation(32 + 208, 64);
        kursAwal.setSize(
                kursAwal.getPreferredSize().width * 2, nominalAwal.getHeight()
        );
        kursAwal.setSelectedIndex(1);
        /**
         * Menambah 'listener' dimana akan menjalankan method konversi awal ke
         * akhir saat telah memilih kurs awal
         */
        kursAwal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nominalAwal.getValue() != null) {
                    prosesKonversiAwalKeAkhir();
                }
            }
        });
        panel.add(kursAwal);

        /**
         * Pembuatan label "Ke" pada atas field nominal akhir
         */
        JLabel labelAkhir = new JLabel("Ke");
        labelAkhir.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        labelAkhir.setLocation(32, 128 - 16);
        labelAkhir.setSize(
                labelAkhir.getPreferredSize().width,
                labelAkhir.getPreferredSize().height
        );
        panel.add(labelAkhir);

        /**
         * Pembuatan field nominal akhir
         */
        nominalAkhir = new JFormattedTextField();
        nominalAkhir.setFormatterFactory(formatMataUang.getFormat());
        nominalAkhir.setLocation(32, 128);
        nominalAkhir.setSize(192, nominalAkhir.getPreferredSize().height * 2);
        /**
         * Menambah 'listener' dimana akan menjalankan method konversi akhir ke
         * awal saat nominal akhir telah diisi atau ada perubahan nilai
         */
        nominalAwal.addPropertyChangeListener("value", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                prosesKonversiAkhirKeAwal();
            }
        });
        /**
         * Menambah 'listener' dimana akan menjalankan method konversi akhir ke
         * awal saat menekan tombol enter atau tab pada field nominal akhir
         */
        nominalAkhir.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER
                        || e.getKeyCode() == KeyEvent.VK_TAB) {
                    prosesKonversiAkhirKeAwal();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panel.add(nominalAkhir);

        /**
         * Pembuatan combo box pemilihan kurs akhir
         */
        kursAkhir = new JComboBox<>(konversi.getKursList());
        kursAkhir.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        kursAkhir.setLocation(32 + 208, 128);
        kursAkhir.setSize(
                kursAkhir.getPreferredSize().width * 2, nominalAkhir.getHeight()
        );
        kursAkhir.setSelectedIndex(0);
        /**
         * Menambah 'listener' dimana akan menjalankan method konversi akhir ke
         * awal saat telah memilih kurs awal
         */
        kursAkhir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nominalAkhir.getValue() != null) {
                    prosesKonversiAwalKeAkhir();
                }
            }
        });
        panel.add(kursAkhir);

        /**
         * Menentukan isi frame dengan panel yang sudah dibuat sebelumnya
         */
        setContentPane(panel);
    }

    /**
     * Method proses konversi nominal awal ke nominal akhir
     */
    @Override
    public void prosesKonversiAwalKeAkhir() {
        try {
            /**
             * Mengambil nilai nominal awal dan diubah menjadi string serta
             * menghapus karakter yang tidak diperlukan (spasi, dsb.)
             */
            String str = nominalAwal.getValue().toString().trim();

            /**
             * Mengambil nilai dari variabel 'str' dan mengkonversi pada kurs
             * IDR dengan nilai-nilai yang sudah ditentukan pada variabel array
             * 'kursValue'
             */
            double dbl = Double.parseDouble(str)
                    * konversi.getKursValueAt(kursAwal.getSelectedIndex());

            if (kursAkhir.getSelectedIndex() == 0) {
                /**
                 * Jika kurs tujuan IDR, field nominal akhir diisi dengan hasil
                 * konversi sebelumnya
                 */
                nominalAkhir.setValue(dbl);
            } else {
                /**
                 * Jika kurs tujuan bukan IDR, field nominal akhir diisi dengan
                 * hasil konversi sebelumnya di konversi dengan kurs tujuan
                 */
                nominalAkhir.setValue(
                        dbl / konversi.getKursValueAt(kursAkhir.getSelectedIndex())
                );
            }
        } catch (NumberFormatException e) {
            /**
             * Exception ini di-'tangkap' saat format input tidak sesuai
             */
        } catch (Exception e) {
            /**
             * Exception ini di-'tangkap' saat ada kesalahan lainnya
             */
        }
    }

    /**
     * Method proses konversi nominal awal ke nominal akhir
     */
    @Override
    public void prosesKonversiAkhirKeAwal() {
        try {
            /**
             * Mengambil nilai nominal akhir dan diubah menjadi string serta
             * menghapus karakter yang tidak diperlukan (spasi, dsb.)
             */
            String str = nominalAkhir.getValue().toString().trim();

            /**
             * Mengambil nilai dari variabel 'str' dan mengkonversi pada kurs
             * IDR dengan nilai-nilai yang sudah ditentukan pada variabel array
             * 'kursValue'
             */
            double dbl = Double.parseDouble(str)
                    * konversi.getKursValueAt(kursAkhir.getSelectedIndex());

            if (kursAwal.getSelectedIndex() == 0) {
                /**
                 * Jika kurs tujuan IDR, field nominal awal diisi dengan hasil
                 * konversi sebelumnya
                 */
                nominalAwal.setValue(dbl);
            } else {
                /**
                 * Jika kurs tujuan bukan IDR, field nominal awal diisi dengan
                 * hasil konversi sebelumnya di konversi dengan kurs tujuan
                 */
                nominalAwal.setValue(
                        dbl / konversi.getKursValueAt(kursAwal.getSelectedIndex())
                );
            }
        } catch (NumberFormatException e) {
            /**
             * Exception ini di-'tangkap' saat format input tidak sesuai
             */
        } catch (Exception e) {
            /**
             * Exception ini di-'tangkap' saat ada kesalahan lainnya
             */
        }
    }

    /**
     * Method utama program
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        }.start();
    }

}
