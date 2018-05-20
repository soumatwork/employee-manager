package employee.processor;

import employee.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GraphProcessorTest {
    private ProcessingContext processingContext;

    @Before
    public void setup() {
        processingContext = new ProcessingContext();

    }

    @Test
    public void testSortingGraph(){
        Map<Employee, List<Employee>> graph = new HashMap<>();

        List<Employee> employees = new LinkedList<>();

        Employee jamie = new Employee();
        jamie.setName("Jamie");
        jamie.setId(150);
        employees.add(jamie);

        Employee alan = new Employee();
        alan.setName("Alan");
        alan.setId(100);
        alan.setManager(jamie);
        employees.add(alan);

        Employee martin = new Employee();
        martin.setName("Martin");
        martin.setId(220);
        martin.setManager(alan);
        employees.add(martin);

        graph.put(alan, Arrays.asList(martin));
        graph.put(jamie, Arrays.asList(alan));

        processingContext.setGraph(graph);

        GraphProcessor graphProcessor = new GraphProcessor(processingContext);
        graphProcessor.process();

        List<Employee> employeeList = processingContext.getTopologicalSortedEmployees();
        assertEquals(jamie, employeeList.get(0));
        assertEquals(alan, employeeList.get(1));
        assertEquals(martin, employeeList.get(2));

    }

    @Test(expected = RuntimeException.class)
    public void testWhenGraphIsCyclic(){
        Map<Employee, List<Employee>> graph = new HashMap<>();

        List<Employee> employees = new LinkedList<>();

        Employee jamie = new Employee();
        jamie.setName("Jamie");
        jamie.setId(150);
        employees.add(jamie);

        Employee alan = new Employee();
        alan.setName("Alan");
        alan.setId(100);
        alan.setManager(jamie);
        employees.add(alan);

        Employee martin = new Employee();
        martin.setName("Martin");
        martin.setId(220);
        martin.setManager(alan);
        employees.add(martin);

        jamie.setManager(martin);

        graph.put(alan, Arrays.asList(martin));
        graph.put(jamie, Arrays.asList(alan));
        graph.put(martin, Arrays.asList(jamie));

        processingContext.setGraph(graph);

        GraphProcessor graphProcessor = new GraphProcessor(processingContext);
        graphProcessor.process();
    }
}