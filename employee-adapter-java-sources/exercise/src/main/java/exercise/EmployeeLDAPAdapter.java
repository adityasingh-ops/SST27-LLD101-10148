package exercise;

public class EmployeeLDAPAdapter implements Employee {
    private final EmployeeLDAP ldapEmployee;
    
    public EmployeeLDAPAdapter(EmployeeLDAP ldapEmployee) {
        this.ldapEmployee = ldapEmployee;
    }
    
    @Override
    public String getId() {
        return ldapEmployee.get("uid"); 
    }
    
    @Override
    public String getFirstName() {
        return ldapEmployee.get("givenName");
    }

    @Override
    public String getLastName() {
        return ldapEmployee.get("sn");
    }

    @Override
    public String getEmail() {
        return ldapEmployee.get("mail");
    }
}
