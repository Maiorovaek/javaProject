package controlStructures.controlStructuresEnum;

import java.util.Random;

public class RandomEnum<E extends Enum<Birds>> {
    Random RND = new Random();
    E[] values;

    public RandomEnum(Class<E> token) {
        values = token.getEnumConstants();
    }

    public E random() {
        return values[RND.nextInt(values.length)];
    }
}
