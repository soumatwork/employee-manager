package employee.processor;

import employee.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GraphCreatorTest {
    private ProcessingContext processingContext;

    @Before
    public void setup() {
        processingContext = new ProcessingContext();
    }

    @Test
    public void testGraphCreation() {
        List<Employee> employees = new LinkedList<>();
        Employee alan = new Employee();
        alan.setName("Alan");
        alan.setId(100);
        employees.add(alan);

        Employee martin = new Employee();
        martin.setName("Martin");
        martin.setId(220);
        employees.add(martin);

        processingContext.setEmployees(employees);
        processingContext.setManagerIds(Arrays.asList(-1l, alan.getId()));

        GraphCreator graphCreator = new GraphCreator(processingContext);
        graphCreator.process();

        assertEquals(1, processingContext.getGraph().size());
        processingContext.getGraph().forEach((manager, employessList) -> {
            assertEquals(alan, manager);
            assertEquals(martin, employessList.get(0));
        });
    }

    @Test(expected = RuntimeException.class)
    public void testWhenManagerIsNotEmployee() {
        List<Employee> employees = new LinkedList<>();
        Employee alan = new Employee();
        alan.setName("Alan");
        alan.setId(100);
        employees.add(alan);

        Employee martin = new Employee();
        martin.setName("Martin");
        martin.setId(220);
        employees.add(martin);

        processingContext.setEmployees(employees);
        processingContext.setManagerIds(Arrays.asList(150l, alan.getId()));

        GraphCreator graphCreator = new GraphCreator(processingContext);
        graphCreator.process();

    }

    @Test(expected = RuntimeException.class)
    public void testWhenThereAreTwoCEOs() {
        List<Employee> employees = new LinkedList<>();
        Employee alan = new Employee();
        alan.setName("Alan");
        alan.setId(100);
        employees.add(alan);

        Employee martin = new Employee();
        martin.setName("Martin");
        martin.setId(220);
        employees.add(martin);

        Employee alex = new Employee();
        alex.setName("Alex");
        alex.setId(275);
        employees.add(alex);

        processingContext.setEmployees(employees);
        processingContext.setManagerIds(Arrays.asList(-1l, alan.getId(), -1l));

        GraphCreator graphCreator = new GraphCreator(processingContext);
        graphCreator.process();

    }
}