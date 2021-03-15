package ubb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FragenRepository {

    List<Fragen> fragenRepo;

    public FragenRepository(List<Fragen> fragenRepo) {
        this.fragenRepo = fragenRepo;
    }

    public List<Fragen> getFragenRepo() {
        return fragenRepo;
    }

    public void setFragenRepo(List<Fragen> fragenRepo) {
        this.fragenRepo = fragenRepo;
    }

    /**
     * add a question
     * @param entity must be not null
     * @return null- if the given entity is saved otherwise returns the entity (id already exists)
     */
    public Fragen add(Fragen entity) {
        for (Fragen f : fragenRepo)
        {
            if (f.getID() == entity.getID())
                return entity;
        }
        fragenRepo.add(entity);
        return null;
    }

    /**
     * find a question by id
     * @param id valid id
     * @return element with the given id
     */
    public Fragen get(int id)
    {
        for (Fragen current : fragenRepo)
        {
            if (current.getID() == id)
                return current;
        }
        return null;
    }
}
