package org.calma.pig.exercices.laboSpacial.repositories.obstacle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.paint.Color;
import org.calma.pig.exercices.laboSpacial.jackson.ColorDeserializer;
import org.calma.pig.exercices.laboSpacial.models.cell.Cell;
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

public class InMemoryObstacleRepository implements IObstacleRepository {
    private List<Obstacle> data;
    private ObjectMapper mapper;

    public InMemoryObstacleRepository() {
        SimpleModule module = new SimpleModule();
        //module.addSerializer(Color.class, new ColorSerializer());
        module.addDeserializer(Color.class, new ColorDeserializer());

        this.mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        this.data = new ArrayList<>();
    }

    public List<Obstacle> findAll(){

        loadFromMemory();

        return this.data;
    }

    @Override
    public void createORupdate(Obstacle obstacle){
        //si l'obstacle existe, on le remplace
        Obstacle e = this.findByName(obstacle.getName());
        if(e!= null){
            this.data.remove(e);
        }

        this.data.add(obstacle);
    }

    public void loadFromMemory(){
        List<Obstacle> obstacles = new ArrayList<>();

        List<Cell> cellsOb1 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                cellsOb1.add(new Cell(i,j));
            }
        }
        List<Cell> cellsOb2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                cellsOb2.add(new Cell(i,j));
            }
        }

        Obstacle ob1 = new Obstacle(
                "Obstacle1",
                "Moyen obstacle rectangulaire vert",
                cellsOb1,
                new Cell(18,5));
        ob1.setColor(Color.GREEN);

        Obstacle ob2 = new Obstacle(
                "Obstacle2",
                "Petit obstacle rectangulaire bleu",
                cellsOb2,
                new Cell(90,36));
        ob2.setColor(Color.BLUE);

        obstacles.add(ob1);
        obstacles.add(ob2);

        this.data = obstacles;
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

    public void updateByName(String name, Obstacle newObstacle){
        Obstacle obstacle = this.findByName(name);

        newObstacle.setColor(obstacle.getColor());

        this.saveObstacle(obstacle);
    }

    public void saveFromMemory() {
        for (Iterator<Obstacle> iterator = data.iterator(); iterator.hasNext(); ) {
            Obstacle obstacle = iterator.next();
            this.saveObstacle(obstacle);
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

        for (Iterator<Obstacle> iterator = data.iterator(); iterator.hasNext(); ) {
            Obstacle obstacle =  iterator.next();

            URL resource = getClass().getResource("/obstacles/" + obstacle.getName() + ".json");
            //resource not found so we create a new file
            if(resource == null){
                File file = null;
                try {
                    file = new File(Paths.get(getClass().getResource("/").toURI()).toFile() + "/obstacles/" + obstacle.getName() + ".json");
                    System.out.println(file.getPath());
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
                mapper.writerWithDefaultPrettyPrinter().writeValue(f, obstacle);
            }
            catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }

            System.out.println("Saved! " + obstacle.getName());
        }
    }

    public void saveObstacle(Obstacle obstacle){
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

    public List<Obstacle> load(){
        List<Obstacle> ret = new ArrayList<>();
        ret.add(new Obstacle("ALL", "ALL", new ArrayList<>(), new Cell(0,0)));

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
        assert files != null;
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
}