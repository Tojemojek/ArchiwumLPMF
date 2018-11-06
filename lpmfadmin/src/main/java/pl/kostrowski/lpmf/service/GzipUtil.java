package pl.kostrowski.lpmf.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

//taken from https://myadventuresincoding.wordpress.com/2016/01/02/java-simple-gzip-utility-to-compress-and-decompress-a-string/

@Service
public class GzipUtil {
 
    public static byte[] zip(final String str) {
        if ((str == null) || (str.length() == 0)) {
            throw new IllegalArgumentException("Cannot zip null or empty string");
        }
 
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzipOutput = new GZIPOutputStream(baos)) {
                gzipOutput.write(str.getBytes(StandardCharsets.UTF_8));
            }
            return baos.toByteArray();
        } catch(IOException e) {
            throw new RuntimeException("Zipa nie będzie :-( ", e);
        }
    }
 
    public static String unzip(final byte[] compressed) {
        if ((compressed == null) || (compressed.length == 0)) {
            throw new IllegalArgumentException("Cannot unzip null or empty bytes");
        }
        if (!isZipped(compressed)) {
            return new String(compressed);
        }
 
        try (ByteArrayInputStream bais = new ByteArrayInputStream(compressed)) {
            try (GZIPInputStream gzipInput = new GZIPInputStream(bais)) {
                try (InputStreamReader inputStreamReader = new InputStreamReader(gzipInput, StandardCharsets.UTF_8)) {
                    try (BufferedReader br = new BufferedReader(inputStreamReader)) {
                        StringBuilder output = new StringBuilder();
                        String line;
                        while((line = br.readLine()) != null){
                            output.append(line);
                        }
                        return output.toString();
                    }
                }
            }
        } catch(IOException e) {
            throw new RuntimeException("Nie udało się odczytać zipa :-(", e);
        }
    }
 
    public static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }
}