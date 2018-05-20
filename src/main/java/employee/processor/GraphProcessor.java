package employee.processor;

import employee.model.Employee;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Performs topological sort and updates {@link ProcessingContext} with the result.
 */
public class GraphProcessor implements Processor {

    private ProcessingContext processingContext;

    public GraphProcessor(ProcessingContext processingContext) {
        this.processingContext = processingContext;
    }

    @Override
    public void process() {
        Map<Employee, List<Employee>> graph = processingContext.getGraph();
        List<Employee> visited = new LinkedList<>();
        for (Employee manager : graph.keySet()) {
            if (!manager.isMarked()) {
                visit(graph, manager, visited);
            }
        }
        processingContext.setTopologicallySortedEmployees(visited);
    }

    private void visit(Map<Employee, List<Employee>> graph, Employee manager, List<Employee> visited) {
        if (manager.isMarked()) {
            return;
        }
        if (manager.isTemporarilyMarked()) {
            throw new RuntimeException("Cycle was detected in the graph");
        }
        manager.setTemporarilyMarked(true);

        if (graph.get(manager) != null) {
            for (Employee employee : graph.get(manager)) {
                visit(graph, employee, visited);
            }
        }
        manager.setMarked(true);
        visited.add(0, manager);
    }
}
