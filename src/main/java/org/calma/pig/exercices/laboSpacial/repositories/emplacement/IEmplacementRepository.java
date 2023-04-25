package org.calma.pig.exercices.laboSpacial.repositories.emplacement;


import org.calma.pig.exercices.laboSpacial.models.emplacement.Emplacement;

import java.io.IOException;
import java.util.List;

public interface IEmplacementRepository {
    List<Emplacement> findAll() throws IOException;

    public Emplacement findByName(String name);

    public void createORupdate(Emplacement emplacement);
}