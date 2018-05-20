package employee;

import employee.model.Employee;
import employee.processor.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class EmployeeParser {

    private List<Employee> employees;
    private List<Long> managerIds;
    private ProcessingContext processingContext;
    private List<Processor> processors;


    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Long> getManagerIds() {
        return managerIds;
    }

    public EmployeeParser() {
        this.processingContext = new ProcessingContext();
        this.employees = new LinkedList<>();
        this.managerIds = new LinkedList<>();
        this.processors = new LinkedList<>();

        processors.add(new GraphCreator(this.processingContext));
        processors.add(new GraphProcessor(this.processingContext));
        processors.add(new HierarchyPrinter(this.processingContext));
    }

    public void parse(File file) {

        try (Reader reader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            bufferedReader.lines().forEach(line -> parseLine(line));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.processingContext.setEmployees(getEmployees());
        this.processingContext.setManagerIds(getManagerIds());

        invokeProcessors();

    }

    private void invokeProcessors() {
        for (Processor processor : this.processors) {
            processor.process();
        }
    }

    protected void parseLine(String line) {
        if (line != null && line.trim().length() > 0) {
            String[] elements = line.split(",");
            if (elements != null && elements.length != 3 && elements.length != 2) {
                throw new RuntimeException("File not valid");
            }
            Employee employee = new Employee();
            employee.setName(elements[0]);
            employee.setId(Long.parseLong(elements[1]));
            employees.add(employee);

            if (elements.length > 2 && elements[2] != null) {
                managerIds.add(Long.parseLong(elements[2]));
            } else {
                managerIds.add(-1l);
            }
        }
    }

}