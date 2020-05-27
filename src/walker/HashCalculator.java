
package walker;

import java.io.FileInputStream;
import java.io.IOException;

public class HashCalculator {
    private static final int FNV_BEGIN = 0x811c9dc5;
    private static final int FNV_STEP = 0x01000193;
    private static final int BYTE_MASK = 0xff;

    public static int hash32(String file) {
        int hash = FNV_BEGIN;
        int value;
        try (FileInputStream stream = new FileInputStream(file)) {
            while ((value = stream.read()) != -1) {
                hash = (hash * FNV_STEP) ^ (value & BYTE_MASK);
            }
            return hash;
        } catch (IOException e) {
            return 0;
        }
    }
}