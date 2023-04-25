package org.calma.pig.exercices.laboSpacial.repositories.obstacle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.jackson.ColorDeserializer;
import org.calma.pig.exercices.laboSpacial.models.obstacle.Obstacle;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonFileObstacleRepository implements IObstacleRepository {
    private List<Obstacle> data;
    private ObjectMapper mapper;

    public JsonFileObstacleRepository() {
        SimpleModule module = new SimpleModule();
        //module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(module);
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.data = new ArrayList<>();
    }

    public List<Obstacle> findAll(){
        return this.load();
    }

    public Obstacle findByName(String name){
        List<Obstacle> obstacles = this.load();

        for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext(); ) {
            Obstacle obstacle =  iterator.next();
            if(obstacle.getName().compareTo(name) == 0){
                return obstacle;
            }
        }

        return null;
    }

    public List<Obstacle> load(){
        List<Obstacle> ret = new ArrayList<>();
        //ret.add(new Obstacle(ObstacleType.AUTRES, "ALL", "ALL", new ArrayList<>(), new ArrayList<>(), new Cell(0,0, CellState.WALKABLE, CellType.STANDARD)));

        File f = null;
        try {
            f = new File(Paths.get(getClass().getResource("/").toURI()).toFile() + "/obstacles/");
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
                Obstacle obstacle = mapper.readValue(file, Obstacle.class);
                ret.add(obstacle);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public void createORupdate(Obstacle obstacle){
        URL resource = getClass().getResource("/obstacles/" + obstacle.getName() + ".json");
        //resource not found so we create a new file
        if(resource == null){
            File file = null;
            try {
                file = new File(Paths.get(getClass().getResource("/").toURI()).toFile() + "/obstacles/" + obstacle.getName() + ".json");
                file.createNewFile();
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }

        //open the newly created resource OR the existing resource
        resource = getClass().getResource("/obstacles/" + obstacle.getName() + ".json");

        File f = null;
        try {
            f = Paths.get(resource.toURI()).toFile();
            this.mapper.writerWithDefaultPrettyPrinter().writeValue(f, obstacle);
        }
        catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Saved! " + obstacle.getName());
    }
}