package employee;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeParserTest {

    @Test
    public void testParseEmployee() {
        EmployeeParser employeeParser = new EmployeeParser();
        employeeParser.parseLine("Alan,100,150");
        employeeParser.parseLine("Martin,220,100");
        employeeParser.parseLine("Arnold,150,");

        assertEquals(3, employeeParser.getEmployees().size());
        assertEquals(3, employeeParser.getManagerIds().size());

    }

    @Test(expected = RuntimeException.class)
    public void testWhenFileHasLessThan2CommaSeparatedValues() {
        EmployeeParser employeeParser = new EmployeeParser();
        employeeParser.parseLine("Alan,100,150");
        employeeParser.parseLine("Martin,220,100");
        employeeParser.parseLine("Arnold,");
    }

    @Test(expected = RuntimeException.class)
    public void testWhenFileHasMoreThan3CommaSeparatedValues() {
        EmployeeParser employeeParser = new EmployeeParser();
        employeeParser.parseLine("Alan,100,150");
        employeeParser.parseLine("Martin,220,100");
        employeeParser.parseLine("Arnold,220,,11");
    }
}