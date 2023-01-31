package org.rbernalop.shared.domain;

import java.lang.annotation.*;

@org.springframework.stereotype.Service
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Service {
}