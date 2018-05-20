package employee.processor;

import employee.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Processes topologically sorted {@link Employee} list and prints hierarchy
 */
public class HierarchyPrinter implements Processor {

    private ProcessingContext processingContext;
    private Map<Employee, Integer> hierarchy;

    public HierarchyPrinter(ProcessingContext processingContext) {
        this.processingContext = processingContext;
    }

    @Override
    public void process() {
        List<Employee> employees = processingContext.getTopologicallySortedEmployees();
        hierarchy = new HashMap<>();

        for (Employee employee : employees) {
            if (employee.getManager() == null) {
                hierarchy.put(employee, 1);
                System.out.println(employee.getName());

            } else {
                Integer level = hierarchy.get(employee.getManager());
                level++;
                hierarchy.put(employee, level);
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < level; i++) {
                    builder.append("\t");
                }
                System.out.println(builder.toString() + employee.getName());
            }
        }
    }

    protected Map<Employee, Integer> getHierarchy() {
        return hierarchy;
    }
}