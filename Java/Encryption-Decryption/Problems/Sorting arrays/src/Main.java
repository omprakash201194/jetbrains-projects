class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        // write your code here
        this.firstName = firstName == null ? "" : firstName;
    }

    public void setLastName(String lastName) {
        // write your code here
        this.lastName = lastName == null ? "" : lastName;
    }

    public String getFullName() {
        if (firstName.isEmpty() && lastName.isEmpty()) {
            return "Unknown";
        }else if (firstName.isEmpty()) {
            return lastName;
        } else if (lastName.isEmpty()) {
            return lastName;
        }else {
            return firstName + " " + lastName;
        }
    }
}