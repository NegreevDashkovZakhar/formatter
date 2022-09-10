package it.sevenbits.formatter;

import it.sevenbits.formatter.formatter.Formatter;
import it.sevenbits.formatter.formatter.IFormatter;
import it.sevenbits.formatter.read.FileReader;
import it.sevenbits.formatter.read.ReaderException;
import it.sevenbits.formatter.write.FileWriter;
import it.sevenbits.formatter.write.WriterException;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Application entry class
 */
public final class Main {

    private Main() {
    }

    /**
     * Application entry method
     *
     * @param args console arguments
     */
    public static void main(final String[] args) {
        try (FileReader reader = new FileReader(new File(args[0]), StandardCharsets.UTF_8);
             FileWriter writer = new FileWriter(new File(args[1]), StandardCharsets.UTF_8)) {
            IFormatter formatter = new Formatter();
            formatter.format(reader, writer);
            System.out.println("File formatted");
        } catch (ReaderException e) {
            System.out.println("Could not read file");
        } catch (WriterException e) {
            System.out.println("Could not write to file");
        } catch (Exception e) {
            System.out.println("Unknown error occurred");
        }
    }
}
