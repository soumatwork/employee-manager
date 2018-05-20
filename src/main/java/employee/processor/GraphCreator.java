package employee.processor;

import employee.model.Employee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Creates graph from employee list
 */
public class GraphCreator implements Processor {

    private ProcessingContext processingContext;

    public GraphCreator(ProcessingContext processingContext) {
        this.processingContext = processingContext;
    }

    /**
     * Creates graph from employee list with edges connecting employees on the same hierarchy
     */
    @Override
    public void process() {
        addManagerToEmployee();

        Map<Employee, List<Employee>> graph = new HashMap<>();
        for (Employee employee : processingContext.getEmployees()) {
            if (employee.getManager() != null && !graph.containsKey(employee.getManager())) {
                graph.put(employee.getManager(), new LinkedList<>());
            }
            if (employee.getManager() == null) {
                if (!graph.containsKey(employee)) {
                    graph.put(employee, new LinkedList<>());
                }
            } else {
                graph.get(employee.getManager()).add(employee);
            }
        }
        processingContext.setGraph(graph);
    }


    /**
     * Adds manager to employee object
     */
    private void addManagerToEmployee() {
        Map<Long, Employee> employeeMap = processingContext.getEmployees().stream()
                .collect(Collectors.toMap(Employee::getId, e -> e));

        /**
         * Updates employee with manager
         */
        for (int i = 0; i < processingContext.getEmployees().size(); i++) {
            long managerId = processingContext.getManagerIds().get(i);
            if (employeeMap.containsKey(managerId)) {
                processingContext.getEmployees().get(i).setManager(employeeMap.get(managerId));
            } else if (managerId != -1) {
                throw new RuntimeException("Manager id " + managerId + " not found");
            }
        }
        /**
         * Validates that only one employee can have null manager
         */
        long count = processingContext.getEmployees().stream().filter(e -> e.getManager() == null).count();
        if (count > 1) {
            throw new RuntimeException("More than one CEO");
        }
        if (count == 0) {
            throw new RuntimeException("No CEO");
        }
    }

}
