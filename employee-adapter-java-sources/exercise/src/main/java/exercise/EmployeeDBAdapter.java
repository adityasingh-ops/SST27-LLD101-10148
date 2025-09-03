package exercise;

public class EmployeeDBAdapter implements Employee {
    private final EmployeeDB dbEmployee;
    
    public EmployeeDBAdapter(EmployeeDB dbEmployee) {
        this.dbEmployee = dbEmployee;
    }
    
    @Override
    public String getId() {
        return String.valueOf(dbEmployee.getId());
    }
    
    @Override
    public String getFirstName() {
        return dbEmployee.getFirstName();
    }
    
    @Override
    public String getLastName() {
        return dbEmployee.getSurname();
    }
    
    @Override
    public String getEmail() {
        return dbEmployee.getEmailAddress();
    }
}
