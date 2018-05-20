package employee;

import java.io.File;

public class Tester {

    public static void main(String[] args) throws Exception {
        if (args != null && args.length > 0) {
            EmployeeParser employeeParser = new EmployeeParser();
            employeeParser
                    .parse(new File(args[0]));
        } else {
            System.out.println("Usage: java -classpath <jar-file> employee.Tester <file path> ");
            System.out.println("e.g java -classpath employee-1.0-SNAPSHOT.jar employee.Tester ./classes/data.csv ");
        }

    }
}
