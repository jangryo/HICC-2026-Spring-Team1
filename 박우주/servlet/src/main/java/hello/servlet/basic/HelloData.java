package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//@Getter, @Setter가 알아서 만들어줌
@Getter
@Setter
public class HelloData {

    private String userName;
    private int age;
}
