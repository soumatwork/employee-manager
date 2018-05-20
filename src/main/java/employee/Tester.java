package employee;

import java.io.File;

public class Tester {

    public static void main(String[] args) throws Exception {
        EmployeeParser employeeParser = new EmployeeParser();
        employeeParser
                .parse(new File("/Users/soum/git/employee-manager/src/main/resources/data.csv"));

    }
}
