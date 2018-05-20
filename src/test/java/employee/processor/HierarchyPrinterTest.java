package employee.processor;

import employee.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HierarchyPrinterTest {

    private ProcessingContext processingContext;

    @Before
    public void setup() {
        processingContext = new ProcessingContext();

    }

    @Test
    public void testWhenGraphIsSorted() {
        Employee jamie = new Employee();
        jamie.setName("Jamie");
        jamie.setId(150);

        Employee alan = new Employee();
        alan.setName("Alan");
        alan.setId(100);
        alan.setManager(jamie);

        Employee martin = new Employee();
        martin.setName("Martin");
        martin.setId(220);
        martin.setManager(alan);


        List<Employee> employeeList = new LinkedList<>();
        employeeList.add(jamie);
        employeeList.add(alan);
        employeeList.add(martin);
        processingContext.setTopologicalSortedEmployees(employeeList);

        HierarchyPrinter hierarchyPrinter = new HierarchyPrinter(processingContext);
        hierarchyPrinter.process();

        Map<Employee, Integer> employeeHierarchy = hierarchyPrinter.getHierarchy();
        assertEquals(Integer.valueOf(1), employeeHierarchy.get(jamie));
        assertEquals(Integer.valueOf(2), employeeHierarchy.get(alan));
        assertEquals(Integer.valueOf(3), employeeHierarchy.get(martin));

    }

}