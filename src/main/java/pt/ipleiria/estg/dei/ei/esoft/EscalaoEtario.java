package pt.ipleiria.estg.dei.ei.esoft;

import java.util.Arrays;

public enum EscalaoEtario {
    Varios, Bejamins, Infantis, Iniciados, Juvenis, Cadetes, Juniores, Sub23, Seniores, Veteranos;

    public static String[] getValues(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
