package org.calma.pig.exercices.laboSpacial.repositories.emplacement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.jackson.ColorDeserializer;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;
import org.calma.pig.exercices.laboSpacial.models.cell.CellState;
import org.calma.pig.exercices.laboSpacial.models.cell.CellType;
import org.calma.pig.exercices.laboSpacial.models.emplacement.Emplacement;
import org.calma.pig.exercices.laboSpacial.models.emplacement.EmplacementType;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryEmplacementRepository implements IEmplacementRepository {
    private List<Emplacement> data;
    private ObjectMapper mapper;

    public InMemoryEmplacementRepository() {
        SimpleModule module = new SimpleModule();
        //module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());

        this.mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.data = new ArrayList<>();
    }

    public List<Emplacement> findAll(){

        List<Cell> geo = new ArrayList<>();
        geo.add(new Cell(0,0));
        geo.add(new Cell(0,1));
        geo.add(new Cell(0,2));
        geo.add(new Cell(0,3));

        List<Cell> entry = new ArrayList<>();
        entry.add(new Cell(0,2));

        Emplacement empl = new Emplacement(
                EmplacementType.TOILETTES,
                "Toilettes1",
                "Toilettes du chef",
                geo,
                entry,
                new Cell(5,5));
        empl.setColor(Color.GREEN);

        data.add(empl);

        //loadFromMemory();

        return this.data;
    }

    @Override
    public void createORupdate(Emplacement emplacement){
        //si l'emplacement existe, on le remplace
        Emplacement e = this.findByName(emplacement.getName());
        if(e!= null){
            this.data.remove(e);
        }

        this.data.add(emplacement);
    }

    public void loadFromMemory(){
        List<Emplacement> emplacements = new ArrayList<>();

        this.data = emplacements;
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

    public void updateByName(String name, Emplacement newEmplacement){
        Emplacement emplacement = this.findByName(name);

        newEmplacement.setColor(emplacement.getColor());

        this.saveEmplacement(emplacement);
    }

    public void saveFromMemory() {
        for (Iterator<Emplacement> iterator = data.iterator(); iterator.hasNext(); ) {
            Emplacement emplacement = iterator.next();
            this.saveEmplacement(emplacement);
        }
        System.out.printf("Saved from memory!");
    }

    public void save(){

    }

    public void saveToJSON(){
        SimpleModule module = new SimpleModule();
        //module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        for (Iterator<Emplacement> iterator = data.iterator(); iterator.hasNext(); ) {
            Emplacement emplacement =  iterator.next();

            URL resource = getClass().getResource("/emplacements/" + emplacement.getName() + ".json");
            //resource not found so we create a new file
            if(resource == null){
                File file = null;
                try {
                    file = new File(Paths.get(getClass().getResource("/").toURI()).toFile() + "/emplacements/" + emplacement.getName() + ".json");
                    System.out.println(file.getPath());
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
                mapper.writerWithDefaultPrettyPrinter().writeValue(f, emplacement);
            }
            catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }

            System.out.println("Saved! " + emplacement.getName());
        }
    }

    public void saveEmplacement(Emplacement emplacement){
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

    public List<Emplacement> load(){
        List<Emplacement> ret = new ArrayList<>();
        ret.add(new Emplacement(EmplacementType.AUTRES, "ALL", "ALL", new ArrayList<>(), new ArrayList<>(), new Cell(0,0, CellState.WALKABLE, CellType.STANDARD)));

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
        assert files != null;
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
}