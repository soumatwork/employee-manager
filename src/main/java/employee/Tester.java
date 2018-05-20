package employee;

import java.io.File;

public class Tester {

    public static void main(String[] args) throws Exception {
        if (args != null && args.length > 0) {
            EmployeeParser employeeParser = new EmployeeParser();
            employeeParser
                    .parse(new File(args[0]));
        } else {
            System.out.println("Usage: java -jar employee-1.0-SNAPSHOT.jar <file path> ");
            System.out.println("e.g java -jar employee-1.0-SNAPSHOT.jar ./classes/data.csv ");
        }

    }
}
