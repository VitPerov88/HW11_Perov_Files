package tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PersonalDataModel {
    @JsonProperty("personalData")
    private PersonalData personalData;

    public PersonalData getPersonalData() {
        return personalData;
    }
    public static class PersonalData {
        private String firstName;
        private String lastName;
        private String address;
        private List<String> phoneNumbers;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getAddress() {
            return address;
        }

        public List<String> getPhoneNumbers() {
            return phoneNumbers;
        }

    }
}