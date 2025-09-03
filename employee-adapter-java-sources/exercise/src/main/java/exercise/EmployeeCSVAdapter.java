package exercise;

public class EmployeeCSVAdapter implements Employee {
    private final EmployeeCSV csvEmployee;
    
    public EmployeeCSVAdapter(EmployeeCSV csvEmployee) {
        this.csvEmployee = csvEmployee;
    }
    
    @Override
    public String getId() {
        return csvEmployee.tokens()[0];
    }
    
    @Override
    public String getFirstName() {
        return csvEmployee.tokens()[1];
    }
    
    @Override
    public String getLastName() {
        return csvEmployee.tokens()[2];
    }
    
    @Override
    public String getEmail() {
        return csvEmployee.tokens()[3];
    }
}
