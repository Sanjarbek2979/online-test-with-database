package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.enums.UserRole;

/**
 * @author Sanjarbek Allayev, чт 17:13. 20.01.2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String name;
    private String phone;
    private UserRole role;
    private boolean active;
    private boolean isDeleted;
}
