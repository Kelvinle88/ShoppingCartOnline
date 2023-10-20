package osc.dto;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private String id;
    private String name;
    private String role;
}
