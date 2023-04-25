package org.calma.pig.exercices.laboCollegeGrid.repositories.emplacement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboCollegeGrid.jackson.ColorDeserializer;
import org.calma.pig.exercices.laboCollegeGrid.models.emplacement.Emplacement;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonFileEmplacementRepository implements IEmplacementRepository {
    private List<Emplacement> data;
    private ObjectMapper mapper;

    public JsonFileEmplacementRepository() {
        SimpleModule module = new SimpleModule();
        //module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(module);
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.data = new ArrayList<>();
    }

    public List<Emplacement> findAll(){
        return this.load();
    }

    public Emplacement findByName(String name){
        List<Emplacement> emplacements = this.load();

        for (Iterator<Emplacement> iterator = emplacements.iterator(); iterator.hasNext(); ) {
            Emplacement emplacement =  iterator.next();
            if(emplacement.getName().compareTo(name) == 0){
                return emplacement;
            }
        }

        return null;
    }

    public List<Emplacement> load(){
        List<Emplacement> ret = new ArrayList<>();
        //ret.add(new Emplacement(EmplacementType.AUTRES, "ALL", "ALL", new ArrayList<>(), new ArrayList<>(), new Cell(0,0, CellState.WALKABLE, CellType.STANDARD)));

        File f = null;
        try {
            f = new File(Paths.get(getClass().getResource("/").toURI()).toFile() + "/emplacements/");
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }

        FilenameFilter filter = (f1, name) -> {
            // We want to find only .json files
            return name.endsWith(".json");
        };
        assert f != null;
        File[] files = f.listFiles(filter);

        // For each pathname in the pathnames array
        if(files == null){
            return new ArrayList<>();
        }

        for (File file : files) {
            // Print the names of files and directories
            try {
                Emplacement emplacement = mapper.readValue(file, Emplacement.class);
                ret.add(emplacement);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public void createORupdate(Emplacement emplacement){
        URL resource = getClass().getResource("/emplacements/" + emplacement.getName() + ".json");
        //resource not found so we create a new file
        if(resource == null){
            File file = null;
            try {
                file = new File(Paths.get(getClass().getResource("/").toURI()).toFile() + "/emplacements/" + emplacement.getName() + ".json");
                file.createNewFile();
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }

        //open the newly created resource OR the existing resource
        resource = getClass().getResource("/emplacements/" + emplacement.getName() + ".json");

        File f = null;
        try {
            f = Paths.get(resource.toURI()).toFile();
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(f, emplacement);
        }
        catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Saved! " + emplacement.getName());
    }
}