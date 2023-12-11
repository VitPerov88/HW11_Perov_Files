package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.model.PersonalDataModel;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
public class JsonPersonalData {
    private ClassLoader cl = JsonPersonalData.class.getClassLoader();

    @Test
    void parseJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = cl.getResourceAsStream("PersonalData.json")) {
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is)) {
                PersonalDataModel personalDataModel = objectMapper.readValue(isr, PersonalDataModel.class);
                PersonalDataModel.PersonalData personalData = personalDataModel.getPersonalData();

                // Проверяем значения полей
                Assertions.assertEquals("Иван", personalData.getFirstName());
                Assertions.assertEquals("Иванов", personalData.getLastName());
                Assertions.assertEquals(List.of(
                        "812 123-1234",
                        "916 123-4567"
                ), personalData.getPhoneNumbers());
            }
        }
    }
}

