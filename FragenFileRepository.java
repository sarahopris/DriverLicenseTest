package ubb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FragenFileRepository {
    FragenRepository fragen;

    /**
     * Constructor, also reads from file
     * @param fragen - FragenRepository
     */
    public FragenFileRepository(FragenRepository fragen) {
        try {
            this.fragen = fragen;
            List<String> questions = (List<String>) Files.readAllLines(Path.of("C:\\Users\\sarah\\Documents\\MAP\\DriverLicenseTest\\src\\ubb\\Fragen"));
            for (String q : questions)
            {
                List<String> question = Arrays.asList(q.split("\\|"));
                if (question.size() == 6)
                {
                    int id = Integer.parseInt(question.get(0));
                    fragen.add(new Fragen(id, question.get(1).strip(), question.get(2).strip(),
                            question.get(3).strip(), question.get(4).strip(),
                            question.get(5).strip()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FragenRepository getFragen() {
        return fragen;
    }

    public void setFragen(FragenRepository fragen) {
        this.fragen = fragen;
    }

    /**
     * find a question by id
     * @param id valid id
     * @return element with the given id
     */
    public Fragen get(int id)
    {
        return fragen.get(id);
    }
}

