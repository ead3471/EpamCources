package com.epam.javase02.t07;

import java.lang.annotation.Documented;

/**
 * Created by Freemind on 2016-09-20.
 */
@Documented
public @interface DevelopAnnotation {
    String author() default "Freemind";
    String lastChangeAuthor() default "Freemind";
    String lastChangeDate();
    String lastChangeReason();

}
