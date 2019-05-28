package dev.sky_lock.parkour;

import java.util.Optional;
import java.util.function.Consumer;

public class Optionals {

   public static <T> void ifPresentOrElse(Optional<T> optional, Consumer<T> present, Runnable notPresent) {
       optional.map(value -> {
           present.accept(value);
           return value;
       }).orElseGet(() -> {
           notPresent.run();
           return null;
       });
    }
}
