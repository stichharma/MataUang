package matauang;

import java.text.DecimalFormat;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 * Class untuk penggunaan format mata uang
 *
 * @author Amalia Yusincha
 */
public class FormatMataUang {

    /**
     *
     */
    private final DefaultFormatterFactory format;

    /**
     * Konstruktor utama, dimana saat pembuatan object akan menginisialisasi
     * format mata uang
     */
    public FormatMataUang() {
        this.format = new DefaultFormatterFactory(
                new NumberFormatter(new DecimalFormat("#,##0.00"))
        );
    }

    /**
     * Method untuk mengambil format mata uang
     *
     * @return format yang sudah ditentukan
     */
    public DefaultFormatterFactory getFormat() {
        return format;
    }

}
