package stud.apach.users.validate;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import stud.apach.users.model.Gender;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserForm {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(1)
    @Max(120)
    private Integer age;

    @NotNull
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
