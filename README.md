# Employee Hierarchy Printer
### Description
A class `EmployeeParser.java` was created, which parses employee records from CSV, creates Employee object and 
invokes processors to process employee object list and print hierarchy. The project is based on maven to build 
it efficiently with unittests.
 
### The design approach
In order to make the design more understandable, flexible and maintainable, the parsing employee records and 
printing hierarchy was delegated to individual processors (`Processor`). The individual processors work within 
a context (`ProcessingContext`) and updates it with the result. `HierarchyPrinter`, which is the last processor in 
the processor chain, prints the hierarchy.

The concept of identifying hierarchy was based on converting employee list into a DAG (directed acyclic graph)
and sorting it topologically based on depth first search algorithm. The reversed topologically sorted employee list 
will have CEO at the top and the ordered hierarchy thereafter.    

### Classes created
- `EmployeeParser`
- `Tester`
- `Employee`
- `GraphCreator`
- `GraphProcessor`
- `HierarchyPrinter`
- `ProcessingContext`
- `Processor`

### Instructions to execute
- Download source files from [git repo](https://github.com/soumatwork/employee-manager.git)
- Ensure java 8 is installed
- Install maven if not installed already [Maven](https://maven.apache.org/install.html)
- Run 'mvn install'
- Execute `cd target/`
- Execute `java -classpath employee-1.0-SNAPSHOT.jar employee.Tester ./classes/data.csv`