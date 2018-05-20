package employee.processor;

import employee.model.Employee;

import java.util.List;
import java.util.Map;

public class ProcessingContext {
    private Map<Employee, List<Employee>> graph;
    private List<Employee> topologicalSortedEmployees;
    private List<Employee> employees;
    private List<Long> managerIds;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Map<Employee, List<Employee>> getGraph() {
        return graph;
    }

    public void setGraph(Map<Employee, List<Employee>> graph) {
        this.graph = graph;
    }

    public List<Employee> getTopologicalSortedEmployees() {
        return topologicalSortedEmployees;
    }

    public void setTopologicalSortedEmployees(List<Employee> topologicalSortedEmployees) {
        this.topologicalSortedEmployees = topologicalSortedEmployees;
    }

    public List<Long> getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(List<Long> managerIds) {
        this.managerIds = managerIds;
    }

}
