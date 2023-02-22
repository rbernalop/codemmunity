package org.rbernalop.apiauthentication.shared.application.user.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.rbernalop.shared.domain.bus.command.Command;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateUserCommand implements Command {
    private String id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private LocalDate birthDate;
}
